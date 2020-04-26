package br.com.studies.algorithms.data;

import org.junit.Assert;
import org.junit.Test;

public class SimpleSetTest {

	@Test
	public void testAddRemove(){
		SimpleSet<Integer> set = new SimpleSet<>(1);
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		set.remove(1);
		set.remove(4);
		Assert.assertEquals(2, set.size());
	}
}
