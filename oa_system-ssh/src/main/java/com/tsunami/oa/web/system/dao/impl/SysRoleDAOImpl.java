package com.tsunami.oa.web.system.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.framework.base.dao.impl.DAOSupportImpl;
import com.tsunami.oa.web.system.dao.SysRoleDAO;
import com.tsunami.oa.web.system.entity.SysRole;

@Repository
public class SysRoleDAOImpl extends DAOSupportImpl<SysRole, Integer> implements SysRoleDAO {

	public Page<SysRole> page(Page<SysRole> page, SysRole sysRole) {
		String sql = "select id,role_name,role_code,status, date_format(t.create_time,'%Y-%m-%d') create_time from sys_role t where 1=1 ";
		if (StringUtils.isNotBlank(sysRole.getRoleName())) {
			sql += " and t.role_name like '%" + sysRole.getRoleName() + "%'";
		}
		if (StringUtils.isNotBlank(sysRole.getRoleCode())) {
			sql += " and t.role_code = '" + sysRole.getRoleCode() + "'";
		}
		return this.findBySQL(page, sql);
	}

	

}
