package com.factorit.api;

public class ApiException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String code;
    public ApiException (String code, String msg) {
        super(msg);
        this.code = code;
    }
    
    public String getCode() {
		return code;
	}
}
