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
        item1 = new Item("babaton contour top","xs" , 50, true);
        item2 = new Item("cozy fleece hoodie", "m", 80, false);
        item3 = new Item("effortless pants", "s", 130, true);
    }

    @Test
    void testIsInStock() {
        assertTrue(item1.isInStock());
        assertFalse(item2.isInStock());
        assertTrue(item3.isInStock());
    }

    @Test
    void testApplyDiscountOnOneItem(){
        assertEquals(50, item1.getPrice());
        item1.applyDiscountOnItem(10);
        assertEquals(45, item1.getPrice());
    }

    @Test
    void testApplySameDiscountOnMultipleItems(){
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



}
