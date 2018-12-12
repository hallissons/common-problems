package br.com.studies.algorithms.arrays;

import java.util.Arrays;
import java.util.Iterator;

public class SubSetIterator implements Iterator<int[]>{
	
	private boolean[] used;
	private int[] items;
	private int k;
	private int currSize;
	private int startIndex;
	
	public SubSetIterator(int[] items, int k){
		this.items = items;
		this.k = k;
	}

	@Override
	public boolean hasNext() {
		return startIndex < items.length;
	}

	@Override
	public int[] next() {
		int[] set = new int[k];
		if(currSize == k){
			return Arrays.copyOf(items, k);
		}
		if(startIndex == items.length){
			throw new IllegalArgumentException("No more elements");
		}
		used[startIndex] = true;
		return null;
	}

}
