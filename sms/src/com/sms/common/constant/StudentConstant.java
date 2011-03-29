package com.sms.common.constant;

public enum StudentConstant {
	NIS("Nis", "nis"), NAME("Name", "name"), ADDRESS("Address","address");
	
	private StudentConstant(String label, String value){
		this.label = label;
		this.value = value;
	}
	
	private String label;
	private String value;
	
	public String getLabel() {
		return label;
	}
	public String getValue() {
		return value;
	}
}
