package ui;

import model.Item;
import model.ShoppingCart;
import model.WishList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.io.IOException;

import java.io.FileNotFoundException;

import static ui.ShoppingCartApp.JSON_STORE;
import static ui.ShoppingCartApp.JSON_STORE_WL;

public class ShoppingCartGUI extends JFrame {
    private ShoppingCart aritzia;
    private WishList myList;
   // private JComboBox<String> printCombo;
    private JTabbedPane tabbedPane;
    private JTextArea cartTextArea;
    private JTextArea wishlistTextArea;
    private JPanel buttonPanel;
    private JPanel buttonPanelWl;

    // constructs a shopping cart gui that has a pane for the shopping cart, and a pane for the wishlist with
    // multiple buttons for each
    public ShoppingCartGUI() {

        aritzia = new ShoppingCart();
        myList = new WishList();

        setTitle("aritzia shopping cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        createUI();
        loadShoppingCart();
        loadWishList();

        setVisible(true);

    }


    //EFFECTS: creates the cart panel and calls createButtons
    private JPanel createCartPanel() {
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartTextArea = new JTextArea();
        cartPanel.add(new JScrollPane(cartTextArea), BorderLayout.CENTER);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        createCartButtons();

        cartPanel.add(buttonPanel, BorderLayout.SOUTH);

        return cartPanel;
    }

    //EFFECTS: creates the buttons for the cart panel
    private void createCartButtons() {
        JButton addToCartButton = new JButton("add item to cart");
        addToCartButton.setForeground(new Color(246, 127, 193));
        addToCartButton.addActionListener(e -> {
            addItems();
            updateCartTextArea();
        });
        buttonPanel.add(addToCartButton);

        JButton displayCartButton = new JButton("display cart");
        displayCartButton.setForeground(new Color(246, 127, 193));
        displayCartButton.addActionListener(e -> displayCartItems());
        buttonPanel.add(displayCartButton);

        JButton loadCartButton = new JButton("load cart");
        loadCartButton.setForeground(new Color(246, 127, 193));
        loadCartButton.addActionListener(e -> loadShoppingCart());
        buttonPanel.add(loadCartButton);

        JButton saveCartButton = new JButton("save cart");
        saveCartButton.setForeground(new Color(246, 127, 193));
        saveCartButton.addActionListener(e -> saveCart());
        buttonPanel.add(saveCartButton);

        JButton clearCartButton = new JButton("clear cart");
        clearCartButton.setForeground(new Color(246, 127, 193));
        clearCartButton.addActionListener(e -> clearCart());
        buttonPanel.add(clearCartButton);
    }

    // EFFECTS: creates wishlist panel and calls createButtonsWl
    private JPanel createWishlistPanel() {
        JPanel wishlistPanel = new JPanel(new BorderLayout());
        wishlistTextArea = new JTextArea();
        wishlistPanel.add(new JScrollPane(wishlistTextArea), BorderLayout.CENTER);

        buttonPanelWl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        createButtonsWl();

        wishlistPanel.add(buttonPanelWl, BorderLayout.SOUTH);

        return wishlistPanel;
    }

    public void createButtonsWl() {
        JButton addToListButton = new JButton("add item to wishlist");
        addToListButton.setForeground(new Color(246, 127, 193));
        addToListButton.addActionListener(e -> {
            addItemsToWishList();
            updateWishlistTextArea();
        });
        buttonPanelWl.add(addToListButton);

        JButton displayListButton = new JButton("display wishlist");
        displayListButton.setForeground(new Color(246, 127, 193));
        displayListButton.addActionListener(e -> displayListItems());
        buttonPanelWl.add(displayListButton);

        JButton loadListButton = new JButton("load wishlist");
        loadListButton.setForeground(new Color(246, 127, 193));
        loadListButton.addActionListener(e -> loadWishList());
        buttonPanelWl.add(loadListButton);

        JButton saveListButton = new JButton("save wishlist");
        saveListButton.setForeground(new Color(246, 127, 193));
        saveListButton.addActionListener(e -> saveList());
        buttonPanelWl.add(saveListButton);

        JButton clearListButton = new JButton("clear wishlist");
        clearListButton.setForeground(new Color(246, 127, 193));
        clearListButton.addActionListener(e -> clearList());
        buttonPanelWl.add(clearListButton);
    }

    //EFFECTS: creates the visual panes
    public void createUI() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("shopping cart", createCartPanel());
        tabbedPane.addTab("wishlist", createWishlistPanel());

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    //EFFECTS: adds items to cart
    private void addItems() {
        String name = JOptionPane.showInputDialog("enter the name of the item:");
        String size = getItemSize();
        String colour = JOptionPane.showInputDialog("enter the colour:");
        int price = Integer.parseInt(JOptionPane.showInputDialog("enter the price:"));

        Item addedItem = new Item(name, colour, size, price, true);

        if (applyDiscountOption()) {
            int discount = Integer.parseInt(JOptionPane.showInputDialog("enter the discount:"));
            addedItem.applyDiscountOnItem(discount);
        }

        aritzia.addItemToCart(addedItem);
        JOptionPane.showMessageDialog(null, "item added successfully to cart!");
    }

    // EFFECTS: gets the items size as a drop-down list
    private String getItemSize() {
        return (String) JOptionPane.showInputDialog(
                null,
                "select a size:",
                "item Size",
                JOptionPane.PLAIN_MESSAGE,
                null,
                Item.getAvailableSizes(),
                Item.getAvailableSizes()[0]
        );
    }

    private boolean applyDiscountOption() {
        int option = JOptionPane.showConfirmDialog(
                null,
                "do you want to apply a discount?",
                "discount",
                JOptionPane.YES_NO_OPTION
        );
        return option == JOptionPane.YES_OPTION;
    }

    private void addItemsToWishList() {
        String name = JOptionPane.showInputDialog("enter the name of the item:");
        String size = (String) JOptionPane.showInputDialog(
                null,
                "select a size:",
                "item Size",
                JOptionPane.PLAIN_MESSAGE,
                null,
                Item.getAvailableSizes(),
                Item.getAvailableSizes()[0]
        );
        String colour = JOptionPane.showInputDialog("enter the colour:");
        String priceString = JOptionPane.showInputDialog("enter the price:");
        int price = Integer.parseInt(priceString);

        Item addedItem = new Item(name, colour, size, price, true);
        myList.addItemToWishList(addedItem);
        JOptionPane.showMessageDialog(null, "item added successfully to wishlist!");
    }

    private void updateCartTextArea() {
        cartTextArea.setText(getCartDisplayText());
    }

    private void updateWishlistTextArea() {
        wishlistTextArea.setText(getWishlistDisplayText());
    }

    private String getCartDisplayText() {
        StringBuilder displayText = new StringBuilder();

        if (aritzia.getNumItems() == 0) {
            displayText.append("There are currently no items in your cart!");
        } else {
            displayText.append("Total items: ").append(aritzia.getNumItems()).append("\n");
            displayText.append("Total: $").append(aritzia.getTotal()).append("\n");
            displayText.append("All items: ").append(aritzia.getNameOfAllItems()).append("\n");
        }

        return displayText.toString();
    }

    private String getWishlistDisplayText() {
        StringBuilder displayText = new StringBuilder();

        if (myList.getNumItemsInWishlist() == 0) {
            displayText.append("there are currently no items in your wishlist!");
        } else {
            displayText.append("total items: ").append(myList.getNumItemsInWishlist()).append("\n");
            displayText.append("all items: ").append(myList.getAllItemsInWishList()).append("\n");
        }

        return displayText.toString();
    }

    private void loadShoppingCart() {
        try {
            JsonReader cartJsonReader = new JsonReader(JSON_STORE);  // Assuming JsonReader takes a file path
            ShoppingCart loadedCart = cartJsonReader.read();
            aritzia = loadedCart;
            updateCartTextArea();  // Update the cart display after loading
            JOptionPane.showMessageDialog(null, "shopping cart loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
            e.printStackTrace();
        }
    }

    private void saveCart() {
        try {
            JsonWriter cartJsonWriter = new JsonWriter(JSON_STORE);
            cartJsonWriter.open();
            cartJsonWriter.write(aritzia);
            cartJsonWriter.close();
            JOptionPane.showMessageDialog(null, "Shopping cart saved successfully!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE);
            e.printStackTrace();
        }
    }

    private void clearCart() {
        int choice = JOptionPane.showConfirmDialog(null,
                "are you sure you want to clear the cart?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            aritzia.clearCart();
            updateCartTextArea(); // Update the display after clearing the cart
            JOptionPane.showMessageDialog(null, "shopping cart cleared!");
        }
    }

    private void clearList() {
        int choice = JOptionPane.showConfirmDialog(null,
                "are you sure you want to clear wishlist?", "confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            myList.clearList();
            updateCartTextArea(); // Update the display after clearing the cart
            JOptionPane.showMessageDialog(null, "wishlist cleared!");
        }
    }

    private void saveList() {
        try {
            JsonWriter cartJsonWriter = new JsonWriter(JSON_STORE_WL);
            cartJsonWriter.open();
            cartJsonWriter.writeWl(myList);
            cartJsonWriter.close();
            JOptionPane.showMessageDialog(null, "wishlist saved successfully!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE_WL);
            e.printStackTrace();
        }
    }

    private void loadWishList() {
        try {
            JsonReader cartJsonReader = new JsonReader(JSON_STORE_WL);
            WishList loadedList = cartJsonReader.readWl();
            myList = loadedList;
            updateWishlistTextArea();
            JOptionPane.showMessageDialog(null, "wish list loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE_WL);
            e.printStackTrace();
        }
    }

    private void displayCartItems() {
        StringBuilder cartItems = new StringBuilder();

        if (aritzia.getNumItems() == 0) {
            cartItems.append("There are currently no items in your cart!");
        } else {
            cartItems.append("Total items: ").append(aritzia.getNumItems()).append("\n");
            cartItems.append("Total: $").append(aritzia.getTotal()).append("\n");
            cartItems.append("All items:\n");
            for (Item item : aritzia.getItems()) {
                cartItems.append(item.getNameOfItem()).append(" - ").append(item.getPrice()).append("\n");
            }
        }

        JTextArea cartDisplay = new JTextArea(cartItems.toString());
        JScrollPane scrollPane = new JScrollPane(cartDisplay);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane,
                "Shopping Cart Items", JOptionPane.PLAIN_MESSAGE);
    }

    private void displayListItems() {
        StringBuilder listItems = new StringBuilder();

        if (myList.getNumItemsInWishlist() == 0) {
            listItems.append("there are currently no items in your cart!");
        } else {
            listItems.append("total items: ").append(myList.getNumItemsInWishlist()).append("\n");
            listItems.append("all items:\n");
            for (Item item : myList.getItems()) {
                listItems.append(item.getNameOfItem()).append(" - ").append(item.getPrice()).append("\n");
            }
        }

        JTextArea listDisplay = new JTextArea(listItems.toString());
        JScrollPane scrollPane1 = new JScrollPane(listDisplay);
        scrollPane1.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane1,
                "wishlist items", JOptionPane.PLAIN_MESSAGE);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShoppingCartGUI());
    }
}

