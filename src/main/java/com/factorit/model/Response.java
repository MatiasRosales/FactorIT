package com.factorit.model;

import java.util.Objects;
import com.factorit.model.ErrorResponse;
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
 * Response
 */
@Validated
public class Response   {
  /**
   * Outcome of the operation
   */
  public enum ResultEnum {
    OK("OK"),
    
    WARN("WARN"),
    
    FAILURE("FAILURE");

    private String value;

    ResultEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ResultEnum fromValue(String text) {
      for (ResultEnum b : ResultEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("result")
  private ResultEnum result = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("errors")
  @Valid
  private List<ErrorResponse> errors = null;

  public Response result(ResultEnum result) {
    this.result = result;
    return this;
  }

  /**
   * Outcome of the operation
   * @return result
  **/
  @ApiModelProperty(value = "Outcome of the operation")
  
    public ResultEnum getResult() {
    return result;
  }

  public void setResult(ResultEnum result) {
    this.result = result;
  }

  public Response message(String message) {
    this.message = message;
    return this;
  }

  /**
   * A message describing the result of the operation
   * @return message
  **/
  @ApiModelProperty(value = "A message describing the result of the operation")
  
    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Response errors(List<ErrorResponse> errors) {
    this.errors = errors;
    return this;
  }

  public Response addErrorsItem(ErrorResponse errorsItem) {
    if (this.errors == null) {
      this.errors = new ArrayList<ErrorResponse>();
    }
    this.errors.add(errorsItem);
    return this;
  }

  /**
   * List of errors that occurred trying to process the request
   * @return errors
  **/
  @ApiModelProperty(value = "List of errors that occurred trying to process the request")
      @Valid
    public List<ErrorResponse> getErrors() {
    return errors;
  }

  public void setErrors(List<ErrorResponse> errors) {
    this.errors = errors;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Response response = (Response) o;
    return Objects.equals(this.result, response.result) &&
        Objects.equals(this.message, response.message) &&
        Objects.equals(this.errors, response.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, message, errors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Response {\n");
    
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
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
