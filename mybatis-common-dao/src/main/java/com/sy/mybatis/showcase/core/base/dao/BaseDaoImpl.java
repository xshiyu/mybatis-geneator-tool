package com.sy.mybatis.showcase.core.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sy.mybatis.showcase.core.base.entity.BaseEntity;
import com.sy.mybatis.showcase.core.exception.DaoException;
import com.sy.mybatis.showcase.core.page.PageBean;
import com.sy.mybatis.showcase.core.page.PageParam;

public abstract class BaseDaoImpl<T extends BaseEntity> extends SqlSessionDaoSupport implements BaseDao<T> {

	protected static final Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);

	public static final String SQL_INSERT = "insert";
	public static final String SQL_BATCH_INSERT = "batchInsert";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_GET_BY_ID = "getById";
	public static final String SQL_DELETE_BY_ID = "deleteById";
	public static final String SQL_LIST_BY = "listBy";
	public static final String SQL_COUNT_BY_PAGE_PARAM = "countByPageParam"; // 根据当前分页参数进行统计

	/**
	 * 注入SqlSessionTemplate实例(要求Spring中进行SqlSessionTemplate的配置).<br/>
	 * 可以调用sessionTemplate完成数据库操作.
	 */
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	@Autowired
	protected SqlSessionFactory sqlSessionFactory;

	@Override
	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	@Override
	public SqlSession getSqlSession() {
		return super.getSqlSession();
	}

	@Override
	public long insert(T t) {

		if (t == null) {
			throw new RuntimeException("T is null");
		}

		int result = sessionTemplate.insert(getStatement(SQL_INSERT), t);

		if (result <= 0) {
			throw DaoException.DB_INSERT_RESULT_0;
		}

		if ((t != null) && (t.getId() != null) && (result > 0)) {
			return t.getId();
		}

		return result;
	}

	@Override
	public long insert(List<T> list) {

		if ((list == null) || (list.size() <= 0)) {
			return 0;
		}

		int result = sessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list);

		if (result <= 0) {
			throw DaoException.DB_INSERT_RESULT_0;
		}

		return result;
	}

	@Override
	public long update(T t) {
		if (t == null) {
			throw new RuntimeException("T is null");
		}

		int result = sessionTemplate.update(getStatement(SQL_UPDATE), t);

		if (result <= 0) {
			throw DaoException.DB_UPDATE_RESULT_0;
		}

		return result;
	}

	@Override
	public T getById(long id) {
		return sessionTemplate.selectOne(getStatement(SQL_GET_BY_ID), id);
	}

	@Override
	public int deleteById(long id) {
		return sessionTemplate.delete(getStatement(SQL_DELETE_BY_ID), id);
	}

	@Override
	public PageBean<T> listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId) {

		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		
		paramMap.put("offset", (pageParam.getPageNum() - 1) * pageParam.getPageSize());
		paramMap.put("pageSize", pageParam.getPageSize());
		paramMap.put("direction", pageParam.getDirection());
		paramMap.put("propertie", pageParam.getPropertie());

		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<T> list = getSqlSession().selectList(getStatement(sqlId), paramMap);

		// 统计总记录数
		Object countObject = getSqlSession().selectOne(getStatement(sqlId), paramMap);
		Long count = Long.valueOf(countObject.toString());

		return new PageBean<T>(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list);
	}

	@Override
	public PageBean<T> listPage(PageParam pageParam, Map<String, Object> paramMap) {

		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		
		paramMap.put("offset", (pageParam.getPageNum() - 1) * pageParam.getPageSize());
		paramMap.put("pageSize", pageParam.getPageSize());
		paramMap.put("direction", pageParam.getDirection());
		paramMap.put("propertie", pageParam.getPropertie());

		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<Object> list = getSqlSession().selectList(getStatement(SQL_LIST_BY), paramMap);

		// 统计总记录数
		Object countObject = getSqlSession().selectOne(getStatement(SQL_COUNT_BY_PAGE_PARAM), paramMap);
		Long count = Long.valueOf(countObject.toString());
		return new PageBean(pageParam.getPageNum(), pageParam.getPageSize(), count.intValue(), list);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> listBy(Map<String, Object> paramMap) {
		return this.listBy(paramMap, SQL_LIST_BY);
	}

	@Override
	public List<T> listBy(Map<String, Object> paramMap, String sqlId) {
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}

		return sessionTemplate.selectList(getStatement(sqlId), paramMap);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getBy(Map<String, Object> paramMap) {
		return this.getBy(paramMap, SQL_LIST_BY);
	}

	@Override
	public T getBy(Map<String, Object> paramMap, String sqlId) {
		if ((paramMap == null) || paramMap.isEmpty()) {
			return null;
		}

		return this.getSqlSession().selectOne(getStatement(sqlId), paramMap);
	}

	public String getStatement(String sqlId) {

		String name = this.getClass().getName();

		StringBuffer sb = new StringBuffer().append(name).append(".").append(sqlId);

		return sb.toString();
	}

}
