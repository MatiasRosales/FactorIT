package com.factorit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.facorit.utils.ErrorCodes;
import com.facorit.utils.TipoCarritoEnum;
import com.factorit.api.ApiException;
import com.factorit.model.Carrito;
import com.factorit.model.Carrito.EstadoEnum;
import com.factorit.model.Carrito.TipoEnum;
import com.factorit.model.Producto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Repository
public class CompraDAO {
	
	private static final Logger log = LoggerFactory.getLogger(CompraDAO.class);
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private Gson gson = new Gson();
	
	@Value("${spring.datasource.url}")
	private String dataSourceUrl;
	
	private CarritoRowMapper carritoRowMapper;
	
	@Autowired
	public CompraDAO(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.carritoRowMapper = new CarritoRowMapper();
	}
	
	private class CarritoRowMapper implements RowMapper<Carrito> {
		@Override
		public Carrito mapRow(ResultSet rs, int rowNum) throws SQLException {

			Carrito carrito = new Carrito();

			String id = rs.getString("id") == null ? null : rs.getString("id");
			String creationDate = rs.getString("creation_date") == null ? null : rs.getString("creation_date");
			String tipoCarrito = rs.getString("tipo_carrito") == null ? null : rs.getString("tipo_carrito");
			
			String estado = rs.getString("estado") == null ? null : rs.getString("estado");
			if (rs.getString("productos") != null) {
				String jsonProductos = rs.getString("productos");
				TypeToken<List<Producto>> list = new TypeToken<List<Producto>>() {};
				List<Producto> productos = gson.fromJson(jsonProductos, list.getType());
				carrito.setProductos(productos);
			} else {
				carrito.setProductos(null);
			}
			String total = rs.getString("total") == null ? null : rs.getString("total");
			
			carrito.setId(id);
			carrito.setEstado(EstadoEnum.fromValue(estado));
			carrito.setTipo(TipoEnum.fromValue(TipoCarritoEnum.nameOf(tipoCarrito)));
			carrito.setTotal(total);
			carrito.setFechaCreacion(creationDate);
			return carrito;
		}
	}
	
	
	public void insertCompra(String id, String idClient,String creationDate,String tipoCarrito) {
		
		String tipoCarritoId = jdbcTemplate.queryForObject(
				"SELECT id FROM tipo_carrito WHERE description = :description;",
				new MapSqlParameterSource().addValue("description", tipoCarrito),
				String.class);
		
		String sqlInsertOperation = "INSERT INTO compras (id, creation_date, tipo_carrito, estado, client_id) "
				+ "VALUES (:id, :creation_date, :tipo_carrito, :estado,:client_id);";
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("id", id, java.sql.Types.VARCHAR)
				.addValue("creation_date", creationDate, java.sql.Types.TIMESTAMP)
				.addValue("tipo_carrito", tipoCarritoId, java.sql.Types.BIGINT)
				.addValue("estado", "ABIERTO", java.sql.Types.VARCHAR)
				.addValue("client_id", idClient, java.sql.Types.BIGINT);
		
		jdbcTemplate.update(sqlInsertOperation, parameterSource);
	}
	
	public void cancelarCompra(String idCompra) {
		
		String sqlDeleteOperation = "UPDATE compras SET estado = 'CANCELADO' where id = :id";
		
		jdbcTemplate.update(sqlDeleteOperation,new MapSqlParameterSource().addValue("id", idCompra));
	}
	
	public boolean verificarCliente(String idCliente) {
		String existe =jdbcTemplate.queryForObject(
				"SELECT COUNT(*) from cliente where id = :idCliente;",
				new MapSqlParameterSource().addValue("idCliente", idCliente,java.sql.Types.BIGINT),
				String.class);
		return !"0".equals(existe);
		
	}
	
	public boolean verificarCarrito(String idCarrito) {
		String existe =jdbcTemplate.queryForObject(
				"SELECT COUNT(*) from compras where id = :idCarrito;",
				new MapSqlParameterSource().addValue("idCarrito", idCarrito),
				String.class);
		return !"0".equals(existe);
		
	}
	
	public Carrito fetchCarrito(String idCarrito) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("idCarrito", idCarrito);
		List<Carrito> carritos = jdbcTemplate.query("SELECT * FROM compras where id = :idCarrito;", paramSource, carritoRowMapper);
		if (carritos.isEmpty())
			throw new ApiException(ErrorCodes.E0002.name(), ErrorCodes.E0002.getDescription());
		return carritos.get(0);
	}
	
	public String getTipoClienteDeCarrito(String carritoId) {
		String sql = "SELECT tipocli.description FROM compras com join cliente cli on com.client_id = cli.id join tipo_cliente tipocli on tipocli.id = cli.tipo_cliente where com.id = :carritoId;";
		return jdbcTemplate.queryForObject(sql,new MapSqlParameterSource().addValue("carritoId", carritoId),String.class);
	}
	
	public void updateCarrito(Carrito carrito) {
		String sql = "UPDATE compras SET productos=:productos , estado=:estado , total= :total  WHERE id= :id;";
		MapSqlParameterSource paramSource = this.getParamSource(carrito);

		if (jdbcTemplate.update(sql, paramSource) == 0)
			throw new ApiException(ErrorCodes.E0001.name(), ErrorCodes.E0001.getDescription());
	}
	
	private MapSqlParameterSource getParamSource(Carrito carrito) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("id", carrito.getId(), java.sql.Types.VARCHAR)
				.addValue("estado", carrito.getEstado().name(),java.sql.Types.VARCHAR)
				.addValue("productos", gson.toJson(carrito.getProductos()),java.sql.Types.VARCHAR)
				.addValue("total", carrito.getTotal(),java.sql.Types.VARCHAR);

		return paramSource;
	}
	
	public List<Carrito> getCarritos(String from, String to, String dni, String order){
		String sqlOrderByFecha = "select com.id,com.creation_date,com.tipo_carrito,com.estado,com.productos,com.total from compras com join cliente cli on cli.id = com.client_id where cli.dni = :DNI and com.estado = 'CERRADO' and com.creation_date between :FROM and :TO order by com.creation_date;";
		String sqlOrderByMonto = "select com.id,com.creation_date,com.tipo_carrito,com.estado,com.productos,com.total from compras com join cliente cli on cli.id = com.client_id where cli.dni = :DNI and com.estado = 'CERRADO' and com.creation_date between :FROM and :TO order by com.monto;";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("DNI", dni,java.sql.Types.VARCHAR);
		paramSource.addValue("FROM", from,java.sql.Types.TIMESTAMP);
		paramSource.addValue("TO", to,java.sql.Types.TIMESTAMP);
		List<Carrito> carritos = new ArrayList<Carrito>();
		if("FECHA".equals(order)) {
			carritos = jdbcTemplate.query(sqlOrderByFecha, paramSource, carritoRowMapper);
		}else {
			carritos = jdbcTemplate.query(sqlOrderByMonto, paramSource, carritoRowMapper);
		}
		return carritos;
		
	}
}
