package com.qps.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class IOUtil {
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

	public static void writeIndexFile(String index, String indexPath) {
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
}
