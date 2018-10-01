package br.com.studies.algorithms.data;

public class Tree<T extends Comparable<T>> {

	private TreeNode<T> head;
	private int count;

	static class TreeNode<T extends Comparable<T>> {
		private T data;
		private TreeNode<T> parent;
		private TreeNode<T> left;
		private TreeNode<T> right;

		public TreeNode(T data) {
			this.data = data;
		}

		public String toString() {
			return String.format("Data: %s", data);
		}
	}

	public static <T extends Comparable<T>> Tree<T> parseTree(T[] arr) {
		Tree<T> t = new Tree<>();
		t.head = fromArray(arr, 0, arr.length - 1, null);
		return t;
	}

	private static <T extends Comparable<T>> TreeNode<T> fromArray(T[] arr, int start, int end, TreeNode<T> head) {
		if (start > end) {
			return null;
		}
		int mid = (start + end) / 2;
		T data = arr[mid];
		TreeNode<T> node = new TreeNode<T>(data);
		node.parent = head;
		node.left = fromArray(arr, start, mid - 1, node);
		node.right = fromArray(arr, mid + 1, end, node);
		return node;
	}

	public void insert(T data) {
		TreeNode<T> node = new TreeNode<>(data);
		if (head == null) {
			this.head = node;
			return;
		}
		TreeNode<T> next = head;
		while (node.parent == null) {
			if (data.compareTo(next.data) > 0) {
				if (next.right == null) {
					node.parent = next;
					next.right = node;
				}
				next = next.right;
			} else {
				if (next.left == null) {
					node.parent = next;
					next.left = node;
				}
				next = next.left;
			}
		}
		count++;
	}

	public boolean hasKey(T key) {
		if (head == null || key == null) {
			return false;
		}

		TreeNode<T> next = head;
		while (next != null) {
			if (key.equals(next.data)) {
				return true;
			}
			if (key.compareTo(next.data) > 0) {
				next = next.right;
			} else {
				next = next.left;
			}
		}
		return false;
	}

	public static <T extends Comparable<T>> boolean isBST(Tree<T> t) {
		if (t.head == null) {
			return false;
		}
		return isBST(t.head, t.head.data);
	}

	private static <T extends Comparable<T>> boolean isBST(TreeNode<T> head, T min) {
		if (head == null) {
			return true;
		}
		boolean isBSTRight = head.right == null || (head.right.data.compareTo(min) > 0
				&& head.right.data.compareTo(head.data) > 0 && isBST(head.right, min));
		boolean isBSTLeft = head.left == null || (head.left.data.compareTo(min) <= 0
				&& head.left.data.compareTo(head.data) <= 0 && isBST(head.left, min));

		return isBSTRight && isBSTLeft;
	}

	public boolean remove(T data) {
		if (head == null) {
			return false;
		}
		// TODO Implement remove logic after finding the node
		return true;
	}

	public String toString() {
		TreeNode<T> next = head;
		StringBuilder result = new StringBuilder();
		toStringInOrder(next, result);
		return String.format("{ %s }", result.toString());
	}

	private void toStringInOrder(TreeNode<T> next, StringBuilder result) {
		if (next == null) {
			return;
		}
		toStringInOrder(next.left, result);
		if (result.length() > 0) {
			result.append(", ");
		}
		result.append(String.valueOf(next.data));
		toStringInOrder(next.right, result);
	}

}
