package br.com.studies.algorithms.arrays;

import java.util.ArrayList;
import java.util.List;

public class MatrixNavigator {

	private String[][] data;

	public MatrixNavigator(String[][] data) {
		this.data = data;
	}

	public void navRow(int r, int c) {
		List<String> res = new ArrayList<>();
		int count = 1;
		for (int i = Math.max(0, c - 4); i < Math.min(data[r].length - 1, c + 4); i++) {
			if (data[r][i] != data[r][i + 1]) {
				count = 1;
				res.clear();
				res.add(data[r][i + 1]);
				continue;
			}
			count++;
			res.add(data[r][i + 1]);
			if (count == 4) {
				System.out.println(data[r][i]);
				break;
			}
		}
		System.out.println(res);
	}

	public void navCol(int r, int c) {
		int count = 1;
		for (int i = r; i < Math.min(r + 4, data.length-1); i++) {
			if (data[i][c] != data[i + 1][c]) {
				break;
			}
			count++;
			if (count == 4) {
				System.out.println(data[i][c]);
				break;
			}
		}
	}
	
	public void navDiag(int r, int c) {
		
	}

	public static void main(String[] args) {
		String[][] data = { { "X", "O", "X", "-1", "X", "X", "X", "X", "O", "X" },
				            { "X", "O", "X", "-1", "X", "X", "X", "X", "O", "X" },
				            { "X", "O", "X", "-1", "X", "X", "X", "X", "O", "X" },
				            { "X", "O", "X", "-1", "X", "X", "X", "X", "O", "X" } };
		MatrixNavigator matrix = new MatrixNavigator(data);
		matrix.navCol(1, 0);
	}
}
