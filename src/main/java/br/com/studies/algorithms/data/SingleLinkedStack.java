package br.com.studies.algorithms.data;

public class SingleLinkedStack<T> {
	private int size = 0;
	private StackNode<T> head;

	public void push(T data) {
		StackNode<T> node = new StackNode<>(data, head);
		this.head = node;
		size++;
	}

	public T pop() {
		if (head == null) {
			return null;
		}
		T data = head.data;
		head = head.next;
		size--;
		return data;
	}

	public int getSize() {
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}

	static class StackNode<T> {
		private final T data;
		private final StackNode<T> next;

		public StackNode(T data, StackNode<T> next) {
			this.data = data;
			this.next = next;
		}

		@Override
		public String toString() {
			return String.valueOf(data);
		}
	}
}
