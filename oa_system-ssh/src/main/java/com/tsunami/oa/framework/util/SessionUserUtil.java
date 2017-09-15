package com.tsunami.oa.framework.util;

import org.apache.struts2.ServletActionContext;

import com.tsunami.oa.web.system.entity.SysUser;


public class SessionUserUtil {

	public static final String SESSION_SYSUSER_KEY = "SESSION_SYSUSER_KEY";

	public static SysUser getOnlineUser() {
		SysUser sysUser = (SysUser) ServletActionContext.getRequest().getSession().getAttribute(SESSION_SYSUSER_KEY);
		return sysUser;
	}

	/**
	 * 保存用户信息到sesson
	 * 
	 * @param sysUser
	 */
	public static void setOnlineUser(SysUser sysUser) {
		ServletActionContext.getRequest().getSession().setAttribute(SESSION_SYSUSER_KEY, sysUser);
	}

	public static void removeOnlineUser() {
		ServletActionContext.getRequest().getSession().removeAttribute(SESSION_SYSUSER_KEY);
	}
}
