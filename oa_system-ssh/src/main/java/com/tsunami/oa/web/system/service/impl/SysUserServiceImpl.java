package com.tsunami.oa.web.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.web.system.dao.SysRoleDAO;
import com.tsunami.oa.web.system.dao.SysUserDAO;
import com.tsunami.oa.web.system.entity.SysRole;
import com.tsunami.oa.web.system.entity.SysUser;
import com.tsunami.oa.web.system.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDAO sysUserDAO;
	@Autowired
	private SysRoleDAO sysRoleDAO;
	
	public void saveOrUpdate(SysUser sysUser) {
		sysUserDAO.saveOrUpdate(sysUser);
	}

	public Page<SysUser> find(Page<SysUser> page, SysUser sysUser) {
		return sysUserDAO.find(page, sysUser);
	}

	public SysUser getById(int userId) {
		return sysUserDAO.get(userId);
	}

	public void daleteById(Integer... ids) {
		for (Integer integer : ids) {
			sysUserDAO.delete(integer);
		}
	}

	public SysUser getSysUser(String name) {
		return sysUserDAO.findUniqueByProperty("username", name);
	}

	public void updateUserRole(int userId, String[] roleId) {
		SysUser sysUser=sysUserDAO.get(userId);
		sysUser.getRoles().clear();
		for (String string : roleId) {
			SysRole sysRole=sysRoleDAO.get(Integer.parseInt(string));
			sysUser.getRoles().add(sysRole);
		}
		sysUserDAO.saveOrUpdate(sysUser);
	}

}
