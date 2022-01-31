package com.factorit.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Producto
 */
@Validated
public class Producto   {
  @JsonProperty("nombre")
  private String nombre = null;

  @JsonProperty("precioUnitario")
  private String precioUnitario = null;

  @JsonProperty("cantidad")
  private String cantidad = null;

  public Producto nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  /**
   * Get nombre
   * @return nombre
  **/
  @ApiModelProperty(value = "")
  
    public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Producto precioUnitario(String precioUnitario) {
    this.precioUnitario = precioUnitario;
    return this;
  }

  /**
   * Get precioUnitario
   * @return precioUnitario
  **/
  @ApiModelProperty(value = "")
  
    public String getPrecioUnitario() {
    return precioUnitario;
  }

  public void setPrecioUnitario(String precioUnitario) {
    this.precioUnitario = precioUnitario;
  }

  public Producto cantidad(String cantidad) {
    this.cantidad = cantidad;
    return this;
  }

  /**
   * Get cantidad
   * @return cantidad
  **/
  @ApiModelProperty(value = "")
  
    public String getCantidad() {
    return cantidad;
  }

  public void setCantidad(String cantidad) {
    this.cantidad = cantidad;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Producto producto = (Producto) o;
    return Objects.equals(this.nombre, producto.nombre) &&
        Objects.equals(this.precioUnitario, producto.precioUnitario) &&
        Objects.equals(this.cantidad, producto.cantidad);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nombre, precioUnitario, cantidad);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Producto {\n");
    
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    precioUnitario: ").append(toIndentedString(precioUnitario)).append("\n");
    sb.append("    cantidad: ").append(toIndentedString(cantidad)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
