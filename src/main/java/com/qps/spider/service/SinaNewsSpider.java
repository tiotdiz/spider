package com.qps.spider.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import com.qps.utils.CloseUtil;
import com.qps.utils.RegExUtil;
import com.qps.utils.WebUtil;

public class SinaNewsSpider {
	public static void crawler() {
		String urlString = "http://roll.news.sina.com.cn/news/gnxw/gdxw1/index.shtml";
		String encoding = "gb2312";
		String ulRegex = "<ul class=\"list_009\">[\\s\\S]*?</ul>";
		String ulInput = WebUtil.urlGetString(urlString, encoding);
		String ulResult = RegExUtil.ulMatch(ulRegex, ulInput);
		String liRegex = "<li>[\\s\\S]*?</li>";
		List<String> listLi = RegExUtil.liMatch(liRegex, ulResult);
		int count = 0;
		for (String str : listLi) {
			if (count > 3) {
				break;
			}
			String indexRegex = "<li><a href=\"([\\S]*?)\" target=\"_blank\">([\\s\\S]*?)</a><span>\\(([\\S]*?) ([\\S]*?)\\)</span></li>";
			String liUrl = RegExUtil.indexMatch(indexRegex, str, 1);
			String liTitle = RegExUtil.indexMatch(indexRegex, str, 2);
			String liDate = RegExUtil.indexMatch(indexRegex, str, 3);
			String liTime = RegExUtil.indexMatch(indexRegex, str, 4);
			System.out.println(liUrl + "\t" + liTitle + "\t" + liDate + "\t"
					+ liTime);

			String dataPath = "E:" + File.separator + "spider_data.dat";
			String indexPath = "E:" + File.separator + "spider_index.txt";
			byte[] b = SinaNewsSpider.detailCrawler(liUrl);
			File dataFile = new File(dataPath);
			long offset = dataFile == null ? 0 : dataFile.length();
			int byteNum = b.length;
			StringBuffer sb = new StringBuffer();
			char splitChar = '\u0001';
			sb.append(liUrl).append(splitChar).append(liTitle)
					.append(splitChar).append(liDate).append(splitChar)
					.append(liTime).append(splitChar).append(offset)
					.append(splitChar).append(byteNum);
			SinaNewsSpider.writeDataFile(b, dataPath);
			writeIndexFile(sb.toString(), indexPath);
			
			count ++;
		}
	}

	public static byte[] detailCrawler(String urlString) {
		// String urlString =
		// "String ulInput = WebUtil.urlGetString(urlString, encoding);";
		// String encoding = "utf-8";
		return WebUtil.urlGetByteArray(urlString);
	}

	public static void writeDataFile(byte[] byteArray, String dataPath) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(dataPath, true);
			os.write(byteArray);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(os);
		}
	}

	private static void writeIndexFile(String index, String indexPath) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream(indexPath, true));
			pw.println(index);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pw);
		}
	}

}
