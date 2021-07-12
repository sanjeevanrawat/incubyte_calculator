package com.incubyte.calculator;

import java.util.*;
import ch.lambdaj.function.convert.Converter;
import ch.lambdaj.*;
import static org.hamcrest.Matchers.*;
import static ch.lambdaj.Lambda.*;
import java.util.regex.*;
public class Calculator {
	public static int add(String text) {
		List<Integer> numbers =  parseNumbersFromStringArray(text) ;
		removeAllNegatives(numbers);
		return sum(numbers).intValue();
	}
	private static void removeAllNegatives(List<Integer> numbers) throws RuntimeException{
		List<Integer> negatives = filter(lessThan(0), numbers);
		if(negatives.size() > 0){
			throw new RuntimeException("negatives not allowed: " + join(negatives));
		}
	}
	private static List<Integer> parseNumbersFromStringArray(String text){
		String[] tokens = getStringArray(text);
		List<Integer> numbers =  convert(tokens, toInt());
		return numbers;
	}
	private static String[] getStringArray(String text){
		if(text.isEmpty()){
			return new String[0];
		}else if(text.startsWith("//")){
			return splitUsingCustomDelimiters((text));
		}else{
			return splitUsingNewLineAndCommas(text);
		}
	}
	private static String[] splitUsingNewLineAndCommas(String text) {
		String[] tokens = text.split(",|\n");
		return tokens;
	}
	private static String[] splitUsingCustomDelimiters(String text){
		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
		m.matches();
		String customDelimiter = m.group(1);
		String numbers = m.group(2);
		return numbers.split(Pattern.quote(customDelimiter));
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
