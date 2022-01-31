package com.facorit.utils;

public enum ErrorCodes {
	
	E0001("No se encontró al cliente"),
	E0002("No se encontró el carrito");
	
	
	private String description;
	
	private ErrorCodes(String desc) {
		this.description = desc;
	}
	
	public String getDescription() {
		return description;
	}
}
