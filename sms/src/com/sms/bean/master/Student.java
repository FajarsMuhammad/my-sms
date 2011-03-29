package com.sms.bean.master;

import java.sql.Timestamp;

public class Student {
	
	private static final long serialVersionUID = 4200220007044449417L;
	private long id;
	private String nis;
	private String name;
	private String address;
	
	private Timestamp createdDate;
	private Integer deleted;
	
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}




	public String getNis() {
		return nis;
	}


	public void setNis(String nis) {
		this.nis = nis;
	}


	public Timestamp getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}


	public Integer getDeleted() {
		return deleted;
	}


	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Student(){}


	



}
