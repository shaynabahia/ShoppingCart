package model;

import java.util.ArrayList;

public class ShoppingCart {
    private int total;
    private final ArrayList<Item> cart;

    //Represents a Shopping cart with list of items in cart,
    // and total price of all items (price > 0)
    public ShoppingCart() {
        this.cart = new ArrayList<>();
        this.total = 0;
    }

    //EFFECTS: adds an item to the cart if status is "in stock"
    public void addItemToCart(Item item) {
        if (item.isInStock()) {
            cart.add(item);
        }
    }

    //EFFECTS: removes an item from the cart if status is "out of stock"
    public void removeItemFromCart(Item item) {
        cart.remove(item);
    }


    //REQUIRES: total > 0
    //EFFECTS: applies given discount (in %) onto all items in the cart
    public int applyTotalDiscount(int discount) {
        if (this.total == 0) {
            return 0;
        }
        if (discount > 0 && discount < 100) {
            double calculateDiscount = 1.0 - ((double) discount / 100);
            //ArrayList<Item> itemsToRemove = new ArrayList<>();
            for (Item item : cart) {
                if (item.isInStock()) {
                    int discountedPrice = (int) (item.getPrice() * calculateDiscount);
                    item.setPrice(discountedPrice);
                    this.total = discountedPrice;
                }
            }
        }
        return total;
    }

    //EFFECTS: returns size of cart
    public int getNumItems() {
        return cart.size();
    }

    //REQUIRES: total >= 0; (0 if no items are in the cart)
    //EFFECTS: returns the total of all items in the cart
    public int getTotal() {
        total = 0;
        for (Item item : cart) {
            total = total + item.getPrice();
        }
        return this.total;
    }


    //MODIFIES: this
    //EFFECTS: returns the name of all items in the cart
    public ArrayList<String> getNameOfAllItems() {
        ArrayList<String> names = new ArrayList<>();
        for (Item item : cart) {
            names.add(item.getNameOfItem());
        }
        return names;
    }

}
