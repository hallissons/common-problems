package br.com.studies.algorithms.dp;

public class TrippleStep {

	public int steps(int n, int[] memo) {
		if (n < 0) {
			return 0;
		}
		if (n == 0) {
			return 1;
		}

		if (memo[n] > -1) {
			return memo[n];
		}

		memo[n] = steps(n - 1, memo) + steps(n - 2, memo) + steps(n - 3, memo);
		return memo[n];
	}
}
