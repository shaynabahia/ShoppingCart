package ui;

import model.Item;
import model.ShoppingCart;
import model.WishList;

import java.sql.SQLOutput;
import java.util.Scanner;

public class ShoppingCartApp {
    private ShoppingCart aritzia = new ShoppingCart();
    private final WishList myList = new WishList();
    private final Scanner input = new Scanner(System.in);



    //EFFECTS: constructs an online store with a shopping cart and wishlist
    public ShoppingCartApp() {
        menuBar();
        this.aritzia = new ShoppingCart();
       // item = new Item();
    }

    private void menuBar() {
        System.out.println("Welcome to Aritzia!");
        System.out.println("To edit your cart (enter edit cart)");
        System.out.println("To edit your wishlist (enter edit wishlist)");
        System.out.println("To see your cart (enter see cart)");
        System.out.println("To see your wishlist (enter see wishlist)");
        System.out.println("quit aritzia (enter quit)");
        String chosen = input.nextLine();

        while (true) {
            if (chosen.equals("edit cart")) {
                System.out.println("add items now!");
                addItems();
            } else if (chosen.equals("edit wishlist")) {
                System.out.println("add items to wish list now!");
                addItemsToWishList();
            } else if (chosen.equals("see cart")) {
                displayCart();
               // chosen = input.nextLine();
            } else if (chosen.equals("see wishlist")) {
                displayWishList();
              //  chosen = input.nextLine();
            } else if (chosen.equals("quit")) {
                System.out.println("thanks for shopping!");
                System.exit(0);
            }
        }
    }

    public void addItems() {
        System.out.println("what is the name of the item?");
        String name = input.nextLine();
        System.out.println("what size would you like?");
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
            String discountString = input.next();
            int discount = Integer.parseInt(discountString);
            addedItem.applyDiscountOnItem(discount);
        }
        aritzia.addItemToCart(addedItem);

        System.out.println("added successfully to cart!");
        System.out.println(addedItem.getSizeOfItem() + " " + addedItem.getPrice());
        input.nextLine();
        menuBar();
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
        System.out.println(addedItem.getSizeOfItem() + " " + addedItem.getPrice());
        menuBar();

    }

    public void displayCart() {
        if (aritzia.getNumItems() == 0) {
            System.out.println("there are currently no items in your cart!");
            menuBar();
        } else {
            System.out.println("total items: " + aritzia.getNumItems());
            System.out.println("total: " + "$" + aritzia.getTotal());
            System.out.println("all items: " + aritzia.getNameOfAllItems());
            menuBar();
        }
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
}





