package com.facorit.utils;

public enum ErrorCodes {
	
	E0001("No se encontrĂ³ al cliente"),
	E0002("No se encontrĂ³ el carrito");
	
	
	private String description;
	
	private ErrorCodes(String desc) {
		this.description = desc;
	}
	
	public String getDescription() {
		return description;
	}
}
