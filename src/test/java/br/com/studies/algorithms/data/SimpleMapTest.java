package br.com.studies.algorithms.data;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class SimpleMapTest {

	@Test
	public void testAddSearch(){
		SimpleMapImpl<Integer, Integer> map = new SimpleMapImpl<>(10);
		map.insert(0, 0);
		map.insert(10, 10);
		map.insert(20, 20);
		map.insert(2, 2);
		map.insert(3, 3);
		assertNotNull(map.remove(0));
		assertNotNull(map.search(10));
		assertNotNull(map.search(20));
	}
}
