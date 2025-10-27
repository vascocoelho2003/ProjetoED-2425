package Stuctures.Stacks;

import Exceptions.EmptyCollectionException;
import Stuctures.Lists.LinearNode;

public class LinkedStack<T> implements StackADT<T> {

    /**
     * The top node of the stack
     */
    private LinearNode<T> top;
    /**
     * The number of elements in the stack
     */
    private int count;

    /**
     * Creates an empty stack
     */
    public LinkedStack(){
        this.top = null;
        this.count = 0;
    }

    /**
     * Adds the specified element to the top of the stack
     * @param element
     */
    @Override
    public void push(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        newNode.setNext(this.top);
       this.top = newNode;
        this.count++;
    }

    /**
     * Removes the element at the top of the stack and returns a reference to it
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if(isEmpty())
            throw new EmptyCollectionException("Stack");

        T result = this.top.getElement();
        this.top = this.top.getNext();
        this.count--;

        return result;
    }

    /**
     * Returns a reference to the element at the top of the stack without removing it
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if(isEmpty())
            throw new EmptyCollectionException("Stack");

        return this.top.getElement();
    }

    /**
     * Returns true if the stack is empty and false otherwise
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * Returns the number of elements in the stack
     * @return
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Returns a string representation of the stack
     * @return
     */
    public String toString(){
        String result = "";
        LinearNode<T> current = this.top;
        while(current != null){
            result += current.getElement().toString() + "\n";
            current = current.getNext();
        }
        return result;
    }
}
