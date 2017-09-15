package com.tsunami.oa.web.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.framework.base.dao.impl.DAOSupportImpl;
import com.tsunami.oa.web.system.dao.SysDepartmentDAO;
import com.tsunami.oa.web.system.entity.SysDepartment;

@Repository
public class SysDepartmentDAOImpl extends DAOSupportImpl<SysDepartment, Integer> implements
		SysDepartmentDAO {

	public Page<SysDepartment> find(Page<SysDepartment> page,
			SysDepartment sysDepartment) {
		String hql = "from  SysDepartment where status = 1 ";
		page = this.find(page, hql);
		return page;	}

	public List<SysDepartment> find(int parentId) {
		String sql = "select * from  sys_department where pid= ?";
		List<SysDepartment> departments = this.findListEntityBySQL(sql, parentId);
		return departments;
	}

}
