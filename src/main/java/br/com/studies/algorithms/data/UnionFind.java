package br.com.studies.algorithms.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UnionFind<T> {

	static class GraphNodeCluster<T> {
		T parent;
		// rank of subtree rooted at i (never more than 31)
		int rank;
	}

	private final Map<T, GraphNodeCluster<T>> clusters;
	private int count; // number of components

	/**
	 * Initializes an empty union-find data structure with <tt>N</tt> sites
	 * <tt>0</tt> through <tt>N-1</tt>. Each site is initially in its own
	 * component.
	 *
	 * @param vertices
	 *            Set<GraphNode<T>> vertices the number of sites
	 * @throws IllegalArgumentException
	 *             if vertices is null
	 */
	public UnionFind(Collection<T> vertices) {
		if (vertices == null || vertices.isEmpty()) {
			throw new IllegalArgumentException();
		}
		int size = vertices.size();
		count = size;
		clusters = new HashMap<>();
		for (T node : vertices) {
			GraphNodeCluster<T> cluster = new GraphNodeCluster<>();
			cluster.parent = node;
			clusters.put(node, cluster);
		}
	}

	/**
	 * Returns the component identifier for the component containing site
	 * <tt>p</tt>.
	 *
	 * @param p
	 *            the GraphNode<T> representing one site
	 * @return the component identifier for the component containing site
	 *         <tt>p</tt>
	 * @throws IndexOutOfBoundsException
	 */
	public GraphNodeCluster<T> find(T p) {
		GraphNodeCluster<T> cluster = clusters.get(p);
		while (p != cluster.parent) {
			// path compression by halving
			clusters.put(p, clusters.get(clusters.get(p).parent));
			cluster = clusters.get(p);
			p = cluster.parent;
		}
		return cluster;
	}

	public int count() {
		return count;
	}

	/**
	 * Returns true if the the two sites are in the same component.
	 *
	 * @param p
	 *            the node representing one site
	 * @param q
	 *            the node representing the other site
	 * @return <tt>true</tt> if the two sites <tt>p</tt> and <tt>q</tt> are in
	 *         the same component; <tt>false</tt> otherwise
	 * @throws IndexOutOfBoundsException
	 *             unless both <tt>0 &le; p &lt; N</tt> and
	 *             <tt>0 &le; q &lt; N</tt>
	 */
	public boolean connected(T p, T q) {
		return find(p).equals(find(q));
	}

	/**
	 * Merges the component containing site <tt>p</tt> with the the component
	 * containing site <tt>q</tt>.
	 *
	 * @param p
	 *            the node representing one site
	 * @param q
	 *            the node representing the other site
	 */
	public void union(T p, T q) {
		GraphNodeCluster<T> rootP = find(p);
		GraphNodeCluster<T> rootQ = find(q);
		if (rootP.equals(rootQ)) {
			return;
		}

		// make root of smaller rank point to root of larger rank
		if (rootP.rank < rootQ.rank) {
			clusters.put(rootP.parent, rootQ);
		} else if (rootP.rank > rootQ.rank) {
			clusters.put(rootQ.parent, rootP);
		} else {
			clusters.put(rootQ.parent, rootP);
			rootP.rank++;
		}
		count--;
	}
}
