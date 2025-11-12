package com.example.iterable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for the Bag class.
 * Tests all functionality including edge cases, normal operations, and iterator behavior.
 */
class BagTest {

    private Bag<String> stringBag;
    private Bag<Integer> integerBag;

    /**
     * Set up fresh Bag instances before each test.
     * This ensures tests don't interfere with each other.
     */
    @BeforeEach
    void setUp() {
        stringBag = new Bag<>();
        integerBag = new Bag<>();
    }

    // ==================== Tests for Empty Bag Operations ====================

    @Test
    @DisplayName("New bag should be empty")
    void testNewBagIsEmpty() {
        assertTrue(stringBag.isEmpty(), "Newly created bag should be empty");
        assertEquals(0, stringBag.size(), "Newly created bag should have size 0");
    }

    @Test
    @DisplayName("Empty bag should not contain any item")
    void testEmptyBagContains() {
        assertFalse(stringBag.contains("test"), "Empty bag should not contain any item");
    }

    @Test
    @DisplayName("Removing from empty bag should return false")
    void testRemoveFromEmptyBag() {
        assertFalse(stringBag.remove("test"), "Removing from empty bag should return false");
    }

    @Test
    @DisplayName("Empty bag iterator should not have next")
    void testEmptyBagIterator() {
        Iterator<String> iterator = stringBag.iterator();
        assertFalse(iterator.hasNext(), "Empty bag iterator should not have next element");
    }

    @Test
    @DisplayName("Calling next on empty bag iterator should throw NoSuchElementException")
    void testEmptyBagIteratorNext() {
        Iterator<String> iterator = stringBag.iterator();
        assertThrows(NoSuchElementException.class, iterator::next,
                "Calling next on empty iterator should throw NoSuchElementException");
    }

    // ==================== Tests for Add Operation ====================

    @Test
    @DisplayName("Adding single item should increase size to 1")
    void testAddSingleItem() {
        stringBag.add("apple");
        assertEquals(1, stringBag.size(), "Size should be 1 after adding one item");
        assertFalse(stringBag.isEmpty(), "Bag should not be empty after adding item");
    }

    @Test
    @DisplayName("Adding multiple items should increase size correctly")
    void testAddMultipleItems() {
        stringBag.add("apple");
        stringBag.add("banana");
        stringBag.add("cherry");
        assertEquals(3, stringBag.size(), "Size should be 3 after adding three items");
        assertFalse(stringBag.isEmpty(), "Bag should not be empty");
    }

    @Test
    @DisplayName("Adding duplicate items should be allowed")
    void testAddDuplicateItems() {
        stringBag.add("apple");
        stringBag.add("apple");
        stringBag.add("apple");
        assertEquals(3, stringBag.size(), "Size should be 3 when adding three duplicate items");
    }

    @Test
    @DisplayName("Adding null should be allowed")
    void testAddNull() {
        stringBag.add(null);
        assertEquals(1, stringBag.size(), "Size should be 1 after adding null");
        assertTrue(stringBag.contains(null), "Bag should contain null");
    }

    @Test
    @DisplayName("Bag should work with different types - Integer")
    void testAddIntegerType() {
        integerBag.add(1);
        integerBag.add(2);
        integerBag.add(3);
        assertEquals(3, integerBag.size(), "Integer bag should have size 3");
        assertTrue(integerBag.contains(2), "Integer bag should contain 2");
    }

    // ==================== Tests for Contains Operation ====================

    @Test
    @DisplayName("Contains should return true for added item")
    void testContainsAddedItem() {
        stringBag.add("apple");
        assertTrue(stringBag.contains("apple"), "Bag should contain the added item");
    }

    @Test
    @DisplayName("Contains should return false for non-existent item")
    void testContainsNonExistentItem() {
        stringBag.add("apple");
        assertFalse(stringBag.contains("banana"), "Bag should not contain item that wasn't added");
    }

    @Test
    @DisplayName("Contains should work with null")
    void testContainsNull() {
        assertFalse(stringBag.contains(null), "Empty bag should not contain null");
        stringBag.add(null);
        assertTrue(stringBag.contains(null), "Bag should contain null after adding it");
    }

    @Test
    @DisplayName("Contains should find duplicates")
    void testContainsDuplicates() {
        stringBag.add("apple");
        stringBag.add("apple");
        assertTrue(stringBag.contains("apple"), "Bag should contain duplicate items");
    }

