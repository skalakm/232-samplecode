package graph;

import java.util.ArrayList;

/**
 * An Adjacency List implementation of the Graph ADT for undirected graphs.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version April 27, 2016
 */
public class CS232GraphAlgorithms {

	/*
	 * Do a depth-first search/traversal of the graph printing out the object
	 * associated with each vertex as it is visited.
	 */
	public static void DFS(CS232Graph<?, ?> g) {
		// mark all vertices as unvisited.
		for (int v = 0; v < g.numVertices(); v++) {
			g.setVertexMark(v, CS232Graph.UNVISITED);
		}

		/*
		 * Go through all of the vertices and do a DFS starting at any that are
		 * unvisited. This this is necessary to ensure that all connected
		 * components are searched. This seems at first to be inefficient.
		 * However, note that when a component is searched by DFSComponent all
		 * of the vertices in that component will be marked as visited. Thus,
		 * DFSComponent will be invoked on each component only once.
		 */
		for (int v = 0; v < g.numVertices(); v++) {
			if (g.getVertexMark(v) == CS232Graph.UNVISITED) {
				DFSComponent(g, v);
			}
		}
	}

	/*
	 * Helper method for DFS that does a DFS of the connected component
	 * containing v.
	 */
	private static void DFSComponent(CS232Graph<?, ?> g, int v) {
		// Mark the vertex as visited.
		g.setVertexMark(v, CS232Graph.VISITED);

		// Do what needs to be done at vertex.
		System.out.println(g.getVertexObject(v));

		/*
		 * Do a DFS starting at each of the unvisited neighbors of v. Applied
		 * recursively this will visit all vertices in the connected component.
		 */
		ArrayList<Integer> neighbors = g.getNeighbors(v);
		for (int n : neighbors) {
			if (g.getVertexMark(n) == CS232Graph.UNVISITED) {
				DFSComponent(g, n);
			}
		}
	}
	
	/*
	 * Do a breadth-first search/traversal of the graph printing out the object
	 * associated with each vertex as it is visited.
	 */
	public static void BFS(CS232Graph<?, ?> g) {
		// Intentionally not implemented - see homework assignment.
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
