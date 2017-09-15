package com.tsunami.oa.web.system.action.system;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tsunami.oa.framework.base.action.BaseAction;
import com.tsunami.oa.framework.base.action.IAction;
import com.tsunami.oa.web.system.entity.SysJob;
import com.tsunami.oa.web.system.service.SysJobService;

@Controller
public class SysJobAction extends BaseAction implements IAction<SysJob> {

	private SysJob sysJob = new SysJob();
	
	@Autowired
	private SysJobService sysJobService;
	
	public SysJob getModel() {
		return sysJob;
	}
	
	@Override
	public String execute() throws Exception {
		return this.SUCCESS;
	}

	@Override
	public String input() throws Exception {
		if(sysJob.getId()>0){
			sysJob=sysJobService.getById(sysJob.getId());
		}
		return this.INPUT;
	}
	
	public void saveOrUpdate() throws Exception {
		if(sysJob.getId()==0){
			sysJob.setCreateTime(new Date());
		}
		sysJobService.saveOrUpdate(sysJob);
		this.push(true);
	}

	public void delete() throws Exception {
		String[] idsList = ids.split(",");
		Integer[] ints = new Integer[idsList.length];
		for (int i = 0; i < idsList.length; i++) {
			ints[i] = Integer.parseInt(idsList[i]);
		}
		sysJobService.deleteById(ints);
		this.push(true);
	}

	public void list() throws Exception {
		page =sysJobService.find(page, sysJob);
		this.push(page);
	}

}
