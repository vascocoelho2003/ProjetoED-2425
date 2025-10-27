package Stuctures.Stacks;

import Exceptions.EmptyCollectionException;

public class SmackStack <T> extends ArrayStack<T> implements SmackStackADT<T> {
    /**
     * Creates an empty stack using the default capacity.
     */
    public SmackStack() {
        super();
    }

    /**
     * Creates an empty stack using the specified capacity.
     * @param initial
     */
    public SmackStack(int initial) {
        super(initial);
    }

    /**
     * Removes and returns the top element from this stack.
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T smack() throws EmptyCollectionException {
        if(isEmpty()) throw new EmptyCollectionException();

        T result = stack[0];
        for (int i = 0; i < top - 1; i++)
            stack[i] = stack[i + 1];
        top--;
        return result;
    }
}
