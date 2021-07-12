package com.incubyte.calculator;

import static org.junit.Assert.*;
import org.junit.*;

public class CalculatorTest {

	@Test
	public void test(){
		assertEquals(0, Calculator.add(""));
	}

}
