package com.incubyte.calculator;

import static org.junit.Assert.*;
import org.junit.*;

public class CalculatorTest {

	@Test
	public void test(){
		assertEquals(0, Calculator.add(""));
	}
	@Test
	public void shouldReturnNumberOnNumber(){
		assertEquals(1, Calculator.add("1"));
	}
	@Test
	public void shouldReturnSumOnTwoNumbersDelimitedByComma(){
		assertEquals(3, Calculator.add("1,2"));
	}
}
