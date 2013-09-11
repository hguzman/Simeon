package com.simeon.controladores;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.simeon.modelo.Municipio;

@ManagedBean(name="ctrlMunicipio")
@ViewScoped
public class CtrlMunicipio {
	private static final Logger log=Logger.getLogger(CtrlMunicipio.class);
	private Long _id;
	private Integer _codDepartamento;
	private Integer _codMunicipio;
	private String _municipio;
	private List<Municipio> _municipios;
	
	public CtrlMunicipio() {
		BasicConfigurator.configure();
		log.debug("CtrlMunicioio");
	}

	public void cargarMunicipios(AjaxBehaviorEvent event){		
		log.debug("Aqui CargarMunicipio");
		log.debug(event);
		Integer mun=(Integer)event.getComponent().getAttributes().get("value");
		log.debug("Valor"+mun);
/*		Integer dpto = (Integer) event.getComponent().getAttributes().get("value");
		log.debug("Valor="+dpto);
		DaoMunicipio daoMunicipio=new DaoMunicipio();
		setMunicipios(daoMunicipio.lista("select * from municipios where cod_departamento="+dpto));
*/	}
	
	public Long getId() {
		return _id;
	}
	public void setId(Long _id) {
		this._id = _id;
	}
	public Integer getCodDepartamento() {
		return _codDepartamento;
	}
	public void setCodDepartamento(Integer _codDepartamento) {
		this._codDepartamento = _codDepartamento;
	}
	public Integer getCodMunicipio() {
		return _codMunicipio;
	}
	public void setCodMunicipio(Integer _codMunicipio) {
		this._codMunicipio = _codMunicipio;
	}
	public String getMunicipio() {
		return _municipio;
	}
	public void setMunicipio(String _municipio) {
		this._municipio = _municipio;
	}
	public List<Municipio> getMunicipios() {
		return _municipios;
	}
	public void setMunicipios(List<Municipio> _municipios) {
		this._municipios = _municipios;
	}
	
}
