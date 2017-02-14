package com.sy.mybatis;

public class Config {
	
	/**
	 * 包名
	 */
	public static String packageName = "com.sy.mybatis.showcase";
	
	/**
	 * 列名规则
	 * 1：Java中的驼峰标识对应Mysql中的下划线
	 * 2：Java和Mysql都是驼峰标识
	 */
	public static Integer columnRule = 2;
	
	/**
	 * ID规则
	 * 1：XML中的id统一为id
	 * 2：XML中的id为表名+id，例如 userId
	 */
	public static Integer idRule = 2;
	
}
