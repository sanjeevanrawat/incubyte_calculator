package com.incubyte.calculator;

import static org.junit.Assert.*;
import org.junit.*;

public class CalculatorTest {

	@Test
	public void test(){
		assertEquals(0, Calculator.add(""));
	}
	@Test
	public void returnSingleNumberForSingleNumber(){
		assertEquals(1, Calculator.add("1"));
	}
	@Test
	public void returnSumForTwoNumbers(){
		assertEquals(3, Calculator.add("1,2"));
	}
	@Test
	public void returnSumForMultipleNumbers(){
		assertEquals(6, Calculator.add("1,2,3"));
	}
	@Test
	public void acceptNewLineAsValidDelimiter(){
		assertEquals(6, Calculator.add("1,2\n3"));
	}
	@Test
	public void acceptCustomDelimiterSyntax(){
		assertEquals(3, Calculator.add("//;\n1;2"));
	}
	@Test
	public void acceptRegExSpecialCharAsCustomDelimiter(){
		assertEquals(3, Calculator.add("//.\n1.2"));
	}
	@Test
	public void raiseExceptionOnNegativesNumbers(){
		try{
			Calculator.add("-1,2,3");
			fail("Exception expected");

		}catch(RuntimeException ex){
			assertEquals("negatives not allowed: -1" , ex.getMessage());
		}
	}

}
