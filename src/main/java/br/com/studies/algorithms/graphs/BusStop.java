package br.com.studies.algorithms.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class BusStop {

	public static void main(String[] args) {
		BusStop search = new BusStop();
		// O(n*m)
		Graph g = Graph.buildGraph(
				"A:C-B1,D-B2\n" + "B:E-B2,F-B3,D-B4\n" + "D:G-B2,H-B5\n" + "F:I-B6\n" + "G:J-Y1\n" + "J:X-B7");
		Node a = g.nodes.get("A");
		Node b = g.nodes.get("G");

		// O(n*m)
		Route route = search.searchPath(g, a, b);
		if (route == null) {
			System.out.println("No such route");
			return;
		}

		// O(route)
		Stack<Route> finalRoute = new Stack<>();
		while (route != null) {
			finalRoute.push(route);
			route = route.previous;
		}

		// O(route)
		Route routeNode = finalRoute.pop();
		System.out.println("Starting from: " + routeNode.node);
		while (!finalRoute.isEmpty()) {
			routeNode = finalRoute.pop();
			Route nextRoute = finalRoute.isEmpty() ? null : finalRoute.peek();
			if (nextRoute != null && routeNode.busName.equals(nextRoute.busName)) {
				continue;
			}
			System.out.println(String.format("Take bus %s to reach %s", routeNode.busName, routeNode.node));
		}
		System.out.println("Final distance: " + routeNode.distance);

	}

	public static class Graph {
		public Map<String, Node> nodes = new HashMap<>();

		public Collection<Edge> addNodes(Node from, String data) {
			String[] neigh = data.split(",");
			List<Edge> nodes = new ArrayList<>(neigh.length);
			for (String n : neigh) {
				String[] busStop = n.split("-");
				Node to = addNode(busStop[0]);
				Edge e = new Edge(from, to, busStop[1]);
				to.children.add(e);
				nodes.add(e);
			}
			from.children.addAll(nodes);

			return nodes;
		}

		public Node addNode(String data) {
			if (nodes.containsKey(data)) {
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

	public static class Edge {
		Node from;
		Node to;
		String busName;

		public Edge(Node from, Node to, String busName) {
			this.from = from;
			this.to = to;
			this.busName = busName;
		}
	}

	public static class Node {
		String data;
		List<Edge> children = new ArrayList<>();
		Edge previous;

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
			Node other = (Node) obj;
			return data.equals(other.data);
		}

		public String toString() {
			return data;
		}
	}

	public class Route {
		Node node;
		String busName;
		Route previous;
		int distance;

		Route(Node node, String busName, Route previous) {
			this.node = node;
			this.busName = busName;
			this.previous = previous;
			if (previous != null) {
				this.distance = previous.distance + 1;
			}
		}

		public String toString() {
			return String.format("%s: %s", node, busName);
		}
	}

	public Route searchPath(Graph g, Node a, Node b) {
		LinkedList<Route> nextToVisit = new LinkedList<>();
		Set<Node> visited = new HashSet<>();
		nextToVisit.add(new Route(a, null, null));
		visited.add(a);

		while (!nextToVisit.isEmpty()) {
			Route p = nextToVisit.poll();
			for (Edge child : p.node.children) {
				if (visited.contains(child.to)) {
					continue;
				}
				visited.add(child.to);
				Route to = new Route(child.to, child.busName, p);
				if (to.node.equals(b)) {
					return to;
				}
				nextToVisit.add(to);
			}
		}
		return null;
	}
}
