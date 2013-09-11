package com.simeon.controladores;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.simeon.datos.DaoMunicipio;
import com.simeon.modelo.Municipio;

@FacesConverter(value="mConverter",forClass=Municipio.class)
public class MunicipioConverter implements Converter {
	private static final Logger log=Logger.getLogger(MunicipioConverter.class);
	private List<Municipio> _municipios;

	public MunicipioConverter() {
		BasicConfigurator.configure();
		log.info("Constructor MunicipiosConverter");
		DaoMunicipio daoMunicipio=new DaoMunicipio();
		setMunicipios(daoMunicipio.lista("select * from municipios;"));
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Object resp=null;
		if(!value.trim().equals("")){
			Long id=Long.parseLong(value);
			for (Municipio mun: getMunicipios()){
				if(mun.getId().equals(id)){
					resp=mun;
				}
			}
		}
		return resp;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value==null || value.equals("")){
			return "";
		}else{
			return String.valueOf(((Municipio)value).getId());
		}
	}

	public List<Municipio> getMunicipios() {
		return _municipios;
	}

	public void setMunicipios(List<Municipio> _municipios) {
		this._municipios = _municipios;
	}

}
