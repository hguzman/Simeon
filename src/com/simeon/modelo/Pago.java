package com.simeon.modelo;

import java.sql.Timestamp;

public class Pago {
	private Integer _codEmpleado;
	private Integer _codServicio;
	private Long _pagoAcordado;
	private Timestamp _fechaAcuerdo;
	
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
	public Timestamp getFechaAcuerdo() {
		return _fechaAcuerdo;
	}
	public void setFechaAcuerdo(Timestamp _fechaAcuerdo) {
		this._fechaAcuerdo = _fechaAcuerdo;
	}
	
	
}
