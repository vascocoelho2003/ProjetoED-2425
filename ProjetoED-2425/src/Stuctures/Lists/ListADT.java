package Stuctures.Lists;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NoSuchElementException;

import java.util.Iterator;

public interface ListADT<T> extends Iterable<T> {
    /**
     * Remove e retorna o primeiro elemento da lista
     * @return
     * @throws EmptyCollectionException
     */
    public T removeFirst() throws EmptyCollectionException;

    /**
     * Remove e retorna o último elemento da lista
     * @return
     * @throws EmptyCollectionException
     */
    public T removeLast() throws EmptyCollectionException;

    /**
     * Remove e retorna o elemento passado como argumento
     * @param element
     * @return
     * @throws EmptyCollectionException
     */
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException, ElementNotFoundException;

    /**
     * Retorna o primeiro elemento da lista
     * @return
     * @throws EmptyCollectionException
     */
    public T first() throws EmptyCollectionException;

    /**
     * Retorna o último elemento da lista
     * @return
     * @throws EmptyCollectionException
     */
    public T last() throws EmptyCollectionException;

    /**
     * Retorna true se a lista contém o elemento passado como argumento
     * @param target
     * @return
     * @throws EmptyCollectionException
     */
    public boolean contains(T target) throws EmptyCollectionException;

    /**
     * Retorna true se a lista está vazia
     * @return
     */
    public boolean isEmpty();

    /**
     * Retorna o número de elementos da lista
     * @return
     */
    public int size();

    /**
     * Retorna um iterador para a lista
     * @return
     */
    Iterator<T> iterator();

    /**
     * Retorna uma representação da lista em String
     * @return
     */
    @Override
    public String toString();

}
