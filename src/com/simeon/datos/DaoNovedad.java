package com.simeon.datos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.simeon.modelo.Novedad;

public class DaoNovedad extends BDatos {
	private String _sql;
	
	public Integer insertar(Novedad novedad){
		Integer resp=0;
		_sql="insert into novedades (idguias,cod_estado,comentarios) value (?,?,?);";
		PreparedStatement sentencia=crearSentencia(_sql);
		try {
			sentencia.setInt(1, novedad.getIdGuias());
			sentencia.setInt(2, novedad.getCodEstado());
			sentencia.setString(3, novedad.getComentarios());
			resp=actualizar(sentencia);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return resp;
	}
}
