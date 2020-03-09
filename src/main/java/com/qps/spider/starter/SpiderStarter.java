package com.qps.spider.starter;

import com.qps.spider.service.SinaNewsSpider;

public class SpiderStarter {

	public static void main(String[] args) {
		SinaNewsSpider.crawler();
		System.out.println("DONE!");
	}

}
