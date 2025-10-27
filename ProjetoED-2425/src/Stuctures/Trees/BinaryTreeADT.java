package Stuctures.Trees;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;

import java.util.Iterator;

public interface BinaryTreeADT<T>{
    /**
     * Returns a reference to the root element
     * @return
     * @throws EmptyCollectionException
     */
    public T getRoot() throws EmptyCollectionException;

    /**
     * Returns true if the binary tree is empty
     * @return
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in the binary tree
     * @return
     */
    public int size();

    /**
     * Returns true if the binary tree contains an element that matches the specified target element
     * @param targetElement
     * @return
     * @throws EmptyCollectionException
     */
    public boolean contains(T targetElement) throws EmptyCollectionException;

    /**
     * Returns a reference to the specified target element if it is found in the binary tree
     * @param targetElement
     * @return
     * @throws EmptyCollectionException
     * @throws ElementNotFoundException
     */
    public T find(T targetElement) throws EmptyCollectionException, ElementNotFoundException;

    /**
     * Returns the string representation of the binary tree
     * @return
     */
    public String toString();

    public Iterator<T> iteratorInOrder();
    public Iterator<T> iteratorPreOrder();
    public Iterator<T> iteratorPostOrder();
    public Iterator<T> iteratorLevelOrder();
}
