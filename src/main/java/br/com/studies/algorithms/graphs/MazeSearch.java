package br.com.studies.algorithms.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MazeSearch {

	public static void main(String[] args) {
		MazeSearch search = new MazeSearch();
		int[][] maze = { { 0, -1, 0, 0 }, { 0, -1, 0, 0 }, { 0, -1, 0, 0 }, { 0, 0, 0, 0 } };
		Position finish = new Position(0, 2);
		Collection<Position> path = search.searchPath(maze, new Position(0, 0), finish);
		System.out.print(finish);
		Position previous = finish.previous;
		while (previous != null) {
			System.out.print(previous);
			previous = previous.previous;
		}
	}

	public static class Position {
		int row;
		int col;
		int distance;
		Position previous;

		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public String toString() {
			return String.format("[%s, %s: %s]", row, col, distance);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + col;
			result = prime * result + row;
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
			Position other = (Position) obj;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			return true;
		}
	}

	public Collection<Position> searchPath(int[][] maze, Position init, Position finish) {
		LinkedList<Position> nextToVisit = new LinkedList<>();
		Collection<Position> visited = new ArrayList<>();
		nextToVisit.add(init);
		visited.add(init);

		while (!nextToVisit.isEmpty()) {
			Position p = nextToVisit.poll();
			Set<Position> edges = getNeighbors(p, maze);
			for (Position to : edges) {
				to.previous = p;
				if (visited.contains(to)) {
					continue;
				}
				to.distance = p.distance + 1;
				visited.add(to);
				if (visited.contains(finish)) {
					finish.previous = p;
					finish.distance = to.distance;
					return visited;
				}
				nextToVisit.add(to);
			}
		}
		return visited;
	}

	private Set<Position> getNeighbors(Position p, int[][] maze) {
		Set<Position> neigh = new HashSet<>();
		addPoint(neigh, createPosition(maze, p.row, p.col - 1));
		addPoint(neigh, createPosition(maze, p.row, p.col + 1));
		addPoint(neigh, createPosition(maze, p.row - 1, p.col));
		addPoint(neigh, createPosition(maze, p.row + 1, p.col));
		return neigh;
	}

	private Position createPosition(int[][] maze, int row, int col) {
		if (row < 0 || col < 0 || row > maze.length - 1 || col > maze[row].length - 1 || maze[row][col] < 0) {
			return null;
		}
		return new Position(row, col);
	}

	private void addPoint(Set<Position> points, Position p) {
		if (p != null) {
			points.add(p);
		}
	}
}
