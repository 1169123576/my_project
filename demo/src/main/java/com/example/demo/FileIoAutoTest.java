package com.example.demo;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.GetPropertiesValue;
import com.example.app.InputStringValue;
import com.example.factory.GetuploadData;
import com.example.factory.HttpClientMainClass;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FetchRet;

//@RestController
public class FileIoAutoTest {
	public String GetupToken(String upToken) {
		return upToken;
	}

	/*
	 * //@RequestMapping("/uploadVideo") public String test() {
	 * 
	 * String data = FileIoAutoTest.uploadVideo(GetPropertiesValue.getUserName(),
	 * GetPropertiesValue.getPassWord()); return data; }
	 */
	public static String uploadVideo(String user, String pwd) {
		// String upToken
		/**
		 * 构造一个带指定 Region 对象的配置类 图片服务器地址，通过七牛云后台查询出在华南，使用region2()
		 * 视频服务器地址，通过七牛云后台查询出在华东，使用region0()
		 */
		Configuration cfgImage = new Configuration(Region.region2());
		Configuration cfgVideo = new Configuration(Region.region0());
		UploadManager uploadImageManager = new UploadManager(cfgImage);
		UploadManager uploadVideoManager = new UploadManager(cfgVideo);

		// 随机获取需要上传的视频信息
		String videoFile = InputStringValue.getVideoList();
		String imageFile = InputStringValue.getImageList();
		String title = InputStringValue.getTitleList();
		System.out.println("视频名称：" + videoFile + "--封面名称：" + imageFile + "--内容标题：" + title);
		// ...生成上传凭证，然后准备上传
		// 如果是Windows情况下，格式是 D:\\qiniu\\test.png
		// 定义变量需要传递的值登录：用户名和用户密码
		// String username = "18675503241";
		// String password = "ds123456";
		// 以下信息不用每次修改,在本机D盘下新建opload_file文件夹，把资源文件放在文件夹下
		String globeUrl = "http://api.edog-online.cn";
		String localVideoPath = "D:\\opload_file\\" + videoFile;
		String localImagePath = "D:\\opload_file\\" + imageFile;
		String inVideoBucket = "wam-videos";
		String inImageBucket = "wam-image";
		String imgType = "image";
		String vidType = "video";

		// 默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = null;
		String inVideoFile = localVideoPath.substring(localVideoPath.lastIndexOf("\\") + 1);
		String inImageFile = localImagePath.substring(localImagePath.lastIndexOf("\\") + 1);
		// 登录接口：String strLogin =
		String strLogin = HttpClientMainClass.sendPost(globeUrl + "/v1/auth/login",
				"phone=" + user + "&password=" + pwd + "&version=2.0.0");
		System.out.println("strLogin:" + strLogin);
		// 提取uid,作用到GetuploadData.sendX_Api_Signature()方法生成x_api_signature签名
		String uid = GetuploadData.sendUid(strLogin);
		// 提取apipath,作用到GetuploadData.sendX_Api_Signature()方法生成x_api_signature签名
		String uploadTokenApiPath = GetuploadData.uploadTokenApiPath();
		String strImage = HttpClientMainClass.sendOrderPost(globeUrl + "/v1/video/getUploadToken", "type=" + imgType,
				uid, uploadTokenApiPath);
		String strVideo = HttpClientMainClass.sendOrderPost(globeUrl + "/v1/video/getUploadToken", "type=" + vidType,
				uid, uploadTokenApiPath);
		String upImageToken = GetuploadData.sendUploadData(strImage);
		// System.out.println("upImageToken:"+upImageToken);
		String upVideoToken = GetuploadData.sendUploadData(strVideo);
		// System.out.println("upVideoToken:"+upVideoToken);
		String uploadBody = null;
		try {
			Response resImage = uploadImageManager.put(localImagePath, key, upImageToken);
			Response resVideo = uploadVideoManager.put(localVideoPath, key, upVideoToken);
			// 解析图片上传成功的结果
			FetchRet fetchRetImage = new Gson().fromJson(resImage.bodyString(), FetchRet.class);
			/*
			 * System.out.println(fetchRetImage.key);
			 * System.out.println(fetchRetImage.hash);
			 * System.out.println(fetchRetImage.fsize);
			 * System.out.println(fetchRetImage.mimeType);
			 */
			// 解析视频上传成功的结果
			FetchRet fetchRetVideo = new Gson().fromJson(resVideo.bodyString(), FetchRet.class);
			/*
			 * System.out.println("视频:" + "\n" + fetchRetVideo.key);
			 * System.out.println(fetchRetVideo.hash);
			 * System.out.println(fetchRetVideo.fsize);
			 * System.out.println(fetchRetVideo.mimeType);
			 */

			/**
			 * 上传的视频信息入库
			 */
			JSONObject jsonVideo = new JSONObject();
			JSONObject jsonImage = new JSONObject();
			// 读取上传图片返回的key、hash、fsize、mimeType，存入video入库参数中
			jsonImage.put("key", fetchRetImage.key);
			jsonImage.put("hash", fetchRetImage.hash);
			jsonImage.put("fsize", fetchRetImage.fsize);
			jsonImage.put("bucket", inImageBucket);
			jsonImage.put("fname", inImageFile);
			jsonImage.put("mimeType", fetchRetImage.mimeType);

			// 读取上传视频返回的key、hash、fsize、mimeType，存入video入库参数中
			jsonVideo.put("key", fetchRetVideo.key);
			jsonVideo.put("hash", fetchRetVideo.hash);
			jsonVideo.put("fsize", fetchRetVideo.fsize);
			jsonVideo.put("bucket", inVideoBucket);
			jsonVideo.put("fname", inVideoFile);
			jsonVideo.put("mimeType", fetchRetVideo.mimeType);
			// 提取apipath,作用到GetuploadData.sendX_Api_Signature()方法生成x_api_signature签名
			String upLoadVideoApiPath = GetuploadData.upLoadVideoApiPath();
			// 调用上传视频入库接口，视频、图片入库
			uploadBody = HttpClientMainClass.sendJsonBodyPost(globeUrl + "/v1/video/uploadVideo", jsonImage, jsonVideo,
					title, uid, upLoadVideoApiPath);
			System.out.println("上传视频信息后返回值:" + uploadBody);

		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uploadBody;

	}
}