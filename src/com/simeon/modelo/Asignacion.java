package com.simeon.modelo;

import java.sql.Timestamp;

public class Asignacion {
	private Integer _idGuia;
	private Integer _codEmpleado;
	private Timestamp _fechaAsignacion;
	private Boolean _pagada;
	
	public Integer getIdGuia() {
		return _idGuia;
	}
	public void setIdGuia(Integer _idGuia) {
		this._idGuia = _idGuia;
	}
	public Integer getCodEmpleado() {
		return _codEmpleado;
	}
	public void setCodEmpleado(Integer _codEmpleado) {
		this._codEmpleado = _codEmpleado;
	}
	public Timestamp getFechaAsignacion() {
		return _fechaAsignacion;
	}
	public void setFechaAsignacion(Timestamp _fechaAsignacion) {
		this._fechaAsignacion = _fechaAsignacion;
	}
	public Boolean getPagada() {
		return _pagada;
	}
	public void setPagada(Boolean _pagada) {
		this._pagada = _pagada;
	}
}
