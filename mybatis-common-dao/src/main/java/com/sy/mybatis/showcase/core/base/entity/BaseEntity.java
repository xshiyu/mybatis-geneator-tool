package com.sy.mybatis.showcase.core.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础公共实体
 * 
 * @author shiyu
 *
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer version = 0;
	/**
	 * 创建时间
	 */
	protected Date createTime = new Date();

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(final Integer version) {
		this.version = version;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(final Date createTime) {
		this.createTime = createTime;
	}

}
