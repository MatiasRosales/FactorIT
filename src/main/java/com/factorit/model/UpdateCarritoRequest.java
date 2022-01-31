package com.factorit.model;

import java.util.Objects;
import com.factorit.model.Carrito;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UpdateCarritoRequest
 */
@Validated
public class UpdateCarritoRequest   {
  @JsonProperty("carrito")
  private Carrito carrito = null;

  public UpdateCarritoRequest carrito(Carrito carrito) {
    this.carrito = carrito;
    return this;
  }

  /**
   * Get carrito
   * @return carrito
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Carrito getCarrito() {
    return carrito;
  }

  public void setCarrito(Carrito carrito) {
    this.carrito = carrito;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateCarritoRequest updateCarritoRequest = (UpdateCarritoRequest) o;
    return Objects.equals(this.carrito, updateCarritoRequest.carrito);
  }

  @Override
  public int hashCode() {
    return Objects.hash(carrito);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateCarritoRequest {\n");
    
    sb.append("    carrito: ").append(toIndentedString(carrito)).append("\n");
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
