package com.tsunami.oa.web.system.service;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.web.system.entity.SysUser;

public interface SysUserService{
	public void saveOrUpdate(SysUser sysUser);
	
	public Page<SysUser> find(Page<SysUser> page,SysUser sysUser);
	
	public SysUser getById(int userId);
	
	public void daleteById(Integer... ids);
	
	public SysUser getSysUser(String name);
	
	/**
	 * 更新用户与角色
	 * @param userId
	 * @param roleId
	 */
	public void updateUserRole(int userId,String[] roleId);

}
