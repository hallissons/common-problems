package br.com.studies.algorithms.dp;

import java.util.HashMap;
import java.util.Map;

public class Knapsack {
	public static class Item {
		int weight;
		int value;
	}

	// Top down dynamic programming solution.
	// Cache values in a HashMap - the cache may
	// be sparse
	public int execute(Item[] items, int weight) {
		// Map: i -> w -> value
		Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();
		return execute(items, weight, 0, cache);
	}

	// Overloaded recursive function
	private int execute(Item[] items, int weight, int i, Map<Integer, Map<Integer, Integer>> cache) {
		if (i == items.length) {
			return 0;
		}

		// Check if the value is in the cache
		if (!cache.containsKey(i)) {
			cache.put(i, new HashMap<>());
		}

		Integer cached = cache.get(i).get(weight);
		if (cached != null) {
			return cached;
		}
		// Compute the item and add it to the cache
		int toReturn;
		if (weight - items[i].weight < 0) {
			toReturn = execute(items, weight, i + 1, cache);
		} else {
			int withCurr = execute(items, weight - items[i].weight, i + 1, cache) + items[i].value;
			int withoutCurr = execute(items, weight, i + 1, cache);
			toReturn = Math.max(withCurr, withoutCurr);
		}
		cache.get(i).put(weight, toReturn);
		return toReturn;
	}
}
