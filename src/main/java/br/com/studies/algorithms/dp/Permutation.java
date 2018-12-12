package br.com.studies.algorithms.dp;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
	
	public List<int[]> permute(int[] nums) {
		List<int[]> results = new ArrayList<>();
		findPermutations(nums, 0, results);
		return results;
	}

	private void findPermutations(int[] arr, int start, List<int[]> results) {
		if (start >= arr.length) {
			results.add(arr.clone());
			return;
		}
		for (int i = start; i < arr.length; i++) {
			swap(arr, start, i);
			findPermutations(arr, start + 1, results);
			swap(arr, start, i);
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
