package Stuctures.Lists;

import Exceptions.NoSuchElementException;

public class ArrayOrderedList<T extends Comparable<T>> extends ArrayList<T> implements OrderedListADT<T> {

    public ArrayOrderedList(){
        super();
    }

    public ArrayOrderedList(int initialCapacity){
        super(initialCapacity);
    }

    @Override
    public void add(T element) throws NoSuchElementException {
        if(size() == this.list.length) expandCapacity();
        int i = 0;
        while(i < this.size && element.compareTo(this.list[i]) > 0){
            i++;
        }
        for(int j = this.size; j > i; j--){
            this.list[j] = this.list[j - 1];
        }
        this.list[i] = element;
        this.size++;
        this.modcount++;
    }
}
