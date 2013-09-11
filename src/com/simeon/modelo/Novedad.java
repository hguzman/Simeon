package com.simeon.modelo;

import java.sql.Timestamp;

public class Novedad {
	private Integer _idGuias;
	private Timestamp _fechaHora;
	private Integer _codEstado;
	private String _comentarios;
	
	public Integer getIdGuias() {
		return _idGuias;
	}
	public void setIdGuias(Integer _idGuias) {
		this._idGuias = _idGuias;
	}
	public Timestamp getFechaHora() {
		return _fechaHora;
	}
	public void setFechaHora(Timestamp _fechaHora) {
		this._fechaHora = _fechaHora;
	}
	public Integer getCodEstado() {
		return _codEstado;
	}
	public void setCodEstado(Integer _codEstado) {
		this._codEstado = _codEstado;
	}
	public String getComentarios() {
		return _comentarios;
	}
	public void setComentarios(String _comentarios) {
		this._comentarios = _comentarios;
	}		
}
