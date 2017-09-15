package com.tsunami.oa.web.system.action.system;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tsunami.oa.framework.base.action.BaseAction;
import com.tsunami.oa.framework.base.action.IAction;
import com.tsunami.oa.framework.util.ExcelUtil;
import com.tsunami.oa.web.system.entity.SysUser;
import com.tsunami.oa.web.system.service.SysRoleService;
import com.tsunami.oa.web.system.service.SysUserService;

/**
 * http://localhost:8080/oa_system-ssh/system/sys-user.action
 * @author CodingTest
 *
 */
@Controller
public class SysUserAction extends BaseAction implements IAction<SysUser> {

	private SysUser sysUser=new SysUser();

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	
	@Override
	public String execute() throws Exception {
		return this.SUCCESS;
	}
	
	@Override
	public String input() throws Exception {
		if(sysUser.getId()>0){
			sysUser=sysUserService.getById(sysUser.getId());
		}
		return this.INPUT;
	}
	
	public SysUser getModel() {
		// TODO Auto-generated method stub
		return sysUser;
	}

	public void saveOrUpdate() throws Exception {
		if(sysUser.getId()==0){
			sysUser.setCreateTime(new Date());
			sysUser.setUrlPhoto(getUploadFileName()[0]);
		}
		
		System.out.println("上传的文件" + getUpload()[0]);
		System.out.println("上传的文件名" + getUploadFileName()[0]);
		
		sysUserService.saveOrUpdate(sysUser);
		this.push(true);
	}

	public void delete() throws Exception {
		String[] idsList = ids.split(",");
		Integer[] ints = new Integer[idsList.length];
		for (int i = 0; i < idsList.length; i++) {
			ints[i] = Integer.parseInt(idsList[i]);
		}
		sysUserService.daleteById(ints);
		this.push(true);
	}

	public void list() throws Exception {
		page=sysUserService.find(page, sysUser);
		this.push(page);
	}
	
	public String role() throws Exception{
		if(sysUser.getId()>0){
			sysUser=sysUserService.getById(sysUser.getId());
		}
		request.setAttribute("roles", sysRoleService.findAll());
		return "role";
	}
	
	public void roleUser() throws Exception{
		if(sysUser.getId()>0){
			sysUser=sysUserService.getById(sysUser.getId());
		}
		String[] roleId=request.getParameterValues("roleId");
		sysUserService.updateUserRole(sysUser.getId(), roleId);
	}
	
	public void export() throws Exception{
		page = sysUserService.find(page, sysUser);
		List<?> result = page.getResults();
		String[] titles = { "密码", "创建时间", "性别", "部门名称", "手机号码", "id","用户名称","状态" };
		ExcelUtil.export(titles, result);
	}
}
