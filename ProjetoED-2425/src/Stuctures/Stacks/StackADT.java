package Stuctures.Stacks;

import Exceptions.EmptyCollectionException;

public interface StackADT<T>{
    public void push(T element);
    public T pop() throws EmptyCollectionException;
    public T peek() throws EmptyCollectionException, EmptyCollectionException;
    public boolean isEmpty();
    public int size();
    public String toString();

}
