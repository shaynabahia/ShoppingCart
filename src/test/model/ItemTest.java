package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    private Item item1;
    private Item item2;
    private Item item3;

    @BeforeEach
    void runBefore() {
        item1 = new Item("babaton contour top", "green", "xs", 50, true);
        item2 = new Item("cozy fleece hoodie", "pink", "m", 80, false);
        item3 = new Item("effortless pants", "blue", "s", 130, true);
    }

    @Test
    void testIsInStock() {
        assertTrue(item1.isInStock());
        assertFalse(item2.isInStock());
        assertTrue(item3.isInStock());
    }

    @Test
    void testApplyDiscountNotInRange() {
        assertEquals(50, item1.getPrice());
        item1.applyDiscountOnItem(-10);
        assertEquals(50, item1.getPrice());
        assertEquals(130, item3.getPrice());
        item3.applyDiscountOnItem(101);
        assertEquals(130, item3.getPrice());
    }

    @Test
    void testApplyDiscountOnOneItem() {
        assertEquals(50, item1.getPrice());
        item1.applyDiscountOnItem(10);
        assertEquals(45, item1.getPrice());
    }

    @Test
    void testApplySameDiscountOnMultipleItems() {
        assertEquals(50, item1.getPrice());
        assertEquals(130, item3.getPrice());
        item1.applyDiscountOnItem(25);
        item3.applyDiscountOnItem(25);
        assertEquals(37, item1.getPrice());
        assertEquals(97, item3.getPrice());
    }

    @Test
    void testApplyDifferentDiscountOnMultItems() {
        assertEquals(50, item1.getPrice());
        assertEquals(130, item3.getPrice());
        item1.applyDiscountOnItem(25);
        item3.applyDiscountOnItem(40);
        assertEquals(37, item1.getPrice());
        assertEquals(78, item3.getPrice());
    }

    @Test
    void testGetSizeOfItem() {
        assertEquals("xs", item1.getSizeOfItem());
        assertEquals("m", item2.getSizeOfItem());
    }

    @Test
    void testGetColour() {
        assertEquals("green", item1.getColour());
        assertEquals("blue", item3.getColour());
    }

    @Test
    void testSetPrice() {
        Item item5 = new Item("shirt", "black", "s", 0, true);
        item5.setPrice(0);
        assertEquals(0, item5.getPrice());
        Item item6 = new Item("shirt", "black", "s", 20, true);
        item6.setPrice(20);
        assertEquals(20, item6.getPrice());
        Item item7 = new Item("shirt", "black", "s", -10, true);
        item7.setPrice(0);
        assertEquals(0, item7.getPrice());
    }

    @Test
    void testGetAvailableSizes() {
        String[] expectedSizes = {"xs", "small", "medium", "large", "xl"};

        String[] actualSizes = Item.getAvailableSizes();

        assertEquals(expectedSizes.length, actualSizes.length);
        for (int i = 0; i < expectedSizes.length; i++) {
            assertEquals(expectedSizes[i], actualSizes[i]);
        }
    }


}
