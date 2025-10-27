package Stuctures.Lists;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NoSuchElementException;
import java.util.Iterator;

public abstract class LinkedList<T> implements  ListADT<T>, Iterable<T>{

    /**
     * The head of the list
     */
    protected LinearNode<T> head;
    /**
     * The tail of the list
     */
    protected  LinearNode<T> tail;
    /**
     * The size of the list
     */
    protected int size;
    /**
     * The number of modifications made to the list
     */
    protected  int modCount;

    /**
     * Creates an empty list
     */
    public LinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.modCount = 0;
    }

    /**
     * Removes and returns the first element from the list
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        T removed = this.head.getElement();
        this.head = this.head.getNext();
        if(this.head == null){
            this.tail = null;
        }
        this.size--;
        this.modCount++;
        return removed;
    }

    /**
     * Removes and returns the last element from the list
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        T removed = this.tail.getElement();
        LinearNode<T> current = this.head;
        while(current.getNext() != this.tail){
            current = current.getNext();
        }
        current.setNext(null);
        this.tail = current;
        this.size--;
        this.modCount++;
        return removed;
    }

    /**
     * Removes and returns the specified element from the list
     * @param element
     * @return
     * @throws EmptyCollectionException
     * @throws NoSuchElementException
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException, ElementNotFoundException {
        if (isEmpty())
            throw new EmptyCollectionException ("List");

        boolean found = false;
        LinearNode<T> previous = null;
        LinearNode<T> current = head;

        while (current != null && !found)
            if (element.equals (current.getElement()))
                found = true;
            else{
                previous = current;
                current = current.getNext();
            }

        if (!found)
            throw new ElementNotFoundException ("List");

        if (size() == 1)
            head = tail = null;
        else if (current.equals (head))
            head = current.getNext();
        else if (current.equals (tail))
        {
            tail = previous;
            tail.setNext(null);
        }
        else
            previous.setNext(current.getNext());

        size--;

        return current.getElement();
    }

    /**
     * Returns the first element from the list
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T first() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        return this.head.getElement();
    }

    /**
     * Returns the last element from the list
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T last() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        return this.tail.getElement();
    }

    /**
     * Returns true if the list contains the specified element
     * @param target
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        LinearNode<T> current = this.head;
        while(current != null){
            if(current.getElement().equals(target)){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Returns true if the list is empty
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    /**
     * Returns the size of the list
     * @return
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns a string representation of the list
     * @return
     */
    @Override
    public String toString(){
        String result = getClass().getSimpleName() + " [";
        if(!isEmpty()){
            LinearNode<T> current = this.head;
            while(current != null){
                result += current.getElement() + ", ";
                current = current.getNext();
            }
        }
        return result + "]";
    }

    /**
     * Returns an iterator for the list
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<T>(){};
    }

    /**
     * Returns an iterator for the list
     * @param <T>
     */
    private abstract class BasicIterator<T> implements Iterator<T>{
        /**
         * The current node
         */
        private LinearNode<T> current;
        /**
         * The expected number of modifications
         */
        private int expectedModCount;
        /**
         * A flag to indicate if it is ok to remove the element
         */
        private boolean okToRemove;

        /**
         * Creates an iterator for the list
         */
        public BasicIterator(){
            this.current = (LinearNode<T>) head;
            this.expectedModCount = modCount;
            this.okToRemove = false;
        }

        /**
         * Returns true if the iterator has a next element
         * @return
         */
        @Override
        public boolean hasNext() {
            return current!=null;
        }

        /**
         * Returns the next element in the list
         * @return
         */
        @Override
        public T next() {
            if(modCount != expectedModCount){
                throw new java.util.ConcurrentModificationException();
            }
            if(!hasNext()){
                throw new java.util.NoSuchElementException();
            }
            T result = current.getElement();
            current = current.getNext();
            okToRemove = true;
            return result;
        }
    }
}
