package Stuctures.Lists;

import Exceptions.EmptyCollectionException;
import Exceptions.NoSuchElementException;

public interface UnorderedListADT<T> extends ListADT<T> {
    /**
     * Adicionar um elemento no inicio da lista
     * @param element
     */
    public void addToFront(T element);

    /**
     * Adicionar um elemento no fim da lista
     * @param element
     */
    public void addToRear(T element);

    /**
     * Adicionar um elemento depois de um elemento especifico
     * @param element
     * @param target
     */
    public void addAfter(T element, T target) throws EmptyCollectionException, NoSuchElementException;
}
