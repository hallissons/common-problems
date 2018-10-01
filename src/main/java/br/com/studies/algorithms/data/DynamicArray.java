package br.com.studies.algorithms.data;

public class DynamicArray {
	
	private static final int INITIAL_MAX = 10;

	private int[] elements = new int[INITIAL_MAX];
	private int size;

	public DynamicArray() {
	}

	public void addAll(DynamicArray ls){
		for (int i = 0; i < ls.size; i++) {
			add(ls.elements[i]);
		}
	}
	
	public void add(int el) {
		ensureCapacity();
		elements[size] = el;
		size++;
	}

	public int remove(int el) {
		for (int i = 0; i < size; i++) {
			if (elements[i] == el) {
				removeAt(i);
				return i;
			}
		}

		return -1;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getAt(int i) {
		if(i < size){
			return elements[i];
		}
		throw new ArrayIndexOutOfBoundsException(i);
	}
	
	public int[] toArray(){
		int[] arr = new int[size];
		System.arraycopy(elements, 0, arr, 0, size);
		return arr;
	}

	@Override
	public String toString() {
		String els = "[";
		for (int i = 0; i < size; i++) {
			els += elements[i];
			if (i + 1 < size) {
				els += ",";
			}
		}
		els += "]";
		return els;
	}

	private void ensureCapacity() {
		if (size + 2 < elements.length) {
			return;
		}
		int[] newArr = new int[elements.length * 2];
		System.arraycopy(elements, 0, newArr, 0, elements.length);
		elements = newArr;
	}

	protected void removeAt(int i) {
		System.arraycopy(elements, i + 1, elements, i, size - 1);
		size--;
	}

}
