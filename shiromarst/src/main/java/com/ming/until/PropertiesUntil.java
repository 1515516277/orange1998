package com.ming.until;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUntil {

	public static Properties read(String file) {
		Properties properties = new Properties();
		try {
			// 对应的配置文件
			InputStream in = PropertiesUntil.class.getClassLoader().getResourceAsStream(file);
			// 读取配置文件
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//获取value
	    //properties.getProperty(String key)
		return properties;
	}

	public static void main(String[] args) {
		System.out.println(read("dbconfig.properties").getProperty("jdbc.url"));

	}
	
}
