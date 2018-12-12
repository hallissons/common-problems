package br.com.studies.algorithms.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ShortestPathSimpler {

	public static class Graph {
		List<List<Integer>> adjLst;
		int size;

		public Graph(int size) {
			adjLst = new ArrayList<>();
			this.size = size;
			while (size-- > 0)// Initialize the adjancency list.
				adjLst.add(new ArrayList<Integer>());
		}

		public void addEdge(int first, int second) {
			adjLst.get(first).add(second);
			adjLst.get(second).add(first);
			// For undirected graph, you gotta add edges from both sides.
		}

		public int[] shortestReach(int startId) { // 0 indexed
			int[] distances = new int[size];
			Arrays.fill(distances, -1); // O(n) space.
			Queue<Integer> que = new LinkedList<>();

			que.add(startId); // Initialize queue.
			distances[startId] = 0;
			HashSet<Integer> seen = new HashSet<>(); // O(n) space. Could be
														// further optimized. I
														// leave it to you to
														// optimize it.

			seen.add(startId);
			while (!que.isEmpty()) { // Standard BFS loop.
				Integer curr = que.poll();
				for (int node : adjLst.get(curr)) {
					if (!seen.contains(node)) {
						que.offer(node);
						// Right place to add the visited set.
						seen.add(node);
						// keep on increasing distance level by level.
						distances[node] = distances[curr] + 6;
					}
				}
			}
			return distances;
		}
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		// BufferedWriter bufferedWriter = new BufferedWriter(System.out);
		int q = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		String[] results = new String[q];

		for (int i = 1; i <= q; i++) {
			

			String[] graphData = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			int n = Integer.valueOf(graphData[0]);
			Graph graph = new Graph(n+1);


			int m = Integer.valueOf(graphData[1]);
			for (int j = 1; j <= m; j++) {
				graphData = scanner.nextLine().split(" ");
				scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
				int n1 = Integer.valueOf(graphData[0]);
				int n2 = Integer.valueOf(graphData[1]);
				graph.addEdge(n1, n2);

				System.out.println(String.format("From: %s To: %s", n1, n2));
			}
			int start = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			int[] distances  = graph.shortestReach(start);
			StringBuilder result = new StringBuilder();
			for (int dist : distances) {
				result.append(dist + " ");
			}
			System.out.println(result.toString());
		}

		// bufferedWriter.write(sb.toString());
		// bufferedWriter.newLine();

		// bufferedWriter.close();

		scanner.close();

	}
}