    // ==================== Tests for Remove Operation ====================

    @Test
    @DisplayName("Remove should remove existing item and return true")
    void testRemoveExistingItem() {
        stringBag.add("apple");
        assertTrue(stringBag.remove("apple"), "Remove should return true for existing item");
        assertEquals(0, stringBag.size(), "Size should be 0 after removing only item");
        assertFalse(stringBag.contains("apple"), "Bag should not contain removed item");
    }

    @Test
    @DisplayName("Remove should return false for non-existent item")
    void testRemoveNonExistentItem() {
        stringBag.add("apple");
        assertFalse(stringBag.remove("banana"), "Remove should return false for non-existent item");
        assertEquals(1, stringBag.size(), "Size should remain unchanged when removing non-existent item");
    }

    @Test
    @DisplayName("Remove should only remove first occurrence of duplicate")
    void testRemoveDuplicate() {
        stringBag.add("apple");
        stringBag.add("apple");
        stringBag.add("apple");
        assertTrue(stringBag.remove("apple"), "Remove should return true");
        assertEquals(2, stringBag.size(), "Size should be 2 after removing one of three duplicates");
        assertTrue(stringBag.contains("apple"), "Bag should still contain remaining duplicates");
    }

    @Test
    @DisplayName("Remove should work with null")
    void testRemoveNull() {
        stringBag.add(null);
        stringBag.add("apple");
        assertTrue(stringBag.remove(null), "Should be able to remove null");
        assertEquals(1, stringBag.size(), "Size should be 1 after removing null");
        assertFalse(stringBag.contains(null), "Bag should not contain null after removal");
    }

    @Test
    @DisplayName("Remove multiple items from bag")
    void testRemoveMultipleItems() {
        stringBag.add("apple");
        stringBag.add("banana");
        stringBag.add("cherry");

        assertTrue(stringBag.remove("banana"), "Should remove banana");
        assertEquals(2, stringBag.size(), "Size should be 2");
        assertFalse(stringBag.contains("banana"), "Should not contain banana");
        assertTrue(stringBag.contains("apple"), "Should still contain apple");
        assertTrue(stringBag.contains("cherry"), "Should still contain cherry");
    }

    // ==================== Tests for Size and IsEmpty Operations ====================

    @Test
    @DisplayName("Size should update correctly with add and remove operations")
    void testSizeUpdates() {
        assertEquals(0, stringBag.size(), "Initial size should be 0");

        stringBag.add("apple");
        assertEquals(1, stringBag.size(), "Size should be 1 after first add");

        stringBag.add("banana");
        assertEquals(2, stringBag.size(), "Size should be 2 after second add");

        stringBag.remove("apple");
        assertEquals(1, stringBag.size(), "Size should be 1 after remove");

        stringBag.remove("banana");
        assertEquals(0, stringBag.size(), "Size should be 0 after removing all items");
    }

    @Test
    @DisplayName("isEmpty should reflect actual bag state")
    void testIsEmpty() {
        assertTrue(stringBag.isEmpty(), "New bag should be empty");

        stringBag.add("apple");
        assertFalse(stringBag.isEmpty(), "Bag with items should not be empty");

        stringBag.remove("apple");
        assertTrue(stringBag.isEmpty(), "Bag should be empty after removing all items");
    }

    // ==================== Tests for Iterator Functionality ====================

    @Test
    @DisplayName("Iterator should iterate over single item")
    void testIteratorSingleItem() {
        stringBag.add("apple");
        Iterator<String> iterator = stringBag.iterator();

        assertTrue(iterator.hasNext(), "Iterator should have next for single item");
        assertEquals("apple", iterator.next(), "Iterator should return the added item");
        assertFalse(iterator.hasNext(), "Iterator should not have next after consuming all items");
    }

