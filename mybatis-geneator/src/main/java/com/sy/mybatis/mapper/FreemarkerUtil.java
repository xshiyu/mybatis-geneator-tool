package com.sy.mybatis.mapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Freemarker工具类
 * 
 * @author 熊诗宇
 *
 */
public class FreemarkerUtil {
	
	/**
	 * 得到模版Template
	 * 
	 * @param name
	 * @return
	 */
	public Template getTemplate(final String name) {
		try {
			// 通过Configuration读取相应的ftl
			final Configuration cfg = new Configuration();
			// 通过当前的这个类路径加载ftl模版文件所在路径
			cfg.setClassForTemplateLoading(this.getClass(), "/ftl");
			// 在模版文件目录中找名称为name的文件
			final Template temp = cfg.getTemplate(name);
			return temp;
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 输出到文件
	 */
	public void fprint(final String name, final Map<String, Object> root, final String filePath, final String outFile) {
		FileWriter out = null;
		try {
			File file = new File(filePath);
			//创建目录
			FileUtils.forceMkdir(file);
			file = new File(filePath + "/" + outFile);
			out = new FileWriter(file);
			final Template temp = getTemplate(name);
			temp.process(root, out);
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
