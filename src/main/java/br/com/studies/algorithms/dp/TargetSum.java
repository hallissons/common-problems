package br.com.studies.algorithms.dp;

public class TargetSum {
	
	// Naive brute force solution. Find every
	// combo
	public int run(int[] nums, int target) {
		return run(nums, target, 0, 0);
	}

	// Overloaded recursive function
	private int run(int[] nums, int target, int i, int sum) {
		// When we've gone through every item, see
		// if we've reached our target sum
		if (i == nums.length) {
			return sum == target ? 1 : 0;
		}
		// Combine the possibilities by adding and
		// subtracting the current value
		return run(nums, target, i + 1, sum + nums[i]) + run(nums, target, i + 1, sum - nums[i]);
	}
}
