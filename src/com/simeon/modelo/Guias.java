package com.simeon.modelo;

import java.sql.Timestamp;

public class Guias {
	private Integer _idGuia;
	private Timestamp _fechaHora;
	private Integer _remitente;
	private Integer _destinatario;
	private Integer _peso;
	private Integer _codServicio;
	private Integer _causaDevolucion;
	private String _imagen;
	private Boolean _impresa;
	
	public Integer getIdGuia() {
		return _idGuia;
	}
	public void setIdGuia(Integer _idGuia) {
		this._idGuia = _idGuia;
	}
	public Timestamp getFechaHora() {
		return _fechaHora;
	}
	public void setFechaHora(Timestamp _fechaHora) {
		this._fechaHora = _fechaHora;
	}
	public Integer getRemitente() {
		return _remitente;
	}
	public void setRemitente(Integer _remitente) {
		this._remitente = _remitente;
	}
	public Integer getDestinatario() {
		return _destinatario;
	}
	public void setDestinatario(Integer _destinatario) {
		this._destinatario = _destinatario;
	}
	public Integer getPeso() {
		return _peso;
	}
	public void setPeso(Integer _peso) {
		this._peso = _peso;
	}
	public Integer getCodServicio() {
		return _codServicio;
	}
	public void setCodServicio(Integer _codServicio) {
		this._codServicio = _codServicio;
	}
	public Integer getCausaDevolucion() {
		return _causaDevolucion;
	}
	public void setCausaDevolucion(Integer _causaDevolucion) {
		this._causaDevolucion = _causaDevolucion;
	}
	public String getImagen() {
		return _imagen;
	}
	public void setImagen(String _imagen) {
		this._imagen = _imagen;
	}
	public Boolean getImpresa() {
		return _impresa;
	}
	public void setImpresa(Boolean _impresa) {
		this._impresa = _impresa;
	}
	
	
}
