package com.simeon.controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.simeon.datos.DaoPagoAcordado;
import com.simeon.datos.DaoPersona;
import com.simeon.datos.DaoServicio;
import com.simeon.modelo.Pago;
import com.simeon.modelo.Persona;
import com.simeon.modelo.Servicio;

@ManagedBean(name="ctrlPago")
@ViewScoped
public class CtrlPago {
	private Integer _codEmpleado;
	private Integer _codServicio;
	private Long _pagoAcordado;
	private List<SelectItem> _empleados;
	private List<SelectItem> _servicios;
	private List<Pago> _pagos;
	
	public CtrlPago() {
		cargarEmpleados();
		cargarServicios();
		cargarPagos();
	}
	
	public void guardarPago(){
		Pago pago= new Pago();
		pago.setCodEmpleado(getCodEmpleado());
		pago.setCodServicio(getCodServicio());
		pago.setPagoAcordado(getPagoAcordado());

		DaoPagoAcordado daoPagoAcordado=new DaoPagoAcordado();
		
		if (daoPagoAcordado.insertar(pago)>0){
			cargarPagos();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Guardado", "¡Muy bien!"));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se Guardo. ", "¡Lo siento!"));								
		}
		
	}
	
	public void cargarPagos(){
		DaoPagoAcordado daoPagoAcordado= new DaoPagoAcordado();
		_pagos=new ArrayList<Pago>();
		for(Pago p: daoPagoAcordado.lista("select * from pago_acordado where cod_empleado="+getCodEmpleado())){
			_pagos.add(p);
		}
	}
	
	private void cargarServicios(){
		DaoServicio daoServicio= new DaoServicio();
		_servicios=new ArrayList<SelectItem>();
		for(Servicio s: daoServicio.lista("select * from servicios")){
			_servicios.add(new SelectItem(s.getCodServicio(),s.getServicio()));
		}
		
	}
	private void cargarEmpleados(){
		DaoPersona daoPersona= new DaoPersona();
		_empleados= new ArrayList<SelectItem>();
		try {
			for(Persona p: daoPersona.listaP("select * from personas where id in (select cod_empleado from empleados);")){
				_empleados.add(new SelectItem(p.getId(),p.getNombre()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Integer getCodEmpleado() {
		return _codEmpleado;
	}
	public void setCodEmpleado(Integer _codEmpleado) {
		this._codEmpleado = _codEmpleado;
	}
	public Integer getCodServicio() {
		return _codServicio;
	}
	public void setCodServicio(Integer _codServicio) {
		this._codServicio = _codServicio;
	}
	public Long getPagoAcordado() {
		return _pagoAcordado;
	}
	public void setPagoAcordado(Long _pagoAcordado) {
		this._pagoAcordado = _pagoAcordado;
	}
	public List<SelectItem> getEmpleados() {
		return _empleados;
	}

	public List<SelectItem> getServicios() {
		return _servicios;
	}

	public List<Pago> getPagos() {
		return _pagos;
	}

	
}
