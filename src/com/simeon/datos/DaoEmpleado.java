package com.simeon.datos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.simeon.modelo.Empleado;

public class DaoEmpleado extends BDatos {
	private String _sql;
	
	public Integer insertar(Empleado empleado){
		Integer resp=0;
		_sql="insert into empleados (cod_empleado, fecha_ingreso) value(?,?)";
		PreparedStatement sentencia=this.crearSentencia(_sql);
		try {
			sentencia.setInt(1, empleado.getCodEmpleado());
			sentencia.setTimestamp(2, empleado.getFechaIngreso());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resp=actualizar(sentencia);
		return resp;
	}
}
