package com.sy.mybatis.showcase.core.page;

import java.io.Serializable;

/**
 * 分页请求参数
 * @author shiyu
 *
 */
public class PageParam implements Serializable{
	
	private static final long serialVersionUID = 4984099118452388673L;

	/**
	 * 每页多少条
	 */
	private final int pageSize;
	
	/**
	 * 查询第几页
	 */
	private final int pageNum;
	
	/**
	 * 排序方式
	 */
	private final String direction;
	
	/**
	 * 排序参数
	 */
	private final String propertie;
	
	/**
	 * 
	 * @param pageNum 查询第几页
	 * @param pageSize 每页多少条
	 * @param direction 排序方式
	 * @param propertie 排序参数
	 */
	public PageParam(final int pageNum, final int pageSize, final String direction, final String propertie){
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.direction = direction;
		this.propertie = propertie;
	}
	
	/**
	 * 构造分页请求，不带排序
	 * @param pageNum 第几页，从1开始
	 * @param pageSize 每页多少条
	 * @param direction 排序方式
	 * @param properties 排序参数
	 */
	public static PageParam buildPageRequest(final int pageNum, final int pageSize){
		return new PageParam(pageNum, pageSize, null, null);
	}
	
	/**
	 * 构造分页请求，带排序
	 * @param pageNum 第几页，从1开始
	 * @param pageSize 每页多少条
	 * @param direction 排序方式
	 * @param properties 排序参数
	 */
	public static PageParam buildPageRequest(final int pageNum, final int pageSize, final String direction, final String propertie){
		return new PageParam(pageNum, pageSize, direction, propertie);
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public String getDirection() {
		return direction;
	}

	public String getPropertie() {
		return propertie;
	}
	
	public int getOffset() {
		return pageNum * pageSize;
	}

}
