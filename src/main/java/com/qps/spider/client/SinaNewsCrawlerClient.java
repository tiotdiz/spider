package com.qps.spider.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;

import com.qps.utils.CloseUtil;

public class SinaNewsCrawlerClient {
	
	public static void search(String indexPath, String dataPath, String encoding){
		String urlStr = "http://news.sina.com.cn/c/2018-10-16/doc-ihmhafir9302901.shtml";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(indexPath));
			String line = null;
			while((line = br.readLine()) != null){
				String[] strArr = line.split('\u0001' + "");
				if(strArr[0].equals(urlStr)){
					long offset = Long.valueOf(strArr[4]);
					int size = Integer.valueOf(strArr[5]);
					System.out.println(readDataFile(offset, size, dataPath, encoding));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String readDataFile(long offset, int size, String dataPath, String encoding){
		String result = "";
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(dataPath, "r");
			raf.seek(offset);
			byte[] b = new byte[size];
			raf.read(b);
			System.out.println(new String(b, encoding));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(raf);
		}
		return result;
	}
	
	public static void main(String[] args) {
		String dataPath = "E:" + File.separator + "spider_data.dat";
		String indexPath = "E:" + File.separator + "spider_index.txt";
		search(indexPath, dataPath, "utf-8");
	}
}
