package Stuctures.Lists;

import Exceptions.EmptyCollectionException;
import Exceptions.NoSuchElementException;

public class DoubleLinkedUnorderedList <T> extends DoubleLinkedList<T> implements UnorderedListADT<T> {

    public DoubleLinkedUnorderedList(){
        super();
    }

    @Override
    public void addToFront(T element) {
        if(isEmpty()){
            this.head = this.tail = new DoubleNode<>(element);
        }
        else{
            DoubleNode<T> newNode = new DoubleNode<>(element);
            newNode.setNext(this.head);
            this.head.setPrevious(newNode);
            this.head = newNode;
        }
        this.count++;
        this.modcount++;
    }

    @Override
    public void addToRear(T element) {
        if(isEmpty()){
            this.head = this.tail = new DoubleNode<>(element);
        }
        else{
            DoubleNode<T> newNode = new DoubleNode<>(element);
            this.tail.setNext(newNode);
            newNode.setPrevious(this.tail);
            this.tail = newNode;
        }
        this.count++;
        this.modcount++;
    }

    @Override
    public void addAfter(T element, T target) throws EmptyCollectionException, NoSuchElementException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        DoubleNode<T> current = this.head;
        while (current != null && !current.getElement().equals(target)) {
            current = current.getNext();
        }
        if (current == null) {
            throw new NoSuchElementException();
        }
        if(current == this.tail){
            addToRear(element);
        }
        else{
            DoubleNode<T> newNode = new DoubleNode<>(element);
            newNode.setNext(current.getNext());
            newNode.setPrevious(current);
            current.getNext().setPrevious(newNode);
            current.setNext(newNode);
            this.count++;
            this.modcount++;
        }
    }
}
