package com.tsunami.oa.framework.base.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.tsunami.oa.framework.base.Page;
import com.tsunami.oa.framework.util.JsonDateValueProcessor;

public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	private Logger logger = Logger.getLogger(BaseAction.class);
	protected HttpServletRequest request = null;
	protected HttpServletResponse response = null;
	protected Integer id;
	protected String ids;
	@SuppressWarnings("rawtypes")
	protected Page page;
	
	/* 上传文件 */
	private File[] upload;
	private String[] uploadFileName;
	private String[] uploadContentType;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		page = new Page(request);
		String windowid = request.getParameter("windowid");
		request.setAttribute("windowid", windowid);
	}

	/**
	 * 发送跳转
	 * 
	 * @param httpUrl
	 */
	protected void redirect(String httpUrl) {
		try {
			response.sendRedirect(httpUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送内容到浏览器，通过ajax接收的
	 * 
	 * @param obj
	 */
	protected void push(Object obj) {
		String text = "";
		/**
		 * 如果是发送Boolean类型
		 */
		JSONObject json = new JSONObject();
		try {
			if (obj instanceof Boolean) {
				if (Boolean.parseBoolean(obj.toString())) {
					json.put("status", true);
					json.put("msg", "操作成功！");
				} else {
					json.put("status", false);
					json.put("msg", "操作失败！");
				}
				text = json.toString();
				/**
				 * 如果是发送是数组类型
				 */
			} else if (obj instanceof String) {
				json.put("status", false);
				json.put("msg", obj.toString());
				text = json.toString();
			} else if (obj instanceof List || obj instanceof JSONArray) {
				/**
				 * 如果发送是page类型
				 */
				text = JSONArray.fromObject(obj).toString();
			} else if (obj instanceof Page) {
				page = (Page) obj;
				json.put("total", page.getTotalCount());
				// String str =
				// JSONArray.toJSONStringWithDateFormat(pageResults.getResults(),
				// "yyyy-MM-dd HH:mm:ss");
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
				json.element("rows", JSONArray.fromObject(page.getResults()), jsonConfig);
				text = json.toString();
			} else if (obj instanceof JSONObject) {
				text = obj.toString();
			} else {
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
				text = JSONObject.fromObject(obj).toString();
			}
			logger.debug("Ajax返回数据：" + text);
			System.out.println("Ajax返回数据：" + text);
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(text);
			response.getWriter().close();
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	
}
