package com.example.iterable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A generic Bag collection that implements the Container interface.
 * This class uses an ArrayList as the backing data structure to store elements.
 *
 * A Bag is an unordered collection that allows duplicate elements.
 *
 * @param <E> the type of elements in this bag
 */
public class Bag<E> implements Container<E> {

    // The ArrayList that stores all the elements in the bag
    private ArrayList<E> elements;

    /**
     * Constructs an empty Bag.
     * Initializes the backing ArrayList.
     */
    public Bag() {
        this.elements = new ArrayList<>();
    }

    /**
     * Adds an item to the bag.
     * The item is added to the end of the internal ArrayList.
     *
     * @param item the element to add to the bag
     */
    @Override
    public void add(E item) {
        elements.add(item);
    }

    /**
     * Removes the first occurrence of the specified item from the bag.
     * Uses ArrayList's remove method which removes the first matching element.
     *
     * @param item the element to be removed from the bag
     * @return true if the item was found and removed, false otherwise
     */
    @Override
    public boolean remove(E item) {
        return elements.remove(item);
    }

    /**
     * Checks if the bag contains the specified item.
     *
     * @param item the element whose presence in the bag is to be tested
     * @return true if the bag contains the specified element, false otherwise
     */
    @Override
    public boolean contains(E item) {
        return elements.contains(item);
    }

    /**
     * Returns the number of elements in the bag.
     *
     * @return the number of elements in this bag
     */
    @Override
    public int size() {
        return elements.size();
    }

    /**
     * Checks if the bag is empty.
     *
     * @return true if the bag contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Returns an iterator over the elements in this bag.
     * The iterator allows traversal of all elements in the bag.
     *
     * @return an Iterator over the elements in this bag
     */
    @Override
    public Iterator<E> iterator() {
        return new BagIterator();
    }

    /**
     * Inner class that implements the Iterator interface for the Bag.
     * This iterator allows us to traverse through all elements in the bag.
     */
    private class BagIterator implements Iterator<E> {

        // Keeps track of the current position in the iteration
        private int currentIndex;

        /**
         * Constructs a BagIterator starting at the beginning of the bag.
         */
        public BagIterator() {
            this.currentIndex = 0;
        }

        /**
         * Checks if there are more elements to iterate over.
         *
         * @return true if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return currentIndex < elements.size();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws java.util.NoSuchElementException if there are no more elements
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No more elements in the bag");
            }
            E item = elements.get(currentIndex);
            currentIndex++;
            return item;
        }
    }
}

