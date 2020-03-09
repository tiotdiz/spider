package com.qps.utils;

public class CloseUtil {
	public static void close(AutoCloseable obj){
		if(obj != null){
			try {
				obj.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
