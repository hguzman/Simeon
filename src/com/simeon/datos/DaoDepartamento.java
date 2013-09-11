package com.simeon.datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.simeon.modelo.Departamento;

public class DaoDepartamento extends BDatos {
	private static final Logger log=Logger.getLogger(DaoDepartamento.class);
	
	public DaoDepartamento() {
		BasicConfigurator.configure();
	}
	
	public List<Departamento> lista(String condicion){
		ArrayList<Departamento> dep = new ArrayList<Departamento>();
		PreparedStatement sentencia=crearSentencia(condicion);
		ResultSet rs=consultar(sentencia);
		try {
			while (rs.next()){
				Departamento dp=new Departamento();
				dp.setCodDepartamento(rs.getInt("cod_departamento"));
				dp.setDepartamento(rs.getString("departamento"));
				dep.add(dp);
			}
		} catch (SQLException e) {
			log.error("Error al cargar la lista Departamentos");
		}
		return dep;
		
	}

}
