
import model.Item;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    Item item;

    protected void checkItem(String name, String colour, String sizeOfItem, int price, boolean inStock) {
        assertEquals(name, item.getNameOfItem());
        assertEquals(colour, item.getColour());
        assertEquals(sizeOfItem, item.getSizeOfItem());
        assertEquals(price, item.getPrice());
        assertEquals(inStock, item.isInStock());
    }

}
