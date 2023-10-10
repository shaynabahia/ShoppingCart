package model;

import java.util.ArrayList;

public class WishList {
    public ArrayList<Item> wishList;

    public WishList() {
        this.wishList = new ArrayList<>();
    }

    public void addItemToWishList(Item item) {
        if (!item.isInStock()) {
            wishList.add(item);
        }
    }

    public int getNumItemsInWIshList() {
        return wishList.size();
    }

}

