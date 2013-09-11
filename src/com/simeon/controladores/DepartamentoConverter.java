package com.simeon.controladores;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.simeon.datos.DaoDepartamento;
import com.simeon.modelo.Departamento;

@FacesConverter(value="depConverter", forClass=Departamento.class)
public class DepartamentoConverter implements Converter {
	private static final Logger log=Logger.getLogger(DepartamentoConverter.class);
	private List<Departamento> _departamentos;
	
	public DepartamentoConverter() {
		BasicConfigurator.configure();
		log.info("Constructor DepartamentoConverter");
		DaoDepartamento daoDepartamento=new DaoDepartamento();
		setDepartamentos(daoDepartamento.lista("select * from departamentos;"));
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		log.info("recibe Str="+value);
		Object resp=null;
		if(!value.trim().equals("")){
			Integer codDepartamento=Integer.parseInt(value);
			for (Departamento dp: getDepartamentos()){
				if(dp.getCodDepartamento().equals(codDepartamento)){
					log.debug("Entrega obj="+dp);
					resp=dp;
				}
			}
		}
		return resp;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		log.debug("Recieb obj="+value);
		if (value==null || value.equals("")){
			return "";
		}else{
			log.debug(String.valueOf(((Departamento)value).getCodDepartamento()));
			return String.valueOf(((Departamento)value).getCodDepartamento());
		}
	}

	public List<Departamento> getDepartamentos() {
		return _departamentos;
	}

	public void setDepartamentos(List<Departamento> _departamentos) {
		this._departamentos = _departamentos;
	}

}
