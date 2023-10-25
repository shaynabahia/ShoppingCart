
import model.Item;
import model.ShoppingCart;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            //assertEquals("My work room", sc.getName());
            assertEquals(0, sc.getNumItems());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            ShoppingCart sc = reader.read();
            ArrayList<String> theseItems = sc.getNameOfAllItems();
            assertEquals(2, theseItems.size());
            checkItem("name", "colour", "size", 20, true);
            checkItem("name", "colour", "size", 30, true);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
