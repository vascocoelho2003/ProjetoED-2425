package Stuctures.Queue;

import Exceptions.EmptyCollectionException;
import Stuctures.Lists.LinearNode;

public class LinkedQueue<T> implements QueueADT<T> {

    /**
     * The front of the queue
     */
    private LinearNode<T> front;
    /**
     * The rear of the queue
     */
    private LinearNode<T> rear;
    /**
     * The size of the queue
     */
    private int size;

    /**
     * Creates an empty queue
     */
    public  LinkedQueue(){
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Adds an element to the rear of the queue
     * @param element
     */
    @Override
    public void enqueue(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        if(isEmpty()){
            this.front = newNode;
        }else{
            this.rear.setNext(newNode);
        }
        this.rear = newNode;
        this.size++;
    }

    /**
     * Removes and returns the element at the front of the queue
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if(isEmpty()) throw new EmptyCollectionException();
        T removed = this.front.getElement();
        this.front = this.front.getNext();
        this.size--;
        return removed;
    }

    /**
     * Returns the element at the front of the queue
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T first() throws EmptyCollectionException {
        if(isEmpty()) throw new EmptyCollectionException();
        return this.front.getElement();
    }

    /**
     * Returns true if the queue is empty
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the size of the queue
     * @return
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns a string representation of the queue
     * @return
     */
    @Override
    public String toString(){
        String result = getClass().getSimpleName() + " { ";
        LinearNode<T> current = this.front;
        if(!isEmpty()){
            do{
                result += current.getElement() + " ";
            }while((current = current.getNext()) != null);
        }
        return result+"}";
    }
}
