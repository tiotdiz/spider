package com.qps.study.regex;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qps.utils.RegExUtil;
import com.qps.utils.WebUtil;

public class RegExStudy {

	public static void indexTest() {
		String urlString = "http://roll.news.sina.com.cn/news/gnxw/gdxw1/index.shtml";
		String encoding = "gb2312";
		String ulRegex = "<ul class=\"list_009\">[\\s\\S]*?</ul>";
		String ulInput = WebUtil.urlGetString(urlString, encoding);
		String ulResult = RegExUtil.ulMatch(ulRegex, ulInput);
		String liRegex = "<li>[\\s\\S]*?</li>";
		List<String> listLi = RegExUtil.liMatch(liRegex, ulResult);
		for (String str : listLi) {
			String indexRegex = "<li><a href=\"([\\S]*?)\" target=\"_blank\">([\\s\\S]*?)</a><span>\\(([\\S]*?) ([\\S]*?)\\)</span></li>";
			String liUrl = RegExUtil.indexMatch(indexRegex, str, 1);
			String liTitle = RegExUtil.indexMatch(indexRegex, str, 2);
			String liDate = RegExUtil.indexMatch(indexRegex, str, 3);
			String liTime = RegExUtil.indexMatch(indexRegex, str, 4);
			System.out.println(liUrl + "\t" + liTitle + "\t" + liDate + "\t" + liTime);
		}
	}

	public static void liTest() {
		String urlString = "http://roll.news.sina.com.cn/news/gnxw/gdxw1/index.shtml";
		String encoding = "gb2312";
		String ulRegex = "<ul class=\"list_009\">[\\s\\S]*?</ul>";
		String ulInput = WebUtil.urlGetString(urlString, encoding);
		String ulResult = RegExUtil.ulMatch(ulRegex, ulInput);
		String liRegex = "<li>[\\s\\S]*?</li>";
		List<String> listLi = RegExUtil.liMatch(liRegex, ulResult);
		for (String str : listLi) {
			System.out.println(str);
		}
	}

	public static void ulTest() {
		String urlString = "http://roll.news.sina.com.cn/news/gnxw/gdxw1/index.shtml";
		String encoding = "gb2312";
		String ulRegex = "<ul class=\"list_009\">[\\s\\S]*?</ul>";
		String ulInput = WebUtil.urlGetString(urlString, encoding);
		String ulResult = RegExUtil.ulMatch(ulRegex, ulInput);
		System.out.println(ulResult);
	}

	public static void patternTest() {
		// [0-9]=\d {1,}=+
		String regex = "\\d{4}";
		Pattern p = Pattern.compile(regex);
		String input = "abcd1234efgh5678";
		Matcher m = p.matcher(input);
		while (m.find()) {
			String result = m.group();
			System.out.println(result);
		}
	}
	public static void main(String[] args) {
		patternTest();
	}

}
