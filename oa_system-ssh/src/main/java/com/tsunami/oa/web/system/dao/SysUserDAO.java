package com.tsunami.oa.web.system.dao;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.framework.base.dao.DAOSupport;
import com.tsunami.oa.web.system.entity.SysUser;

public interface SysUserDAO extends DAOSupport<SysUser, Integer> {
	
	public Page<SysUser> find(Page<SysUser> page,SysUser sysUser);
}
