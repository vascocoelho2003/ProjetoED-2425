package Stuctures.Lists;

public class DoubleNode<T>{
    private T element;
    private DoubleNode<T> next;
    private DoubleNode<T> previous;

    public DoubleNode(T element){
        this.element = element;
        this.next = null;
        this.previous = null;
    }

    public DoubleNode(){
        this.element = null;
        this.next = null;
        this.previous = null;
    }

    public DoubleNode(T element, DoubleNode<T> previous, DoubleNode<T> next){
        this.element = element;
        this.previous = previous;
        this.next = next;
    }

    public T getElement() {
        return this.element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public DoubleNode<T> getNext() {
        return this.next;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    public DoubleNode<T> getPrevious() {
        return this.previous;
    }

    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }
}
