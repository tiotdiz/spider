package com.qps.study.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

import com.qps.utils.CloseUtil;
import com.qps.utils.WebUtil;

public class IOStudy {
	
	public static void test(){
		String dateFile = "E:" + File.separator + "spider.dat"; 
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(dateFile, "r");
			long pos = 0;
			raf.seek(pos);
			int size = 79747;
			byte[] b = new byte[size];
			raf.read(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(raf);
		}
	}

	public static void testFile() {
		String dateFile = "E:" + File.separator + "spider.dat";
		String urlString = "http://news.sina.com.cn/o/2018-11-06/doc-ihmutuea7351575.shtml";
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(dateFile, true));
			byte[] b = WebUtil.urlGetByteArray(urlString);
			System.out.println(b.length);
			bos.write(b);
			System.out.println("DONE!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(bos);
		}
	}

	private static void testBAOS() {
		String urlString = "http://news.sina.com.cn/o/2018-11-06/doc-ihmutuea7351575.shtml";
		String encoding = "utf-8";

		URL url = null;
		URLConnection conn = null;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedInputStream bis = null;
		byte[] byteArray = null;
		try {
			url = new URL(urlString);
			conn = url.openConnection();
			bis = new BufferedInputStream(conn.getInputStream());
			int b = -1;
			while ((b = bis.read()) != -1) {
				baos.write(b);
			}
			byteArray = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(bis);
			CloseUtil.close(baos);
		}
		System.out.println("ByteArray length =" + byteArray.length);
		System.out.println("=========================================");
		try {
			System.out.println(new String(byteArray, encoding));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		test();
	}
}
