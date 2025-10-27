package Stuctures.Lists;

import Exceptions.EmptyCollectionException;
import Exceptions.NoSuchElementException;

import java.util.Iterator;

public class CircularDoubleLinkedList<T> implements ListADT<T>{

    private DoubleNode<T> head;
    private DoubleNode<T> tail;
    private int count;

    public void add(T element){
        DoubleNode<T> newNode = new DoubleNode<>(element);
        if(isEmpty()){
            head = newNode;
            tail = newNode;
            head.setNext(tail);
            tail.setPrevious(head);
        }else{
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
            tail.setNext(head);
            head.setPrevious(tail);
        }
        count++;
    }
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if(isEmpty()) throw new EmptyCollectionException();
        T removed = head.getElement();
        head = head.getNext();
        head.setPrevious(tail);
        tail.setNext(head);
        count--;
        return removed;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if(isEmpty()) throw new EmptyCollectionException();
        T removed = tail.getElement();
        tail = tail.getPrevious();
        tail.setNext(head);
        head.setPrevious(tail);
        count--;
        return removed;
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        if(isEmpty()) throw new EmptyCollectionException();
        DoubleNode<T> current = head;
        while(current != tail && !current.getElement().equals(element)){
            current = current.getNext();
        }
        if(current.getElement().equals(element)) {
            if (current == head) {
                return removeFirst();
            } else if (current == tail) {
                return removeLast();
            } else {
                current.getPrevious().setNext(current.getNext());
                current.getNext().setPrevious(current.getPrevious());
                count--;
                return current.getElement();
            }
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public T first() throws EmptyCollectionException {
        if(isEmpty()) throw new EmptyCollectionException();
        return head.getElement();
    }

    @Override
    public T last() throws EmptyCollectionException {
        if(isEmpty()) throw new EmptyCollectionException();
        return tail.getElement();
    }

    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if(isEmpty()) throw new EmptyCollectionException();
        DoubleNode<T> current = head;
        while(current != tail && !current.getElement().equals(target)){
            current = current.getNext();
        }
        return current.getElement().equals(target);
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString(){
        String result = getClass().getSimpleName() + " { ";
        if (!isEmpty()) {
            DoubleNode<T> current = head;
            do {
                result += current.getElement() + " ";
            } while ((current = current.getNext()) != head);
        }
        return result + "}";
    }

    @Override
    public Iterator<T> iterator() {
        DoubleLinkedUnorderedList<T> list = new DoubleLinkedUnorderedList<>();
        DoubleNode<T> current = head;

        do {
            list.addToRear(current.getElement());
        } while ((current = current.getNext()) != head);
        return list.iterator();
    }
}
