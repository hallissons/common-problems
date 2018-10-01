package br.com.studies.algorithms.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingleLinkedListWord {
	private static final Logger LOGGER = LoggerFactory.getLogger(SingleLinkedListWord.class);

	private Node head;
	private int size = 0;

	static class Node {
		Node next;
		char data;

		public Node(char data) {
			this.data = data;
		}

		public String toString() {
			return String.valueOf(data);
		}
	}

	public Node add(char value) {
		Node n = new Node(value);
		if (head == null) {
			head = n;
			size++;
			return n;
		}

		Node current = head;
		while (current.next != null) {
			current = current.next;
		}
		current.next = n;
		size++;
		return n;
	}

	public Node remove(Node n) {
		if (n == null) {
			return null;
		}
		return remove(n.data);
	}

	public Node remove(char value) {
		if (head == null || value == '\0') {
			return null;
		}

		Node current = head;
		if (value == current.data) {
			head = head.next;
			size--;
			return current;
		}
		while (current.next != null) {
			if (value == current.next.data) {
				current.next = current.next.next;
				size--;
				return current;
			}
			current = current.next;
		}
		return null;
	}

	public void removeDuplicates() {
		if (head == null) {
			return;
		}

		Map<Character, Node> cache = new HashMap<>();
		cache.put(head.data, head);
		Node current = head;
		while (current.next != null) {
			if (cache.containsKey(current.next.data)) {
				current.next = current.next.next;
				size--;
				continue;
			}
			cache.put(current.next.data, current.next);
			current = current.next;
		}
	}

	public Node kthToLast(int kth) {
		if (head == null) {
			return null;
		}

		Node current = head;
		Node runner = head;

		for (int i = 1; i <= kth; i++) {
			if (runner != null) {
				runner = runner.next;
			} else {
				return null;
			}
		}

		while (runner != null) {
			runner = runner.next;
			if (runner != null) {
				current = current.next;
			}
		}

		return current;
	}

	public static int sumInverse(SingleLinkedListWord la, SingleLinkedListWord lb) {
		return sumInverse(la.head, lb.head, 0, 0);
	}

	private static int sumInverse(Node na, Node nb, int carryOver, int factor) {
		int pow = (int) Math.pow(10, factor);
		if (na == null && nb == null) {
			return carryOver * pow;
		}

		int a = na == null ? 0 : na.data;
		int b = nb == null ? 0 : nb.data;
		int sum = (a + b + carryOver);
		Node nextA = na == null || na.next == null ? null : na.next;
		Node nextB = nb == null || nb.next == null ? null : nb.next;

		return ((sum % 10) * pow) + sumInverse(nextA, nextB, (sum / 10), ++factor);
	}

	public static int sum(SingleLinkedListWord la, SingleLinkedListWord lb) {
		if (la == null || lb == null) {
			return 0;
		}

		StringBuilder cache = new StringBuilder();
		int sum = 0;

		Node current = la.head;
		while (current != null) {
			cache.append(current.data);
			current = current.next;
		}

		sum = Integer.valueOf(cache.toString());
		cache.delete(0, cache.length());
		current = lb.head;
		while (current != null) {
			cache.append(current.data);
			current = current.next;
		}
		sum += Integer.valueOf(cache.toString());
		return sum;
	}

	public void splitAroundPivot(char pivot) {
		Map<Character, Node> afterPivot = new HashMap<>();
		Node current = head;
		Node prev = null;
		while (current != null) {
			if (current.data >= pivot) {
				afterPivot.put(current.data, current);
				if (current == head) {
					head = current.next;
				} else {
					prev.next = current.next;
				}
				size--;
			} else {
				prev = current;
			}
			current = current.next;
		}
		for (Entry<Character, Node> entry : afterPivot.entrySet()) {
			current = entry.getValue();
			if (head == null) {
				head = current;
			}
			current.next = null;
			if (prev != null) {
				prev.next = current;
			}
			prev = current;
			size++;
		}
		LOGGER.info(toString());
	}

	public int[] toArray() {
		if (head == null) {
			return new int[0];
		}
		int[] elements = new int[size];
		Node current = head;
		int i = 0;
		while (current != null) {
			elements[i++] = current.data;
			current = current.next;
		}
		return elements;
	}

	public boolean isPalindrome() {
		Node curr = head;
		char[] word = new char[getSize()];
		int idx = 0;

		while (curr != null) {
			word[idx++] = curr.data;
			curr = curr.next;
		}

		for (int i = 0; i < word.length / 2; i++) {
			if (word[i] != word[(word.length - 1) - i]) {
				return false;
			}
		}
		return true;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public String toString() {
		return Arrays.toString(toArray());
	}
}
