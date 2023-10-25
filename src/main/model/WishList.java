package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class WishList implements Writable {
    private final ArrayList<Item> wishList;

    public WishList() {
        this.wishList = new ArrayList<>();
    }

    public void addItemToWishList(Item item) {
        if (!item.isInStock()) {
            wishList.add(item);
        }
    }

    public int getNumItemsInWishlist() {
        return wishList.size();
    }

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

