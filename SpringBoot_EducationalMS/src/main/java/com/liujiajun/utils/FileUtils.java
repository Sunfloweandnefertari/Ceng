package com.liujiajun.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;

public class FileUtils {
	/**
	 *  base64字符串转成MultipartFile格式 
	 * @param base64
	 * @return
	 */
	public static MultipartFile base64ToMultipart(String base64) {
	    try {
	        String[] baseStrs = base64.split(",");

	        BASE64Decoder decoder = new BASE64Decoder();
	        byte[] b = new byte[0];
	        b = decoder.decodeBuffer(baseStrs[1]);

	        for(int i = 0; i < b.length; ++i) {
	            if (b[i] < 0) {
	                b[i] += 256;
	            }
	        }

	        return new BASE64DecodedMultipartFile(b, baseStrs[0]);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	/**
     * 上传
     * @param file 文件数组
     * @param filePath 存储路径
     * @param fileName 名
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath, String fileName){
    	try {
    		File targetFile = new File(filePath); 
        	if(!targetFile.exists()){ 
        	targetFile.mkdirs(); 
        	} 
        	FileOutputStream out = new FileOutputStream(filePath+fileName);
        	out.write(file);
        	out.flush();
        	out.close();
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	
    }
}
