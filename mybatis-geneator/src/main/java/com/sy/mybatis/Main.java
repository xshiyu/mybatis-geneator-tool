package com.sy.mybatis;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.sy.mybatis.mapper.FreemarkerUtil;
import com.sy.mybatis.mapper.SqlModule;
import com.sy.mybatis.showcase.entity.User;

public class Main {

	public static void main(final String[] args) {
		final File f = new File(Main.class.getResource("/").getPath());
		final String path = f.getPath();
		final SqlModule sqlModule = new SqlModule(User.class);
		// 表名
		sqlModule.setTableName("user");
		final FreemarkerUtil freemarkerUtil = new FreemarkerUtil();
		final Map<String, Object> module = new HashMap<>();
		module.put("module", sqlModule);
		freemarkerUtil.fprint("sql_mapper.xml", module, path + "/resources/", "User.mapper.xml");
	}

}
