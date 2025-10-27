package Stuctures.Trees;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NoSuchElementException;
import Exceptions.NonComparableElementException;

public class ArrayBinarySearchTree<T> extends ArrayBinaryTree<T> implements BinarySearchTreeADT<T> {

    protected int height;
    protected int maxIndex;

    public ArrayBinarySearchTree() {
        super();
        this.height = 0;
        this.maxIndex = -1;
    }

    public ArrayBinarySearchTree (T element) {
        super(element);
        this.height = 1;
        this.maxIndex = 0;
    }
    @Override
    public void addElement(T element) throws NonComparableElementException {
        if (!(element instanceof Comparable)) {
            throw new NonComparableElementException();
        }
        if (this.tree.length < this.maxIndex * 2 + 3) {
            expandCapacity();
        }
        if (isEmpty()) {
            this.tree[0] = element;
            this.maxIndex = 0;
        } else {
            boolean added = false;
            int currentIndex = 0;
            while (!added) {
                if (((Comparable<T>) element).compareTo((this.tree[currentIndex]) ) < 0) {
                    // go left
                    if (this.tree[currentIndex * 2 + 1] == null) {
                        this.tree[currentIndex * 2 + 1] = element;
                        added = true;
                        if (currentIndex * 2 + 1 > this.maxIndex) {
                            this.maxIndex = currentIndex * 2 + 1;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 1;
                    }
                } else {
                    // go right
                    if (this.tree[currentIndex * 2 + 2] == null) {
                        this.tree[currentIndex * 2 + 2] = element;
                        added = true;
                        if (currentIndex * 2 + 2 > this.maxIndex) {
                            this.maxIndex = currentIndex * 2 + 2;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 2;
                    }
                }
            }
        }
        this.height = (int) (Math.log(this.maxIndex + 1) / Math.log(2)) + 1;
        this.size++;
    }

    private void expandCapacity(){
        T[] newTree = (T[]) (new Object[tree.length * 2]);
        for (int i = 0; i < tree.length; i++) {
            newTree[i] = tree[i];
        }
        tree = newTree;
    }
    @Override
    public T removeElement(T targetElement) throws NonComparableElementException, EmptyCollectionException, ElementNotFoundException, NoSuchElementException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        T removed = null;
        boolean found = false;

        for(int i = 0; (i<=this.maxIndex)&& !found; i++){
            if((this.tree[i]!=null)&& targetElement.equals(this.tree[i])){
                found = true;
                removed = tree[i];
                try{
                    replacement(i);
                }catch(NoSuchElementException e){
                    tree[i] = null;
                }
                this.size--;
            }
        }
        if(!found){
            throw new NoSuchElementException();
        }
        return removed;
    }

    private void replacement(int targetIndex) throws NoSuchElementException{
        if(tree[targetIndex*2+2]!=null){
            tree[targetIndex] = tree[targetIndex * 2 + 3];
            replacement(targetIndex*2+2);
        }else if(tree[targetIndex*2+1]!=null) {
            tree[targetIndex] = tree[targetIndex * 2 + 1];
            replacement(targetIndex * 2 + 1);
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public void removeAllOccurrences(T targetElement) throws EmptyCollectionException, NonComparableElementException, ElementNotFoundException {
        try {
            removeElement(targetElement);
        } catch (NoSuchElementException e) {
            throw new RuntimeException(e);
        }
        while(contains(targetElement)){
            try {
                removeElement(targetElement);
            } catch (NoSuchElementException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public T removeMin() throws EmptyCollectionException, ElementNotFoundException, NonComparableElementException, NoSuchElementException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        return removeElement(findMin());
    }

    @Override
    public T removeMax() throws EmptyCollectionException, ElementNotFoundException, NonComparableElementException, NoSuchElementException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        return removeElement(findMax());
    }

    @Override
    public T findMin() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        T min = tree[0];
        for(int i = 0 ; i <=maxIndex; i++){
            if ((tree[i] != null) && (((Comparable<T>) tree[i]).compareTo(min) < 0)) {
                min = tree[i];
            }
        }
        return min;
    }

    @Override
    public T findMax() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        T max = tree[0];
        for(int i = 0 ; i <=maxIndex; i++){
            if ((tree[i] != null) && (((Comparable<T>) tree[i]).compareTo(max) > 0)) {
                max = tree[i];
            }
        }
        return max;
    }
}
