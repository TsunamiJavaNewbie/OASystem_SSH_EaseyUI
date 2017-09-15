package com.tsunami.oa.web.system.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.framework.base.dao.impl.DAOSupportImpl;
import com.tsunami.oa.web.system.dao.SysJobDAO;
import com.tsunami.oa.web.system.entity.SysJob;

@Repository
public class SysJobDAOImpl extends DAOSupportImpl<SysJob, Integer> implements SysJobDAO {

	public Page<SysJob> find(Page<SysJob> page, SysJob sysJob) {
		String sql="select id,job_name,job_code,status,date_format(create_time,'%Y-%m-%d') create_time from sys_job where 1=1";
		if(StringUtils.isNoneBlank(sysJob.getJobName())){
			sql +=" and j.job_name like  '%" + sysJob.getJobName() + "%'";
		}
		page = this.findBySQL(page, sql);
		return page;
	}
	
}
