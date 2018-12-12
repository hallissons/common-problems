package br.com.studies.algorithms.graphs;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.studies.algorithms.data.Graph;
import br.com.studies.algorithms.data.Graph.GraphNode;

public final class GraphSearch {

	private GraphSearch() {
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(GraphSearch.class);

	public static <T extends Comparable<T>> Stack<GraphNode<T>> topologicalOrder(Graph<T> graph) {
		Set<GraphNode<T>> explored = new HashSet<>();
		Stack<GraphNode<T>> nodes = new Stack<>();
		for (GraphNode<T> v : graph.getVertices()) {
			if (!explored.contains(v)) {
				dfs(explored, nodes, v);
			}
		}
		return nodes;
	}

	public static <T> void dfs(Set<GraphNode<T>> explored, Stack<GraphNode<T>> nodes, GraphNode<T> node) {
		explored.add(node);
		System.out.print("(");
		for (GraphNode<T> edge : node.getEdges()) {
			if (!explored.contains(edge)) {
				dfs(explored, nodes, edge);
			}
		}
		System.out.print(node.getData()+")");
		LOGGER.info("Node: " + node.getData());
		nodes.add(node);
	}
}
