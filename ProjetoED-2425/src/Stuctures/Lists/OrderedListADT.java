package Stuctures.Lists;

import Exceptions.NoSuchElementException;
import Exceptions.NonComparableElementException;

public interface OrderedListADT<T> extends ListADT<T> {
    /**
     * Adiciona um elemento à lista de forma ordenada
     * @param element
     */
    public void add(T element) throws NonComparableElementException, NoSuchElementException;
}
