package com.tsunami.oa.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tsunami.oa.framework.util.SessionUserUtil;
import com.tsunami.oa.web.system.entity.SysUser;

public class SystemFilter implements Filter {

	public SystemFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String path = request.getRequestURI();
		if (path.contains("action") && !path.contains("index!login.action")) {
			System.out.println("SystemFilter：" + path);
//			SysUser sysUser = (SysUser) request.getSession().getAttribute(SessionUserUtil.SESSION_SYSUSER_KEY);
//			if (null == sysUser) {
//				response.sendRedirect(request.getContextPath() + "/login.jsp");
//				return;
//			}
		}
		// 做你要过滤的操作
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
