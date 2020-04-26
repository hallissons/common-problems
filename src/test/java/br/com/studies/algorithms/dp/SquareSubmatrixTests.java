package br.com.studies.algorithms.dp;

import org.junit.Assert;
import org.junit.Test;

public class SquareSubmatrixTests {

	@Test
	public void testSquareSubmatrix() {
		boolean[][] matrix = { 
				{ true, true, true, true, true  }, 
				{ true, true, true, true, false },
				{ true, true, true, true, false },
				{ true, true, true, true, false },
				{ true, false, false, false, false}};
		SquareSubmatrix ss = new SquareSubmatrix();
		int max = ss.run(matrix);
		Assert.assertEquals(4, max);
	}
}
