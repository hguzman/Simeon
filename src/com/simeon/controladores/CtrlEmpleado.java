package com.simeon.controladores;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.simeon.datos.DaoEmpleado;
import com.simeon.datos.DaoPersona;
import com.simeon.modelo.Empleado;
import com.simeon.modelo.Persona;

@ManagedBean(name="ctrlEmpleado")
@ViewScoped
public class CtrlEmpleado {
	private Integer _id;
	private Integer _codEmpleado;
	private Date _fechaIngreso;
	private Persona _empleado;
	private List<Persona> _personas;
	private String _sql;
	
	public CtrlEmpleado() {
		_sql="select * from personas;";
		DaoPersona daoPersona=new DaoPersona();

			try {
				setPersonas(daoPersona.listaP(_sql));
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void guardarEmpleado(){
		Empleado empleado=new Empleado();
		empleado.setCodEmpleado(getEmpleado().getId());
		empleado.setFechaIngreso(new Timestamp(getFechaIngreso().getTime()));
		
		DaoEmpleado daoEmpleado=new DaoEmpleado();
		try {
			FacesContext facesContext=FacesContext.getCurrentInstance();
			if (daoEmpleado.insertar(empleado)>0){
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Guardado", "Muy bien!"));
			}else{
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas para guardar", "Lo siento!"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public List<Persona> completarPersona(String contenido){
		List<Persona> listaRetorna	= new ArrayList<Persona>();
		for (Persona p: getPersonas()){
			if (p.getNombre().toUpperCase().contains(contenido.toUpperCase())){
				listaRetorna.add(p);
			}
		}
		return listaRetorna;
	}
	
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
	public Date getFechaIngreso() {
		return _fechaIngreso;
	}
	public void setFechaIngreso(Date _fechaIngreso) {
		this._fechaIngreso = _fechaIngreso;
	}
	public Persona getEmpleado() {
		return _empleado;
	}
	public void setEmpleado(Persona _empleado) {
		this._empleado = _empleado;
	}

	public List<Persona> getPersonas() {
		return _personas;
	}

	public void setPersonas(List<Persona> _personas) {
		this._personas = _personas;
	}

}
