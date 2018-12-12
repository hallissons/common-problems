package br.com.studies.algorithms.data;

import java.util.Arrays;
import java.util.Comparator;

public class Heap<T> {

	private static final int INITIAL_DEFAULT_CAPACITY = 10;

	private Object[] elements;
	private int capacity;
	private int count;
	private final Comparator<? super T> comparator;

	public Heap() {
		this(INITIAL_DEFAULT_CAPACITY);
	}

	public Heap(int capacity) {
		this(capacity, null);
	}

	public Heap(int capacity, Comparator<? super T> comparator) {
		this.capacity = capacity;
		this.comparator = comparator;
		elements = new Object[capacity];
	}

	@SuppressWarnings("unchecked")
	public T peek() {
		if (count == 0) {
			return null;
		}
		return (T) elements[0];
	}

	@SuppressWarnings("unchecked")
	public T poll() {
		if (count == 0) {
			return null;
		}
		Object element = elements[0];
		elements[0] = elements[count - 1];
		count--;
		heapifyDown();
		return (T) element;
	}

	public void add(T element) {
		ensureCapacity();
		elements[count] = element;
		count++;
		if (count > 1) {
			heapifyUp();
		}
	}

	private void heapifyUp() {
		int index = count - 1;
		while (hasParent(index) && compare(getParent(index), elements[index]) > 0) {
			swap(getParentIndex(index), index);
			index = getParentIndex(index);
		}
	}

	private void heapifyDown() {
		int index = 0;
		while (hasLeft(index)) {
			int smallerChildIndex = getLeftIndex(index);
			if (hasRight(index) && compare(getRight(index), getLeft(index)) < 0) {
				smallerChildIndex = getRightIndex(index);
			}

			if (compare(elements[index], elements[smallerChildIndex]) < 0) {
				break;
			}
			swap(index, smallerChildIndex);
			index = smallerChildIndex;
		}
	}

	private boolean ensureCapacity() {
		if (elements.length == capacity - 1) {
			capacity *= 2;
			elements = Arrays.copyOf(elements, capacity);
			return true;
		}
		return false;
	}

	private boolean swap(int firstIdx, int secondIdx) {
		if (!isIndexValid(firstIdx) || !isIndexValid(secondIdx)) {
			return false;
		}
		Object tmp = elements[firstIdx];
		elements[firstIdx] = elements[secondIdx];
		elements[secondIdx] = tmp;
		return true;
	}

	private T getParent(int index) {
		return getAt(getParentIndex(index));
	}

	private T getLeft(int index) {
		return getAt(getLeftIndex(index));
	}

	private T getRight(int index) {
		return getAt(getRightIndex(index));
	}

	private boolean hasLeft(int index) {
		return getLeft(index) != null;
	}

	private boolean hasRight(int index) {
		return getRight(index) != null;
	}

	private boolean hasParent(int index) {
		return getParent(index) != null;
	}

	private boolean isIndexValid(int index) {
		return (index < 0 || index < count);
	}

	private int getParentIndex(int index) {
		return (index - 1) / 2;
	}

	private int getLeftIndex(int index) {
		return (2 * index) + 1;
	}

	private int getRightIndex(int index) {
		return (2 * index) + 2;
	}

	@SuppressWarnings("unchecked")
	private T getAt(int index) {
		if (isIndexValid(index)) {
			return (T) elements[index];
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private int compare(Object elA, Object elB) {
		if (elA == null || elB == null) {
			return 0;
		}
		if (comparator != null) {
			return comparator.compare((T) elA, (T) elB);
		} else if (elA instanceof Comparable) {
			return ((Comparable<Object>) elA).compareTo(elB);
		}
		throw new IllegalArgumentException(
				"The elements don't implement Comparable and a comparator class wasn't provided");
	}
}
