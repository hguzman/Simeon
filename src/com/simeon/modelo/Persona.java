package com.simeon.modelo;

public class Persona {
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
}
