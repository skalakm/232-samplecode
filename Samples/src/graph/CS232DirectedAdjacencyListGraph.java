package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * An Adjacency List implementation of the Graph ADT for undirected graphs.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version April 27, 2016
 * 
 * @param <V>
 *            the type of object associated with the vertices.
 * @param <E>
 *            the type of object associated with the edges.
 */
public class CS232DirectedAdjacencyListGraph<V, E> extends
		CS232AbstractGraph<V, E> {

	/*
	 * Holds the list of edges leaving each vertex. So, index v contains a list
	 * of the edges for which v is the start index.
	 */
	protected LinkedList<Edge<E>>[] edgeLists;

	/**
	 * Construct a new AdjacencyListGraph with the specified number of vertices.
	 * 
	 * @param numVertices
	 *            the number of vertices in the graph.
	 */
	public CS232DirectedAdjacencyListGraph(int numVertices) {
		super(numVertices);

		edgeLists = (LinkedList<Edge<E>>[]) new LinkedList<?>[numVertices];
		for (int i = 0; i < edgeLists.length; i++) {
			edgeLists[i] = new LinkedList<Edge<E>>();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void addEdge(int v1, int v2, E value) {
		// Intentionally not implemented - see homework assignment.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * {@inheritDoc}
	 */
	public E getEdge(int v1, int v2) {
		// Intentionally not implemented - see homework assignment.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * {@inheritDoc}
	 */
	public E removeEdge(int v1, int v2) {
		// Intentionally not implemented - see homework assignment.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Integer> getNeighbors(int v) {
		// Intentionally not implemented - see homework assignment.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Class used to hold the information about each edge in the graph.
	 */
	protected static class Edge<E> {
		public E edgeObject;
		public int startVertex; // not strictly necessary, but seems nice.
		public int endVertex;

		public Edge(int sv, int ev, E obj) {
			edgeObject = obj;
			startVertex = sv;
			endVertex = ev;
		}

		/*
		 * Check if two edges are equal in a directed graph.
		 */
		public boolean equals(Edge<E> e) {
			return (startVertex == e.startVertex && endVertex == e.endVertex);
		}
	}
}