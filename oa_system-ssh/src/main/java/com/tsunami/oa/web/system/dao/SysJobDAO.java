package com.tsunami.oa.web.system.dao;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.framework.base.dao.DAOSupport;
import com.tsunami.oa.web.system.entity.SysJob;

public interface SysJobDAO extends DAOSupport<SysJob, Integer>{

	public Page<SysJob> find(Page<SysJob> page,SysJob sysJob);
	
}
