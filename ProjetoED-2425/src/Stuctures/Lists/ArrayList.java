package Stuctures.Lists;

import Exceptions.EmptyCollectionException;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class ArrayList <T> implements ListADT<T>, Iterable<T>{

    /**
     * Default capacity of the list
     */
    private final static int DEFAULT_CAPACITY = 100;
    /**
     * List of elements
     */
    protected T[] list;
    /**
     * Number of elements in the list
     */
    protected  int size, modcount;

    /**
     * Creates an empty list with the default capacity
     */
    public ArrayList(){
        this.list = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.modcount = 0;
    }

    /**
     * Creates an empty list with the specified capacity
     * @param initialCapacity
     */
    public ArrayList(int initialCapacity){
        this.list = (T[]) new Object[initialCapacity];
        this.size = 0;
        this.modcount = 0;
    }

    /**
     * Creates a new list with the elements of the specified list
     */
    protected void expandCapacity(){
        T[] newList = (T[]) new Object[this.list.length*2];
        for(int i = 0; i < this.list.length; i++){
            newList[i] = this.list[i];
        }
        this.list = newList;
    }

    /**
     * Removes and returns the first element from the list
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException("List is empty");
        }
        T element = this.list[0];
        for(int i = 0; i < size-1; i++){
            this.list[i] = this.list[i+1];
        }
        this.list[this.size-1] = null;
        this.size--;
        this.modcount++;
        return element;
    }

    /**
     * Removes and returns the last element from the list
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException("List is empty");
        }
        T element = this.list[this.size-1];
        this.list[this.size-1] = null;
        this.size--;
        this.modcount++;
        return element;
    }

    /**
     * Removes and returns the specified element from the list
     * @param element
     * @return
     * @throws EmptyCollectionException
     * @throws NoSuchElementException
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        if(isEmpty()){
            throw new EmptyCollectionException("List is empty");
        }
        int i =0;
        while(i<size() && !this.list[i].equals(element)){
            i++;
        }
        if(i == size()){
            throw new NoSuchElementException();
        }
        T removed = this.list[i];
        for(int j = i; j<size()-1; j++){
            this.list[j]=this.list[j+1];
        }
        this.list[this.size-1] = null;
        this.size--;
        this.modcount++;
        return removed;
    }

    /**
     * Returns the first element from the list
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T first() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException("List is empty");
        }
        return this.list[0];
    }

    /**
     * Returns the last element from the list
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T last() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException("List is empty");
        }
        return this.list[this.size-1];
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
        int i  = 0;
        while(i<size() && !this.list[i].equals(target)){
            i++;
        }

        return i < size();
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
     * Returns the number of elements in the list
     * @return
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns an iterator for the list
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator(){};
    }

    /**
     * Returns a string representation of the list
     * @return
     */
    @Override
    public String toString(){
        String result = getClass().getSimpleName() + " {";
        if(!isEmpty()){
            for(int i = 0 ; i < size(); i++){
                result += this.list[i] + " ";
            }
        }
        return result + "}";
    }

    /**
     * Inner class that implements the iterator for the list
     */
    private class BasicIterator implements Iterator<T>{
        /**
         * Index of the current element
         */
        private int current;
        /**
         * Expected number of modifications
         */
        private int expectedModCount;
        /**
         * Flag to indicate if it is possible to remove the current element
         */
        private boolean okToRemove;

        /**
         * Creates a new iterator
         */
        public BasicIterator(){
            this.current=0;
            this.expectedModCount=modcount;
            this.okToRemove=false;
        }

        /**
         * Returns true if there are more elements in the list
         * @return
         */
        @Override
        public boolean hasNext(){
            return current < size();
        }

        /**
         * Returns the next element in the list
         * @return
         */
        @Override
        public T next(){
            if(modcount != expectedModCount) throw new ConcurrentModificationException();
            if(!hasNext()) throw new NoSuchElementException();
            okToRemove = true;
            return list[current++];
        }

        /**
         * Removes the current element from the list
         */
        @Override
        public void remove(){
            if(modcount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if(!okToRemove){
                throw new NoSuchElementException();
            }
            try {
                ArrayList.this.remove(list[current-1]);
                current--;
                expectedModCount++;
                okToRemove=false;
            } catch (EmptyCollectionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
