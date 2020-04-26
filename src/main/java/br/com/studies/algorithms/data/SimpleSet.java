package br.com.studies.algorithms.data;

public class SimpleSet<E> {

	private static final int INITIAL_CAPACITY = 16;

	private int size;

	private final Node<E>[] elements;

	public SimpleSet() {
		this(INITIAL_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public SimpleSet(int initialCapacity) {
		elements = (Node<E>[]) new Node[initialCapacity];
	}

	public void add(E e) {
		int index = getIndex(e);
		Node<E> l = elements[index];
		if (l == null) {
			l = new Node<>(null, null, e);
		} else {
			Node<E> curr = l;
			while (curr.next != null && !curr.value.equals(e)) {
				curr = curr.next;
			}
			if (curr.value.equals(e)) {
				curr.value = e;
			} else {
				curr.next = new Node<>(null, curr, e);
			}
		}
		elements[index] = l;
		size++;
	}

	public boolean contains(E e) {
		int index = getIndex(e);
		Node<E> l = elements[index];
		if (l == null) {
			return false;
		}
		Node<E> curr = l;
		while (curr != null) {
			if (curr.value.equals(e)) {
				return true;
			}
			curr = curr.next;
		}
		return false;
	}

	public void remove(E e) {
		int index = getIndex(e);
		Node<E> l = elements[index];
		if (l == null) {
			return;
		}
		Node<E> curr = l;
		do {
			if (curr.value.equals(e)) {
				break;
			}
			curr = curr.next;
		} while (curr != null);
		
		if(curr != null){
			if(curr.prev == null && curr.next == null){
				elements[index] = null;
			} else if(curr.prev == null){
				curr.next.prev = null;
				elements[index] = curr.next;
			} else if(curr.next == null){ 
				curr.prev.next = null;
				curr.prev = null;
			} else {
				curr.next.prev = curr.prev;
				curr.prev.next = curr.next;
				curr.next = null;
				curr.prev = null;
			}
			size--;
		}
	}

	public int size() {
		return size;
	}

	private int getIndex(E e) {
		return e.hashCode() % elements.length;
	}

	private static class Node<E> {
		Node<E> next;
		Node<E> prev;
		E value;

		public Node(Node<E> next, Node<E> prev, E value) {
			this.next = next;
			this.prev = prev;
			this.value = value;
		}
	}
}
