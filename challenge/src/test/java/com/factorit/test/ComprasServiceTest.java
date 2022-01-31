package com.factorit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.factorit.api.ApiException;
import com.factorit.challenge.ChallengeApplication;
import com.factorit.model.Carrito;
import com.factorit.model.Carrito.EstadoEnum;
import com.factorit.model.Producto;
import com.factorit.services.CarritoService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChallengeApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class ComprasServiceTest {
	
	@Autowired
	private CarritoService carritoService;
	
	@Test
	public void createCarrito() {
		Carrito carrito = carritoService.createCarrito("1","COMUN");
		assertNotNull(carrito);
	}
	
	
	@Test
	public void getCarrito() {
		Carrito carrito = carritoService.fetchCarrito("ASD123");
		assertNotNull(carrito);
	}
	
	@Test 
	public void cancelarCarrito() {
		carritoService.eliminarCarrito("ASD123");
		Carrito carrito = carritoService.fetchCarrito("ASD123");
		assertEquals("CANCELADO",carrito.getEstado().name());
	}
	
	@Test
	public void actualizarCarrito() {
		Carrito carrito = carritoService.fetchCarrito("ASD123");
		carrito.setEstado(EstadoEnum.CERRADO);
		List<Producto> productos = new ArrayList<Producto>();
		Producto prod = new Producto();
		prod.setNombre("Producto 1");
		prod.setCantidad("4");
		prod.setPrecioUnitario("600");
		productos.add(prod);
		//precio total = 2400, con un 4x3= 1800
		Producto prod2 = new Producto();
		prod2.setNombre("Producto 2");
		prod2.setCantidad("2");
		prod2.setPrecioUnitario("200");
		productos.add(prod2);
		carrito.setProductos(productos);
		//total = 400, 1800+400 = 2200
		//descuento por ser carrito especial pasa a $2050
		//descuento por ser cliente normal, no tiene descuento, pese a que supera $2000
		
		carritoService.patchCarrito(carrito.getId(),carrito );
		
		carrito = carritoService.fetchCarrito("ASD123");
		assertEquals(2,carrito.getProductos().size());
		assertEquals("2050.0",carrito.getTotal());
	}

	@Test(expected = ApiException.class)
	public void createCarritoSinCliente() {
		carritoService.createCarrito("50","COMUN");
	}
	
	@Test(expected = ApiException.class)
	public void getCarritoInexistente() {
		carritoService.fetchCarrito("ASD123456");
	}
	
}
