package model;

import java.util.ArrayList;

public class ShoppingCart {
    private int total;
    public ArrayList<Item> cart;

    //Represents a Shopping cart with list of items in cart,
    // and total price of all items (price > 0)
    public ShoppingCart() {
        this.cart = new ArrayList<>();
        this.total = 0;
    }

    //REQUIRES: cannot be null
    //EFFECTS: adds an item to the cart if status is "in stock"
    public void addItemToCart(Item item) {
        if (item.isInStock()) {
            cart.add(item);
        } else {
            cart.remove(item);
        }
    }

    //EFFECTS: applies given discount (in %) onto all items in the cart
    public int applyTotalDiscount(int discount) {
        if (discount > 0 && discount < 100) {
            double calculateDiscount = 1.0 - ((double) discount / 100);
            int totalPrice = getTotal();
            int newTotalPrice = (int) (totalPrice * calculateDiscount);

            for (Item item : cart) {
               return (item.getPrice() * newTotalPrice) / total;
            }
        }
        return discount;
    }

    public int getNumItems() {
        return cart.size();
    }

    //REQUIRES: total >= 0; (0 if no items are in the cart)
    //EFFECTS: returns the total of all items in the cart
    public int getTotal() {
        int total = 0;
        for (Item item : cart) {
            total += item.getPrice();
        }
        return total;
    }

}
