package com.tsunami.oa.web.system.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.framework.base.dao.impl.DAOSupportImpl;
import com.tsunami.oa.web.system.dao.SysMenuDAO;
import com.tsunami.oa.web.system.entity.SysMenu;

@Repository
public class SysMenuDAOImpl extends DAOSupportImpl<SysMenu, Integer> implements SysMenuDAO {

	public Page<SysMenu> find(Page<SysMenu> page, SysMenu sysMenu) {
		String hql="from SysMenu where status = 1";
		return this.find(page, hql);
	}

	public List<SysMenu> find(int parentId) {
		String sql = "select * from  sys_menu where pid= ?";
		return this.findListEntityBySQL(sql, parentId);
	}

}
