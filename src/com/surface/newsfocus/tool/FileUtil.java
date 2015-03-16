package com.surface.newsfocus.tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
import android.os.StatFs;


public class FileUtil {

	/**
	 * ��/sdcard/apmt·��������ı�
	 * @param text �ı���Ϣ
	 */
	public static void addFile(String text){
		addFile(text,Environment.getExternalStorageDirectory().getPath()+"/apmt/","a.txt");
	}

	/**
	 * ���·������ı�
	 * @param text �ı���Ϣ
	 * @param path ·��
	 */
	public static void addFile(String text,String path,String fileName){
		File file = new File(path+fileName);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}

		FileWriter fileWriter = null;
		try {
			fileWriter =new FileWriter(path+fileName);
			fileWriter.write(text);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(fileWriter!=null){
					fileWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ����ļ��л�ɾ���ļ�
	 * @param file
	 */
	public static void delete(File file) {  
		if (file.isFile()) {  
			file.delete();  
			return;  
		}  
		if(file.isDirectory()){  
			File[] childFiles = file.listFiles();  
			if (childFiles == null || childFiles.length == 0) {  
				file.delete();  
				return;  
			}  

			for (int i = 0; i < childFiles.length; i++) {  
				delete(childFiles[i]);  
			}  
			file.delete();  
		}
	} 
	/**
	 * ��ȡ�ֻ�ʣ���ڴ�
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static long getSDFreeSize(){
		//ȡ��SD���ļ�·��
		File path = Environment.getExternalStorageDirectory(); 
		StatFs sf = new StatFs(path.getPath()); 
		//��ȡ������ݿ�Ĵ�С(Byte)
		long blockSize = sf.getBlockSize(); 
		//���е���ݿ������
		long freeBlocks = sf.getAvailableBlocks();
		//����SD�����д�С
		//return freeBlocks * blockSize;  //��λByte
		//return (freeBlocks * blockSize)/1024;   //��λKB
		return (freeBlocks * blockSize)/1024 /1024; //��λMB
	}	
}
