package com.example.factory;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.DigestUtils;

import com.example.app.GetPropertiesValue;


//@Test
public class GetuploadData {

	/**
	 * json解析，提取需要的data参数的值
	 */
	// String转JSON对象
	public static String sendUploadData(String uploadData) {
		try {
			JSONObject jsonData = new JSONObject(new String(uploadData));
			// System.out.println("jsonData:" + jsonData);
			uploadData = jsonData.getString("data");
			System.out.println("data:" + uploadData);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uploadData;
	}

	/**
	 * json解析，提取uid
	 */
	public static String sendUid(String strLogin) {
		JSONObject jsonDataUid = new JSONObject(new String(strLogin));
		JSONObject strLogindata = jsonDataUid.getJSONObject("data");
		JSONObject DataUser = strLogindata.getJSONObject("user");
		String uid = String.valueOf(DataUser.getInt("id"));
		// System.out.println("DataUser:" + DataUser);
		// String strLogindata=jsonData.getString("data");
		System.out.println("uid:" + uid);
		return uid;

	}

	/**
	 * 获取GetPropertiesValue.getUploadTokenApiPath类返回的uploadTokenApiPath
	 */
	public static String uploadTokenApiPath() {
		String uploadTokenPath = GetPropertiesValue.getUploadTokenApiPath();
		return uploadTokenPath;
	}

	/**
	 * 获取GetPropertiesValue.getUpLoadVideoApiPath类返回的upLoadVideoApiPath
	 */
	public static String upLoadVideoApiPath() {
		String upLoadVideoPath = GetPropertiesValue.getUpLoadVideoApiPath();
		return upLoadVideoPath;
	}

	/**
	 * 通过拼接 "dher"、uid、apiPath、毫秒时间戳/100000，再进行MD5加密，生成请求头x_api_signature签名
	 */
	/*
	 * public static String sendX_Api_Signature(String uid, String apiPath) { String
	 * signature = "dher" + apiPath + uid + System.currentTimeMillis() / 100000;
	 * String x_api_signature = DigestUtils.md5DigestAsHex(signature.getBytes()); //
	 * System.out.println("signature：" + signature); // System.out.println("md5加密后："
	 * + x_api_signature); return x_api_signature;
	 * 
	 * }
	 */

	public static String sendMusicData(String musicData) {
		return musicData;
	}

}
