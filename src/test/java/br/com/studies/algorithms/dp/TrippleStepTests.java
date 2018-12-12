package br.com.studies.algorithms.dp;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class TrippleStepTests {

	@Test
	public void testTenSteps() {
		TrippleStep ts = new TrippleStep();
		int n = 10;
		int[] memo = new int[n + 1];
		Arrays.fill(memo, -1);
		int steps = ts.steps(n, memo);
		Assert.assertEquals(274, steps);
		System.out.println();
	}
}
