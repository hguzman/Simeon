package com.simeon.controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.BasicConfigurator;


import com.simeon.datos.BDatos;
import com.simeon.datos.DaoGuia;
import com.simeon.datos.DaoPersona;
import com.simeon.modelo.Guias;
import com.simeon.modelo.Persona;



@ManagedBean(name="ctrlGuias")
@ViewScoped
public class CtrlGuias {
	private Persona _personaRemitente;
	private Persona _personaDestinatario;	
	private List<Persona> _personas;
	private String _sql;
	private JasperPrint jasperPrint;
	
	public CtrlGuias() {
		_sql="select * from personas;";
		DaoPersona daoPersona=new DaoPersona();

			try {
				setPersonas(daoPersona.listaP(_sql));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	public void imprimir(ActionEvent actionEvent) throws JRException, IOException{
		BasicConfigurator.configure();
//		log.debug("Entro al evento imprimir");

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("ng", 1);
		DaoGuia daoGuia=new DaoGuia();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.responseComplete();
		
		jasperPrint=JasperFillManager.fillReport("/home/hguzman/workspace/Simeon/WebContent/WEB-INF/reportes/guiasFaltantes1.jasper", param,BDatos.getConexion());

		HttpServletResponse httpServletResponse=(HttpServletResponse)facesContext.getExternalContext().getResponse();

		httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-disposition", "inline; filename=report.pdf");  
	    ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();  
	    JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
	    daoGuia.actualizarImpresion();
//	    log.debug("Aqui termino la ejecución");
	}

	public void guardarGuia(){
		
		Guias guias=new Guias();
//		log.debug("valor Remitente"+this.getPersonaRemitente().getCodTipo());
		guias.setRemitente(getPersonaRemitente().getId());
		guias.setDestinatario(getPersonaDestinatario().getId());
		guias.setPeso(0);
//		guias.setValor(0);
		guias.setCodServicio(6);
		guias.setCausaDevolucion(1);
		
		DaoGuia daoGuia=new DaoGuia();
		try {
			FacesContext facesContext=FacesContext.getCurrentInstance();
			if (daoGuia.insertar(guias)>0){
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

	
	public Persona getPersonaRemitente() {
		return _personaRemitente;
	}
	public void setPersonaRemitente(Persona _personaRemitente) {
		this._personaRemitente = _personaRemitente;
	}


	public List<Persona> getPersonas() {
		return _personas;
	}


	public void setPersonas(List<Persona> _personas) {
		this._personas = _personas;
	}

	public Persona getPersonaDestinatario() {
		return _personaDestinatario;
	}

	public void setPersonaDestinatario(Persona _personaDestinatario) {
		this._personaDestinatario = _personaDestinatario;
	}

}
