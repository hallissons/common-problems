package br.com.studies.algorithms.dp;

public class MakingChange {

	// Top down dynamic solution. Cache the values
	// as we compute them
	private static final int[] COINS = new int[] { 10, 6, 1 };

	public int run(int amount) {
		// Initialize cache with values as -1
		int[] cache = new int[amount + 1];
		for (int i = 1; i < amount + 1; i++) {
			cache[i] = -1;
		}
		return run(amount, cache);
	}

	// Overloaded recursive function
	private int run(int amount, int[] cache) {
		// Return the value if it’s in the cache
		if (cache[amount] >= 0) {
			return cache[amount];
		}
		int minCoins = Integer.MAX_VALUE;
		// Find the best coin
		for (int coin : COINS) {
			if (amount - coin >= 0) {
				int currMinCoins = run(amount - coin, cache);
				if (currMinCoins < minCoins) {
					minCoins = currMinCoins;
				}
			}
		}
		// Save the value into the cache
		cache[amount] = minCoins + 1;
		return cache[amount];
	}
}
