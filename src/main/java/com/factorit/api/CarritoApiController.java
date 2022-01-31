package com.factorit.api;

import com.factorit.model.Carrito;
import com.factorit.model.CreateCarritoResponse;
import com.factorit.model.ErrorResponse;
import com.factorit.model.FetchCarritoResponse;
import com.factorit.model.Response;
import com.factorit.model.Response.ResultEnum;
import com.factorit.model.UpdateCarritoRequest;
import com.factorit.services.CarritoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CarritoApiController implements CarritoApi {

    private static final Logger log = LoggerFactory.getLogger(CarritoApiController.class);

    
    @Autowired
    private CarritoService carritoService;

    public ResponseEntity<CreateCarritoResponse> createCart(@ApiParam(value = "ID del usuario." ) @RequestHeader(value="userId", required=false) String userId
,@ApiParam(value = "tipo de carrito" ) @RequestHeader(value="tipo", required=false) String tipo
) {
        	CreateCarritoResponse response = (CreateCarritoResponse) new CreateCarritoResponse()
                	.result(ResultEnum.OK)
                	.message("Se creo el carrito");
            try {
            	Carrito cart =carritoService.createCarrito(userId, tipo);
            	response.setCarrito(cart);
                return new ResponseEntity<CreateCarritoResponse>(response, HttpStatus.OK);
            } catch (ApiException e) {
                log.error("Ocurrio un error al crear el alta de un carrito", e);
                response.setMessage("Ocurrio un error al crear el alta de un carrito");
                response.setResult(ResultEnum.FAILURE);
                ErrorResponse error = new ErrorResponse().code(e.getCode()).detail(e.getMessage());
                response.setErrors(new ArrayList<ErrorResponse>());
                response.getErrors().add(error);
                return new ResponseEntity<CreateCarritoResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    public ResponseEntity<Response> deleteCarrito(@ApiParam(value = "ID del carrito." ,required=true) @RequestHeader(value="carritoId", required=true) String carritoId
) {
        	Response response = new Response()
        			.result(ResultEnum.OK)
        			.message("Se elimino el carrito");
            try {
            	carritoService.eliminarCarrito(carritoId);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            } catch (ApiException e) {
                log.error("No se pudo eliminar el carrito", e);
                response.setMessage("Ocurrio un error al eliminar el carrito");
                response.setResult(ResultEnum.FAILURE);
                ErrorResponse error = new ErrorResponse().code(e.getCode()).detail(e.getMessage());
                response.setErrors(new ArrayList<ErrorResponse>());
                response.getErrors().add(error);
                return new ResponseEntity<Response>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    public ResponseEntity<FetchCarritoResponse> fetchCarrito(@ApiParam(value = "id del carrito" ,required=true) @RequestHeader(value="carritoId", required=true) String carritoId
) {
        	FetchCarritoResponse response = (FetchCarritoResponse) new FetchCarritoResponse()
        			.result(ResultEnum.OK)
        			.message("Se obtuvo info del carrito");
            try {
            	Carrito carrito = carritoService.fetchCarrito(carritoId);
            	response.setCarrito(carrito);
                return new ResponseEntity<FetchCarritoResponse>(response, HttpStatus.NOT_IMPLEMENTED);
            } catch (ApiException e) {
                log.error("No se pudo obtener informacion del carrito", e);
                response.setResult(ResultEnum.FAILURE);
                response.setMessage("No se pudo obtener informacion del carrito");
                ErrorResponse error = new ErrorResponse().code(e.getCode()).detail(e.getMessage());
                response.setErrors(new ArrayList<ErrorResponse>());
                response.getErrors().add(error);
                return new ResponseEntity<FetchCarritoResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    public ResponseEntity<FetchCarritoResponse> updateCarrito(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UpdateCarritoRequest body
,@ApiParam(value = "id de carrito." ,required=true) @RequestHeader(value="carritoId", required=true) String carritoId
) {
    	FetchCarritoResponse response = (FetchCarritoResponse) new FetchCarritoResponse()
    			.result(ResultEnum.OK)
    			.message("Se actualizo el carrito");
            try {
            	Carrito carrito = carritoService.patchCarrito(carritoId, body.getCarrito());
            	response.setCarrito(carrito);
            	
                return new ResponseEntity<FetchCarritoResponse>( response,HttpStatus.OK);
            } catch (ApiException e) {
                log.error("No se pudo actualizar el carrito", e);
                response.setResult(ResultEnum.FAILURE);
                response.setMessage("No se pudo actualizar el carrito");
                ErrorResponse error = new ErrorResponse().code(e.getCode()).detail(e.getMessage());
                response.setErrors(new ArrayList<ErrorResponse>());
                response.getErrors().add(error);
                return new ResponseEntity<FetchCarritoResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }

}
