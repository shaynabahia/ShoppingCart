package model;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import persistence.Writable;

// constructs a shopping cart with a total amount, and a list of items starting at empty
public class ShoppingCart implements Writable {
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

    //EFFECTS: gets all items from the cart and returns it
    public ArrayList<Item> getItems() {
        return cart;
    }

    //EFFECTS: clears all items from the cart
    public void clearCart() {
        cart.clear();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("total", total);
        json.put("items", itemsToJson());
        return json;
    }

    // EFFECTS: returns things in this cart as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : cart) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

}
