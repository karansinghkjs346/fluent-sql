package com.ivanceras.fluent;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ivanceras.fluent.SQL.Breakdown;

public class TestSQLBuilderSelect {

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
		String expected = "SELECT * FROM products WHERE price IS NOT NULL";
		String actual = new SQL().SELECT_ALL().FROM("products").WHERE("price").IS_NOT_NULL().build().sql;
		CTest.cassertEquals(expected, actual);
	}
	
	@Test
	public void test2(){
		String expected = "SELECT name FROM products WHERE price >= ? ";
		Breakdown actual2 = new SQL().SELECT("name").FROM("products").WHERE("price").GREATER_THAN_OR_EQUAL("10").build();
		CTest.cassertEquals(expected, actual2.sql);
		assertArrayEquals(new Object[]{"10"}, actual2.parameters);
		
	}
	@Test
	public void test4(){
		String expected = "SELECT name FROM products WHERE price > ? LIMIT 10 OFFSET 1";
		String actual2 = new SQL().SELECT("name").FROM("products").WHERE("price").GREATER_THAN("10").LIMIT(10).OFFSET(1).build().sql;
		CTest.cassertEquals(expected, actual2);
		
	}
	@Test
	public void test3(){
		String expected = "SELECT name FROM products WHERE price < ? ";
		String actual2 = new SQL().SELECT("name").FROM("products").WHERE("price").LESS_THAN("10").build().sql;
		CTest.cassertEquals(expected, actual2);
		
	}
	@Test
	public void test5(){
		String expected = "SELECT name FROM products LEFT JOIN item USING item_id, name WHERE price > ? LIMIT 10 OFFSET 1";
		String actual2 = new SQL().SELECT("name").FROM("products").LEFT_JOIN("item").USING("item_id", "name").WHERE("price").GREATER_THAN("10").LIMIT(10).OFFSET(1).build().sql;
		CTest.cassertEquals(expected, actual2);
		
	}
	@Test
	public void test6(){
		String expected = "SELECT name" +
							" FROM products" +
							" LEFT JOIN item" +
							" ON item_id = product_id" +
							" AND products.name =  item.name" +
							" WHERE price > ?" +
							" LIMIT 10" +
							" OFFSET 1";
		String actual2 = new SQL()
							.SELECT("name")
							.FROM("products")
							.LEFT_JOIN("item")
							.ON("item_id").EQUAL_TO("product_id")
							.AND("products.name", "item.name")
							.WHERE("price").GREATER_THAN("10")
							.LIMIT(10)
							.OFFSET(1)
							.build()
							.sql;
		
		CTest.cassertEquals(expected, actual2);
		
	}


}
