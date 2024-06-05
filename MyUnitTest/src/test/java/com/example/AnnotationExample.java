package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class AnnotationExample {

	@BeforeClass
	public static void uppu1() {
		System.out.println("Before all cases");

	}
	@Before
	public void uppu2() {
		System.out.println("Before each cases");

	}
	@After
	public void uppu3() {
		System.out.println("after each cases");

	}
	@AfterClass
	public static void uppu4() {
		System.out.println("aafter all cases");

	}
	
	@Ignore
	public void uppu5() {
		System.out.println("this will be ignored");

	}
	
	@Test
	public void testUppu()
	{
		String str1="Upasana";
		String str2="Upasana";
		assertEquals(str1,str2);
		int x=10;int y=20;
		assertTrue(y>x);
	}
	@Test
	public void testUppu1()
	{
		String str1="Upasana";
		String str2="Upasana";
		assertEquals(str1,str2);
		int x=10;int y=20;
		assertTrue(y>x);
	}

}
