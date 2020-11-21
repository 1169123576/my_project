package com.example.demo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogOnJFrame {

	@RequestMapping("/uploadfile")
	public String test() {
		String data = LogOnJFrame.init();
		return data;
	}

	private static JFrame jFrame = new JFrame("发布");
	private static Container c = jFrame.getContentPane();
	private static JLabel a1 = new JLabel("用户名");
	private static JTextField username = new JTextField();
	private static JLabel a2 = new JLabel("密   码");
	private static JPasswordField password = new JPasswordField();
	private static JButton upvideobtn = new JButton("发布视频");
	private static JButton upmusicbtn = new JButton("发布音乐");
	private static String datas = null;
	@SuppressWarnings("null")
	public static String init() {

		// 设置窗体的位置及大小
		jFrame.setBounds(600, 200, 300, 220);
		// 设置一层相当于桌布的东西
		c.setLayout(new BorderLayout());// 布局管理器
		// 设置按下右上角X号后关闭
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗体可见
		jFrame.setVisible(true);
		// 初始化--往窗体里放其他控件
		/* 标题部分--North */
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(new JLabel("视频发布系统"));
		c.add(titlePanel, "North");

		/* 输入部分--Center */
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		a1.setBounds(50, 20, 50, 20);
		a2.setBounds(50, 60, 50, 20);
		fieldPanel.add(a1);
		fieldPanel.add(a2);
		username.setBounds(110, 20, 120, 20);
		password.setBounds(110, 60, 120, 20);
		fieldPanel.add(username);
		fieldPanel.add(password);
		c.add(fieldPanel, "Center");

		/* 按钮部分--South */
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(upvideobtn);
		buttonPanel.add(upmusicbtn);
		c.add(buttonPanel, "South");
		// 按下去发布视频
		upvideobtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String uname = username.getText();
				String pwd = String.valueOf(password.getPassword());
				datas= FileIoAutoTest.uploadVideo(uname, pwd);
				System.out.println("datas:"+datas);
			}
		});
		// 按下去发布音乐
		upvideobtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String uname = username.getText();
				String pwd = String.valueOf(password.getPassword());
				System.out.println(uname + pwd);
			}
		});
		String data = datas;
		System.out.println("最终data"+data);
		return data;
	}
}
