package Stuctures.Graphs;

import Exceptions.EmptyCollectionException;

import java.util.Iterator;

public class Network<T> extends Graph<T> implements NetworkADT<T>{

    protected double[][] adjMatrix;

    public Network() {
        numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }


    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }

    public void addEdge(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
            adjMatrix[index2][index1] = weight;
        }
    }

    @Override
    public double shortestPathWeight(T startVertex, T targetVertex) throws EmptyCollectionException {
        return shortestPathWeight(getIndex(startVertex), getIndex(targetVertex));
    }

    public double shortestPathWeight(int startIndex, int targetIndex) throws EmptyCollectionException {
        double result = 0;
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return Double.POSITIVE_INFINITY;
        }

        int index1, index2;
        Iterator<Integer> it = iteratorShortestPathIndices(startIndex,
                targetIndex);

        if (it.hasNext()) {
            index1 = it.next();
        } else {
            return Double.POSITIVE_INFINITY;
        }

        while (it.hasNext()) {
            index2 = it.next();
            result += adjMatrix[index1][index2];
            index1 = index2;
        }

        return result;
    }
}
