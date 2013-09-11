package com.simeon.modelo;

public class Municipio {
	private Long _id;
	private Integer _codDepartamento;
	private Integer _codMunicipio;
	private String _municipio;
	
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
}
