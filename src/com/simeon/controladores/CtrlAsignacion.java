package com.simeon.controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.simeon.datos.DaoAsignacion;
import com.simeon.datos.DaoPersona;
import com.simeon.modelo.Asignacion;
import com.simeon.modelo.Persona;

@ManagedBean(name="ctrlAsignacion")
@ViewScoped
public class CtrlAsignacion {
	private Integer _idGuia;
	private Integer _codEmpleado;
	private List<SelectItem> _empleados;
	private List<Asignacion> _guias;
	
	public CtrlAsignacion() {
		cargarEmpleados();
		cargarGuias();
	}
	
	public void guardarAsignacion(){
		Asignacion asignacion=new Asignacion();
		asignacion.setIdGuia(getIdGuia());
		asignacion.setCodEmpleado(getCodEmpleado());
		DaoAsignacion daoAsignacion= new DaoAsignacion();
		FacesContext facesContext=FacesContext.getCurrentInstance();
		if (daoAsignacion.insertar(asignacion)>0){
			cargarGuias();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Guardado", "Muy bien!"));
		}else{
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas para guardar", "Lo siento!"));			
		}
		setIdGuia(null);
	}
	
	public void cargarGuias(){
		DaoAsignacion daoAsignacion=new DaoAsignacion();
		_guias=new ArrayList<Asignacion>();
//		for(Asignacion a:daoAsignacion.lista("select * from empleados_guias where cod_empleado="+getCodEmpleado())){
		for(Asignacion a:daoAsignacion.lista("select * from empleados_guias where cod_empleado="+getCodEmpleado()+" and idguias not in (select idguias from novedades where cod_estado=3);")){			
			_guias.add(a);
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

	public List<SelectItem> getEmpleados() {
		return _empleados;
	}

	public List<Asignacion> getGuias() {
		return _guias;
	}

}
