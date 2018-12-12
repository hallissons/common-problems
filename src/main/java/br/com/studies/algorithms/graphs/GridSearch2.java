package br.com.studies.algorithms.graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GridSearch2 {

	static class Point {
		int row, col;

		Point(int x, int y) {
			this.row = x;
			this.col = y;
		}
	}

	static int game[][], helper[][];
	static Queue<Point> move = new LinkedList<Point>();
	static int n;

	static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {

		n = grid.length;
		game = new int[n][n];
		helper = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i].charAt(j) == '.') {
					game[i][j] = 100;
				} else {
					game[i][j] = -1;
				}
			}
		}

		Point start = new Point(startX, startY);
		move.add(start);
		game[startX][startY] = 0;
		while (!move.isEmpty()) {
			Point current = move.remove();
			if (helper[current.row][current.col] == 0) {
				helper[current.row][current.col] = 1;
				moveGenerator(current);
			}
		}

		return game[goalX][goalY];

	}

	public static void moveGenerator(Point p) {
		int row = p.row;
		int col = p.col;
		int value = game[row][col];
		
		for (int i = row; i < n && game[i][col] != -1; i++) {
			addStep(i, col, value);
			move.add(new Point(i, col));
		}
		for (int i = row; i >= 0 && game[i][col] != -1; i--) {
			addStep(i, col, value);
			move.add(new Point(i, col));
		}
		for (int i = col; i < n && game[row][i] != -1; i++) {
			addStep(row, i, value);
			move.add(new Point(row, i));
		}
		for (int i = col; i >= 0 && game[row][i] != -1; i--) {
			addStep(row, i, value);
			move.add(new Point(row, i));
		}
	}

	public static void addStep(int row, int col, int value) {
		if (game[row][col] > value + 1){
			game[row][col] = value + 1;
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String[] grid = new String[n];
		for (int grid_i = 0; grid_i < n; grid_i++) {
			grid[grid_i] = in.next();
		}
		int startX = in.nextInt();
		int startY = in.nextInt();
		int goalX = in.nextInt();
		int goalY = in.nextInt();
		int result = minimumMoves(grid, startX, startY, goalX, goalY);
		System.out.println(result);
		in.close();
	}
}
