package com.simeon.datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.simeon.modelo.Asignacion;

public class DaoAsignacion extends BDatos {
	private String _sql;
	
	public Integer insertar(Asignacion asignacion){
		Integer resp=0;
		_sql="insert into empleados_guias (idguias,cod_empleado) value(?,?)";
		PreparedStatement sentencia=this.crearSentencia(_sql);
		try {
			sentencia.setInt(1, asignacion.getIdGuia());
			sentencia.setInt(2, asignacion.getCodEmpleado());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resp=actualizar(sentencia);
		return resp;
	}

	public List<Asignacion> lista(String condicion){
		ArrayList<Asignacion> asig = new ArrayList<Asignacion>();
		PreparedStatement sentencia=crearSentencia(condicion);
		ResultSet rs=consultar(sentencia);
		try {
			while (rs.next()){
				Asignacion a=new Asignacion();
				a.setIdGuia(rs.getInt("idguias"));
				a.setCodEmpleado(rs.getInt("cod_empleado"));
				a.setFechaAsignacion(rs.getTimestamp("fecha_asignacion"));
				a.setPagada(rs.getBoolean("pagada"));
				
				asig.add(a);
			}
		} catch (SQLException e) {
		}
		
		return asig;

	}
}
