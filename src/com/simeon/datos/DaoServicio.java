package com.simeon.datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.simeon.modelo.Servicio;

public class DaoServicio extends BDatos {

	public List<Servicio> lista(String condicion){
		ArrayList<Servicio> serv = new ArrayList<Servicio>();
		PreparedStatement sentencia=crearSentencia(condicion);
		ResultSet rs=consultar(sentencia);
		try {
			while (rs.next()){
				Servicio s=new Servicio();
				s.setCodServicio(rs.getInt("cod_servicio"));
				s.setServicio(rs.getString("servicio"));
				s.setValor(rs.getLong("valor"));
				serv.add(s);
			}
		} catch (SQLException e) {
		}
		
		return serv;
		
	}
	
}
