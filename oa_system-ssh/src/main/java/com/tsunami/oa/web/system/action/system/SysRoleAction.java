package com.tsunami.oa.web.system.action.system;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tsunami.oa.framework.base.action.BaseAction;
import com.tsunami.oa.framework.base.action.IAction;
import com.tsunami.oa.web.system.entity.SysRole;
import com.tsunami.oa.web.system.service.SysRoleService;

/**
 * http://localhost:8080/oa_system-ssh/system/sys-role.action
 * @author CodingTest
 *
 */
@Controller
public class SysRoleAction extends BaseAction implements IAction<SysRole> {

	private SysRole sysRole=new SysRole();
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Override
	public String execute() throws Exception {
		return this.SUCCESS;
	}
	
	@Override
	public String input() throws Exception {
		if(sysRole.getId()>0){
			sysRole= sysRoleService.getById(sysRole.getId());
		}
		return this.INPUT;
	}
	
	public SysRole getModel() {
		// TODO Auto-generated method stub
		return sysRole;
	}

	public void saveOrUpdate() throws Exception {
		sysRole.setCreateTime(new Date());
		sysRoleService.saveOrUpdate(sysRole);
		this.push(true);
	}

	public void delete() throws Exception {
		String[] idsList = ids.split(",");
		Integer[] ints = new Integer[idsList.length];
		for (int i = 0; i < idsList.length; i++) {
			ints[i] = Integer.parseInt(idsList[i]);
		}
		sysRoleService.deleteById(ints);
		this.push(true);
	}

	public void list() throws Exception {
		page =sysRoleService.find(page, sysRole);
		this.push(page);
	}
	
	public String menu() throws Exception{
		if(sysRole.getId()>0){
			sysRole= sysRoleService.getById(sysRole.getId());
			sysRole.getMenus();
		}
		return "menu";
	}
	
	/**
	 * 保存角色与菜单的关系
	 * @throws Exception
	 */
	public void roleMenu() throws Exception{
		String[] menuIds=request.getParameterValues("menuId");
		sysRoleService.updateRoleMenu(sysRole.getId(), menuIds);
		this.push(true);
	}

}
