package com.tsunami.oa.framework.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.tsunami.oa.framework.util.AjaxUtil;

public class ActionMethodInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
		String path = request.getRequestURI();
		System.out.println("����·��:" + path);
		String result = "error";
		try {
			result = invocation.invoke();
		} catch (Exception e) {
			System.out.println("struts�����쳣��Ϣ:" + e.getLocalizedMessage());
			AjaxUtil.ajaxResponse("{\"status\":\"false\"}");
			e.printStackTrace();
		}
		return result;
	}
}
