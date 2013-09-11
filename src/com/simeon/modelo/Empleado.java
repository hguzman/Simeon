package com.simeon.modelo;

import java.sql.Timestamp;

public class Empleado {
	private Integer _id;
	private Integer _codEmpleado;
	private Timestamp _fechaIngreso;
	
	public Integer getId() {
		return _id;
	}
	public void setId(Integer _id) {
		this._id = _id;
	}
	public Integer getCodEmpleado() {
		return _codEmpleado;
	}
	public void setCodEmpleado(Integer _codEmpleado) {
		this._codEmpleado = _codEmpleado;
	}
	public Timestamp getFechaIngreso() {
		return _fechaIngreso;
	}
	public void setFechaIngreso(Timestamp _fechaIngreso) {
		this._fechaIngreso = _fechaIngreso;
	}

	
}
