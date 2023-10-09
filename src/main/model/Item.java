package model;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private  final String name;
    private final int price;
    private String sizeOfItem;
    private boolean inStock;

    //REQUIRES: price > 0
    //EFFECTS: constructs a Plant with a name, and price of plant (in CAD $$)
    public Item(String name, String sizeOfItem, int price, boolean inStock) {
        this.name = name;
        this.price = price;
        this.sizeOfItem = sizeOfItem;
        this.inStock = inStock;

    }

    //MODIFIES: this, item
    //EFFECTS: given the item and the discount percentage (if 25% discount, int = 25),
    //         applies the given amount onto item and returns the discounted price of item
    public void applyDiscountOnItem(Item item, int discount) {
         //stub
    }

    public boolean isInStock() {
        return this.inStock;

    }

    public int getPrice() {
        return this.price;
    }
}
