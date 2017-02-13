package com.sy.mybatis.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtil {

	private static final String SETTER_PREFIX = "set";

	private static final String GETTER_PREFIX = "get";

	
	
	/**
	 * 判断是否为基本数据类型
	 * @param cls
	 * @return
	 */
	public static boolean isPrimitive(final Class<?> cls){
		return cls.isPrimitive() || PrimitiveClasses.contains(cls);
	}
	
	private final static List<Class<?>> PrimitiveClasses = new ArrayList<Class<?>>(){{
		add(Long.class);
		add(Integer.class);
		add(String.class);
		add(java.util.Date.class);
		add(java.sql.Timestamp.class);
		add(Boolean.class);
		add(Character.class);
		add(Float.class);
		add(BigDecimal.class);
	}};
	
	/**
	 * 根据Field获得它的get方法
	 * @param fe
	 * @return
	 */
	public static Method getGetMethod(final Field fe) {

		Method me = null;
		try {
			me = fe.getDeclaringClass().getMethod(GETTER_PREFIX + subFieldName(fe.getName()));
		} catch (final Exception e) {
		}
		return me;
	}

	/**
	 * 根据Field获得它的set方法
	 * @param fe
	 * @return
	 */
	public static Method getSetMethod(final Field fe) {
		Method me = null;
		try {
			me = fe.getDeclaringClass().getMethod(SETTER_PREFIX + subFieldName(fe.getName()), fe.getType());
		} catch (final Exception e) {
		}
		return me;
	}
	
	/**
	 * 转换fieldName的首字母到大写
	 * @param fieldName
	 * @return
	 */
	private static String subFieldName(String fieldName) {
		if (fieldName.length() > 1) {
			fieldName = fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
		} else {
			fieldName = fieldName.toUpperCase();
		}
		return fieldName;
	}
	
}
