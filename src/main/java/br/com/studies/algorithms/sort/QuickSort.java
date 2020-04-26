package br.com.studies.algorithms.sort;

public final class QuickSort {

	private QuickSort() {
	}

	public static void sort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	public static void sort(int[] arr, int l, int r) {
		int index = partition(arr, l, r);
		if (l < index - 1) {
			sort(arr, l, index - 1);
		}
		if (index < r) {
			sort(arr, index, r);
		}
	}

	private static int partition(int[] arr, int l, int r) {
		int p = arr[(l + r) / 2];
		while (l <= r) {
			while (arr[l] < p)
				l++;
			while (arr[r] > p)
				r--;

			if (l <= r) {
				swap(l, r, arr);
				l++;
				r--;
			}
		}

		return l;
	}

	private static void swap(int i, int j, int[] arr) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
