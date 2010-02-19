package org.todomap.geocoder.util;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * A non-threadsafe stack.
 * 
 * @author kocka
 * 
 * @param <E>
 *            element type
 */
public final class Stack<E> extends ArrayList<E> {
	private static final long serialVersionUID = 4168530197036196010L;

	/**
	 * Check if the stack is empty and throw an exception is it is.
	 */
	private void checkEmpty() {
		if (size() == 0) {
			throw new EmptyStackException();
		}
	}

	/**
	 * Return the top element.
	 * 
	 * @return top element.
	 */
	public E peek() {
		checkEmpty();
		return get(size() - 1);
	}

	/**
	 * Return and remove the top element.
	 * @return
	 */
	public E pop() {
		checkEmpty();
		return remove(size() - 1);
	}

	/**
	 * Push a new element into the stack.
	 * @param element
	 */
	public void push(final E element) {
		add(element);
	}
}
