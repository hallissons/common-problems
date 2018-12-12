package br.com.studies.algorithms.graphs;

import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GridSearch {
	static class Position {
		int row;
		int col;

		public Position(int row, int col) {
			this.row = row;
			this.col = col;
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
			Position other = (Position) obj;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return String.format("Row: %s Col: %s", row, col);
		}

	}

	// Complete the minimumMoves function below.
	static int minimumMoves(String[] input, int startX, int startY, int goalX, int goalY) {
		int[][] grid = new int[input.length][input.length];
		for (int row = 0; row < input.length; row++) {
			for (int col = 0; col < input.length; col++) {
				if (input[row].charAt(col) == 'X') {
					grid[row][col] = -1;
				} else {
					grid[row][col] = Integer.MAX_VALUE;
				}
			}
		}
		moveInGrid(grid, startX, startY, goalX, goalY);
		return grid[goalX][goalY];
	}

	static void moveInGrid(int[][] grid, int startRow, int startCol, int goalRow, int goalCol) {
		Queue<Position> nextToVisit = new LinkedList<>();
		nextToVisit.add(new Position(startRow, startCol));
		int[][] visited = new int[grid.length][grid.length];

		grid[startRow][startCol] = 0;
		while (!nextToVisit.isEmpty()) {
			Position start = nextToVisit.poll();
			if (visited[start.row][start.col] == 0) {
				visited[start.row][start.col] = 1;
				moveOnGrid(grid, start, nextToVisit);
			}		
		}
	}

	static void moveOnGrid(int[][] grid, Position start, Queue<Position> nextToVisit) {
		int row = start.row;
		int col = start.col;
		int value = grid[row][col];

		for (int i = row; i < grid.length && grid[i][col] != -1; i++) {
			addStep(i, col, value, grid);
			nextToVisit.add(new Position(i, col));
		}
		for (int i = row; i >= 0 && grid[i][col] != -1; i--) {
			addStep(i, col, value, grid);
			nextToVisit.add(new Position(i, col));
		}
		for (int i = col; i < grid.length && grid[row][i] != -1; i++) {
			addStep(row, i, value, grid);
			nextToVisit.add(new Position(row, i));
		}
		for (int i = col; i >= 0 && grid[row][i] != -1; i--) {
			addStep(row, i, value, grid);
			nextToVisit.add(new Position(row, i));
		}
	}

	public static void addStep(int row, int col, int value, int[][] grid) {
		if (grid[row][col] > value + 1) {
			grid[row][col] = value + 1;
		}
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		PrintStream bufferedWriter = System.out;

		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		String[] grid = new String[n];

		for (int i = 0; i < n; i++) {
			String gridItem = scanner.nextLine();
			grid[i] = gridItem;
		}

		String[] startXStartY = scanner.nextLine().split(" ");

		int startX = Integer.parseInt(startXStartY[0]);
		int startY = Integer.parseInt(startXStartY[1]);
		int goalX = Integer.parseInt(startXStartY[2]);
		int goalY = Integer.parseInt(startXStartY[3]);

		int result = minimumMoves(grid, startX, startY, goalX, goalY);

		bufferedWriter.print(String.valueOf(result));
		bufferedWriter.println();

		// bufferedWriter.close();

		scanner.close();
	}
}
