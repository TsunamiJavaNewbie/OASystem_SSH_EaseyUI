package com.tsunami.oa.framework.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

import com.tsunami.oa.framework.base.Page;

public interface DAOSupport<T, PK extends Serializable> {
	public void saveOrUpdate(T entity);

	public void delete(T entity);

	public void delete(PK id);

	public List<T> findAll();

	public Page<T> findAll(Page<T> page);

	/**
	 * Gets the object by ID .
	 */
	public T get(final PK id);

	/**
	 * Press the HQL Query object list .
	 * 
	 * @param hql
	 *            hqlStatement
	 * @param values
	 *            Number of variable parameters
	 */
	public List find(String hql, Object... values);

	/**
	 * Press the HQL query paging . No support for automatically gets the total
	 * number of results ,User further execution of the query .
	 * 
	 * @param page
	 *            Paging parameters .Including the pageSize and firstResult .
	 * @param hql
	 *            hqlStatement .
	 * @param values
	 *            Number of variable parameters .
	 * 
	 * @return Paging query results ,Comes with a results list and all query
	 *         parameters .
	 */
	public Page<T> find(Page<T> page, String hql, Object... values);

	/**
	 * Press the HQL query only object .
	 */
	public Object findUnique(String hql, Object... values);

	/**
	 * Press the HQL query results Intger class shape .
	 */
	public Integer findInt(String hql, Object... values);

	/**
	 * According to the results of the HQL Query type long .
	 */
	public Long findLong(String hql, Object... values);

	/**
	 * According to the Criterion query object list .
	 * 
	 * @param criterion
	 *            Number of variable Criterion .
	 */
	public List<T> findByCriteria(Criterion... criterion);

	/**
	 * According to the Criterion paging query .
	 * 
	 * @param page
	 *            Paging parameters .Including the pageSize, firstResult,
	 *            orderBy, asc, autoCount . Where firstResult can be directly
	 *            specified ,You can also specify pageNo . autoCountSpecifies
	 *            whether dynamic gets total number of results .
	 * 
	 * @param criterion
	 *            Number of variable Criterion .
	 * @return Paging query results .Comes with a results list and all query
	 *         parameters .
	 */
	public Page<T> findByCriteria(Page page, Criterion... criterion);
	
	/**
	 * sql∑÷“≥¡–±Ì
	 * @param page
	 * @param sql
	 * @param values
	 * @return
	 */
	public Page findBySQL(Page page, String sql, Object... values);

	/**
	 * Find a list of objects by property .
	 */
	public List<T> findByProperty(String propertyName, Object value);

	/**
	 * Find unique object by property .
	 */
	public T findUniqueByProperty(String propertyName, Object value);

	/**
	 * Depending on the query function and argument list to create a Query
	 * object ,Subsequent to processing ,The auxiliary function .
	 */
	public Query createQuery(String queryString, Object... values);

	/**
	 * According to the Criterion conditions create Criteria ,Subsequent to
	 * processing ,The auxiliary function .
	 */
	public Criteria createCriteria(Criterion... criterions);

	/**
	 * Determine the object's property value is unique within the database .
	 * 
	 */
	public boolean isPropertyUnique(String propertyName, Object newValue, Object orgValue);
}
