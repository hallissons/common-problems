	package br.com.studies.algorithms.bit;

import org.junit.Test;

public class BitTest {

	@Test
	public void testNegateZero() {
		
		System.out.println(bitSwapRequired(1, 2));
		
		//int value = 50;

		// System.out.println(getBits(value));
		// value = setBit(value, 0);
		// System.out.println(value);
		// value = clearBit(value, 5);
		// value = clearBit(value, 4);
		// value = clearBit(value, 1);
		// System.out.println(value);
		//System.out.println(getBits(~0 << 30));
		// System.out.println(getBits(setBit(value, 2)));
	}

	int bitSwapRequired(int a, int b) {
		int count = 0;
		System.out.println("a: "+getBits(a));
		System.out.println("b: "+getBits(b));
		for (int c = a ^ b; c != 0; c = c >> 1) {
			System.out.println("c: "+getBits(c));
			count += c & 1;
		}
		return count;
	}

	private int clearBit(int value, int i) {
		return value & ~(1 << i);
	}

	private int setBit(int value, int i) {
		return (value | (1 << i));
	}

	private boolean getBit(int value, int i) {
		return (value & (1 << i)) != 0;
	}

	private String getBits(int value) {
		StringBuilder binary = new StringBuilder();
		for (int i = 0; i < 31; i++) {
			if (getBit(value, i)) {
				binary.insert(0, 1);
			} else {
				binary.insert(0, 0);
			}
		}
		return binary.toString();
	}
}
