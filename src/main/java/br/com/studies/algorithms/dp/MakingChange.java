package br.com.studies.algorithms.dp;

public class MakingChange {

	// Top down dynamic solution. Cache the values
	// as we compute them
	private static final int[] COINS = new int[] { 10, 6, 1 };

	public int run(int c) {
		// Initialize cache with values as -1
		int[] cache = new int[c + 1];
		for (int i = 1; i < c + 1; i++) {
			cache[i] = -1;
		}
		return run(c, cache);
	}

	// Overloaded recursive function
	private int run(int c, int[] cache) {
		// Return the value if it’s in the cache
		if (cache[c] >= 0) {
			return cache[c];
		}
		int minCoins = Integer.MAX_VALUE;
		// Find the best coin
		for (int coin : COINS) {
			if (c - coin >= 0) {
				int currMinCoins = run(c - coin, cache);
				if (currMinCoins < minCoins) {
					minCoins = currMinCoins;
				}
			}
		}
		// Save the value into the cache
		cache[c] = minCoins + 1;
		return cache[c];
	}
}
