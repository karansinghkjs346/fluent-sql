package com.ivanceras.fluent;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSQLBuilderDelete {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String expected = "DELETE FROM products WHERE price IS NOT NULL";
		String actual = new SQL().DELETE().FROM("products").WHERE("price").IS_NOT_NULL().build().sql;
		CTest.cassertEquals(expected, actual);
	}
	@Test
	public void test2(){
		String expected = "DELETE FROM products WHERE price = ? ";
		String actual2 = new SQL().DELETE().FROM("products").WHERE("price").EQUAL_TO("10").build().sql;
		CTest.cassertEquals(expected, actual2);
		
	}

}
