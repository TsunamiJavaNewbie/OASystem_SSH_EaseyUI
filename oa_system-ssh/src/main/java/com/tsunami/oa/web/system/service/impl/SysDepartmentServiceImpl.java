package com.tsunami.oa.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.framework.util.DateUtils;
import com.tsunami.oa.web.system.dao.SysDepartmentDAO;
import com.tsunami.oa.web.system.entity.SysDepartment;
import com.tsunami.oa.web.system.service.SysDepartmentService;

@Service
public class SysDepartmentServiceImpl implements SysDepartmentService {

	@Autowired
	private SysDepartmentDAO sysDepartmentDAO;
	
	public void saveOrUpdate(SysDepartment sysDepartment) {
		sysDepartmentDAO.saveOrUpdate(sysDepartment);
	}

	public Page<SysDepartment> find(Page<SysDepartment> page,
			SysDepartment sysDepartment) {
		return sysDepartmentDAO.find(page, sysDepartment);
	}

	public SysDepartment getById(int id) {
		return sysDepartmentDAO.get(id);
	}

	public void deleteById(Integer... ids) {
		for (Integer integer : ids) {
			sysDepartmentDAO.delete(integer);
		}
	}

	public JSONArray getJSONArrayByPId(int pid) {
		List<SysDepartment> departments=sysDepartmentDAO.find(pid);
		return getNode(departments);
	}

	/**
	 * µÝ¹é·½·¨Æ´½Ó
	 * 
	 * @param departments
	 * @return
	 */
	private JSONArray getNode(List<SysDepartment> departments) {
		JSONArray array = new JSONArray();
		if (null != departments && departments.size() > 0) {
			JSONObject json = new JSONObject();
			for (SysDepartment sysDepartment : departments) {
				json.element("id", sysDepartment.getId());
				json.element("departmentName", sysDepartment.getDepartmentName());
				json.element("text", sysDepartment.getDepartmentName());
				json.element("status", sysDepartment.getStatus());
				json.element("addr", sysDepartment.getAddr());
				json.element("createTime", DateUtils.DateToDate(sysDepartment.getCreateTime()));
				json.element("children", getNode(sysDepartment.getDepartments()));
				array.add(json);
			}
		}
		return array;
	}

}
