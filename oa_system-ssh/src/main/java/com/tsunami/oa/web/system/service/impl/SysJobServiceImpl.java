package com.tsunami.oa.web.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.web.system.dao.SysJobDAO;
import com.tsunami.oa.web.system.entity.SysJob;
import com.tsunami.oa.web.system.service.SysJobService;

@Service
public class SysJobServiceImpl implements SysJobService {

	@Autowired
	private SysJobDAO sysJobDAO;
	
	public void saveOrUpdate(SysJob sysJob) {
		sysJobDAO.saveOrUpdate(sysJob);
	}

	public Page<SysJob> find(Page<SysJob> page, SysJob sysJob) {
		return sysJobDAO.find(page, sysJob);
	}

	public SysJob getById(int id) {
		return sysJobDAO.get(id);
	}

	public void deleteById(Integer... ids) {
		for (Integer integer : ids) {
			sysJobDAO.delete(integer);
		}

	}

}
