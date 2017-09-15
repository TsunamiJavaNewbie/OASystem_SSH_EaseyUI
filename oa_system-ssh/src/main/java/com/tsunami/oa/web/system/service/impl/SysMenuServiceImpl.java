package com.tsunami.oa.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.framework.util.DateUtils;
import com.tsunami.oa.web.system.dao.SysMenuDAO;
import com.tsunami.oa.web.system.entity.SysMenu;
import com.tsunami.oa.web.system.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDAO sysMenuDAO;
	
	public void saveOrUpdate(SysMenu sysMenu) {
		sysMenuDAO.saveOrUpdate(sysMenu);
	}

	public Page<SysMenu> find(Page<SysMenu> page, SysMenu sysMenu) {
		return sysMenuDAO.find(page, sysMenu);
	}

	public SysMenu getById(int id) {
		return sysMenuDAO.get(id);
	}

	public void deleteById(Integer... ids) {
		for (Integer integer : ids) {
			sysMenuDAO.delete(integer);
		}
	}

	public JSONArray getJSONArrayByPId(int pid) {
		List<SysMenu> sysMenus=sysMenuDAO.find(pid);
		return getNode(sysMenus);
	}
	
	public JSONArray getNode(List<SysMenu> sysMenus){
		JSONArray array = new JSONArray();
		if(null!=sysMenus&&sysMenus.size()>0){
			JSONObject json = new JSONObject();
			for (SysMenu sysMenu : sysMenus) {
				json.element("id", sysMenu.getId());
				json.element("menuName", sysMenu.getMenuName());
				json.element("text", sysMenu.getMenuName());
				json.element("status", sysMenu.getStatus());
				json.element("url", sysMenu.getUrl());
				json.element("createTime", DateUtils.DateToDate(sysMenu.getCreateTime()));
				json.element("children", getNode(sysMenu.getMenus()));
				array.add(json);
			}
		}
		return array;
	}

	public List<SysMenu> getByPid(int id) {
		return sysMenuDAO.find(id);
	}

}
