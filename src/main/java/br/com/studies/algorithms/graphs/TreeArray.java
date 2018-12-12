package br.com.studies.algorithms.graphs;

public class TreeArray {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
		TreeNode root = fromArray(arr, 0, arr.length - 1);
		System.out.println(root.inOrder());
	}

	public static class TreeNode {
		public TreeNode(int data) {
			this.data = data;
		}

		int data;
		TreeNode left;
		TreeNode right;

		public String inOrder() {
			return inOrder(this);
		}

		private String inOrder(TreeNode root) {
			if (root == null) {
				return "";
			}
			return String.format("%s %s%s", inOrder(root.left), Integer.toString(root.data), inOrder(root.right));
		}
	}

	public static TreeNode fromArray(int[] arr, int start, int end) {
		if (end < start) {
			return null;
		}
		int mid = (start + end) / 2;
		TreeNode node = new TreeNode(arr[mid]);
		node.left = fromArray(arr, start, mid - 1);
		node.right = fromArray(arr, mid + 1, end);
		return node;
	}
}
