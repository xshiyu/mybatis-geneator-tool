package com.sy.mybatis.util;

public class DbUtil {
	
	public static String DBSpe = "_";
	
	/**
	 * 驼峰标识转换
	 * dbName -> db_name
	 * @param str
	 * @return
	 */
	public static String fieldName2DbName(String str){
		str = str.replaceAll("([A-Z])", (DBSpe + "$1"));
		str = str.toLowerCase();
		if(str.startsWith("_")){
			return str.substring(1, str.length());
		}
		return str;
	}
	
}
