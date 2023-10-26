package persistence;

import model.Item;
import model.ShoppingCart;
import model.WishList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWLWriterTest extends JsonWLTest{
    @Test
    void testWriterInvalidFile() {
        try {
            WishList wl = new WishList();
            JsonWriter writerWl = new JsonWriter("./data/my\0illegal:fileName.json");
            writerWl.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWishList() {
        try {
            WishList wl = new WishList();
            JsonWriter writerWl = new JsonWriter("./data/testWriterEmptyWishList.json");
            writerWl.open();
            writerWl.writeWl(wl);
            writerWl.close();

            JsonReader readerWl = new JsonReader("./data/testWriterEmptyWishList.json");
            wl = readerWl.readWl();
           // assertEquals(0, wl.getTotal());
            assertEquals(0, wl.getNumItemsInWishlist());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWishList() {
        try {
            WishList wl = new WishList();
            Item itemToTest = new Item("shirt", "pink", "", 90, false);
            Item itemToTest2 = new Item("pants", "green", "", 190, false);
            wl.addItemToWishList(itemToTest);
            wl.addItemToWishList(itemToTest2);
            JsonWriter writerWl = new JsonWriter("./data/testWriterGeneralWishList.json");
            writerWl.open();
            writerWl.writeWl(wl);
            writerWl.close();

            JsonReader readerWl = new JsonReader("./data/testWriterGeneralWishList.json");
            wl = readerWl.readWl();
            //assertEquals(280, wl.getTotal());
            ArrayList<String> items = wl.getAllItemsInWishList();
            assertEquals(2, items.size());
            checkItemWl(itemToTest, "shirt", "pink", "", 90, false);
            checkItemWl(itemToTest2, "pants", "green", "", 190, false);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

