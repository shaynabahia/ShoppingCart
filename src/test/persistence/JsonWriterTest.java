package persistence;

import model.Item;
import model.ShoppingCart;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ShoppingCart sc = new ShoppingCart();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyShoppingCart() {
        try {
            ShoppingCart sc = new ShoppingCart();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyShoppingCart.json");
            writer.open();
            writer.write(sc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyShoppingCart.json");
            sc = reader.read();
            assertEquals(0, sc.getTotal());
            assertEquals(0, sc.getNumItems());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralShoppingCart() {
        try {
            ShoppingCart sc = new ShoppingCart();
            Item itemToTest = new Item("shirt", "pink", "", 90, true);
            Item itemToTest2 = new Item("pants", "green", "", 190, true);
            sc.addItemToCart(itemToTest);
            sc.addItemToCart(itemToTest2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralShoppingCart.json");
            writer.open();
            writer.write(sc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralShoppingCart.json");
            sc = reader.read();
            assertEquals(280, sc.getTotal());
            ArrayList<String> items = sc.getNameOfAllItems();
            assertEquals(2, items.size());
            checkItem(itemToTest, "shirt", "pink", "", 90, true);
            checkItem(itemToTest2, "pants", "green", "", 190, true);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

