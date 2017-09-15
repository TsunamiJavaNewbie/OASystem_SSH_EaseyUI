package com.tsunami.oa.web.system.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.tsunami.oa.framework.util.SessionUserUtil;
import com.tsunami.oa.web.system.entity.SysMenu;
import com.tsunami.oa.web.system.entity.SysUser;
import com.tsunami.oa.web.system.service.SysMenuService;
import com.tsunami.oa.web.system.service.SysUserService;

@Controller
public class IndexAction extends ActionSupport{
	
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysUserService sysUserService;

	private String username;
	private String password;

	@Override
	public String execute() throws Exception {
		List<SysMenu> menus = sysMenuService.getByPid(0);
		
		/*��Զ���ϰ
		��¼�����ݵ�¼��Ա�����ҳ����Ľ�ɫ����ͨ����ɫ�ҳ��˵�����ʾ������Ĳ˵���*/
		
		ServletActionContext.getRequest().setAttribute("menus", menus);
		return this.SUCCESS;
	}

	public void login() throws Exception {
		if (StringUtils.isBlank(username))
			username = ServletActionContext.getRequest().getParameter("username");
		if (StringUtils.isBlank(password))
			password = ServletActionContext.getRequest().getParameter("password");
		
		SysUser sysUser = sysUserService.getSysUser(username);
		JSONObject json = new JSONObject();
		if (null == sysUser) {
			json.element("status", false);
			json.element("msg", "�û���������...");
		} else if (!password.equals(sysUser.getPassword())) {
			json.element("status", false);
			json.element("msg", "��������...");
		} else {
			SessionUserUtil.setOnlineUser(sysUser);
			json.element("status", true);
			json.element("msg", "index.action");
		}
		ServletActionContext.getResponse().getWriter().write(json.toString());
		ServletActionContext.getResponse().getWriter().close();
	}

	public void logout() throws Exception {
		SessionUserUtil.removeOnlineUser();
		//redirect("login.jsp");
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
