/**
 * @author dianarose
 *
 */
package com.data.structures.implementation;

public class DynamicArray<T> {
	
	private T[] array;
	private int length = 0;
	private int capacity = 0;
	private static final int DEFAULT_CAPACITY = 10;
	
	public DynamicArray() {
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public DynamicArray(int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("");
		
		this.capacity = capacity;
		array = (T[]) new Object[capacity];
	}
	
	/**
	 * Returns the current size of the array.
	 * @return
	 */
	public int size() {
		return length;
	}
	
	/**
	 * Checks if the array is empty;
	 * @return
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Returns the element at specified location in the array.
	 * @param index
	 * @return
	 */
	public T get(int index) {
		if (index < 0 || index > length)
			throw new IndexOutOfBoundsException("");
		
		return array[index];
	}
	
	/**
	 * Sets the element at specified location in the array.
	 * @param index
	 * @param element
	 */
	public void set(int index, T element) {
		if (index < 0 || index > length)
			throw new IndexOutOfBoundsException("");
		
		this.array[index] = element;
	}
	
	/**
	 * Adds the element at the end of the array.
	 * @param element
	 */
	@SuppressWarnings("unchecked")
	public void add(T element) {
		//check if static array is full
		if (length+1 > capacity) {
			//if static array is full, create a new static array double the size of the current one
			capacity = capacity == 0 ? 1 : (capacity * 2);
			T[] newArray = (T[]) new Object[capacity];

			//copy contents of the current array, if not empty, to the new array
			if (!isEmpty())
				for (int i=0; i < length; i++)
					newArray[i] = array[i];
				
			//set static array to be the new array
			array = newArray;
		}
		
		//add the element to the end of the array
		array[length] = element;
		//increment length
		length++;
	}
	
	/**
	 * Adds all given elements to the end of the array.
	 * @param elements
	 */
	public void addAll(T[] elements) {
		for (T element : elements) {
			add(element);
		}
	}
	
	/**
	 * Inserts the element at the specified location in the array. 
	 * Current element in the location and its subsequent elements are shifted to the right.
	 * @param index
	 * @param element
	 */
	public void add(int index, T element) {
		//check if index is within bounds
		if (index < 0 || index > length)
			throw new IndexOutOfBoundsException("");
		
		//if array is full, double the capacity
		if (length+1 > capacity) {
			capacity = capacity == 0 ? 1 : (capacity * 2);
		}
		
		//create new array
		T[] newArray = (T[]) new Object[capacity];
		
		//copy contents of the current array, if not empty, to the new array
		if (!isEmpty()) {
			for (int i=0, ni=0; i < length; i++, ni++) {
				//if in specified index, insert the new element
				if (i == index) {
					newArray[ni] = element; 
					ni++;
				}
				newArray[ni] = array[i];
			}
		}
			
		//set static array to be the new array
		array = newArray;
		//increment length
		length++;
	}
	
	/**
	 * Returns the location in the array of the first occurrence of the given element.
	 * @param element
	 * @return
	 */
	public int indexOf(T element) {
		for (int i=0; i < length; i++)
			if (array[i].equals(element))
				return i;
		return -1;
	}
	
	/**
	 * Returns true if the element is present in the array. Otherwise, returns false.
	 * @param element
	 * @return
	 */
	public boolean contains(T element) {
		return indexOf(element) > -1;
	}
	
	/**
	 * Removes the first occurrence of the given element from the array.
	 * @param element
	 */
	public boolean remove(T element) {
		int index = indexOf(element);
		if (index < 0)
			return false;
		
		removeAt(index);
		return true;
	}
	
	/**
	 * Removes the element at the specified location in the array. 
	 * Subsequent elements are are shifted to the left.
	 * @param index
	 * @return
	 */
	public T removeAt(int index) {
		//check if index is within bounds
		if (index < 0 || index > length)
			throw new IndexOutOfBoundsException("");
		
		T element = array[index];
		length--;
		for (int i = index; i < length; i++)
			array[i] = array[i+1];
		array[length] = null;
		return element;
	}
	
	/**
	 * Removes all contents of the array.
	 */
	public void clear() {
		for (int i=0; i < length; i++)
			array[i] = null;
		
		length = 0;
	}
	
	/**
	 * Returns a String representation of the array contents.
	 */
	public String toString() {
		if (length == 0)
			return "[]";
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i=0; i < length-1; i++)
			sb.append(array[i]).append(", ");
		sb.append(array[length-1]).append("]");
		return sb.toString();
	}

}