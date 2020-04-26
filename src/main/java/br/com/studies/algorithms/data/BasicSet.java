package br.com.studies.algorithms.data;

import java.util.HashMap;

public class BasicSet<V> {

	private static final Object PRESENT = new Object();
	
	private HashMap<V, Object> values;
	
	public BasicSet(){
		values = new HashMap<>();
	}
	
	public boolean isEmpty(){
		return values.isEmpty();
	}
	
	public int size(){
		return values.size();
	}
	
	public boolean add(V value){
		return values.put(value, PRESENT) == null;
	}
	
	public boolean remove(V value){
		return values.remove(value) == PRESENT;
	}
}
