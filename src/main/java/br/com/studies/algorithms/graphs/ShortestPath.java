package br.com.studies.algorithms.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ShortestPath {

	public static class Graph {
		List<Node> nodes = new ArrayList<>();

		public Node addNode(int data) {
			Node node = new Node(data);
			nodes.add(node);
			return node;
		}
	}

	public static class Node {
		List<Node> childs = new ArrayList<>();
		int data;

		public Node(int data) {
			this.data = data;
		}

		public void addChild(Node n) {
			if(!childs.contains(n) && !n.childs.contains(this)){
				childs.add(n);
				n.childs.add(this);
			} else{
				System.out.println("Duplicated edge!");
			}
		}
	}

	public static class NodeDistance {
		Node node;
		int distance = 0;

		public NodeDistance(Node node, int distance) {
			this.node = node;
			this.distance = distance;
		}
	}

	public static Map<Node, Integer> getAllNodesDistances(Graph g, Node start) {
		LinkedList<Node> nextToVisit = new LinkedList<>();
		Map<Node, Integer> visited = new HashMap<>();
		nextToVisit.push(start);
		visited.put(start, 0);
		while (!nextToVisit.isEmpty()) {
			Node next = nextToVisit.poll();
			for (Node c : next.childs) {
				if (visited.containsKey(c)) {
					continue;
				}
				visited.put(c, visited.get(next)+6);
				nextToVisit.offer(c);
			}
		}
		return visited;
	}


	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		//BufferedWriter bufferedWriter = new BufferedWriter(System.out);
		int q = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		String[] results = new String[q];

		for (int i = 1; i <= q; i++) {
			Graph graph = new Graph();

			String[] graphData = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			int n = Integer.valueOf(graphData[0]);
			for (int ni = 1; ni <= n; ni++) {
				graph.addNode(ni);
			}

			int m = Integer.valueOf(graphData[1]);
			for (int j = 1; j <= m; j++) {
				graphData = scanner.nextLine().split(" ");
				scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
				int n1 = Integer.valueOf(graphData[0]);
				int n2 = Integer.valueOf(graphData[1]);
				graph.nodes.get(n1 - 1).addChild(graph.nodes.get(n2 - 1));
				//graph.nodes.get(n2 -1).addChild(graph.nodes.get(n1-1));

				System.out.println(String.format("From: %s To: %s", n1, n2));
			}
			int start = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			Map<Node, Integer> dists = getAllNodesDistances(graph, graph.nodes.get(start - 1));
			StringBuilder result = new StringBuilder();
			for(Node node : graph.nodes){
				if(node.data == start){
					continue;
				}
				if(!dists.containsKey(node)){
					result.append("-1 ");
				} else if(dists.get(node) != null){
					result.append(dists.get(node)+" ");
				}
			}
			System.out.println(result.toString());
		}
		
		
		// bufferedWriter.write(sb.toString());
		//bufferedWriter.newLine();

		//bufferedWriter.close();

		scanner.close();

	}
}
