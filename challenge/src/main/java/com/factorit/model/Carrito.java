package com.factorit.model;

import java.util.Objects;
import com.factorit.model.Producto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Carrito
 */
@Validated
public class Carrito   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("fechaCreacion")
  private String fechaCreacion = null;

  /**
   * Estado de carrito
   */
  public enum EstadoEnum {
    ABIERTO("ABIERTO"),
    
    CANCELADO("CANCELADO"),
    
    CERRADO("CERRADO");

    private String value;

    EstadoEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static EstadoEnum fromValue(String text) {
      for (EstadoEnum b : EstadoEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("estado")
  private EstadoEnum estado = null;

  /**
   * Tipo de carrito
   */
  public enum TipoEnum {
    COMUN("COMUN"),
    
    ESPECIAL("ESPECIAL");

    private String value;

    TipoEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TipoEnum fromValue(String text) {
      for (TipoEnum b : TipoEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("tipo")
  private TipoEnum tipo = null;

  @JsonProperty("productos")
  @Valid
  private List<Producto> productos = null;

  @JsonProperty("total")
  private String total = null;

  public Carrito id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Numero de carrito
   * @return id
  **/
  @ApiModelProperty(value = "Numero de carrito")
  
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Carrito fechaCreacion(String fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
    return this;
  }

  /**
   * Fecha de creacion del carrito
   * @return fechaCreacion
  **/
  @ApiModelProperty(value = "Fecha de creacion del carrito")
  
    public String getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(String fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public Carrito estado(EstadoEnum estado) {
    this.estado = estado;
    return this;
  }

  /**
   * Estado de carrito
   * @return estado
  **/
  @ApiModelProperty(value = "Estado de carrito")
  
    public EstadoEnum getEstado() {
    return estado;
  }

  public void setEstado(EstadoEnum estado) {
    this.estado = estado;
  }

  public Carrito tipo(TipoEnum tipo) {
    this.tipo = tipo;
    return this;
  }

  /**
   * Tipo de carrito
   * @return tipo
  **/
  @ApiModelProperty(value = "Tipo de carrito")
  
    public TipoEnum getTipo() {
    return tipo;
  }

  public void setTipo(TipoEnum tipo) {
    this.tipo = tipo;
  }

  public Carrito productos(List<Producto> productos) {
    this.productos = productos;
    return this;
  }

  public Carrito addProductosItem(Producto productosItem) {
    if (this.productos == null) {
      this.productos = new ArrayList<Producto>();
    }
    this.productos.add(productosItem);
    return this;
  }

  /**
   * Get productos
   * @return productos
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<Producto> getProductos() {
    return productos;
  }

  public void setProductos(List<Producto> productos) {
    this.productos = productos;
  }

  public Carrito total(String total) {
    this.total = total;
    return this;
  }

  /**
   * Precio total del carrito
   * @return total
  **/
  @ApiModelProperty(value = "Precio total del carrito")
  
    public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Carrito carrito = (Carrito) o;
    return Objects.equals(this.id, carrito.id) &&
        Objects.equals(this.fechaCreacion, carrito.fechaCreacion) &&
        Objects.equals(this.estado, carrito.estado) &&
        Objects.equals(this.tipo, carrito.tipo) &&
        Objects.equals(this.productos, carrito.productos) &&
        Objects.equals(this.total, carrito.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fechaCreacion, estado, tipo, productos, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Carrito {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    fechaCreacion: ").append(toIndentedString(fechaCreacion)).append("\n");
    sb.append("    estado: ").append(toIndentedString(estado)).append("\n");
    sb.append("    tipo: ").append(toIndentedString(tipo)).append("\n");
    sb.append("    productos: ").append(toIndentedString(productos)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
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
