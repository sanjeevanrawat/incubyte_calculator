package com.incubyte.calculator;

import java.util.*;
import ch.lambdaj.function.convert.Converter;
import ch.lambdaj.*;
import static ch.lambdaj.Lambda.*;
public class Calculator {
	public static int add(String text) {
		List<Integer> numbers =  parseNumbers(text) ;
		return sum(numbers).intValue();
	}
	private static List<Integer> parseNumbers(String text){
		String[] tokens = tokenize(text);
		List<Integer> numbers =  convert(tokens, toInt());
		return numbers;
	}
	private static String[] tokenize(String text){
		if(text.isEmpty()){
			return new String[0];
		}else{
			String[] tokens = text.split(",");
			return tokens;
		}
	}
	private static Converter<String, Integer> toInt(){
		return new Converter<String, Integer>(){
			public Integer convert(String from){
				return toInt(from);
			}
		};
	}
	private static int toInt(String text) throws NumberFormatException{
		return Integer.parseInt(text);
	}
}
