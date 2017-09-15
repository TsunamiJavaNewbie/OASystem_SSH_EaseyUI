package com.tsunami.oa.web.system.action.system;

import java.util.Date;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tsunami.oa.framework.base.action.BaseAction;
import com.tsunami.oa.framework.base.action.IAction;
import com.tsunami.oa.web.system.entity.SysMenu;
import com.tsunami.oa.web.system.service.SysMenuService;

/**
 * http://localhost:8080/oa_system-ssh/system/sys-menu.action
 * @author CodingTest
 *
 */
@Controller
public class SysMenuAction extends BaseAction implements IAction<SysMenu> {

	private SysMenu sysMenu = new SysMenu();
	
	@Autowired
	private SysMenuService sysMenuService;
	
	public SysMenu getModel() {
		return sysMenu;
	}
	
	@Override
	public String input() throws Exception {
		if(sysMenu.getId()>0){
			sysMenu=sysMenuService.getById(sysMenu.getId());
		}
		return this.INPUT;
	}

	public void saveOrUpdate() throws Exception {
		sysMenu.setCreateTime(new Date());
		sysMenuService.saveOrUpdate(sysMenu);
		this.push(true);
	}

	public void delete() throws Exception {
		sysMenuService.deleteById(sysMenu.getId());
		this.push(true);
	}

	public void list() throws Exception {
		page = sysMenuService.find(page, sysMenu);
		this.push(page);
	}
	
	public void tree() throws Exception{
		JSONArray array=sysMenuService.getJSONArrayByPId(sysMenu.getId());
		this.push(array);
	}

}
