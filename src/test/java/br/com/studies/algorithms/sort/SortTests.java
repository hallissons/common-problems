package br.com.studies.algorithms.sort;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

public class SortTests {

	@Test
	public void testMergeSort() {
		int[] arr = { 9, 1, 3, 5, 6, 3, 1 };
		int[] expected = arr.clone();
		Arrays.sort(expected);
		MergeSort.sort(arr);
		assertArrayEquals(expected, arr);
	}
	
	@Test
	public void testQuickSort() {
		int[] arr = { 9, 1, 3, 5, 6, 3, 1 };
		int[] expected = arr.clone();
		Arrays.sort(expected);
		QuickSort.sort(arr);
		assertArrayEquals(expected, arr);
	}
}
