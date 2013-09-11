package com.simeon.datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.simeon.modelo.Persona;


public class DaoPersona extends BDatos {
	private static final Logger log=Logger.getLogger(DaoPersona.class);
	private StringBuffer _sql; 
	private String sql;
	
	public DaoPersona() {
		BasicConfigurator.configure();
	}
	
	public Integer insertar(Persona persona){		
		Integer resp=0;
		_sql=new StringBuffer();
		_sql.append("insert into personas ");
		_sql.append("(cod_tipo,num_documento,nombre,direccion,barrio,telefono,celular,email,cod_departamento,cod_municipio) ");
		_sql.append("value ");
		_sql.append("(?,?,?,?,?,?,?,?,?,?)");
		log.debug(_sql);
		PreparedStatement sentencia=this.crearSentencia(_sql.toString());
		try {
			sentencia.setInt(1, persona.getCodTipo());
			sentencia.setLong(2, persona.getNumDocumento());
			sentencia.setString(3, persona.getNombre());
			sentencia.setString(4, persona.getDireccion());
			sentencia.setString(5, persona.getBarrio());
			sentencia.setString(6, persona.getTelefono());
			sentencia.setString(7, persona.getCelular());
			sentencia.setString(8, persona.getEmail());
			sentencia.setInt(9, persona.getCodDepartamento());
			sentencia.setInt(10, persona.getCodMunicipio());

			resp=actualizar(sentencia);
			return resp;
			
		} catch (SQLException e) {
			log.error("Error al crear la sentencia");
		}
		
		return resp;
	}
	
	public List<Persona> listaP(String valor) throws SQLException{
		
		List<Persona> listaCompletar;
		listaCompletar = new ArrayList<Persona>(); 
		PreparedStatement sentencia=crearSentencia(valor);
		ResultSet rs=consultar(sentencia);
		log.debug("Creo Bien el RecordSet");
		while (rs.next()){
			Persona per=new Persona();
			
			per.setId(rs.getInt("id"));
			per.setCodTipo(rs.getInt("cod_tipo"));
			per.setNumDocumento(rs.getLong("num_documento"));			
			per.setNombre(rs.getString("nombre"));
			per.setDireccion(rs.getString("direccion"));
			per.setBarrio(rs.getString("barrio"));
			per.setTelefono(rs.getString("telefono"));
			per.setCelular(rs.getString("celular"));
			per.setEmail(rs.getString("email"));
			per.setCodDepartamento(rs.getInt("cod_departamento"));
			per.setCodMunicipio(rs.getInt("cod_municipio"));
			
			listaCompletar.add(per); //Adiciona El objeto a la lista
		}
		return listaCompletar;

	}
	
	public Integer obtenerId(Integer tipo, Long numero){
		Integer resp=0;
		sql="select * from personas where cod_tipo="+ tipo +" and num_documento="+numero;
		PreparedStatement sentencia=crearSentencia(sql);
		ResultSet rs=consultar(sentencia);
		try {
			rs.next();
			log.info("Idobt="+rs.getInt("id"));
			resp=rs.getInt("id");
		} catch (SQLException e) {
			log.error("Error al asignar el id");
		}
		return resp;
	}

}
