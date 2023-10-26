package persistence;

import model.Item;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkItem(Item item, String name, String colour, String sizeOfItem, int price, boolean inStock) {
        assertEquals(name, item.getNameOfItem());
        assertEquals(colour, item.getColour());
        assertEquals(sizeOfItem, item.getSizeOfItem());
        assertEquals(price, item.getPrice());
        assertEquals(inStock, item.isInStock());
    }

}
