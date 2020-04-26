package br.com.studies.algorithms.data;

public interface SimpleMap<K, V> {
	void insert(K key, V value);

	V search(K key);

	boolean remove(K key);
	
	int size();
}