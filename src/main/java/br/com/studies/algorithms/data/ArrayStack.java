package br.com.studies.algorithms.data;

public class ArrayStack extends DynamicArray {
		
	public int push(int i){
		add(i);
		return i;
	}
	
	public int pop(){
		int index = getSize() -1;
		int el = getAt(index);
		removeAt(index);
		return el;
	}

	public int peek(){
		int index = getSize() -1;
		return getAt(index);
	}	
}
