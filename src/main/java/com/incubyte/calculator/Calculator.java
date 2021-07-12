package com.incubyte.calculator;
import ch.lambdaj.function.convert.Converter;
import java.util.regex.Matcher;
import  ch.lambdaj.*;
import java.util.regex.*;
import static org.hamcrest.Matchers.*;
import static ch.lambdaj.Lambda.*;
import java.util.*;

public class Calculator {
	static int count = 0;
	public static int add(String text) {
		count = count + 1;
		List<Integer> numbers =  parseNumbers(text) ;
		ensureAllNonNegative(numbers);
		return sum(numbers).intValue();
	}
	public static int GetCalledCount(){
		return count;
	}
	private static List<Integer> parseNumbers(String text){
		String[] tokens = tokenize(text);
		List<Integer> numbers =  convert(tokens, toInt());
		return numbers;
	}
	private static void ensureAllNonNegative(List<Integer> numbers) throws RuntimeException{
		List<Integer> negatives = filter(lessThan(0), numbers);
		if(negatives.size() > 0){
			throw new RuntimeException("negatives not allowed: " + join(negatives));
		}
	}
	private static String[] tokenize(String text){
		if(text.isEmpty()){
			return new String[0];
		}else if(text.startsWith("//")){
			return splitUsingCustomDelimiterSyntax((text));
		}else{
			return splitUsingNewLineAndCommas(text);
		}
	}
	private static String[] splitUsingNewLineAndCommas(String text) {
		String[] tokens = text.split(",|\n");
		return tokens;
	}
	private static String[] splitUsingCustomDelimiterSyntax(String text){
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
