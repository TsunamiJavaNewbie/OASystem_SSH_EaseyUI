package com.tsunami.oa.web.system.service;

import java.util.List;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.web.system.entity.SysRole;

public interface SysRoleService {
	
	public void saveOrUpdate(SysRole sysUser);

	public Page<SysRole> find(Page<SysRole> page, SysRole sysRole);

	public SysRole getById(int roleId);

	public void deleteById(Integer... ids);

	public void updateRoleMenu(int roleId, String[] menuIds);

	/**
	 * 所有的角色
	 * 
	 * @return
	 */
	public List<SysRole> findAll();
}
