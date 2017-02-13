package com.sy.mybatis.showcase.core.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 * @author shiyu
 *
 * @param <T>
 */
public class PageBean<T> implements Serializable {
	
	private static final long serialVersionUID = 363460947283682126L;

	/**
	 * 查询第几页
	 */
	private final int pageNum;
	
	/**
	 * 每页多少条
	 */
	private final int pageSize;
	
	/**
	 * 总条数
	 */
	private final int total;
	
	/**
	 * 总页数
	 */
	private final int totalPages;
	
	/**
	 * 分页结果序列
	 */
	private final List<T> content;

	public PageBean(final int pageNum, final int pageSize, final int total, final List<T> content) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
		this.content = content;
		this.totalPages = pageSize == 0 ? 0 : (int) Math.ceil((double) total / (double) pageSize);
	}

	/**
	 * 返回当前页面数
	 * @return
	 */
	public int getPageNum(){
		return pageNum;
	}

	/**
	 * 返回每页长度
	 * @return
	 */
	public int getPageSize(){
		return pageSize;
	}

	/**
	 * 返回总页数
	 * @return
	 */
	public int getTotalPages(){
		return totalPages;
	}

	/**
	 * 是否还有下一页
	 * @return
	 */
	public boolean hasNextPage(){
		return ((getPageNum() + 1) * getPageSize()) < total;
	}

	/**
	 * 将查询出的结果以list形式返回
	 * @return
	 */
	public List<T> getContent(){
		return content;
	}

	/**
	 * 是否查询出结果
	 * @return
	 */
	public boolean hasContent(){
		return !content.isEmpty();
	}

	/**
	 * 获取总条数
	 * @return
	 */
	public int getTotal() {
		return total;
	}
	
}
