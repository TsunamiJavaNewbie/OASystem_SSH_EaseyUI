package com.tsunami.oa.web.system.dao;

import java.util.List;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.framework.base.dao.DAOSupport;
import com.tsunami.oa.web.system.entity.SysDepartment;

public interface SysDepartmentDAO extends DAOSupport<SysDepartment, Integer> {

	public Page<SysDepartment> find(Page<SysDepartment> page,SysDepartment sysDepartment);
	
	/**
	 * 提供一个父ID 返回一堆的节点
	 * @param parentId
	 * @return
	 */
	public List<SysDepartment> find(int parentId);
}
