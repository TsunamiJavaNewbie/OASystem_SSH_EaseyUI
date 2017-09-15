package com.tsunami.oa.web.system.dao;

import java.util.List;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.framework.base.dao.DAOSupport;
import com.tsunami.oa.web.system.entity.SysRole;

public interface SysRoleDAO extends DAOSupport<SysRole, Integer> {

	public Page<SysRole> page(Page<SysRole> page,SysRole sysRole);
	
	/**
	 * ËùÓÐ½ÇÉ«
	 */
	public List<SysRole> findAll();
}
