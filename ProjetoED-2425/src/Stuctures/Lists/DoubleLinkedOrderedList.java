package Stuctures.Lists;

import Exceptions.NonComparableElementException;

public class DoubleLinkedOrderedList<T extends Comparable> extends DoubleLinkedList<T> implements OrderedListADT<T> {

    public DoubleLinkedOrderedList(){
        super();
    }

    @Override
    public void add(T element) throws NonComparableElementException {
        if(!(element instanceof Comparable)){
            throw new NonComparableElementException();
        }
        if(this.isEmpty()){
            this.head = this.tail = new DoubleNode<>(element);
        }else{
            DoubleNode<T> current = this.head;
            DoubleNode<T> previous = null;
            while(current != null && ((Comparable<T>) element).compareTo(current.getElement())>0){
                previous = current;
                current = current.getNext();
            }
            DoubleNode<T> newNode = new DoubleNode<>(element);
            if(previous==null){
                newNode.setNext(this.head);
                this.head.setPrevious(newNode);
                this.head = newNode;
            }else{
                newNode.setNext(current);
                newNode.setPrevious(previous);
                previous.setNext(newNode);
                if(current != null){
                    current.setPrevious(newNode);
                }else{
                    this.tail=newNode;
                }
            }
        }
        this.count++;
        this.modcount++;
    }

}
