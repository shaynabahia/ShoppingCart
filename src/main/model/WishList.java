package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class WishList implements Writable {
    private final ArrayList<Item> wishList;

    // constructs a wishlist as an empty arraylist of items
    public WishList() {
        this.wishList = new ArrayList<>();
    }

    //EFFECTS: adds an item to the wishlist if it's NOT in stock
    public void addItemToWishList(Item item) {
        if (!item.isInStock()) {
            wishList.add(item);
        }
    }

    //EFFECTS: returns the number of items in the wishlist
    public int getNumItemsInWishlist() {
        return wishList.size();
    }

    //EFFECTS: returns the items in the wishlist
    public ArrayList<Item> getItems() {
        return wishList;
    }

    //EFFECTS: gets the names of all items in the wishlist and returns them
    public ArrayList<String> getAllItemsInWishList() {
        ArrayList<String> wishlistNames = new ArrayList<>();
        for (Item item : wishList) {
            wishlistNames.add(item.getNameOfItem());
        }
        return wishlistNames;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        //json.put("name", name);
        json.put("wishlist", itemsWlToJson());
        return json;
    }

    // EFFECTS: returns items in this wishlist as a JSON array
    private JSONArray itemsWlToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item w : wishList) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }



}

