package org.todomap.geocoder.util;

import java.util.EmptyStackException;

import junit.framework.Assert;

import org.junit.Test;

public class StackTest {
	@Test(expected=EmptyStackException.class)
	public void testPop_empty() {
		Stack<String> stack = new Stack<String>();
		stack.pop();
	}
	@Test(expected=EmptyStackException.class)
	public void testPeek_empty() {
		Stack<String> stack = new Stack<String>();
		stack.peek();
	}
	@Test
	public void testBehavior() {
		Stack<String> stack = new Stack<String>();
		Assert.assertTrue(stack.isEmpty());
		stack.add("1");
		Assert.assertEquals("1", stack.peek());
		stack.add("2");
		Assert.assertEquals("2", stack.peek());
		Assert.assertEquals("2", stack.pop());
		Assert.assertEquals("1", stack.peek());
		Assert.assertEquals("1", stack.pop());
		Assert.assertTrue(stack.isEmpty());
	}
}
