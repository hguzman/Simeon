package com.simeon.controladores;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;

import com.simeon.datos.DaoTiposDocumento;
import com.simeon.modelo.TiposDocumentos;

@FacesConverter(value="tiposDocConverter", forClass=TiposDocumentos.class)
public class TiposDocConverter implements Converter {
	private static final Logger log=Logger.getLogger(TiposDocConverter.class);
	private List<TiposDocumentos> _tiposDocumentos;
	
	public TiposDocConverter() {
		BasicConfigurator.configure();
		log.info("Constructor TiposConverter");
		DaoTiposDocumento daoTiposDocumento=new DaoTiposDocumento();
		setTiposDocumentos(daoTiposDocumento.lista("select * from tipos_documentos;"));
	}
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Object resp=null;
		if(!value.trim().equals("")){
			Integer codTipo=Integer.parseInt(value);
			for (TiposDocumentos td: getTiposDocumentos()){
				if(td.getCodTipo().equals(codTipo)){
					resp=td;
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
			return String.valueOf(((TiposDocumentos)value).getCodTipo());
		}

	}
	public List<TiposDocumentos> getTiposDocumentos() {
		return _tiposDocumentos;
	}
	public void setTiposDocumentos(List<TiposDocumentos> _tiposDocumentos) {
		this._tiposDocumentos = _tiposDocumentos;
	}

}
