package br.com.studies.algorithms.graphs;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import br.com.studies.algorithms.data.Graph;
import br.com.studies.algorithms.data.Graph.GraphNode;

public class GraphSearchTests {

	@Test
	public void testTopologicalSort() {
		Graph<String> graph = buildGraph("a-d,d-c,e,f-a,f-b,b-d");
		Stack<GraphNode<String>> ordered = GraphSearch.topologicalOrder(graph);
		String sequence = "";
		while (!ordered.isEmpty()) {
			sequence += ordered.pop().getData();
		}	
		Assert.assertEquals("febadc", sequence);
	}

	public Graph<String> buildGraph(String g) {
		Graph<String> graph = new Graph<>();
		String[] edges = g.split(",");
		for (String e : edges) {
			String[] edge = e.split("-");
			GraphNode<String> a = graph.getVertice(edge[0]);
			GraphNode<String> b = null;
			if (a == null) {
				a = graph.addVertex(edge[0]);
			}
			if (edge.length > 1) {
				b = graph.getVertice(edge[1]);
				if (b == null) {
					b = graph.addVertex(edge[1]);
				}
				a.addEdge(b);
			}
		}
		return graph;
	}
}
