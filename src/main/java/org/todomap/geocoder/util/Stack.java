package org.todomap.geocoder.util;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * A non-threadsafe stack.
 * 
 * @author kocka
 *
 * @param <E>	element type
 */
public class Stack<E> extends ArrayList<E> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4168530197036196010L;
	public void push(E element) {
		
	}
	public E pop() {
		checkEmpty();
		return remove(size() - 1);
	}
	private void checkEmpty() {
		if(size() == 0) {
			throw new EmptyStackException();
		}
	}
	public E peek() {
		checkEmpty();
		return get(size() - 1);
	}
}
