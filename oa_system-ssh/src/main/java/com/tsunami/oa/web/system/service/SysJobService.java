package com.tsunami.oa.web.system.service;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.web.system.entity.SysJob;

public interface SysJobService {
	
	public void saveOrUpdate(SysJob sysJob);
	
	public Page<SysJob> find(Page<SysJob> page,SysJob sysJob);
	
	public SysJob getById(int id);
	
	public void deleteById(Integer... ids);
}
