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
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FetchComprasResponse
 */
@Validated
public class FetchComprasResponse extends Response  {
  @JsonProperty("carritos")
  @Valid
  private List<Carrito> carritos = null;

  public FetchComprasResponse carritos(List<Carrito> carritos) {
    this.carritos = carritos;
    return this;
  }

  public FetchComprasResponse addCarritosItem(Carrito carritosItem) {
    if (this.carritos == null) {
      this.carritos = new ArrayList<Carrito>();
    }
    this.carritos.add(carritosItem);
    return this;
  }

  /**
   * Get carritos
   * @return carritos
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<Carrito> getCarritos() {
    return carritos;
  }

  public void setCarritos(List<Carrito> carritos) {
    this.carritos = carritos;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FetchComprasResponse fetchComprasResponse = (FetchComprasResponse) o;
    return Objects.equals(this.carritos, fetchComprasResponse.carritos) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(carritos, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FetchComprasResponse {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    carritos: ").append(toIndentedString(carritos)).append("\n");
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
