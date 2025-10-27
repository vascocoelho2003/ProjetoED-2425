package Stuctures.Lists;

import Exceptions.EmptyCollectionException;
import Exceptions.NoSuchElementException;

public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    public ArrayUnorderedList() {
        super();
    }

    public ArrayUnorderedList(int initial) {
        super(initial);
    }

    @Override
    public void addToFront(T element) {
        if(size() == this.list.length) expandCapacity();
        for(int i = size(); i >0; i--){
            this.list[i] = this.list[i-1];
        }
        this.list[0] = element;
        this.size++;
        this.modcount++;
    }

    @Override
    public void addToRear(T element) {
        if(size() == this.list.length) expandCapacity();
        this.list[size()] = element;
        this.size++;
        this.modcount++;
    }

    @Override
    public void addAfter(T element, T target) throws EmptyCollectionException, NoSuchElementException {
        if(isEmpty()) throw new EmptyCollectionException();
        if(size()==this.list.length) expandCapacity();
        int i = 0;
        while(i < size() && !target.equals(this.list[i])){
            i++;
        }
        if(i == this.size){
            throw new NoSuchElementException();
        }
        for(int j = this.size; j>this.size; j--){
            this.list[j]=this.list[j-1];
        }
        this.list[i+1] = element;
        this.size++;
        this.modcount++;
    }
}
