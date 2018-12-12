package br.com.studies.algorithms.sort;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class MergeSortInversions {

	static final Partition EMPTY_PARTITION = new Partition(new int[] {}, 0);

	static class Partition {
		int[] arr;
		long inversions;

		Partition(int[] arr, long inversions) {
			this.arr = arr;
			this.inversions = inversions;
		}
	}

	// Complete the countInversions function below.
	static long countInversions(int[] arr) {
		// System.out.println("Original: "+ Arrays.toString(arr));
		return mergeSort(new Partition(arr, 0)).inversions;
	}

	static Partition mergeSort(Partition p) {
		if (p.arr.length <= 1) {
			return p;
		}
		int mid = (p.arr.length / 2) + (p.arr.length % 2);

		int[] leftArr = Arrays.copyOfRange(p.arr, 0, mid);
		int[] rightArr = Arrays.copyOfRange(p.arr, mid, p.arr.length);
		Partition left = mergeSort(new Partition(leftArr, 0));
		Partition right = mergeSort(new Partition(rightArr, 0));
		Partition mergeP = merge(left, right);
		mergeP.inversions += left.inversions + right.inversions;
		return mergeP;
	}

	static Partition merge(Partition left, Partition right) {
		System.out.println("Left: " + Arrays.toString(left.arr));
		System.out.println("Right: " + Arrays.toString(right.arr));
		int[] tmp = new int[left.arr.length + right.arr.length];
		Partition result = new Partition(tmp, 0);

		int riIdx = 0;
		int leIdx = 0;
		long inversions = 0;
		int idx = 0;

		while (leIdx < left.arr.length && riIdx < right.arr.length) {
			if (left.arr[leIdx] <= right.arr[riIdx]) {
				tmp[idx] = left.arr[leIdx];
				leIdx++;
			} else {
				tmp[idx] = right.arr[riIdx];
				riIdx++;
				inversions += left.arr.length - leIdx;
			}
			idx++;
		}

		System.out.println("Left index: " + leIdx + " Left length: " + left.arr.length + " Idx: " + idx);
		if (leIdx < left.arr.length) {
			for (int i = leIdx; i < left.arr.length; i++) {
				tmp[idx] = left.arr[i];
				idx++;
			}
		}

		if (riIdx < right.arr.length) {
			for (int i = riIdx; i < right.arr.length; i++) {
				tmp[idx] = right.arr[i];
				idx++;
			}
		}

		result.inversions = inversions;
		result.arr = tmp;
		System.out.println(Arrays.toString(tmp));
		return result;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		// BufferedWriter bufferedWriter = new BufferedWriter(new
		// FileWriter(System.getenv("OUTPUT_PATH")));

		PrintStream bufferedWriter = System.out;
		int t = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int tItr = 0; tItr < t; tItr++) {
			int n = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			int[] arr = new int[n];

			String[] arrItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int i = 0; i < n; i++) {
				int arrItem = Integer.parseInt(arrItems[i]);
				arr[i] = arrItem;
			}

			long result = countInversions(arr);

			bufferedWriter.print(String.valueOf(result));
			bufferedWriter.println();
		}

		bufferedWriter.close();

		scanner.close();
	}
}
