/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.16).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.factorit.api;

import com.factorit.model.CreateCarritoResponse;
import com.factorit.model.FetchCarritoResponse;
import com.factorit.model.Response;
import com.factorit.model.UpdateCarritoRequest;
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
@Api(value = "carrito", description = "the carrito API")
public interface CarritoApi {

    @ApiOperation(value = "Creacion de un carrito.", nickname = "createCart", notes = "", response = CreateCarritoResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Se crea un nuevo carrito", response = CreateCarritoResponse.class),
        @ApiResponse(code = 400, message = "Bad request, parameter has invalid values, or request otherwise malformed", response = Response.class),
        @ApiResponse(code = 404, message = "Operation was not found", response = Response.class),
        @ApiResponse(code = 500, message = "Internal Server Error.", response = Response.class) })
    @RequestMapping(value = "/carrito",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<CreateCarritoResponse> createCart(@ApiParam(value = "ID del usuario." ) @RequestHeader(value="userId", required=false) String userId
,@ApiParam(value = "tipo de carrito" ) @RequestHeader(value="tipo", required=false) String tipo
);


    @ApiOperation(value = "cancela un carrito", nickname = "deleteCarrito", notes = "", response = Response.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Se cancelo la orden de compra", response = Response.class),
        @ApiResponse(code = 400, message = "Bad request, parameter has invalid values, or request otherwise malformed", response = Response.class),
        @ApiResponse(code = 404, message = "Operation was not found", response = Response.class),
        @ApiResponse(code = 500, message = "Internal Server Error.", response = Response.class) })
    @RequestMapping(value = "/carrito",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Response> deleteCarrito(@ApiParam(value = "ID del carrito." ,required=true) @RequestHeader(value="carritoId", required=true) String carritoId
);


    @ApiOperation(value = "Retorna el estado del carrito de un cliente", nickname = "fetchCarrito", notes = "", response = FetchCarritoResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Se obtienen las compras realizadas del usuario", response = FetchCarritoResponse.class),
        @ApiResponse(code = 400, message = "Bad request, parameter has invalid values, or request otherwise malformed", response = Response.class),
        @ApiResponse(code = 404, message = "Operation was not found", response = Response.class),
        @ApiResponse(code = 500, message = "Internal Server Error.", response = Response.class) })
    @RequestMapping(value = "/carrito",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<FetchCarritoResponse> fetchCarrito(@ApiParam(value = "id del carrito" ,required=true) @RequestHeader(value="carritoId", required=true) String carritoId
);


    @ApiOperation(value = "Actualiza informacion del carrito", nickname = "updateCarrito", notes = "", response = FetchCarritoResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Se actualizo el carrito", response = FetchCarritoResponse.class),
        @ApiResponse(code = 400, message = "Bad request, parameter has invalid values, or request otherwise malformed", response = Response.class),
        @ApiResponse(code = 404, message = "Operation was not found", response = Response.class),
        @ApiResponse(code = 500, message = "Internal Server Error.", response = Response.class) })
    @RequestMapping(value = "/carrito",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PATCH)
    ResponseEntity<FetchCarritoResponse> updateCarrito(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UpdateCarritoRequest body
,@ApiParam(value = "id de carrito." ,required=true) @RequestHeader(value="carritoId", required=true) String carritoId
);

}
