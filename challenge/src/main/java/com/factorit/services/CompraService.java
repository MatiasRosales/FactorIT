package com.factorit.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factorit.dao.CompraDAO;
import com.factorit.model.Carrito;

@Service
public class CompraService {
	
	@Autowired
	private CompraDAO compraDao;
	
	public List<Carrito> getCompras(String from,String to,String documentNumber,String order){
		if(to == null || to.isEmpty()) {
			java.sql.Date date= new java.sql.Date(System.currentTimeMillis());
			Timestamp timestamp = new Timestamp(date.getTime());
			to  = timestamp.toString();
		}
		return compraDao.getCarritos(from, to, documentNumber, order);
	}

}
