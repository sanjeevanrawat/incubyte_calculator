package com.incubyte.calculator;

import ch.lambdaj.function.convert.Converter;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.lessThan;

public class Calculator {
	private int count;
	public Calculator(){
		this.count = 0;
	}
	public int getCount(){
		return count;
	}
	public int add(String text) {
		count = count + 1;
		List<Integer> numbers =  parseNumbersFromString(text) ;
		removeAllNegatives(numbers);
		ignoreAllNumberGreaterThan1000(numbers);
		return sum(numbers).intValue();
	}
	private void removeAllNegatives(List<Integer> numbers) throws RuntimeException{
		List<Integer> negatives = filter(lessThan(0), numbers);
		if(negatives.size() > 0){
			throw new RuntimeException("negatives not allowed: " + join(negatives));
		}
	}
	private List<Integer> parseNumbersFromString(String text){
		String[] tokens = getStringArray(text);
		List<Integer> numbers =  convert(tokens, toInt());
		return numbers;
	}
	private String[] getStringArray(String text){
		if(text.isEmpty()){
			return new String[0];
		}else if(text.startsWith("//[")){
			return splitUsingCustomDelimitersOfAnyLength((text));
		}else if(text.startsWith("//")){
			return splitUsingCustomDelimiters((text));
		}else{
			return splitUsingNewLineAndCommas(text);
		}
	}
	private String[] splitUsingCustomDelimitersOfAnyLength(String text){
		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m = p.matcher(text);
		String delimiter = "";
		while(m.find()) {
			delimiter += Pattern.quote(m.group(1)) + "|";
		}
		delimiter = delimiter.substring(0, delimiter.length() - 1);
		String[] arr = text.split(delimiter);
		List<String> list = new LinkedList<String>();
		for(int i = 0; i < arr.length; i++){
			if(arr[i].contains("n")){
				String[] temp = arr[i].split("n");
				arr[i] = temp[temp.length - 1];
			}
			if(arr[i].matches(".*\\d.*")){
				list.add(arr[i]);
			}
		}
		String[] numbers = list.toArray(new String[0]);
		return numbers;
	}
	private String[] splitUsingNewLineAndCommas(String text) {
		String[] tokens = text.split(",|\n");
		return tokens;
	}
	private String[] splitUsingCustomDelimiters(String text){
		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
		m.matches();
		String customDelimiter = m.group(1);
		String numbers = m.group(2);
		return numbers.split(Pattern.quote(customDelimiter));
	}
	private Converter<String, Integer> toInt(){
		return new Converter<String, Integer>(){
			public Integer convert(String from){
				return toInt(from);
			}
		};
	}
	private void ignoreAllNumberGreaterThan1000(List<Integer> numbers) throws RuntimeException{
		int i = 0;
		while(i < numbers.size()){
			if(numbers.get(i) > 1000){
				numbers.remove(i);
			}else{
				i++;
			}
		}
	}
	private int toInt(String text) throws NumberFormatException{
		return Integer.parseInt(text);
	}
}