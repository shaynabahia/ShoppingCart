package persistence;

import model.Item;
import model.ShoppingCart;
import model.WishList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWLReaderTest extends JsonWLTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader readerWl = new JsonReader("./data/noSuchFile.json");
        try {
            WishList wl = readerWl.readWl();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWishList() {
        JsonReader readerWl = new JsonReader("./data/testReaderEmptyWishList.json");
        try {
            WishList wl = readerWl.readWl();
            //assertEquals(0, wl.getTotal());
            assertEquals(0, wl.getNumItemsInWishlist());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWishList() {
        JsonReader readerWl = new JsonReader("./data/testReaderGeneralWishList.json");
        try {
            WishList wl = readerWl.readWl();
            ArrayList<Item> items = wl.getItems();

            assertEquals(2, items.size());


            Item firstItem = items.get(0);
            assertEquals("shirt", firstItem.getNameOfItem());
            assertEquals("pink", firstItem.getColour());
            assertEquals("small", firstItem.getSizeOfItem());
            assertEquals(90, firstItem.getPrice());
            assertFalse(firstItem.isInStock());

            Item secondItem = items.get(1);
            assertEquals("pants", secondItem.getNameOfItem());
            assertEquals("green", secondItem.getColour());
            assertEquals("medium", secondItem.getSizeOfItem());
            assertEquals(190, secondItem.getPrice());
            assertFalse(secondItem.isInStock());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
