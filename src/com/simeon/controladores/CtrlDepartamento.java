package com.simeon.controladores;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.simeon.modelo.Departamento;

@ManagedBean(name="ctrlDepartamento")
@ViewScoped
public class CtrlDepartamento {
	private Integer _codDepartamento;
	private String _departamento;
	private List<Departamento> _departamentos;
	
/*	public CtrlDepartamento() {
		DaoDepartamento daoDepartamento=new DaoDepartamento();
		setDepartamentos(daoDepartamento.lista("select * from departamentos;"));
	}
*/	
	public Integer getCodDepartamento() {
		return _codDepartamento;
	}
	public void setCodDepartamento(Integer _codDepartamento) {
		this._codDepartamento = _codDepartamento;
	}
	public String getDepartamento() {
		return _departamento;
	}
	public void setDepartamento(String _departamento) {
		this._departamento = _departamento;
	}
	public List<Departamento> getDepartamentos() {
		return _departamentos;
	}
	public void setDepartamentos(List<Departamento> _departamentos) {
		this._departamentos = _departamentos;
	}

	
}
