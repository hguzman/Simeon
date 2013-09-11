package com.simeon.controladores;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.simeon.datos.DaoDepartamento;
import com.simeon.datos.DaoMunicipio;
import com.simeon.datos.DaoPersona;
import com.simeon.datos.DaoTiposDocumento;
import com.simeon.modelo.Departamento;
import com.simeon.modelo.Municipio;
import com.simeon.modelo.Persona;
import com.simeon.modelo.TiposDocumentos;


@ManagedBean(name="ctrlPersona")
@ViewScoped
public class CtrlPersona {
	private static final Logger log=Logger.getLogger(CtrlPersona.class);
	private Integer _id;
	private Integer _codTipo;
	private Long _numDocumento;
	private String _nombre;
	private String _direccion;
	private String _barrio;
	private String _telefono;
	private String _celular;
	private String _email;
	private Integer _codDepartamento;
	private Integer _codMunicipio;
	private List<SelectItem> _tipos;
	private List<SelectItem> _departamentos;
	private List<SelectItem> _municipios;
	
	public CtrlPersona() {
		BasicConfigurator.configure();
		log.info("CtrolPersona");
		cargaTipos();
		cargaDeptos();
	}
	
	public void guardarPersona(){
		Persona persona=new Persona();
		persona.setCodTipo(getCodTipo());
		persona.setNumDocumento(getNumDocumento());
		persona.setNombre(getNombre().toUpperCase());
		persona.setDireccion(getDireccion());
		persona.setBarrio(getBarrio());
		persona.setTelefono(getTelefono());
		persona.setCelular(getCelular());
		persona.setEmail(getEmail());
		persona.setCodDepartamento(getCodDepartamento());
		persona.setCodMunicipio(getCodMunicipio());

		DaoPersona daoPersona= new DaoPersona();		
		if (daoPersona.insertar(persona)>0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Guardado", "¡Muy bien!"));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se Guardo. ", "¡Lo siento!"));								
		}
	}
	
	private void cargaDeptos(){
		DaoDepartamento dpto= new DaoDepartamento();
		_departamentos= new ArrayList<SelectItem>();
		for (Departamento dp: dpto.lista("select * from departamentos;")){
			_departamentos.add(new SelectItem(dp.getCodDepartamento(),dp.getDepartamento()));
		}
		
	}
	public void cargaMunic(){
		if (getCodDepartamento()!=null && !getCodDepartamento().equals("")){
			DaoMunicipio dm= new DaoMunicipio();
			_municipios=new ArrayList<SelectItem>();
			for(Municipio mun: dm.lista("select * from municipios where cod_departamento="+getCodDepartamento())){
				_municipios.add(new SelectItem(mun.getCodMunicipio(),mun.getMunicipio()));
			}
		}
	}
	private void cargaTipos(){
		DaoTiposDocumento dt= new DaoTiposDocumento();
		_tipos=new ArrayList<SelectItem>();
		for (TiposDocumentos t: dt.lista("select * from tipos_documentos;")){
			_tipos.add(new SelectItem(t.getCodTipo(),t.getTipo()));
		}		
	}
	public Integer getId() {
		return _id;
	}
	public void setId(Integer _id) {
		this._id = _id;
	}
	public Integer getCodTipo() {
		return _codTipo;
	}
	public void setCodTipo(Integer _codTipo) {
		this._codTipo = _codTipo;
	}
	public Long getNumDocumento() {
		return _numDocumento;
	}
	public void setNumDocumento(Long _numDocumento) {
		this._numDocumento = _numDocumento;
	}
	public String getNombre() {
		return _nombre;
	}
	public void setNombre(String _nombre) {
		this._nombre = _nombre;
	}
	public String getDireccion() {
		return _direccion;
	}
	public void setDireccion(String _direccion) {
		this._direccion = _direccion;
	}
	public String getBarrio() {
		return _barrio;
	}
	public void setBarrio(String _barrio) {
		this._barrio = _barrio;
	}
	public String getTelefono() {
		return _telefono;
	}
	public void setTelefono(String _telefono) {
		this._telefono = _telefono;
	}
	public String getCelular() {
		return _celular;
	}
	public void setCelular(String _celular) {
		this._celular = _celular;
	}
	public String getEmail() {
		return _email;
	}
	public void setEmail(String _email) {
		this._email = _email;
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

	public List<SelectItem> getTipos() {
		return _tipos;
	}

	public List<SelectItem> getDepartamentos() {
		return _departamentos;
	}

	public List<SelectItem> getMunicipios() {
		return _municipios;
	}

}
