package Stuctures.Trees;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Stuctures.Lists.ArrayUnorderedList;

import java.util.Iterator;

public class ArrayBinaryTree <T> implements  BinaryTreeADT<T>{
    protected final int DEFAULT_CAPACITY = 100;
    protected T[] tree;
    protected int size;

    public ArrayBinaryTree(){
        this.tree = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.size = 0;
    }

    public ArrayBinaryTree(T element){
        this.tree = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.tree[0] = element;
        this.size = 1;
    }
    @Override
    public T getRoot() {
        return this.tree[0];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(T targetElement) {
        try {
            if(find(targetElement)!=null) {
                return true;
            }
        } catch (EmptyCollectionException e) {
            throw new RuntimeException(e);
        } catch (ElementNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public T find(T targetElement) throws EmptyCollectionException,ElementNotFoundException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }

        T element = null;
        boolean found = false;

        for(int i = 0; i < this.size && !found; i++){
            if(this.tree[i].equals(targetElement)){
                found = true;
                element = this.tree[i];
            }
        }

        if(!found){
            throw new ElementNotFoundException();
        }

        return element;
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        inOrder(0, tempList);

        return tempList.iterator();
    }

    private void inOrder(int node, ArrayUnorderedList<T> tempList) {
        if (node < this.size) {
            inOrder(node * 2 + 1, tempList);
            if (this.tree[node] != null) {
                tempList.addToRear(this.tree[node]);
            }
            inOrder(node * 2 + 2, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        preOrder(0, tempList);

        return tempList.iterator();
    }

    private void preOrder(int node, ArrayUnorderedList<T> tempList) {
        if (node < this.size) {
            tempList.addToRear(this.tree[node]);
            preOrder(node * 2 + 1, tempList);
            preOrder(node * 2 + 2, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        postOrder(0, tempList);

        return tempList.iterator();
    }

    private void postOrder(int node, ArrayUnorderedList<T> tempList) {
        if (node < this.size) {
            postOrder(node * 2 + 1, tempList);
            postOrder(node * 2 + 2, tempList);
            tempList.addToRear(this.tree[node]);
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();

        for (int i = 0; i < this.size; i++) {
            tempList.addToRear(this.tree[i]);
        }

        return tempList.iterator();
    }
}
