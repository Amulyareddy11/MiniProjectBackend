package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MyTest {
    @Test
	public void testFindBig()
	{
		assertEquals(20,Calculator.findBig(10,20));
	}
    @Test
	public void testFindSmall()
	{
		assertEquals(10,Calculator.findSmall(10,20));
	}
}
