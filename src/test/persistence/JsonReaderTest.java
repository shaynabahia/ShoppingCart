package persistence;

import model.Item;
import model.ShoppingCart;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ShoppingCart sc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyShoppingCart() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyShoppingCart.json");
        try {
            ShoppingCart sc = reader.read();
            assertEquals(0, sc.getTotal());
            assertEquals(0, sc.getNumItems());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralShoppingCart() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralShoppingCart.json");
        try {
            ShoppingCart sc = reader.read();
            ArrayList<Item> items = sc.getItems();

            assertEquals(2, items.size());


            Item firstItem = items.get(0);
            assertEquals("shirt", firstItem.getNameOfItem());
            assertEquals("pink", firstItem.getColour());
            assertEquals("", firstItem.getSizeOfItem());
            assertEquals(90, firstItem.getPrice());
            assertTrue(firstItem.isInStock());

            Item secondItem = items.get(1);
            assertEquals("pants", secondItem.getNameOfItem());
            assertEquals("green", secondItem.getColour());
            assertEquals("", secondItem.getSizeOfItem());
            assertEquals(190, secondItem.getPrice());
            assertTrue(secondItem.isInStock());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
