package model;


import org.json.JSONObject;
import persistence.Writable;

public class Item implements Writable {
    private final String name;
    private int price;
    private final String sizeOfItem;
    private final boolean inStock;
    private final String colour;

    //REQUIRES: price > 0
    //EFFECTS: constructs a Plant with a name, and price of plant (in CAD $$)
    public Item(String name, String colour, String sizeOfItem, int price, boolean inStock) {
        this.name = name;
        this.colour = colour;
        this.price = price;
        this.sizeOfItem = sizeOfItem;
        this.inStock = inStock;

    }

    public static String[] getAvailableSizes() {
        String[] availableSizes = new String[5];  // Replace 3 with the number of available sizes

        // Populate the availableSizes array with Item objects
//        availableSizes[0] = new Item("name", "Color", "xs", 20, true);
//        availableSizes[1] = new Item("name", "Color", "small", 25, true);
//        availableSizes[2] = new Item("name", "Color", "medium", 30, true);
//        availableSizes[3] = new Item("name", "colour", "large", 35, true);
//        availableSizes[4] = new Item("name", "colour", "extra large", 40, true);
        availableSizes[0] = "xs";
        availableSizes[1] = "small";
        availableSizes[2] = "medium";
        availableSizes[3] = "large";
        availableSizes[4] = "xl";

        return availableSizes;
    }

    //MODIFIES: this, item
    //EFFECTS: given the item and the discount percentage (if 25% discount, int = 25),
    //         applies the given amount onto item and returns the discounted price of item
    public void applyDiscountOnItem(int discount) {
        //int discount = 0;
        if (discount > 0 && discount < 100) {
            double calculateDiscount = 1.0 - ((double) discount / 100);
            this.price = (int) (price * calculateDiscount);
        }
    }

    //EFFECTS: checks if the item is in stock
    public boolean isInStock() {
        return this.inStock;

    }

    //EFFECTS: sets the price of the item if price is >= 0
    public void setPrice(int price) {
        if (price >= 0) {
            this.price = price;
        } else {
            this.price = 0;
        }
    }

    //EFFECTS: gets price of the item
    public int getPrice() {
        return this.price;
    }

    //EFFECTS: gets the size of the item
    public String getSizeOfItem() {
        return this.sizeOfItem;
    }

    //EFFECTS: gets name of the item
    public String getNameOfItem() {
        return this.name;
    }

    //EFFECTS: gets the colour of the item
    public String getColour() {
        return this.colour;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        json.put("size", sizeOfItem);
        json.put("colour", colour);
        json.put("stock", inStock);
        return json;
    }


}
