package com.artisan.commonUtils.getPath;

import org.apache.commons.lang.StringUtils;
/**
 * 

 * @ClassName: PathUtil

 * @Description: 主要是利用了System.getProperty("user.dir")获取工程的绝对路径,然后再做相关操作。

 * @author: Mr.Yang

 * @date: 2017年8月27日 下午5:10:38
 */
public class PathUtil {
	/**
	 * 获取到classes目录
	 * @return path
	 */
	public static String getClassPath(){
		String systemName = System.getProperty("os.name");
		//判断当前环境，如果是Windows 要截取路径的第一个 '/'
		if(!StringUtils.isBlank(systemName) && systemName.indexOf("Windows") !=-1){
			return PathUtil.class.getResource("/").getFile().toString().substring(1);
		}else{
			return PathUtil.class.getResource("/").getFile().toString();
		}
	}
	
	/**
	 * 获取当前对象的路径
	 * @param object
	 * @return path
	 */
	public static String getObjectPath(Object object){
		return object.getClass().getResource(".").getFile().toString();
	}
	
	/**
	 * 获取到项目的路径
	 * @return path
	 */
	public static String getProjectPath(){
		return System.getProperty("user.dir");
	}
	
	
	/**
	 * 获取 web-inf目录
	 * @return path
	 */
	public static String getWEB_INF(){
		return getClassPath().replace("classes/", "");
	}
	
	/**
	 * 文本换行符号
	 * @return
	 */
	public static String nextLine(){
		 String nextLine = System.getProperty("line.separator");
		 return nextLine;
	}
	
	
}
