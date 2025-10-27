package Stuctures.Queue;

import Exceptions.EmptyCollectionException;

public interface QueueADT <T>{
    /**
     * Adiciona um elemento ao final da fila
     * @param element
     */
    public void enqueue (T element);

    /**
     * Remove e retorna o elemento do inicio da fila
     * @return
     */
    public T dequeue() throws EmptyCollectionException;

    /**
     * Retorna o elemento do inicio da fila sem remove-lo
     * @return
     */
    public T first() throws EmptyCollectionException;

    /**
     * Retorna true se a fila estiver vazia
     * @return
     */
    public boolean isEmpty();

    /**
     * Retorna o numero de elementos na fila
     * @return
     */
    public int size();

}
