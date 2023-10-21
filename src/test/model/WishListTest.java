package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class WishListTest {
    private Item item1;
    private Item item2;
    private Item item3;
    private WishList testWishList; 

    @BeforeEach
    void runBefore() {
        item1 = new Item("super puff", "black","s",200, true);
        item2 = new Item("cozy fleece hoodie", "green", "m", 80, false);
        item3 = new Item("effortless pants", "blue", "s", 130, false);
        testWishList = new WishList(); 
    }

    @Test
    void testConstructor() {
        WishList wishList = new WishList();
        assertNotNull(wishList);
    }

    @Test
    void testAddOneItemToWishList() {
        testWishList = new WishList(); 
        assertFalse(item2.isInStock()); 
        testWishList.addItemToWishList(item2);
        assertEquals(1, testWishList.getNumItemsInWishlist());
    }

    @Test
    void testAddItemToWishListInStock() {
        testWishList = new WishList();
        testWishList.addItemToWishList(item1);
        assertEquals(0, testWishList.getNumItemsInWishlist());
    }

    @Test
    void testAddMultipleItemsToWishList() {
        testWishList = new WishList();
        assertFalse(item2.isInStock());
        assertFalse(item3.isInStock());
        testWishList.addItemToWishList(item2);
        testWishList.addItemToWishList(item3);
        assertEquals(2, testWishList.getNumItemsInWishlist());
    }

    @Test
    void testGetAllItemsInWishList() {
        testWishList = new WishList();
        ArrayList<String> itemsInWishList = testWishList.getAllItemsInWishList();
        assertFalse(item2.isInStock());
        assertFalse(item3.isInStock());
        testWishList.addItemToWishList(item2);
        testWishList.addItemToWishList(item3);
        assertEquals(2, testWishList.getNumItemsInWishlist());
        assertTrue(itemsInWishList.contains(item2));
        assertTrue(itemsInWishList.contains(item3));
    }
}