    @Test
    @DisplayName("Iterator should iterate over multiple items")
    void testIteratorMultipleItems() {
        stringBag.add("apple");
        stringBag.add("banana");
        stringBag.add("cherry");

        Iterator<String> iterator = stringBag.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            String item = iterator.next();
            assertNotNull(item, "Iterated item should not be null");
            count++;
        }
        assertEquals(3, count, "Iterator should iterate over all 3 items");
    }

    @Test
    @DisplayName("Iterator should handle duplicates")
    void testIteratorWithDuplicates() {
        stringBag.add("apple");
        stringBag.add("apple");
        stringBag.add("banana");

        Iterator<String> iterator = stringBag.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        assertEquals(3, count, "Iterator should iterate over all items including duplicates");
    }

    @Test
    @DisplayName("Iterator should handle null elements")
    void testIteratorWithNull() {
        stringBag.add("apple");
        stringBag.add(null);
        stringBag.add("banana");

        Iterator<String> iterator = stringBag.iterator();
        boolean foundNull = false;
        int count = 0;
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (item == null) {
                foundNull = true;
            }
            count++;
        }
        assertTrue(foundNull, "Iterator should iterate over null element");
        assertEquals(3, count, "Iterator should iterate over all 3 items");
    }

    @Test
    @DisplayName("Multiple iterators should work independently")
    void testMultipleIterators() {
        stringBag.add("apple");
        stringBag.add("banana");

        Iterator<String> iterator1 = stringBag.iterator();
        Iterator<String> iterator2 = stringBag.iterator();

        assertTrue(iterator1.hasNext(), "First iterator should have next");
        assertTrue(iterator2.hasNext(), "Second iterator should have next");

        assertEquals("apple", iterator1.next(), "First iterator should get first item");
        assertEquals("apple", iterator2.next(), "Second iterator should also get first item");
    }

    @Test
    @DisplayName("Calling next without hasNext check should work if items exist")
    void testIteratorNextWithoutHasNext() {
        stringBag.add("apple");
        Iterator<String> iterator = stringBag.iterator();

        assertEquals("apple", iterator.next(), "Should be able to call next directly if item exists");
    }

    @Test
    @DisplayName("Calling next beyond end should throw NoSuchElementException")
    void testIteratorNextBeyondEnd() {
        stringBag.add("apple");
        Iterator<String> iterator = stringBag.iterator();

        iterator.next(); // Consume the only item
        assertThrows(NoSuchElementException.class, iterator::next,
                "Calling next beyond end should throw NoSuchElementException");
    }

    @Test
    @DisplayName("Enhanced for loop should work with bag")
    void testEnhancedForLoop() {
        stringBag.add("apple");
        stringBag.add("banana");
        stringBag.add("cherry");

        int count = 0;
        for (String item : stringBag) {
            assertNotNull(item, "Item in enhanced for loop should not be null");
            count++;
        }
        assertEquals(3, count, "Enhanced for loop should iterate over all items");
    }

    // ==================== Integration Tests ====================

    @Test
    @DisplayName("Complex scenario: multiple operations")
    void testComplexScenario() {
        // Add items
        stringBag.add("apple");
        stringBag.add("banana");
        stringBag.add("cherry");
        stringBag.add("apple"); // duplicate
        assertEquals(4, stringBag.size(), "Size should be 4");

        // Check contains
        assertTrue(stringBag.contains("banana"), "Should contain banana");
        assertFalse(stringBag.contains("date"), "Should not contain date");

        // Remove one apple
        assertTrue(stringBag.remove("apple"), "Should remove apple");
        assertEquals(3, stringBag.size(), "Size should be 3");
        assertTrue(stringBag.contains("apple"), "Should still contain another apple");

        // Iterate
        int count = 0;
        for (String item : stringBag) {
            count++;
        }
        assertEquals(3, count, "Should iterate over 3 items");

        // Remove all
        stringBag.remove("apple");
        stringBag.remove("banana");
        stringBag.remove("cherry");
        assertTrue(stringBag.isEmpty(), "Bag should be empty after removing all items");
    }

    @Test
    @DisplayName("Test with custom objects")
    void testWithCustomObjects() {
        Bag<Person> personBag = new Bag<>();
        Person person1 = new Person("Alice", 30);
        Person person2 = new Person("Bob", 25);

        personBag.add(person1);
        personBag.add(person2);

        assertEquals(2, personBag.size(), "Person bag should have size 2");
        assertTrue(personBag.contains(person1), "Should contain person1");
        assertTrue(personBag.remove(person1), "Should remove person1");
        assertEquals(1, personBag.size(), "Size should be 1 after removal");
    }

    /**
     * Simple Person class for testing with custom objects.
     */
    private static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && name.equals(person.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode() + age;
        }
    }
}

