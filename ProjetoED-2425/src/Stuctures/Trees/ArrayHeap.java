package Stuctures.Trees;

import Exceptions.EmptyCollectionException;

public class ArrayHeap<T> extends  ArrayBinaryTree<T> implements HeapADT<T>{
    public ArrayHeap(){
        super();
    }

    @Override
    public void addElement(T element) {
        if(this.size == this.tree.length){
            expandCapacity();
        }
        this.tree[size]=element;
        this.size++;
        if(this.size > 1){
            heapifyAdd();
        }
    }

    private void expandCapacity(){
        T[] newTree = (T[])(new Object[this.tree.length*2]);
        for(int i = 0; i < this.tree.length; i++){
            newTree[i] = this.tree[i];
        }
        this.tree = newTree;
    }

    private void heapifyAdd(){
        T temp;
        int next = this.size - 1;
        temp = this.tree[next];
        while((next != 0) && (((Comparable)temp).compareTo(this.tree[(next - 1) / 2]) < 0)){
            this.tree[next] = this.tree[(next - 1) / 2];
            next = (next - 1) / 2;
        }
        this.tree[next] = temp;
    }

    @Override
    public T removeMin() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        T minElement = this.tree[0];
        this.tree[0] = this.tree[this.size-1];
        heapifyRemove();
        this.size--;

        return minElement;
    }

    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return tree[0];
    }

    private void heapifyRemove(){
        T temp;
        int node = 0;
        int left = 1;
        int right = 2;
        int next;

        if ((tree[left] == null) && (tree[right] == null)) {
            next = size;
        } else if (tree[left] == null) {
            next = right;
        } else if (tree[right] == null) {
            next = left;
        } else if (((Comparable)tree[left]).compareTo(tree[right]) < 0) {
            next = left;
        } else {
            next = right;
        }
        temp = tree[node];
        while ((next < size) && (((Comparable) tree[next]).compareTo(temp) < 0)) {
            tree[node] = tree[next];
            node = next;
            left = 2 * node + 1;
            right = 2 * (node + 1);
            if ((tree[left] == null) && (tree[right] == null)) {
                next = size;
            } else if (tree[left] == null) {
                next = right;
            } else if (tree[right] == null) {
                next = left;
            } else if (((Comparable) tree[left]).compareTo(tree[right]) < 0) {
                next = left;
            }
            else {
                next = right;
            }
        }
        tree[node] = temp;
    }
}
