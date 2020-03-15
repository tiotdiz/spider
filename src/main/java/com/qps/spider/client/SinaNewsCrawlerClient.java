package com.qps.spider.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;

import com.qps.utils.CloseUtil;
import com.qps.utils.IOUtil;

public class SinaNewsCrawlerClient {
	public static void main(String[] args) {
		test();
	}
	
	public static void test(){
		String urlStr = "http://news.sina.com.cn/o/2018-11-06/doc-ihmutuea7351575.shtml";
		
		String liDate = "";
		String baseDir = "E:" + File.separator + "spider_db" + liDate + File.separator;
		
		String dataPath = baseDir  +"spider_data.dat";
		String indexPath = baseDir + "spider_index.txt";
		String encoding = "utf-8";
		search(urlStr, indexPath, dataPath, encoding);
	}
	
	public static void search(String urlStr, String indexPath, String dataPath, String encoding){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(indexPath));
			String line = null;
			while((line = br.readLine()) != null){
				String[] strArr = line.split('\u0001' + "");
				if(strArr[0].equals(urlStr)){
					long offset = Long.valueOf(strArr[4]);
					int size = Integer.valueOf(strArr[5]);
					System.out.println(IOUtil.readDataFile(offset, size, dataPath, encoding));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
