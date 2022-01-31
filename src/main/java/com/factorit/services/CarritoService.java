package com.factorit.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facorit.utils.ErrorCodes;
import com.factorit.api.ApiException;
import com.factorit.dao.CompraDAO;
import com.factorit.model.Carrito;
import com.factorit.model.Carrito.EstadoEnum;
import com.factorit.model.Carrito.TipoEnum;
import com.factorit.model.Producto;
import com.factorit.model.UpdateCarritoRequest;

@Service
public class CarritoService {
	
	@Autowired
	private CompraDAO compraDao;
	
	private static final Logger log = LoggerFactory.getLogger(CarritoService.class);
	
	public Carrito createCarrito(String clientId,String tipoCarrito) {
		log.debug("[ComprasService.createCarrito] creando carrito "+TipoEnum.fromValue(tipoCarrito).name()+" para cliente "+ clientId);
		String idCarrito = UUID.randomUUID().toString();
		if(!existeCliente(clientId)){
			throw new ApiException(ErrorCodes.E0001.name(),ErrorCodes.E0001.getDescription());
		}
		java.sql.Date date= new java.sql.Date(System.currentTimeMillis());
		Timestamp timestamp = new Timestamp(date.getTime());
		String creationDateString = new SimpleDateFormat("dd/MM/yyyy").format(timestamp);
		compraDao.insertCompra(idCarrito, clientId,timestamp.toString(), tipoCarrito);
		
		Carrito cart = new Carrito();
		cart.setId(idCarrito);
		cart.setProductos(new ArrayList<Producto>());
		cart.setFechaCreacion(creationDateString);
		cart.setEstado(EstadoEnum.ABIERTO);
		cart.setTipo(TipoEnum.fromValue(tipoCarrito));
		cart.setTotal("0.00");
		return cart;
	}
	
	private boolean existeCliente(String clientId) {
		return compraDao.verificarCliente(clientId);
	}
	
	private boolean existeCarrito(String idCarrito) {
		return compraDao.verificarCarrito(idCarrito);
	}
	
	public void eliminarCarrito(String carritoId) {
		log.debug("[ComprasService.eliminarCarrito] eliminando carrito "+carritoId);
		if(!existeCarrito(carritoId)) {
			throw new ApiException(ErrorCodes.E0002.name(),ErrorCodes.E0002.getDescription());
		}
		compraDao.cancelarCompra(carritoId);
	}
	
	public Carrito fetchCarrito(String carritoId) {
		log.debug("[ComprasService.fetchCarrito] obteniendo información de carrito "+carritoId);
		return compraDao.fetchCarrito(carritoId);
	}
	
	public Carrito patchCarrito(String carritoId,Carrito carrito) {
		log.debug("[ComprasService.patchCarrito] eliminando carrito "+carritoId);
		if(!existeCarrito(carritoId)) {
			throw new ApiException(ErrorCodes.E0002.name(),ErrorCodes.E0002.getDescription());
		}
		calcularPrecioTotal(carrito);
		compraDao.updateCarrito(carrito);
		
		return carrito;
	}
	
	private void calcularPrecioTotal(Carrito carrito) {
		Double total = (double) 0;
		Double precioProducto;
		for(Producto producto: carrito.getProductos()) {
			if(Integer.parseInt(producto.getCantidad()) >= 4 ) {
				precioProducto = Double.valueOf(producto.getPrecioUnitario()) * (Integer.parseInt(producto.getCantidad()) -1);
			}else {
				precioProducto = Double.valueOf(producto.getPrecioUnitario()) * Integer.parseInt(producto.getCantidad());
			}
			total += precioProducto;
		}
		carrito.setTotal(total.toString());
		descontarPorTipoDeCarrito(carrito);
		descontarPorTipoDeCliente(carrito);
	}
	
	private void descontarPorTipoDeCarrito(Carrito carrito) {
		if(getCantidadDeProductosTotales(carrito) > 3) {
			Double total =Double.parseDouble(carrito.getTotal());
			switch(carrito.getTipo()) {
			case COMUN:
				total = total - 100;
				carrito.setTotal(total.toString());
				break;
			case ESPECIAL:
				total = total - 150;
				carrito.setTotal(total.toString());
			}
		}
	}
	
	private int getCantidadDeProductosTotales(Carrito carrito) {
		int contador=0;
		for(Producto producto: carrito.getProductos()) {
			contador += Integer.parseInt( producto.getCantidad() );
		}
		return contador;
	}
	
	private void descontarPorTipoDeCliente(Carrito carrito){
		if(Double.parseDouble(carrito.getTotal()) > 2000) {
			Double total =Double.parseDouble(carrito.getTotal());
			switch(compraDao.getTipoClienteDeCarrito(carrito.getId())) {
			case "VIP":
				total = total - 500;
				carrito.setTotal(total.toString());
				break;
			}
		}
	}

}
