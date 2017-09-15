package com.tsunami.oa.framework.util;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

public class AjaxUtil {

	// ajax·µ»Øtext
	public static void ajaxResponse(String text) {
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(text);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}