/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.16).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.factorit.api;

import com.factorit.model.FetchComprasResponse;
import com.factorit.model.Response;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@Api(value = "compras", description = "the compras API")
public interface ComprasApi {

    @ApiOperation(value = "Retorna las compras de un cliente", nickname = "fetchCompras", notes = "", response = FetchComprasResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Compras realizadas del usuario", response = FetchComprasResponse.class),
        @ApiResponse(code = 400, message = "Bad request, parameter has invalid values, or request otherwise malformed", response = Response.class),
        @ApiResponse(code = 404, message = "Operation was not found", response = Response.class),
        @ApiResponse(code = 500, message = "Internal Server Error.", response = Response.class) })
    @RequestMapping(value = "/compras",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<FetchComprasResponse> fetchCompras(@NotNull @ApiParam(value = "parametro desde", required = true) @Valid @RequestParam(value = "from", required = true) String from
,@ApiParam(value = "orden especificado" ,required=true) @RequestHeader(value="order", required=true) String order
,@ApiParam(value = "DNI del usuario." ) @RequestHeader(value="document", required=false) String document
,@ApiParam(value = "parametro hasta") @Valid @RequestParam(value = "to", required = false) String to
);

}
