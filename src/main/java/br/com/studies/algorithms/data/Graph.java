package br.com.studies.algorithms.data;

import java.util.HashSet;
import java.util.Set;

public class Graph<T extends Comparable<T>> {

	private final Set<GraphNode<T>> vertices;

	public Graph() {
		this.vertices = new HashSet<>();
	}

	public GraphNode<T> addVertex(T data) {
		GraphNode<T> node = new GraphNode<>(data);
		this.vertices.add(node);
		return node;
	}
	
	public Set<GraphNode<T>> getVertices(){
		return vertices;
	}
	
	public GraphNode<T> getVertice(T data){
		for (GraphNode<T> n : vertices) {
			if(n.data.equals(data)){
				return n;
			}
		}
		return null;
	}
	
	public String toString(){
		return vertices.toString();
	}

	public static class GraphNode<T> {
		private final T data;
		private final Set<GraphNode<T>> edges;
		
		public T getData(){
			return data;
		}

		public GraphNode(T data) {
			this.data = data;
			this.edges = new HashSet<>();
		}
		
		public Set<GraphNode<T>> getEdges(){
			return edges;
		}

		public void addEdge(GraphNode<T> to) {
			edges.add(to);
		}

		public void removeEdge(GraphNode<T> to) {
			edges.remove(to);
		}

		public String toString() {
			return String.valueOf(data);
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
			@SuppressWarnings("unchecked")
			GraphNode<T> other = (GraphNode<T>) obj;
			if (data == null) {
				if (other.data != null)
					return false;
			} else if (!data.equals(other.data))
				return false;
			return true;
		}	
	}
}
