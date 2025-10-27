package Stuctures.Trees;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NonComparableElementException;

public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    public LinkedBinarySearchTree() {
        super();
    }

    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    @Override
    public void addElement(T element) throws NonComparableElementException {
        if(!(element instanceof Comparable)){
            throw new NonComparableElementException();
        }

        BinaryTreeNode<T> temp = new BinaryTreeNode<>(element);
        if(isEmpty()){
            this.root = temp;
        }else{
            BinaryTreeNode<T> current = this.root;
            boolean added = false;
            while(!added){
                if(((Comparable)element).compareTo(current.getElement()) < 0){
                    if(current.getLeft() == null){
                        current.setLeft(temp);
                        added = true;
                    }else{
                        current = current.getLeft();
                    }
                }else{
                    if(current.getRight() == null){
                        current.setRight(temp);
                        added = true;
                    }else{
                        current = current.getRight();
                    }
                }
            }
        }
        this.size++;
    }

    @Override
    public T removeElement(T targetElement) throws ElementNotFoundException, EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        T removed = null;
        if (targetElement.equals(this.root.getElement())) {
            removed = this.root.getElement();
            this.root = replacement(this.root);
            this.size--;
        } else {
            if (!(targetElement instanceof Comparable)) {
                throw new ElementNotFoundException();
            }
            BinaryTreeNode<T> current, parent = this.root;
            boolean found = false;
            if (((Comparable) targetElement).compareTo(this.root.getElement()) < 0) {
                current = this.root.getLeft();
            } else {
                current = this.root.getRight();
            }
            while (current != null && !found) {
                if (targetElement.equals(current.getElement())) {
                    found = true;
                    this.size--;
                    removed = current.getElement();
                    if (current == parent.getLeft()) {
                        parent.setLeft(replacement (current));
                    } else {
                        parent.setRight(replacement(current));
                    }
                } else {
                    parent = current;
                    if (((Comparable) targetElement).compareTo(current.getElement()) < 0) {
                        current = current.getLeft();
                    } else {
                        current = current.getRight();
                    }
                }
            }
            if (!found) {
                throw new ElementNotFoundException();
            }
        }
        return removed;
    }

    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> replaced;
        if ((node.getLeft() == null) && (node.getRight() == null)) {
            replaced = null;
        } else if ((node.getLeft() != null) && (node.getRight() == null)) {
            replaced = node.getLeft();
        } else if ((node.getLeft() == null) && (node.getRight() != null)) {
            replaced = node.getRight();
        } else {
            BinaryTreeNode<T> current = node.getRight();
            BinaryTreeNode<T> parent = node;
            while (current.getLeft() != null) {
                parent = current;
                current = current.getLeft();
            }
            if (node.getRight() == current) {
                current.setLeft(node.getLeft());
            } else {
                parent.setLeft(current.getRight());
                current.setRight(node.getRight());
                current.setLeft(node.getLeft());
            }
            replaced = current;
        }
        return replaced;
    }

    @Override
    public void removeAllOccurrences(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
        removeElement(targetElement);
        while (contains(targetElement)) {
            removeElement(targetElement);
        }
    }

    @Override
    public T removeMin() throws EmptyCollectionException, ElementNotFoundException {
        return removeElement((findMin()));
    }

    @Override
    public T removeMax() throws EmptyCollectionException, ElementNotFoundException {
        return removeElement(findMax());
    }

    @Override
    public T findMin() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }else{
            BinaryTreeNode<T> current = this.root;
            while(current.getLeft() != null){
                current = current.getLeft();
            }
            return current.getElement();
        }
    }

    @Override
    public T findMax() throws EmptyCollectionException {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }else{
            BinaryTreeNode<T> current = this.root;
            while(current.getRight() != null){
                current = current.getRight();
            }
            return current.getElement();
        }
    }
}
