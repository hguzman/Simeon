package com.simeon.datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.simeon.modelo.Pago;

public class DaoPagoAcordado extends BDatos {
	private String _sql;
	
	public Integer insertar(Pago pago){
		Integer resp=0;
		_sql="insert into pago_acordado (cod_empleado,cod_servicio,pago_acordado) value (?,?,?)";
		PreparedStatement sentencia=this.crearSentencia(_sql);
		
		try {
			sentencia.setInt(1, pago.getCodEmpleado());
			sentencia.setInt(2, pago.getCodServicio());
			sentencia.setLong(3, pago.getPagoAcordado());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp=actualizar(sentencia);
		return resp;
	}
	
	public List<Pago> lista(String condicion){
		ArrayList<Pago> pagos = new ArrayList<Pago>();
		PreparedStatement sentencia=crearSentencia(condicion);
		ResultSet rs=consultar(sentencia);
		try {
			while (rs.next()){
				Pago p=new Pago();
				p.setCodEmpleado(rs.getInt("cod_empleado"));
				p.setCodServicio(rs.getInt("cod_servicio"));
				p.setPagoAcordado(rs.getLong("pago_acordado"));
				p.setFechaAcuerdo(rs.getTimestamp("fecha_acuerdo"));
				
				pagos.add(p);
			}
		} catch (SQLException e) {
		}
		
		return pagos;
		
	}

}
