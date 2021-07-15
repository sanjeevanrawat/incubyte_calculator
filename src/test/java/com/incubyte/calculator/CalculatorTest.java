package com.incubyte.calculator;

import static org.junit.Assert.*;
import org.junit.*;

public class CalculatorTest {
	private static Calculator calculator;
	@BeforeClass
	public static void init(){
		calculator = new Calculator();
	}
	@Test
	public void test(){
		assertEquals(0, calculator.add(""));
	}
	@Test
	public void returnSingleNumberForSingleNumber(){
		assertEquals(1, calculator.add("1"));
	}
	@Test
	public void returnSumForTwoNumbers(){
		assertEquals(3, calculator.add("1,2"));
	}
	@Test
	public void returnSumForMultipleNumbers(){
		assertEquals(6, calculator.add("1,2,3"));
	}
	@Test
	public void acceptNewLineAsValidDelimiter(){
		assertEquals(6, calculator.add("1,2\n3"));
	}
	@Test
	public void acceptCustomDelimiterSyntax(){
		assertEquals(3, calculator.add("//;\n1;2"));
	}
	@Test
	public void acceptRegExSpecialCharAsCustomDelimiter(){
		assertEquals(3, calculator.add("//.\n1.2"));
	}
	@Test
	public void raiseExceptionOnNegativesNumbers(){
		try{
			calculator.add("-1,2,3");
			fail("Exception expected");

		}catch(RuntimeException ex){
			assertEquals("negatives not allowed: -1" , ex.getMessage());
		}
	}
	@Test
	public void shouldIgnoreNumberGreaterThan1000(){
		assertEquals(2, calculator.add("2,10001,2000"));
	}
	@Test
	public void acceptCustomDelimiterOfAnyLength(){
		assertEquals(6, calculator.add("//[***]\\n1***2***3"));
	}
	@Test
	public void getCount(){
		assertEquals(10, calculator.getCount());
	}
}