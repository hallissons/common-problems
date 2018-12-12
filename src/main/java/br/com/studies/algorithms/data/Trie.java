package br.com.studies.algorithms.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {

	private final TrieNode head;

	public Trie() {
		head = new TrieNode();
	}

	public void addWord(String word) {
		head.addWord(word);
	}
	
	public String getWord(String phrase) {
		if (head == null) {
			return "";
		}
		return head.getWord(phrase);
	}

	public String[] getWords(String phrase) {
		if (head == null) {
			return new String[] {};
		}
		int count = 0;
		List<String> words = new ArrayList<>();
		while(count < phrase.length()){
			String word = head.getWord(phrase.substring(count));
			if(!"$".equals(word)){
				words.add(word);
			}
			count += word.length();
		}
		
		return words.toArray(new String[words.size()]);
	}

	static class TrieNode {
		private Map<Character, TrieNode> alpha = new HashMap<>();
		private boolean isWord;

		public void addWord(String word) {
			if (word == null || word.isEmpty()) {
				return;
			}
			Character first = word.charAt(0);
			TrieNode prefix = alpha.get(first);
			if (prefix == null) {
				prefix = new TrieNode();
				alpha.put(first, prefix);
			}
			if (word.length() > 1) {
				prefix.addWord(word.substring(1));
			} else {
				prefix.isWord = true;
			}
		}

		public String getWord(String phrase) {
			if (phrase == null) {
				return "";
			}
			Character first = phrase.charAt(0);
			TrieNode prefix = alpha.get(first);
			if(prefix == null){
				return "$";
			}
			if (prefix.isWord) {
				return String.valueOf(first);
			} else {
				return String.valueOf(first) + prefix.getWord(phrase.substring(1));
			}
		}
	}
}
