package br.com.studies.algorithms.data;

import org.junit.Assert;
import org.junit.Test;

import br.com.studies.algorithms.data.Trie;

public class TrieTest {

	@Test
	public void testSearchWord(){
		Trie t =new Trie();
		t.addWord("test");
		String word = t.getWord("test");
		Assert.assertNotNull(word);
	}
	
	@Test
	public void testSearchWords(){
		Trie t =new Trie();
		t.addWord("test");
		String[] words = t.getWords("test-test");
		Assert.assertEquals(2, words.length);
	}
}
