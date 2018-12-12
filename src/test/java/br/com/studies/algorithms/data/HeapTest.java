package br.com.studies.algorithms.data;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

import br.com.studies.algorithms.data.Heap;

public class HeapTest {

	@Test
	public void testHeap() {
		Comparator<Integer> inv = (Integer o1, Integer o2) -> o1.compareTo(o2);
		Heap<Integer> heap = new Heap<>(50, inv);
		heap.add(10);
		heap.add(20);
		heap.add(5);
		heap.add(2);
		heap.add(30);
		heap.add(1);
		Integer[] ordered = new Integer[6];
		for (int i = 0; i < 6; i++) {
			ordered[i] = heap.poll();
		}
		Assert.assertEquals("[1, 2, 5, 10, 20, 30]", Arrays.toString(ordered));
	}

}
