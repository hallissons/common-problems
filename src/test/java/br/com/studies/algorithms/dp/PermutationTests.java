package br.com.studies.algorithms.dp;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PermutationTests {

	@Test
	public void testPermute() {
		Permutation pe = new Permutation();
		List<int[]> perms = pe.permute(new int[] { 1, 2, 3 });
		StringBuilder result = new StringBuilder();
		for (int[] p : perms) {
			result.append(Arrays.toString(p));
		}
		Assert.assertEquals("[1, 2, 3][1, 3, 2][2, 1, 3][2, 3, 1][3, 2, 1][3, 1, 2]", result.toString());
		Assert.assertEquals(6, perms.size());
	}
}
