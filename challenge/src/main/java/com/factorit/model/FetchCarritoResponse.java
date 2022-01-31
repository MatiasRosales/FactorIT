package com.factorit.model;

import java.util.Objects;
import com.factorit.model.Carrito;
import com.factorit.model.ErrorResponse;
import com.factorit.model.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FetchCarritoResponse
 */
@Validated
public class FetchCarritoResponse extends Response  {
  @JsonProperty("carrito")
  private Carrito carrito = null;

  public FetchCarritoResponse carrito(Carrito carrito) {
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
    FetchCarritoResponse fetchCarritoResponse = (FetchCarritoResponse) o;
    return Objects.equals(this.carrito, fetchCarritoResponse.carrito) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(carrito, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FetchCarritoResponse {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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
