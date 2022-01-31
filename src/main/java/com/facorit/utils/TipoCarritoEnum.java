package com.facorit.utils;

public enum TipoCarritoEnum {

	COMUN("1","COMUN"),
	ESPECIAL("2","ESPECIAL");
	
	
	private String id;
	private String descripcion;
	
	
	private TipoCarritoEnum(String id,String descripcion) {
		this.id= id	;
		this.descripcion = descripcion;
	}
	
	public String getId() {
		return id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public static String nameOf(String id) {
		for(TipoCarritoEnum tipo: TipoCarritoEnum.values()) {
			if(tipo.getId().equals(id)) {
				return tipo.getDescripcion();
			}
		}
		return id;
	}
}
