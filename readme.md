# mybatis-geneator-tool
## 介绍
- 可通过此工具自动生成Mybatis XML文件和dao的class类
- 可结合BaseDao完成基础操作，BaseDao支持的方法如下
```java
public interface BaseDao<T> {
	//根据实体对象新增记录
	long insert(T entity);
	//批量保存对象
	long insert(List<T> list);
	//更新实体对应的记录
	long update(T entity);
	//根据ID查找记录
	T getById(long id);
	//根据ID删除记录
	int deleteById(long id);
	//分页查询
	PageBean<T> listPage(PageParam pageParam, Map<String, Object> paramMap);
	//根据条件分页查询
	PageBean<T> listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId);
	//根据条件查询，返回列表
	List<T> listBy(Map<String, Object> paramMap);
	List<T> listBy(Map<String, Object> paramMap, String sqlId);
	//根据条件查询返回单个对象
	T getBy(Map<String, Object> paramMap);
	T getBy(Map<String, Object> paramMap, String sqlId);
}
```

## Quick start
1、编写实体

2、在Main方法中指定实体类class、表名

3、运行main方法

## 后期计划
1、配置JDBC，自动连接到mysql，生成实体、dao、XML Mapper文件