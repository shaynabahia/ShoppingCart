package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class WishListTest {
    private Item item2;
    private Item item3;
    private WishList testWishList; 

    @BeforeEach
    void runBefore() {
        item2 = new Item("cozy fleece hoodie", "m", 80, false);
        item3 = new Item("effortless pants", "s", 130, false);
        testWishList = new WishList(); 
    }

    @Test
    void testAddOneItemToWishList() {
        testWishList = new WishList(); 
        assertFalse(item2.isInStock()); 
        testWishList.addItemToWishList(item2);
        assertEquals(1, testWishList.getNumItemsInWIshList());
    }

    @Test
    void testAddMultipleItemsToWishList() {
        testWishList = new WishList();
        assertFalse(item2.isInStock());
        assertFalse(item3.isInStock());
        testWishList.addItemToWishList(item2);
        testWishList.addItemToWishList(item3);
        assertEquals(2, testWishList.getNumItemsInWIshList());
    }
}
