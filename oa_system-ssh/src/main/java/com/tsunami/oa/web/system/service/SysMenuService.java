package com.tsunami.oa.web.system.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.web.system.entity.SysMenu;

public interface SysMenuService {

	public void saveOrUpdate(SysMenu sysMenu);

	public Page<SysMenu> find(Page<SysMenu> page, SysMenu sysMenu);

	public SysMenu getById(int id);

	public void deleteById(Integer... ids);

	public JSONArray getJSONArrayByPId(int pid);

	public List<SysMenu> getByPid(int id);

}
