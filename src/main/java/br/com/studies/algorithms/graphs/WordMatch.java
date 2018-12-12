package br.com.studies.algorithms.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordMatch {

	public static void main(String[] args) {
		WordMatch search = new WordMatch();
		Graph g = Graph.buildGraph("A:C,D\nB:E,F,D\nD:G,H\nF:I\nJ:X");
		Node a = g.nodes.get("A");
		Node b = g.nodes.get("J");
		System.out.println(search.searchPath(g, a, b));
	}

	public static class Graph {
		public Map<String, Node> nodes = new HashMap<>();

		public Collection<Node> addNodes(Node from, String data) {
			String[] neigh = data.split(",");
			List<Node> nodes = new ArrayList<>(neigh.length);
			for (String n : neigh) {
				Node to = addNode(n.trim());
				nodes.add(to);
				to.words.add(from);
			}
			from.words.addAll(nodes);

			return nodes;
		}

		public Node addNode(String data) {
			if(nodes.containsKey(data)){
				return nodes.get(data);
			}
			Node n = new Node(data);
			nodes.put(data, n);
			return n;
		}

		public static Graph buildGraph(String data) {
			Graph g = new Graph();
			String[] lines = data.split("\n");
			for (String line : lines) {
				String[] node = line.split(":");
				Node n = g.addNode(node[0].trim());
				g.addNodes(n, node[1]);
			}
			return g;
		}
	}

	public static class Node {
		String data;
		List<Node> words = new ArrayList<>();

		public Node(String data) {
			this.data = data;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (data == null) {
				if (other.data != null)
					return false;
			} else if (!data.equals(other.data))
				return false;
			return true;
		}

		public String toString() {
			return data;
		}
	}

	public boolean searchPath(Graph g, Node a, Node b) {
		LinkedList<Node> nextToVisit = new LinkedList<>();
		Set<Node> visited = new HashSet<>();
		nextToVisit.add(a);
		visited.add(a);

		while (!nextToVisit.isEmpty()) {
			Node p = nextToVisit.poll();
			Collection<Node> edges = p.words;
			for (Node to : edges) {
				if (visited.contains(to)) {
					continue;
				}
				visited.add(to);
				if (to.equals(b)) {
					return true;
				}
				nextToVisit.add(to);
			}
		}
		return false;
	}

}
