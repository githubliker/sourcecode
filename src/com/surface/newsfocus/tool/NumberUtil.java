package com.surface.newsfocus.tool;

public class NumberUtil {

	/**
	 * ���ַ�ת����int�����
	 * @param str
	 * @return 
	 */
	public static Integer parseInt(String str){
		if(str==null||str.equals("")){
			return null;
		}else{
			try{
				return Integer.parseInt(str);
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}
	/**
	 * ���ַ�ת����int�����
	 * @param str
	 * @return 
	 */
	public static Integer parseInt(String str,int defaultValue){
		if(str==null||str.equals("")){
			return defaultValue;
		}else{
			try{
				return Integer.parseInt(str);
			}catch(Exception e){
				e.printStackTrace();
				return defaultValue;
			}
		}
	}

	/**
	 * ���ַ�ת����Double�����
	 * @param str
	 * @return
	 */
	public static Double parseDouble(String str){
		if(str==null||str.equals("")){
			return null;
		}else{
			try{
				return Double.parseDouble(str);
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * ���ַ�ת����Double��ݣ�����ת��ʱ����0
	 * @param str
	 * @return
	 */
	public static Double parseDoubleZero(String str){
		return parseDouble(str, 0.0);
	}
	
	/**
	 * ���ַ�ת����Double��ݣ�����ת��ʱ����defaultNumber
	 * @param str
	 * @return
	 */
	public static Double parseDouble(String str,Double defaultNumber){
		if(str==null||str.equals("")){
			return defaultNumber;
		}else{
			try{
				return Double.parseDouble(str);
			}catch(Exception e){
				e.printStackTrace();
				return defaultNumber;
			}
		}
	}
	
	/**
	 * ���ַ�ת����Double��ݣ�����ת��ʱ����0
	 * @param str
	 * @return
	 */
	public static Double parseDoubleZeroToOne(String str){
		if(parseDoubleZero(str)==0){
			return 1.0;
		}else{
			return parseDouble(str, 1.0);
		}
	}

	/**
	 * ���ַ�ת����������ݣ�����ת��ʱ����0
	 * @param str
	 * @return
	 */
	public static Integer parseIntZero(String str){
		if(str==null||str.equals("")){
			return 0;
		}else{
			try{
				return parseInt(str);
			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}
		}
	}

	/**
	 * �ж�str��large�Ĵ�С 
	 * @param str �Ƚ���
	 * @param large ���Ƚ���
	 * @return ��һ�������largeС����true
	 */
	public static boolean idSmallThan(String str,float large){
		if(parseDoubleZero(str)<large){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isAllEmptyOrZero(String... strs){
		for(String string:strs){
			if(parseDoubleZero(string)!=0){
				return false;
			}
		}
		return true;
	}
}
