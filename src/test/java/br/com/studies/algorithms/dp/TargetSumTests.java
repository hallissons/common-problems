package br.com.studies.algorithms.dp;

import org.junit.Test;

public class TargetSumTests {

	@Test
	public void testTargetSum(){
		int[] arr = {1, 1, 1, 1, 1};
		TargetSum ts = new TargetSum();
		System.out.println(ts.run(arr, 5));
	}
}
