package model;

public class Item {
    private  final String name;
    private int price;
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
    public int applyDiscountOnItem(int discount) {
        if (discount > 0 && discount < 100) {
            double calculateDiscount = 1.0 - ((double) discount /100);
            int newPrice = (int) (price * calculateDiscount);
            this.price = newPrice;
        }
        return discount;
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
}
