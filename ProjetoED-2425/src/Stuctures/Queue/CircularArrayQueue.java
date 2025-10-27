package Stuctures.Queue;

import Exceptions.EmptyCollectionException;

public class CircularArrayQueue<T> implements QueueADT<T> {

    /**
     * Default capacity of the queue
     */
    private final static int DEFAULT_CAPACITY = 100;
    /**
     * Array that represents the queue
     */
    private T[] queue;
    /**
     * Index of the front of the queue
     */
    private int front;
    /**
     * Index of the rear of the queue
     */
    private int rear;
    /**
     * Number of elements in the queue
     */
    private int count;

    /**
     * Creates an empty queue with the default capacity
     */
    public CircularArrayQueue() {
        this.queue = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    /**
     * Creates an empty queue with the specified capacity
     * @param initialCapacity
     */
    public CircularArrayQueue(int initialCapacity) {
        this.queue = (T[]) (new Object[initialCapacity]);
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    /**
     * Expands the capacity of the queue
     */
    private void expandCapacity() {
        T[] larger = (T[]) (new Object[queue.length * 2]);
        for (int index = front; index < rear; index++) {
            larger[index] = queue[index];
        }
        queue = larger;
    }

    /**
     * Adds the specified element to the rear of the queue
     * @param element
     */
    @Override
    public void enqueue(T element) {
        if(size() == this.queue.length -1){
            expandCapacity();
        }
        this.queue[this.rear] = element;
        this.rear=(this.rear+1)% this.queue.length;
        this.count++;
    }

    /**
     * Removes and returns the element at the front of the queue
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if(isEmpty()) throw new EmptyCollectionException();
        T removed = this.queue[this.front];
        this.queue[this.front] = null;
        this.front = (this.front + 1) % this.queue.length;
        this.count--;
        return removed;
    }

    /**
     * Returns without removing the element at the front of the queue
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public T first() throws EmptyCollectionException {
       if(isEmpty()) throw new EmptyCollectionException();
       return this.queue[this.front];
    }

    /**
     * Returns true if the queue is empty
     * @return
     */
    @Override
    public boolean isEmpty() {
       return size()==0;
    }

    /**
     * Returns the number of elements in the queue
     * @return
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Returns a string representation of the queue
     * @return
     */
    @Override
    public String toString(){
        String result = getClass().getSimpleName() + " { ";
        if(!isEmpty()){
            int current = this.front;
            do{
                result += this.queue[current] + " ";
                current = (current + 1) % this.queue.length;
            }while(current != this.rear);
        }
        return result + "}";
    }
}
