package com.sy.mybatis.mapper;

import java.util.HashMap;
import java.util.Map;

public class CreateSqlMap {
	
	final FreemarkerUtil fu = new FreemarkerUtil();
	Map<String, Object> root = new HashMap<String, Object>();
	
	/**
	 * 
	 * @param clazz
	 * @param toCatalog 生成到的目录
	 * @param fileName 文件名称
	 */
	public void create(final Class<?> clazz, final String toCatalog, final String fileName) {
		final SqlModule sqlModule = new SqlModule(clazz);
		root.put("sqlModule", sqlModule);
		fprint("sqlMap.ftl", toCatalog, fileName);
	}
	
	/**
	 * 输出到文件
	 */
	private void fprint(final String name, final String filePath, final String fileName) {
		fu.fprint(name, root, filePath, fileName);
		System.out.println(fileName + "生成成功，路径为：" + filePath);
	}

}
