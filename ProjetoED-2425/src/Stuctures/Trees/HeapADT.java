package Stuctures.Trees;

import Exceptions.EmptyCollectionException;

public interface HeapADT<T> extends BinaryTreeADT<T> {
    /**
     * Adds the specified element to this heap.
     * @param element
     */
    void addElement(T element);

    /**
     * Removes the element with the lowest value from this heap.
     * @return
     * @throws EmptyCollectionException
     */
    T removeMin() throws EmptyCollectionException;

    /**
     * Returns a reference to the element with the lowest value in this heap.
     * @return
     * @throws EmptyCollectionException
     */
    T findMin() throws EmptyCollectionException;
}
