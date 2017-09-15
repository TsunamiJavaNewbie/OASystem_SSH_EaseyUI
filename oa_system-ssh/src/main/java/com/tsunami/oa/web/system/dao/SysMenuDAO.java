package com.tsunami.oa.web.system.dao;

import java.util.List;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.framework.base.dao.DAOSupport;
import com.tsunami.oa.web.system.entity.SysMenu;

public interface SysMenuDAO extends DAOSupport<SysMenu, Integer>{

	public Page<SysMenu> find(Page<SysMenu> page,SysMenu sysMenu);
	
	/**
	 * 提供一个父ID 返回一堆的节点
	 * @param parentId
	 * @return
	 */
	public List<SysMenu> find(int parentId);
	
}
