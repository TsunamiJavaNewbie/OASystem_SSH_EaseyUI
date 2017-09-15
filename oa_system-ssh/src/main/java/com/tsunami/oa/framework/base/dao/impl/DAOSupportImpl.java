package com.tsunami.oa.framework.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.framework.base.dao.DAOSupport;

public class DAOSupportImpl<T, PK extends Serializable> extends HibernateDaoSupport implements DAOSupport<T, PK> {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected SessionFactory sessionFactory;

	protected Session session;

	protected Class<?> entityClass;

	public DAOSupportImpl() {
		this.entityClass = (Class<?>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Autowired
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public void saveOrUpdate(T entity) {
		Assert.notNull(entity);
		super.getHibernateTemplate().saveOrUpdate(entity);
		logger.info("save or update entity: {}", entity);
	}

	public void delete(T entity) {
		Assert.notNull(entity);
		super.getHibernateTemplate().delete(entity);
		logger.info("delete entity: {}", entity);
	}

	public void delete(PK id) {
		Assert.notNull(id);
		delete(get(id));
	}

	public List<T> findAll() {
		return findByCriteria();
	}

	public Page<T> findAll(Page<T> page) {
		return findByCriteria(page);
	}

	@SuppressWarnings("unchecked")
	public T get(final PK id) {
		return (T) super.getHibernateTemplate().get(entityClass, id);
	}

	@SuppressWarnings("rawtypes")
	public List find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}


	public Page<T> find(Page<T> page, String hql, Object... values) {
		Assert.notNull(page);
		Long count = countByHql(hql, values);
		page.setTotalCount(count.intValue());
		Query q = createQuery(hql, values);
		q.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize());
		q.setMaxResults(page.getPageSize());
		page.setResults(q.list());
		return page;
	}

	/**
	 * <根据HQL得到记录数>
	 * 
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            不定参数的Object数组
	 * @return 记录总数
	 * @see com.itv.launcher.util.IBaseDao#countByHql(java.lang.String,
	 *      java.lang.Object[])
	 */
	public Long countByHql(String hql, Object... values) {
		hql = "select count(*) " + hql.substring(hql.indexOf("from"));
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);

		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return (Long) query.uniqueResult();
	}
	
	/**
	 * Press the HQL query only object .
	 */
	public Object findUnique(String hql, Object... values) {
		return createQuery(hql, values).uniqueResult();
	}

	public Integer findInt(String hql, Object... values) {
		return (Integer) findUnique(hql, values);
	}

	public Long findLong(String hql, Object... values) {
		return (Long) findUnique(hql, values);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<T> findByCriteria(Page page, Criterion... criterion) {
		Assert.notNull(page);
		Criteria c = createCriteria(criterion);
		c.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize());
		c.setMaxResults(page.getPageSize());
		page.setResults(c.list());
		return page;
	}

	/**
	 * Find a list of objects by property .
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName);
		return createCriteria(Restrictions.eq(propertyName, value)).list();
	}

	@SuppressWarnings("unchecked")
	public T findUniqueByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(Restrictions.eq(propertyName, value)).uniqueResult();
	}

	public Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString);
		Query queryObject = this.getSessionFactory().getCurrentSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}

	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	public boolean isPropertyUnique(String propertyName, Object newValue, Object orgValue) {
		if (newValue == null || newValue.equals(orgValue))
			return true;

		Object object = findUniqueByProperty(propertyName, newValue);
		return (object == null);
	}
	
	/**
	 * 使用sql语句进行操作
	 * @param sql
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findListEntityBySQL(String sql, Object... values) {
		SQLQuery query = (SQLQuery) this.getSessionFactory().getCurrentSession().createSQLQuery(sql)
				.addEntity(entityClass);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findListBySQL(String sql, Object... values) {
		SQLQuery query = (SQLQuery) this.getSessionFactory().getCurrentSession().createSQLQuery(sql)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page findBySQL(Page page, String sql, Object... values) {
		Assert.notNull(page);
		Long count = countBySQL(sql, values);
		page.setTotalCount(count.intValue());
		SQLQuery q = (SQLQuery) createQueryBySQL(sql, values).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		q.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize());
		q.setMaxResults(page.getPageSize());
		page.setResults(q.list());
		return page;
	}

	/**
	 * <根据HQL得到记录数>
	 * 
	 * @param sql
	 *            SQL语句
	 * @param values
	 *            不定参数的Object数组
	 * @return 记录总数
	 * @see com.itv.launcher.util.IBaseDao#countByHql(java.lang.String,
	 *      java.lang.Object[])
	 */
	public Long countBySQL(String sql, Object... values) {
		sql = "select count(*) " + sql.substring(sql.indexOf("from"));
		SQLQuery query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return Long.parseLong(query.uniqueResult().toString());
	}
	
	public SQLQuery createQueryBySQL(String sql, Object... values) {
		Assert.hasText(sql);
		SQLQuery sqlQuery = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				sqlQuery.setParameter(i, values[i]);
			}
		}
		return sqlQuery;
	}
	
}
