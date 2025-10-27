package Stuctures.Stacks;

import Exceptions.EmptyCollectionException;

public interface SmackStackADT <T> extends StackADT<T> {
    /**
     * Removes and returns the top element from this stack.
     * @return
     * @throws EmptyCollectionException
     */
    public T smack() throws EmptyCollectionException;
}
