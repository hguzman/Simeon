package com.simeon.controladores;

import java.sql.SQLException;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.simeon.datos.DaoPersona;
import com.simeon.modelo.Persona;

@FacesConverter(value="personac", forClass=Persona.class)
public class PersonaConverter implements Converter {
			
	private static List<Persona> _personasDB;
	private String _sql;
	private static final Logger log=Logger.getLogger(PersonaConverter.class);


	public PersonaConverter() { 
		BasicConfigurator.configure();
		
		_sql="select * from personas;";
		DaoPersona daoPersona=new DaoPersona();

		try {
			setPersonasDB(daoPersona.listaP(_sql));
		} catch (SQLException e) {			
			log.debug("Error al cargar la lista"+e.getMessage());
		}
	}

	
	public static List<Persona> getPersonasDB() {
		return _personasDB;
	}

	public static void setPersonasDB(List<Persona> _personasDB) {
		PersonaConverter._personasDB = _personasDB;
	}


	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) {
		if(submittedValue.trim().equals("")){
			return null;	
		}else{
			Integer numDocumento=Integer.parseInt(submittedValue);
			for (Persona p: getPersonasDB()){
				log.debug("BD="+p.getNumDocumento());
				log.debug("Recibido="+numDocumento);
				if(p.getId().equals(numDocumento)){
					log.debug("Entro a entregar el objeto");
					log.debug(p);
					return p;
				}
			}
		}
		log.debug("valor recibido asobjet="+submittedValue);
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value==null || value.equals("")){
			return "";
		}else{
			return String.valueOf(((Persona)value).getId());
		}

//		log.debug("Valor recibido sqtrin="+value);
	}

}
