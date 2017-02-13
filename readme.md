# mybatis-geneator-tool
## 介绍
- 可通过此工具自动生成Mybatis XML文件和dao的class类
- 可结合BaseDao完成基础操作，BaseDao支持的方法如下
<pre>
/**
 * 数据访问层基础支撑接口
 * @author shiyu
 *
 * @param <T>
 */
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
</pre>
