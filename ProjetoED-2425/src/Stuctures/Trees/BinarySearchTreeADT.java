package Stuctures.Trees;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NoSuchElementException;
import Exceptions.NonComparableElementException;

public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T>{
    /**
     * Adds the specified element to this binary search tree if it is not already present.
     * @param element
     * @throws NonComparableElementException
     */
    public void addElement(T element) throws NonComparableElementException;

    /**
     * Removes and returns the specified element from this binary search tree.
     * @param targetElement
     * @return
     * @throws NonComparableElementException
     * @throws EmptyCollectionException
     * @throws ElementNotFoundException
     * @throws NoSuchElementException
     */
    public T removeElement(T targetElement) throws NonComparableElementException, EmptyCollectionException, ElementNotFoundException, NoSuchElementException;

    /**
     *
     * @param targetElement
     * @throws EmptyCollectionException
     * @throws NonComparableElementException
     * @throws ElementNotFoundException
     */
    public void removeAllOccurrences(T targetElement) throws EmptyCollectionException, NonComparableElementException, ElementNotFoundException;

    /**
     * Removes and returns the smallest element from this binary search tree.
     * @return
     * @throws EmptyCollectionException
     * @throws ElementNotFoundException
     * @throws NonComparableElementException
     * @throws NoSuchElementException
     */
    public T removeMin() throws EmptyCollectionException, ElementNotFoundException, NonComparableElementException, NoSuchElementException;

    /**
     * Removes and returns the largest element from this binary search tree.
     * @return
     * @throws EmptyCollectionException
     * @throws ElementNotFoundException
     * @throws NonComparableElementException
     * @throws NoSuchElementException
     */
    public T removeMax() throws EmptyCollectionException, ElementNotFoundException, NonComparableElementException, NoSuchElementException;

    /**
     * Returns a reference to the smallest element in this binary search tree.
     * @return
     * @throws EmptyCollectionException
     */
    public T findMin() throws EmptyCollectionException;

    /**
     * Returns a reference to the largest element in this binary search tree.
     * @return
     * @throws EmptyCollectionException
     */
    public T findMax() throws EmptyCollectionException;

}
