package br.com.studies.algorithms.base;

import java.util.HashSet;
import java.util.Set;

public class Solution {

	public static void main(String[] args) {
		String input = "heeeeeello world!!!";
		String ordering = "he!";
		System.out.println(checkOrdering(input, ordering));
	}

	public static boolean checkOrdering(String input, String ordering) {
		Set<Character> cache = new HashSet<>();
		
		for (int i = 0; i < ordering.length(); i++) {
			cache.add(ordering.charAt(i));	
		}
		int orderIndex = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ordering.charAt(orderIndex)) {
				continue;
			}
			if (orderIndex < ordering.length() - 1 && input.charAt(i) == ordering.charAt(orderIndex + 1)) {
				orderIndex++;
				continue;
			}
			if(cache.contains(input.charAt(i))){
				return false;
			}
		}
		return true;
	}
}
