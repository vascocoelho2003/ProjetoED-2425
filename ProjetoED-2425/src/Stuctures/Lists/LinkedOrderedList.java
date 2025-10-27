package Stuctures.Lists;

import Exceptions.NoSuchElementException;
import Exceptions.NonComparableElementException;

public class LinkedOrderedList<T> extends LinkedList<T> implements OrderedListADT<T> {

    @Override
    public void add(T element) throws NonComparableElementException, NoSuchElementException {
        if (!(element instanceof Comparable)) throw new NonComparableElementException();
        Comparable<T> comparableElement = (Comparable<T>) element;
        LinearNode<T> newNode = new LinearNode<>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            LinearNode<T> current = head;
            LinearNode<T> previous = null;
            while (current != null && comparableElement.compareTo(current.getElement()) > 0) {
                previous = current;
                current = current.getNext();
            }
            if (previous == null) {
                newNode.setNext(head);
                head = newNode;
            } else {
                newNode.setNext(current);
                previous.setNext(newNode);
            }
            if (newNode.getNext() == null) {
                tail = newNode;
            }
        }
        size++;
        modCount++;
    }
}
