package Stuctures.Lists;

import Exceptions.EmptyCollectionException;

public class LinkedListSentinel<T> {
    private final LinearNode<T> head;
    private LinearNode<T> tail;
    private int size;

    public LinkedListSentinel() {
        this.tail = new LinearNode<>(null);
        this.head = this.tail;
        this.size = 0;
    }

    public void add(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        this.tail.setNext(newNode);
        this.tail = newNode;
        this.size++;
    }

    public void remove(int index) throws EmptyCollectionException, IndexOutOfBoundsException {
        if (isEmpty()) throw new EmptyCollectionException();
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        LinearNode<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setNext(current.getNext().getNext());
        this.size--;
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public int size(){
        return this.size;
    }

    public String toString(){
        String result = getClass().getSimpleName() + " [";
        if(!isEmpty()){
            LinearNode<T> current = this.head;
            while(current != null){
                result += current.getElement() + ", ";
                current = current.getNext();
            }
            result = result.substring(0, result.length()-2);
        }
        return result + "]";
    }
}
