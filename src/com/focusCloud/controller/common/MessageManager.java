package com.focusCloud.controller.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 消息管理
 * 
 * @author XIAKUN
 * 
 */
public class MessageManager {

	// 消息Properties存放对象
	private static Properties p = null;

	/**
	 * 获取消息内容
	 * 
	 * @param msgId
	 *            消息ID
	 * @return 消息
	 * @throws Exception
	 */
	public static String getMsg(String msgId) {
		String rtnStr = null;
		if (p == null || p.size() == 0) {
			try {
				createMsgMap();
			} catch (Exception e) {
				return rtnStr;
			}
		}
		if (p.get(msgId) != null) {
			rtnStr = p.getProperty(msgId);
		}
		return rtnStr;
	}

	/**
	 * 读取消息内容
	 * 
	 * @throws Exception
	 */
	private static void createMsgMap() throws Exception {
		p = new Properties();
		String temp = MessageManager.class.getResource("/").getPath()
				.replaceAll("%20", " ");
		InputStream in = new BufferedInputStream(new FileInputStream(temp
				+ "info.properties"));
		p.load(in);
	}

}
