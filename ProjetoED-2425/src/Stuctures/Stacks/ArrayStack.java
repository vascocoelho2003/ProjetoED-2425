package Stuctures.Stacks;

import Exceptions.EmptyCollectionException;

public class ArrayStack<T> implements StackADT<T> {
    /**
     * The default capacity of the stack
     */
    protected final int DEFAULT_CAPACITY = 5;
    /**
     * The top of the stack
     */
    protected int top;
    /**
     * The array that represents the stack
     */
    protected T[] stack;

    /**
     * Creates an empty stack using the default capacity
     */
    public ArrayStack() {
        this.top = 0;
        this.stack = (T[])(new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Creates an empty stack using the specified capacity
     * @param initialCapacity
     */
    public ArrayStack(int initialCapacity) {
        this.top = 0;
        this.stack = (T[])(new Object[initialCapacity]);

    }

    /**
     * Adds the specified element to the top of the stack
     * @param element
     */
    @Override
    public void push(T element) {
        if(size() == this.stack.length) {
            expandCapacity();
        }
        this.stack[top] = element;
        this.top++;
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

        this.top--;
        T result = this.stack[this.top];
        this.stack[this.top] = null;

        return result;
    }

    /**
     * Returns a reference to the element at the top of the stack
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T peek() throws EmptyCollectionException{
        if(isEmpty())
            throw new EmptyCollectionException("Stack");

        return this.stack[this.top-1];
    }

    /**
     * Returns true if the stack is empty
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.top == 0;
    }

    /**
     * Returns the number of elements in the stack
     * @return
     */
    @Override
    public int size() {
        return this.top;
    }

    /**
     * Creates a new array with double the capacity of the current stack
     */
    private void expandCapacity() {
        T[] larger = (T[])(new Object[this.stack.length * 2]);
        for(int i = 0; i < this.stack.length; i++) {
            larger[i] = this.stack[i];
        }
        this.stack = larger;
    }

    /**
     * Returns a string representation of the stack
     * @return
     */
    @Override
    public String toString() {
        String string = "";
        for(int i = 0; i < this.top; i++) {
            string += this.stack[i] + " ";
        }
        return string;
    }
}
