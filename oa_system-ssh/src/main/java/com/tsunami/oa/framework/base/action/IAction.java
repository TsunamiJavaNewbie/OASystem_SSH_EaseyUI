package com.tsunami.oa.framework.base.action;

import com.opensymphony.xwork2.ModelDriven;

public interface IAction<T> extends ModelDriven<T> {

	public String input() throws Exception;

	public void saveOrUpdate() throws Exception;

	public void delete() throws Exception;

	public void list() throws Exception;
	
}
