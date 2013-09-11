package com.simeon.controladores;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.simeon.datos.DaoTiposDocumento;
import com.simeon.modelo.TiposDocumentos;

@ManagedBean(name="ctrlTipoDocumento")
@ViewScoped
public class CtrlTipoDocumento {
	private List<TiposDocumentos> _tipos;
	
	public CtrlTipoDocumento() {
		DaoTiposDocumento daoTiposDocumento=new DaoTiposDocumento();		
		setTipos(daoTiposDocumento.lista("select * from tipos_documentos;"));
	}

	public List<TiposDocumentos> getTipos() {
		return _tipos;
	}

	public void setTipos(List<TiposDocumentos> _tipos) {
		this._tipos = _tipos;
	}
	
}
