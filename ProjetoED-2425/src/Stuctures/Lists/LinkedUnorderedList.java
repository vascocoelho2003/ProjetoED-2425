package Stuctures.Lists;

import Exceptions.EmptyCollectionException;
import Exceptions.NoSuchElementException;

public class LinkedUnorderedList<T> extends LinkedList<T> implements UnorderedListADT<T> {

    @Override
    public void addToFront(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
        modCount++;
    }

    @Override
    public void addAfter(T element, T target) throws EmptyCollectionException, NoSuchElementException {
        if (isEmpty()) throw new EmptyCollectionException();
        if (!contains(target)) throw new NoSuchElementException();
        LinearNode<T> newNode = new LinearNode<>(element);
        LinearNode<T> current = head;
        while (!current.getElement().equals(target)) {
            current = current.getNext();
        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        if (newNode.getNext() == null) {
            tail = newNode;
        }
        size++;
        modCount++;
    }
}
