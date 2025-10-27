package Stuctures.Trees;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Stuctures.Lists.DoubleLinkedUnorderedList;
import Stuctures.Queue.LinkedQueue;

import java.util.Iterator;

public class LinkedBinaryTree<T> implements  BinaryTreeADT<T>{

    protected BinaryTreeNode<T> root;
    protected int size;

    public LinkedBinaryTree() {
        this.root = null;
        this.size = 0;
    }

    public LinkedBinaryTree(T element) {
        this.root = new BinaryTreeNode<>(element);
        this.size = 1;
    }

    @Override
    public T getRoot() throws EmptyCollectionException{
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        return this.root.getElement();
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(T targetElement) throws EmptyCollectionException {
        if(isEmpty()) {
            throw new EmptyCollectionException();
        }
        try {
            find(targetElement);
            return true;
        } catch (ElementNotFoundException e) {
            return false;
        }
    }

    @Override
    public T find(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
       if(isEmpty()){
           throw new EmptyCollectionException();
       }
         BinaryTreeNode<T> current = findAgain(targetElement, this.root);

         if(current == null) {
             throw new ElementNotFoundException(getClass().getSimpleName());
         }

         return current.getElement();
    }

    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next){
        if(next == null){
            return null;
        }
        if(next.getElement().equals(targetElement)){
            return next;
        }

        BinaryTreeNode<T> temp = findAgain(targetElement, next.getLeft());

        if(temp == null){
            temp = findAgain(targetElement, next.getRight());
        }

        return temp;
    }

    @Override
    public String toString() {
        DoubleLinkedUnorderedList<T> tempList = new DoubleLinkedUnorderedList<>();

        inOrder(root, tempList);

        return tempList.toString();
    }
    @Override
    public Iterator<T> iteratorInOrder() {
        DoubleLinkedUnorderedList<T> tempList = new DoubleLinkedUnorderedList<>();

        inOrder(this.root, tempList);

        return tempList.iterator();
    }

    private void inOrder(BinaryTreeNode<T> node, DoubleLinkedUnorderedList<T> tempList) {
        if (node != null) {
            inOrder(node.getLeft(), tempList);
            tempList.addToRear(node.getElement());
            inOrder(node.getRight(), tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        DoubleLinkedUnorderedList<T> tempList = new DoubleLinkedUnorderedList<>();

        preOrder(this.root, tempList);

        return tempList.iterator();
    }

    private void preOrder(BinaryTreeNode<T> node, DoubleLinkedUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.getElement());
            preOrder(node.getLeft(), tempList);
            preOrder(node.getRight(), tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        DoubleLinkedUnorderedList<T> tempList = new DoubleLinkedUnorderedList<>();

        postOrder(this.root, tempList);

        return tempList.iterator();
    }

    private void postOrder(BinaryTreeNode<T> node, DoubleLinkedUnorderedList<T> tempList) {
        if (node != null) {
            postOrder(node.getLeft(), tempList);
            postOrder(node.getRight(), tempList);
            tempList.addToRear(node.getElement());
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        DoubleLinkedUnorderedList<T> tempList = new DoubleLinkedUnorderedList<>();
        LinkedQueue<BinaryTreeNode<T>> queue = new LinkedQueue<>();

        if (!isEmpty()) {
            queue.enqueue(this.root);

            while (!queue.isEmpty()) {
                BinaryTreeNode<T> next = null;
                try {
                    next = queue.dequeue();
                } catch (EmptyCollectionException e) {
                    throw new RuntimeException(e);
                }

                if (next.getLeft() != null) {
                    queue.enqueue(next.getLeft());
                }

                if (next.getRight() != null) {
                    queue.enqueue(next.getRight());
                }

                tempList.addToRear(next.getElement());
            }
        }

        return tempList.iterator();
    }
}
