package com.tsunami.oa.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.web.system.dao.SysMenuDAO;
import com.tsunami.oa.web.system.dao.SysRoleDAO;
import com.tsunami.oa.web.system.entity.SysMenu;
import com.tsunami.oa.web.system.entity.SysRole;
import com.tsunami.oa.web.system.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDAO sysRoleDAO;
	@Autowired 
	private SysMenuDAO sysMenuDAO;
	
	public void saveOrUpdate(SysRole sysUser) {
		sysRoleDAO.saveOrUpdate(sysUser);
	}

	public Page<SysRole> find(Page<SysRole> page, SysRole sysRole) {
		return sysRoleDAO.page(page, sysRole);
	}

	public SysRole getById(int roleId) {
		return sysRoleDAO.get(roleId);
	}

	public void deleteById(Integer... ids) {
		for (Integer integer : ids) {
			sysRoleDAO.delete(integer);
		}
	}

	public void updateRoleMenu(int roleId, String[] menuIds) {
		SysRole role = sysRoleDAO.get(roleId);
		role.getMenus().clear();
		for (String string : menuIds) {
			SysMenu menu=sysMenuDAO.get(Integer.parseInt(string));
			role.getMenus().add(menu);
		}
		sysRoleDAO.saveOrUpdate(role);
	}

	public List<SysRole> findAll() {
		return sysRoleDAO.findAll();
	}

}
