package com.qps.study.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.qps.utils.CloseUtil;
import com.qps.utils.WebUtil;

public class NetStudy {

	public static void test() {
		// URL URLConnection
		// Uniformed Resource Location
		// HTTP Hyper Text Transfer Protocal
		String urlString = "http://roll.news.sina.com.cn/news/gnxw/gdxw1/index.shtml";
//		String encoding = "utf-8";
		String encoding = "gb2312";

		URL url = null;
		URLConnection conn = null;
		BufferedReader br = null;

		try {
			url = new URL(urlString);
			conn = url.openConnection();
			br = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), encoding));
			String line = null;
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(br);
		}
	}

	public static void main(String[] args) {
//		test();
		String urlString = "http://roll.news.sina.com.cn/news/gnxw/gdxw1/index.shtml";
		String encoding = "gb2312";
		System.out.println(WebUtil.urlGetString(urlString, encoding));
	}
}
