package com.simeon.datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.simeon.modelo.Municipio;

public class DaoMunicipio extends BDatos {
	private static final Logger log=Logger.getLogger(DaoMunicipio.class);
	
	public DaoMunicipio() {
		BasicConfigurator.configure();
	}
	
	public List<Municipio> lista(String condicion){
		ArrayList<Municipio> municipio = new ArrayList<Municipio>();
		PreparedStatement sentencia=crearSentencia(condicion);
		ResultSet rs=consultar(sentencia);
		try {
			while (rs.next()){
				Municipio m=new Municipio();
				m.setId(rs.getLong("id"));
				m.setCodDepartamento(rs.getInt("cod_departamento"));
				m.setCodMunicipio(rs.getInt("cod_municipio"));
				m.setMunicipio(rs.getString("municipio"));
				municipio.add(m);
			}
		} catch (SQLException e) {
			log.error("Error al cargar la lista Municipios");
		}
		return municipio;
		
	}
	
}
