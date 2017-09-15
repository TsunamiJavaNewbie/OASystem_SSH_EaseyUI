package com.tsunami.oa.web.system.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.framework.base.dao.impl.DAOSupportImpl;
import com.tsunami.oa.web.system.dao.SysUserDAO;
import com.tsunami.oa.web.system.entity.SysUser;

@Repository
public class SysUserDAOImpl extends DAOSupportImpl<SysUser, Integer> implements SysUserDAO {

	public Page<SysUser> find(Page<SysUser> page, SysUser sysUser) {
		String sql="select u.id,u.username,u.password,u.sex,u.mobile,u.status,date_format(u.create_time,'%Y-%m-%d') create_time,d.department_name from sys_user u ,sys_department d where u.department_id=d.id ";
		if(StringUtils.isNoneBlank(sysUser.getUsername())){
			sql += " and u.username like  '%" + sysUser.getUsername() + "%'";
		}
		return this.findBySQL(page, sql);
	}

}
