package model;

public class Item {
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

//    public void setPrice(int price) {
//        this.price = price;
   // }

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

    public boolean isInStock() {
        return this.inStock;

    }

    public int getPrice() {
        return this.price;
    }

    public String getSizeOfItem() {
        return this.sizeOfItem;
    }

    public String getNameOfItem() {
        return this.name;
    }

    public String getColour() {
        return this.colour;
    }
}
