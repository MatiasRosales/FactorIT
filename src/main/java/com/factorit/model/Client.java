package com.factorit.model;

public class Client {
	private Integer clientId;
	private String nombre;
	private String apellido;
	private String dni;
	private Integer tipo_cliente;
	
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Integer getTipo_cliente() {
		return tipo_cliente;
	}
	public void setTipo_cliente(Integer tipo_cliente) {
		this.tipo_cliente = tipo_cliente;
	}

}
