package com.simeon.datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.simeon.modelo.TiposDocumentos;

public class DaoTiposDocumento extends BDatos {
	private static final Logger log=Logger.getLogger(DaoPersona.class);
	
	public DaoTiposDocumento() {
		BasicConfigurator.configure();
	}
	
	public List<TiposDocumentos> lista(String condicion){
		ArrayList<TiposDocumentos> lis = new ArrayList<TiposDocumentos>();
		PreparedStatement sentencia=crearSentencia(condicion);
		ResultSet rs=consultar(sentencia);
		try {
			while (rs.next()){
				TiposDocumentos td=new TiposDocumentos();
				td.setCodTipo(rs.getInt("cod_tipo"));
				td.setTipo(rs.getString("tipo"));
				lis.add(td);
			}
		} catch (SQLException e) {
			log.error("Error al cargar la lista");
		}
		return lis;
		
	}
}
