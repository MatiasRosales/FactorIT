package com.factorit.api;

import com.factorit.model.Carrito;
import com.factorit.model.ErrorResponse;
import com.factorit.model.FetchComprasResponse;
import com.factorit.model.Response;
import com.factorit.model.Response.ResultEnum;
import com.factorit.services.CompraService;
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
public class ComprasApiController implements ComprasApi {
	
	@Autowired
	private CompraService compraService;

    private static final Logger log = LoggerFactory.getLogger(ComprasApiController.class);

    public ResponseEntity<FetchComprasResponse> fetchCompras(@NotNull @ApiParam(value = "parametro desde", required = true) @Valid @RequestParam(value = "from", required = true) String from
,@ApiParam(value = "orden especificado" ,required=true) @RequestHeader(value="order", required=true) String order
,@ApiParam(value = "DNI del usuario." ) @RequestHeader(value="document", required=false) String document
,@ApiParam(value = "parametro hasta") @Valid @RequestParam(value = "to", required = false) String to
) {
    	FetchComprasResponse response = (FetchComprasResponse) new FetchComprasResponse()
    			.result(ResultEnum.OK)
    			.message("Se obtienen las compras del cliente");
            try {
            	List<Carrito> listaCarritos = compraService.getCompras(from,to,document,order);
            	response.setCarritos(listaCarritos);
                return new ResponseEntity<FetchComprasResponse>( response,HttpStatus.OK);
            } catch (ApiException e) {
                log.error("Ocurrio un error al obtener las compras", e);
                response.setMessage("Ocurrio un error al obtener las compras");
                response.setResult(ResultEnum.FAILURE);
                ErrorResponse error = new ErrorResponse().code(e.getCode()).detail(e.getMessage());
                response.setErrors(new ArrayList<ErrorResponse>());
                response.getErrors().add(error);
                return new ResponseEntity<FetchComprasResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }

}
