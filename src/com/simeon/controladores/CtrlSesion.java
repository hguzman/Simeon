package com.simeon.controladores;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.simeon.datos.BDatos;


@ManagedBean(name="ctrlSesion")
@SessionScoped
public class CtrlSesion {
	private static final Logger log=Logger.getLogger(CtrlSesion.class.getName());
	private String _login;
	private String _passwd;

	public CtrlSesion() {
		BasicConfigurator.configure();
	}
	
	public String entrar(){
		String resp="error";
		BDatos datos=new BDatos("com.mysql.jdbc.Driver");
		datos.setUrl("jdbc:mysql://localhost:3306/simeon");
		datos.setUsuario(getLogin());
		datos.setPasswd(getPasswd());
		if (datos.conectar()){
			log.info("Puede Entrar");
			resp="success";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se puede iniciar Sesión", "¡Lo siento!"));
		}
		return resp;
	}
	
	public String salir(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();

        Object session = ec.getSession(false);
        HttpSession httpSession = (HttpSession) session;
        httpSession.invalidate();
        return "salir";
    }

	public void puedeEntrar(){
		if (getLogin()==null){
	        FacesContext context = FacesContext.getCurrentInstance();
	        ExternalContext ec = context.getExternalContext();
	        	
	        String ctxPath = ((ServletContext) ec.getContext()).getContextPath();        
	        try {
				ec.redirect(ctxPath + "/faces/index.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        						
		}
	}
	
	public String getLogin() {
		return _login;
	}
	public void setLogin(String _login) {
		this._login = _login;
	}
	public String getPasswd() {
		return _passwd;
	}
	public void setPasswd(String _passwd) {
		this._passwd = _passwd;
	}
	
	
}
