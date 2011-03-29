package com.sms.common.constant;

public enum PageConstant {
	FIRST_RESULT(0), MAX_RESULTS(10);
	
	private PageConstant(int value){
		this.value = value;
	}
	
	private int value;
	
	public int getValue() {
		return value;
	}
}
