package com.qps.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

public class WebUtil {
	public static byte[] urlGetByteArray(String urlString){
		URL url = null;
		URLConnection conn = null;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedInputStream bis = null;
		byte[] byteArray = new byte[0];
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
		return byteArray;
	}
	
	public static String urlGetString(String urlString, String encoding) {
		String result = null;
		StringBuffer sb = new StringBuffer();
		URL url = null;
		URLConnection conn = null;
		BufferedReader br = null;

		try {
			url = new URL(urlString);
			conn = url.openConnection();
			br = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), encoding));
			String line = null;
			while ((line = br.readLine()) != null) {
//				System.out.println(line);
				sb.append(line).append(System.lineSeparator());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(br);
		}
		result = sb.toString();
		return result;
	}
	
}
