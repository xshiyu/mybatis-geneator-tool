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

	/**
	 * 根据实体对象新增记录.
	 * 
	 * @param entity
	 *            .
	 * @return id 返回插入数据的ID
	 */
	long insert(T entity);

	/**
	 * 批量保存对象.
	 * 
	 * @param entity
	 *            .
	 * @return id 返回插入的行数
	 */
	long insert(List<T> list);

	/**
	 * 更新实体对应的记录.
	 * 
	 * @param entity
	 *            .
	 * @return
	 */
	long update(T entity);

	/**
	 * 根据ID查找记录.
	 * 
	 * @param id
	 *            .
	 * @return entity .
	 */
	T getById(long id);

	/**
	 * 根据ID删除记录.
	 * 
	 * @param id
	 *            .
	 * @return
	 */
	int deleteById(long id);

	/**
	 * 分页查询 .
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param paramMap
	 *            业务条件查询参数.
	 * @return
	 */
	PageBean<T> listPage(PageParam pageParam, Map<String, Object> paramMap);

	PageBean<T> listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId);

	/**
	 * 根据条件查询 listBy: <br/>
	 * 
	 * @param paramMap
	 * @return 返回集合
	 */
	List<T> listBy(Map<String, Object> paramMap);

	List<T> listBy(Map<String, Object> paramMap, String sqlId);

	/**
	 * 根据条件查询 listBy: <br/>
	 * 
	 * @param paramMap
	 * @return 返回实体
	 */
	T getBy(Map<String, Object> paramMap);

	T getBy(Map<String, Object> paramMap, String sqlId);

	SqlSessionTemplate getSessionTemplate();

	SqlSession getSqlSession();
}
</pre>
