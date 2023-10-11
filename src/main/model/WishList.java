package model;

import java.util.ArrayList;

public class WishList {
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

    public ArrayList<Item> getAllItemsInWishList() {
        return wishList;
    }

}

