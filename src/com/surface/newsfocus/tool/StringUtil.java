package com.surface.newsfocus.tool;

import java.io.UnsupportedEncodingException;

public class StringUtil {

	/**
	 * �ı��ַ����
	 * @param str Ŀ���ַ�
	 * @return ת���ַ�
	 */
	public static String changeEncoding(String str,String encoding){
		try {
			String returnStr = new String(str.getBytes(), encoding);
			return returnStr;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

}
