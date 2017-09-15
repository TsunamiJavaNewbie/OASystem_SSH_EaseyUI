package com.tsunami.oa.web.system.action.system;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tsunami.oa.framework.base.action.BaseAction;
import com.tsunami.oa.framework.base.action.IAction;
import com.tsunami.oa.web.system.entity.SysDepartment;
import com.tsunami.oa.web.system.service.SysDepartmentService;

@Controller
public class SysDepartmentAction extends BaseAction implements IAction<SysDepartment> {

	private SysDepartment sysDepartment=new SysDepartment();
	
	@Autowired
	private SysDepartmentService sysDepartmentService;
	
	public SysDepartment getModel() {
		return sysDepartment;
	}

	@Override
	public String input() throws Exception {
		if(sysDepartment.getId()>0){
			sysDepartment=sysDepartmentService.getById(sysDepartment.getId());
		}
		return this.INPUT;
	}
	
	public void saveOrUpdate() throws Exception {
		sysDepartmentService.saveOrUpdate(sysDepartment);
		this.push(true);
	}

	public void delete() throws Exception {
		sysDepartmentService.deleteById(sysDepartment.getId());
		this.push(true);
	}

	public void list() throws Exception {
		page = sysDepartmentService.find(page, sysDepartment);
		this.push(page);
	}
	
	public void tree() throws Exception{
		JSONArray array = sysDepartmentService.getJSONArrayByPId(0);
		this.push(array);
	}

}
