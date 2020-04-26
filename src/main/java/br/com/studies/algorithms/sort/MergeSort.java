package br.com.studies.algorithms.sort;

public final class MergeSort {

	private MergeSort() {
	}

	public static void sort(int[] arr) {
		int[] aux = new int[arr.length];
		sort(arr, aux, 0, arr.length - 1);
	}

	private static void sort(int[] arr, int[] aux, int l, int r) {
		if (l >= r) {
			return;
		}
		int m = (l + r) / 2;
		sort(arr, aux, l, m);
		sort(arr, aux, m + 1, r);
		merge(arr, aux, l, m, r);
	}

	private static void merge(int[] arr, int[] aux, int l, int m, int r) {
		for (int i = l; i <= r; i++) {
			aux[i] = arr[i];
		}

		int lp = l;
		int rp = m + 1;
		int curr = l;

		while (lp <= m && rp <= r) {
			if (aux[lp] <= aux[rp]) {
				arr[curr] = aux[lp];
				lp++;
			} else {
				arr[curr] = aux[rp];
				rp++;
			}
			curr++;
		}

		int rem = m - lp;
		for (int i = 0; i <= rem; i++) {
			arr[curr + i] = aux[lp + i];
		}
	}
}
