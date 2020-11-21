package com.example.app;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertiesValue {

	/**
	 * loginAccount.properties解析，提取username
	 */
	public static String getUserName() {
		String username = null;
		Properties properties = new Properties();
		// 使用InPutStream流读取properties文件
		try {
			InputStream inputStream = new BufferedInputStream(
					new FileInputStream(new File("jsonFile/loginAccount.properties")));
			properties.load(inputStream);
			// 获取key对应的value值
			username = properties.getProperty("username");
			System.out.println("username: " + username);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return username;

	}

	/**
	 * loginAccount.properties解析文件，提取password
	 */
	public static String getPassWord() {
		String pwd = null;
		Properties properties = new Properties();
		// 使用InPutStream流读取properties文件
		try {
			InputStream inputStream = new BufferedInputStream(
					new FileInputStream(new File("jsonFile/loginAccount.properties")));
			properties.load(inputStream);
			pwd = properties.getProperty("password");
			System.out.println("password: " + pwd);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pwd;

	}

	/**
	 * videoApiList.properties解析文件，提取upLoadTokenApiPath的值
	 */
	public static String getUploadTokenApiPath() {
		String uploadApiPath = null;
		Properties properties = new Properties();
		// 使用InPutStream流读取properties文件
		try {
			InputStream inputStream = new BufferedInputStream(
					new FileInputStream(new File("jsonFile/videoApiList.properties")));
			properties.load(inputStream);
			// 获取key对应的value值，截取最后一个/后面的值
			uploadApiPath = properties.getProperty("UploadToken")
					.substring(properties.getProperty("UploadToken").lastIndexOf("/") + 1);
			uploadApiPath.substring(uploadApiPath.lastIndexOf("\\") + 1);
			System.out.println("UploadToken: " + uploadApiPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uploadApiPath;

	}

	/**
	 * videoApiList.properties解析文件，提取upLoadVideoApiPath的值
	 */
	public static String getUpLoadVideoApiPath() {
		String uploadVideoApiPath = null;
		Properties properties = new Properties();
		// 使用InPutStream流读取properties文件
		try {
			InputStream inputStream = new BufferedInputStream(
					new FileInputStream(new File("jsonFile/videoApiList.properties")));
			properties.load(inputStream);
			// 获取key对应的value值，截取最后一个/后面的值
			uploadVideoApiPath = properties.getProperty("UploadVideo")
					.substring(properties.getProperty("UploadVideo").lastIndexOf("/") + 1);
			uploadVideoApiPath.substring(uploadVideoApiPath.lastIndexOf("\\") + 1);
			System.out.println("UploadVideo: " + uploadVideoApiPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uploadVideoApiPath;
	}

}
