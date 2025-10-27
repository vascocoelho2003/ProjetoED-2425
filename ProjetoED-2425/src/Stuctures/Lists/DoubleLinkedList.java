package Stuctures.Lists;

import Exceptions.EmptyCollectionException;

import Exceptions.ConcurrentModificationException;
import java.util.Iterator;
import Exceptions.NoSuchElementException;

public class DoubleLinkedList <T> implements ListADT<T>, Iterable<T>{

    protected DoubleNode<T> head;
    protected DoubleNode<T> tail;
    protected  int count, modcount;

    public DoubleLinkedList(){
        this.head = null;
        this.tail = null;
        this.count = 0;
        this.modcount = 0;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        T removed = this.head.getElement();
        this.head = this.head.getNext();
        this.count--;
        if(isEmpty()){
            this.tail = null;
        }else{
            this.head.setPrevious(null);
        }
        this.modcount++;
        return removed;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        T removed = this.tail.getElement();
        this.tail = this.tail.getPrevious();
        this.count-- ;
        if(isEmpty()){
            this.head = null;
        }else{
            this.tail.setNext(null);
        }
        this.modcount++;
        return removed;
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        boolean found = false;
        DoubleNode<T> current = this.head;
        while(current != null && !found){
            if(element.equals(current.getElement())){
                found = true;
            }else{
                current = current.getNext();
            }
        }

        if(!found){
            throw new NoSuchElementException();
        }

        if(size()==1){
            this.head = this.tail = null;
        }else if(current.equals(this.head)){
            this.head = current.getNext();
            this.head.setPrevious(null);
        }else if(current.equals(this.tail)){
            this.tail = current.getPrevious();
            this.tail.setNext(null);
        }else{
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
        }
        this.count--;
        this.modcount++;
        return current.getElement();
    }

    @Override
    public T first() throws EmptyCollectionException {
       if(isEmpty()){
           throw new EmptyCollectionException();
       }
       return this.head.getElement();
    }

    @Override
    public T last() throws EmptyCollectionException {
       if(isEmpty()){
           throw new EmptyCollectionException();
       }
       return this.tail.getElement();
    }

    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if(isEmpty()) throw new EmptyCollectionException();
        DoubleNode<T> current = this.head;
        while(current != null){
            if(target.equals(current.getElement())){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public String toString(){
        String result = getClass().getSimpleName()+"{";
        if(!isEmpty()){
            DoubleNode<T> current = this.head;
            while(current != null){
                result += current.getElement()+" ";
                current = current.getNext();
            }
        }
        return result + "}";
    }

    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<T>(){};
    }

    public abstract class BasicIterator<E> implements Iterator<T>{
        protected DoubleNode<T> current;
        protected int expectedModCount;
        protected  boolean okToRemove;

        public BasicIterator(){
            this.current=head;
            this.expectedModCount=modcount;
            this.okToRemove=false;
        }

        @Override
        public boolean hasNext(){
            return current != null;
        }

        @Override
        public T next(){
            if(modcount != expectedModCount){
                try {
                    throw new ConcurrentModificationException();
                } catch (ConcurrentModificationException e) {
                    throw new RuntimeException(e);
                }
            }
            if(!hasNext()){
                try {
                    throw new NoSuchElementException();
                } catch (NoSuchElementException e) {
                    throw new RuntimeException(e);
                }
            }
            T result = current.getElement();
            current = current.getNext();
            okToRemove=true;
            return result;
        }

        @Override
        public void remove(){
            if(modcount != this.expectedModCount){
                try {
                    throw new ConcurrentModificationException();
                } catch (ConcurrentModificationException e) {
                    throw new RuntimeException(e);
                }
            }
            if(!okToRemove){
                try {
                    throw new NoSuchElementException();
                } catch (NoSuchElementException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                try {
                    DoubleLinkedList.this.remove(current.getPrevious().getElement());
                } catch (NoSuchElementException e) {
                    throw new RuntimeException(e);
                }
            } catch (EmptyCollectionException e) {
                throw new RuntimeException(e);
            }
            expectedModCount++;
            okToRemove=false;
        }
    }
}
