package com.qps.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtil {
	public static String ulMatch(String regex,String input){
		String result = null;
		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		while(m.find()){
			result = m.group();
			sb.append(result);
		}
		return sb.toString();
	}
	
	public static List<String> liMatch(String regex,String input){
		String result = null;
		List<String> list = new ArrayList<String>();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		while(m.find()){
			result = m.group();
			list.add(result);
		}
		return list;
	}
	
	public static String indexMatch(String regex,String input,int grpNum){
		String result = null;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		while(m.find()){
			result = m.group(grpNum);
		}
		return result;
	}
}
