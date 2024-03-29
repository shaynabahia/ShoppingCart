package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;
    ShoppingCart testShoppingCart = new ShoppingCart();

    @BeforeEach
    void runBefore() {
        item1 = new Item("babaton contour top","green", "xs" , 50, true);
        item2 = new Item("cozy fleece hoodie", "blue", "m", 80, false);
        item3 = new Item("effortless pants", "tan", "s", 130, true);
        item4 = new Item("shirt", "black", "m", 0, true);
        testShoppingCart = new ShoppingCart();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testShoppingCart.getTotal());
        assertEquals(0, testShoppingCart.getNumItems());
    }

    @Test
    void testAddOneItem() {
        testShoppingCart = new ShoppingCart();
        testShoppingCart.addItemToCart(item1);
        assertEquals(1, testShoppingCart.getNumItems());
        assertEquals(50, testShoppingCart.getTotal());
        assertTrue(item1.isInStock());
    }

    @Test
    void testAddMultipleItems() {
        testShoppingCart = new ShoppingCart();
        testShoppingCart.addItemToCart(item1);
        testShoppingCart.addItemToCart(item2);
        testShoppingCart.addItemToCart(item3);
        assertTrue(item1.isInStock());
        assertFalse(item2.isInStock());
        assertTrue(item3.isInStock());
        assertEquals(2, testShoppingCart.getNumItems());
        assertEquals(180, testShoppingCart.getTotal());
    }
    @Test
    void testApplyTotalDiscount() {
        testShoppingCart = new ShoppingCart();
        testShoppingCart.addItemToCart(item4);
        assertTrue(item4.isInStock());
        assertEquals(1, testShoppingCart.getNumItems());
        assertEquals(0, testShoppingCart.getTotal());
        testShoppingCart.applyTotalDiscount(10);
        assertEquals(0, testShoppingCart.getTotal());
        testShoppingCart = new ShoppingCart();
        testShoppingCart.addItemToCart(item1);
        assertTrue(item1.isInStock());
        testShoppingCart.applyTotalDiscount(0);
        assertTrue(testShoppingCart.getTotal() > 0);
    }
    @Test
    void testApplyDiscountOnItem100() {
        testShoppingCart = new ShoppingCart();
        testShoppingCart.addItemToCart(item1);
        assertTrue(item1.isInStock());
        assertEquals(50, testShoppingCart.getTotal());
        testShoppingCart.applyTotalDiscount(100);
        assertEquals(50, testShoppingCart.getTotal());
    }


    @Test
    void testApplyTotalDiscountOneItem() {
        testShoppingCart = new ShoppingCart();
        testShoppingCart.addItemToCart(item1);
        assertTrue(item1.isInStock());
        assertEquals(1, testShoppingCart.getNumItems());
        assertEquals(50, testShoppingCart.getTotal());
        testShoppingCart.applyTotalDiscount(10);
        assertEquals(45, testShoppingCart.getTotal());
    }

    @Test
    void testApplyTotalDiscountOneItemNotInStock() {
        testShoppingCart = new ShoppingCart();
        testShoppingCart.addItemToCart(item2);
        assertFalse(item2.isInStock());
      //  assertEquals(0, testShoppingCart.getTotal());
        testShoppingCart.applyTotalDiscount(10);
        assertEquals(0, testShoppingCart.getTotal());
    }

    @Test
    void testApplyDiscountOneItemNotInStock() {
        testShoppingCart = new ShoppingCart();
        testShoppingCart.addItemToCart(item1);
        testShoppingCart.addItemToCart(item2);
        assertTrue(item1.isInStock());
        assertFalse(item2.isInStock());
        testShoppingCart.removeItemFromCart("cozy fleece hoodie");
        assertEquals(1, testShoppingCart.getNumItems());
        testShoppingCart.getTotal();
        testShoppingCart.applyTotalDiscount(10);
        assertEquals(45, testShoppingCart.getTotal());
    }

    @Test
    void testApplyTotalDiscountMultipleItems() {
        testShoppingCart = new ShoppingCart();
        testShoppingCart.addItemToCart(item1);
        testShoppingCart.addItemToCart(item2);
        testShoppingCart.addItemToCart(item3);
        assertTrue(item1.isInStock());
        assertFalse(item2.isInStock());
        assertTrue(item3.isInStock());
        assertEquals(2, testShoppingCart.getNumItems());
        assertEquals(180, testShoppingCart.getTotal());
        testShoppingCart.applyTotalDiscount(25);
        assertEquals(134, testShoppingCart.getTotal());

    }

    @Test
    void testGetNameOfAllItems() {
        testShoppingCart = new ShoppingCart();
        testShoppingCart.addItemToCart(item1);
        testShoppingCart.addItemToCart(item2);
        testShoppingCart.addItemToCart(item3);
        assertTrue(item1.isInStock());
        assertFalse(item2.isInStock());
        assertTrue(item3.isInStock());
        ArrayList<String> names = testShoppingCart.getNameOfAllItems();

        assertEquals(2, names.size());
        assertTrue(names.contains(item1.getNameOfItem()));
        assertFalse(names.contains(item2.getNameOfItem()));
        assertTrue(names.contains(item3.getNameOfItem()));
        assertEquals("babaton contour top", names.get(0));
        assertEquals("effortless pants", names.get(1));
    }

    @Test
    void testClearCart() {
        testShoppingCart = new ShoppingCart();
        testShoppingCart.addItemToCart(item1);
        testShoppingCart.addItemToCart(item2);
        testShoppingCart.clearCart();
        assertEquals(0, testShoppingCart.getNumItems());
    }

    @Test
    void testRemoveItem() {
        testShoppingCart = new ShoppingCart();
        testShoppingCart.addItemToCart(item1);
        testShoppingCart.addItemToCart(item3);
        testShoppingCart.removeItemFromCart(item3.getNameOfItem());
        assertFalse(testShoppingCart.removeItemFromCart(String.valueOf(item3)));
        assertEquals("effortless pants", item3.getNameOfItem());
        assertEquals(1, testShoppingCart.getNumItems());

    }
}