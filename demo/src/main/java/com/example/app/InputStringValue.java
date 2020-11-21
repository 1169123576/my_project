package com.example.app;

import java.util.ArrayList;
import java.util.Random;

public class InputStringValue {
	/**
	 * 	随机获取视频资源
	 */
	public static String getVideoList() {
		ArrayList<String> videoList = new ArrayList<String>();
		videoList.add("20b888piCadD.mp4");
		videoList.add("45D888piCQm5.mp4");
		videoList.add("douyin1.mp4");
		videoList.add("douyin2.mp4");
		videoList.add("sc2.mp4");
		videoList.add("sc3.mp4");
		videoList.add("shipin.mp4");
		videoList.add("suc.mp4");
		videoList.add("v1.mp4");
		videoList.add("v2.mp4");
		videoList.add("v3.mp4");
		videoList.add("v4.mp4");
		//videoList.add("VID.mp4");
		videoList.add("video1.mp4");
		videoList.add("video2.mp4");
		videoList.add("video3.mp4");
		videoList.add("video4.mp4");
		videoList.add("video5.mp4");
		Random random = new Random();
		int n = random.nextInt(videoList.size());
		String videoFile = videoList.get(n);
		return videoFile;
	}

	/**
	 * 	随机获取图片资源
	 */
	public static String getImageList() {
		ArrayList<String> imageList = new ArrayList<String>();
		imageList.add("cover1.jpg");
		imageList.add("cover2.jpg");
		imageList.add("cover3.jpg");
		imageList.add("cover4.jpg");
		imageList.add("cover5.jpg");
		imageList.add("sc2.jpg");
		imageList.add("sc3.jpg");
		imageList.add("微信图片_20200902154507.jpg");
		imageList.add("微信图片_20200902154507.jpg");
		imageList.add("微信图片_202009021545071.jpg");
		imageList.add("微信图片_202009021545072.jpg");
		imageList.add("微信图片_202009021545073.jpg");
		//imageList.add("微信图片_202009021545074.jpg");
		imageList.add("微信图片_202009021545075.jpg");
		imageList.add("微信图片_202009021545076.jpg");
		imageList.add("微信图片_202009021545077.jpg");
		Random random = new Random();
		int n = random.nextInt(imageList.size());
		String imageFile = imageList.get(n);
		return imageFile;
	}

	/**
	 * 	随机获取视频内容
	 */
	public static String getTitleList() {
		ArrayList<String> titleList = new ArrayList<String>();
		titleList.add("等会er_地中海二哈犬犬舍");
		titleList.add("等会er_地中海拉布拉多犬舍");
		titleList.add("等会er_地中海金毛寻回犬犬舍");
		titleList.add("等会er_地中海博美犬犬舍");
		titleList.add("等会er_地中海德国黑背犬犬舍");
		titleList.add("等会er_地中海边境牧羊犬犬舍");
		titleList.add("等会er_地中海八哥犬犬舍");
		titleList.add("等会er_地中海萨摩耶犬犬舍");
		titleList.add("等会er_地中海阿拉斯加犬犬舍");
		titleList.add("等会er_地中海泰迪犬犬舍");
		titleList.add("等会er_地中海柯基犬犬舍");
		Random random = new Random();
		int n = random.nextInt(titleList.size());
		String title = titleList.get(n);
		return title;
	}
}
