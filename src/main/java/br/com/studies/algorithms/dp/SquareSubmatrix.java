package br.com.studies.algorithms.dp;

public class SquareSubmatrix {

	// Top down dynamic programming solution. Cache
	// the values to avoid repeating computations
	public int run(boolean[][] arr) {
		// Initialize cache. Don't need to initialize
		// to -1 because the only cells that will be
		// 0 are ones that are false and we want to
		// skip those ones anyway
		int[][] cache = new int[arr.length][arr[0].length];
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j]) {
					max = Math.max(max, run(arr, i, j, cache));
				}
			}
		}
		return max;
	}

	// Overloaded recursive function
	private int run(boolean[][] arr, int i, int j, int[][] cache) {
		if (i == arr.length || j == arr[0].length) {
			return 0;
		}
		if (!arr[i][j]) {
			return 0;
		}
		// If the value is set in the cache return
		// it. Otherwise compute and save to cache
		if (cache[i][j] > 0) {
			return cache[i][j];
		}

		int down = run(arr, i + 1, j, cache);
		int rigth = run(arr, i, j + 1, cache);
		int up = run(arr, i + 1, j + 1, cache);
		cache[i][j] = min(down, rigth, up) + 1;
		return cache[i][j];
	}

	private int min(int... arr) {
		int min = arr[0];
		for (int i : arr) {
			if (i < min) {
				min = i;
			}
		}
		return min;
	}
}
