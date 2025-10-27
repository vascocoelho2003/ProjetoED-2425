package Stuctures.Graphs;

import Exceptions.EmptyCollectionException;
import Exceptions.EmptyCollectionException;

public interface NetworkADT<T> extends GraphADT<T> {

        void addEdge(T vertex1, T vertex2, double weight);

        double shortestPathWeight(T vertex1, T vertex2) throws EmptyCollectionException, EmptyCollectionException;
}
