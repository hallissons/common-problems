package br.com.studies.algorithms.data;

public class SimpleMapImpl<K, V> implements SimpleMap<K, V> {

	private MapEntry<K, V>[] data;
	private int size;

	@SuppressWarnings("unchecked")
	public SimpleMapImpl(int initialCap) {
		data = (MapEntry<K, V>[]) new MapEntry[initialCap];
	}

	@Override
	public void insert(K key, V value) {
		int index = getIndex(key);
		MapEntry<K, V> bucket = data[index];
		if (bucket == null) {
			bucket = new MapEntry<>(key, value);
			data[index] = bucket;
			return;
		}
		if (bucket.key.equals(key)) {
			bucket.value = value;
		} else {
			boolean updated = false;
			while (bucket.next != null) {
				if (bucket.next.key.equals(key)) {
					bucket.next.value = value;
					updated = true;
					break;
				}
				bucket = bucket.next;
			}
			if (!updated) {
				bucket.next = new MapEntry<>(key, value);
			}
		}
		size++;
	}

	@Override
	public V search(K key) {
		int index = getIndex(key);
		MapEntry<K, V> bucket = data[index];
		if (bucket == null) {
			return null;
		}
		MapEntry<K, V> curr = bucket;
		while (curr != null) {
			if (curr.key.equals(key)) {
				return curr.value;
			}
			curr = curr.next;
		}
		return null;
	}

	@Override
	public boolean remove(K key) {
		int index = getIndex(key);
		MapEntry<K, V> bucket = data[index];
		if (bucket == null) {
			return false;
		}
		if (bucket.key.equals(key)) {
			data[index] = bucket.next;
			size--;
			return true;
		}

		MapEntry<K, V> curr = bucket;
		while (curr.next != null) {
			if (curr.next.key.equals(key)) {
				curr.next = curr.next.next;
				size--;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int size() {
		return size;
	}

	private int getIndex(K key) {
		return key.hashCode() % data.length;
	}


	static class MapEntry<K, V> {
		K key;
		V value;
		MapEntry<K, V> next;

		public MapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}
