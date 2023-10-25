package ui;

import model.Item;
import model.ShoppingCart;
import model.WishList;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ShoppingCartApp {

    private ShoppingCart aritzia;
    private WishList myList = new WishList();
    private Scanner input;
    private static final String JSON_STORE = "./data/shoppingCart.json";
    private static final String JSON_STORE_WL = "./data/wishList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JsonWriter jsonWriterWl;
    private JsonReader jsonReaderWl;


    //EFFECTS: constructs an online store with a shopping cart and wishlist
    public ShoppingCartApp() throws FileNotFoundException {
        this.aritzia = new ShoppingCart();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriterWl = new JsonWriter(JSON_STORE_WL);
        jsonReaderWl = new JsonReader(JSON_STORE_WL);
        runShoppingCart();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runShoppingCart() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        while (keepGoing) {
            System.out.println("welcome to aritzia!");
            menuBar();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;

            } else {
                processCommand(command);
            }
        }

        System.out.println("\n thanks for shopping!");

    }


    private void menuBar() {

        System.out.println("to edit your cart (enter edit cart)");
        System.out.println("to edit your wishlist (enter edit wishlist)");
        System.out.println("to see your cart (enter see cart)");
        System.out.println("to see your wishlist (enter see wishlist)");
        System.out.println("to save your cart (enter save cart)");
        System.out.println("to load your cart (enter load cart)");
        System.out.println("to save your wishlist (enter save list)");
        System.out.println("to load your wishlist (enter load list)");
        System.out.println("quit aritzia (enter quit)");

    }

    private void processCommand(String command) {

        if (command.equals("edit cart")) {
            System.out.println("add items now!");
            addItems();
        } else if (command.equals("edit wishlist")) {
            System.out.println("add items to wish list now!");
            addItemsToWishList();
        } else if (command.equals("see cart")) {
            displayCart();
        } else if (command.equals("see wishlist")) {
            displayWishList();
        } else if (command.equals("save cart")) {
            saveShoppingCart();
        } else if (command.equals("load cart")) {
            loadShoppingCart();
        } else if (command.equals("save list")) {
            saveWishList();
        } else if (command.equals("load list")) {
            loadWishList();
        } else {
            System.out.println("invalid selection");
        }
    }

    public void addItems() {
        System.out.println("what is the name of the item?");
        String name = input.nextLine();
        System.out.println("what size would you like?");
        readItemSize();
        String size = input.nextLine();
        System.out.println("what colour would you like?");
        String colour = input.nextLine();
        System.out.println("what is the price?");
        String priceString = input.nextLine();
        System.out.println("would you like to apply a discount?");
        String userInput = input.nextLine();

        int price = Integer.parseInt(priceString);

        Item addedItem = new Item(name, colour, size, price, true);

        if (userInput.equals("yes")) {
            System.out.println("how much is the discount?");
            String discountString = input.nextLine();
            int discount = Integer.parseInt(discountString);
            addedItem.applyDiscountOnItem(discount);
        }

        aritzia.addItemToCart(addedItem);
        System.out.println("added successfully to cart!");
        System.out.println(addedItem.getNameOfItem() + " " + addedItem.getPrice());
        menuBar();
        input.nextLine();
    }


    public void addItemsToWishList() {
        System.out.println("what is the name of the item?");
        String name = input.nextLine();
        System.out.println("what size would you like?");
        String size = input.nextLine();
        System.out.println("what colour would you like?");
        String colour = input.nextLine();
        System.out.println("what is the price?");
        String priceString = input.nextLine();
        int price = Integer.parseInt(priceString);

        Item addedItem = new Item(name, colour, size, price, false);

        myList.addItemToWishList(addedItem);
        System.out.println("added successfully to wishlist!");
        System.out.println(addedItem.getNameOfItem() + " " + addedItem.getPrice());
        //menuBar();

    }

    public void displayCart() {
        if (aritzia.getNumItems() == 0) {
            System.out.println("there are currently no items in your cart!");
        } else {
            System.out.println("total items: " + aritzia.getNumItems());
            System.out.println("total: " + "$" + aritzia.getTotal());
            System.out.println("all items: " + aritzia.getNameOfAllItems());
        }
        menuBar();
        input.nextLine();
    }

    public void displayWishList() {
        if (myList.getNumItemsInWishlist() == 0) {
            System.out.println("there are currently no items in your wishlist !");
        } else {
            System.out.println(myList.getAllItemsInWishList());
            menuBar();
            input.nextLine();
        }
        menuBar();
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadShoppingCart() {
        try {
            aritzia = jsonReader.read();
            System.out.println("Loaded " + aritzia.getNameOfAllItems() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWishList() {
        try {
            myList = jsonReaderWl.readWl();
            System.out.println("Loaded " + myList.getNumItemsInWishlist() + " from " + JSON_STORE_WL);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_WL);
        }
    }

    // EFFECTS: prompts user to select a size for the item and returns it
    private String readItemSize() {
        System.out.println("Please select a size for your item");

        int menuLabel = 1;
        for (String size : Item.getAvailableSizes()) {
            System.out.println(menuLabel + ": " + size);
            menuLabel++;
        }

        int menuSelection = input.nextInt();

        if (menuSelection >= 1 && menuSelection <= Item.getAvailableSizes().length) {
            return Item.getAvailableSizes()[menuSelection - 1];
        } else {
            System.out.println("invalid selection. please choose a valid size.");
            return readItemSize();
        }
    }


    // EFFECTS: saves the shopping cart to file
    private void saveShoppingCart() {
        try {
            jsonWriter.open();
            jsonWriter.write(aritzia);
            jsonWriter.close();
            System.out.println("Saved " + aritzia.getNameOfAllItems() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the wishList to file
    private void saveWishList() {
        try {
            jsonWriterWl.open();
            jsonWriterWl.writeWl(myList);
            jsonWriterWl.close();
            System.out.println("Saved " + myList.getNumItemsInWishlist() + " to " + JSON_STORE_WL);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_WL);
        }
    }


}





