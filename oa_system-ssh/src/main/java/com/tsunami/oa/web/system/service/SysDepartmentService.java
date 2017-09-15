package com.tsunami.oa.web.system.service;

import net.sf.json.JSONArray;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.web.system.entity.SysDepartment;

public interface SysDepartmentService {
	public void saveOrUpdate(SysDepartment sysDepartment);

	public Page<SysDepartment> find(Page<SysDepartment> page, SysDepartment sysDepartment);

	public SysDepartment getById(int id);

	public void deleteById(Integer... ids);

	public JSONArray getJSONArrayByPId(int pid);
}
