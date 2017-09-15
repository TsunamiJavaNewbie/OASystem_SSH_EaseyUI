package com.tsunami.oa.web.system.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="sys_department")
public class SysDepartment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "department_name")
	private String departmentName;
	@Column(name = "addr")
	private String addr;
	@Column(name = "status")
	private int status;
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 多對一的父节点
	 */
	@ManyToOne(cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	@JoinColumn(name="pid")
	private SysDepartment parent;
	/**
	 * 一对多的子节点
	 */
	@OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY,mappedBy="parent")
	private List<SysDepartment> departments = new ArrayList<SysDepartment>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public SysDepartment getParent() {
		return parent;
	}
	public void setParent(SysDepartment parent) {
		this.parent = parent;
	}
	public List<SysDepartment> getDepartments() {
		return departments;
	}
	public void setDepartments(List<SysDepartment> departments) {
		this.departments = departments;
	}
	
}
