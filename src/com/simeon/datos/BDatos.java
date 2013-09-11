package com.simeon.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


public class BDatos {
	private static final Logger log=Logger.getLogger(BDatos.class.getName());
	private String _driver;
	private static Connection _conexion; // Cualquier instancia que se genere
	private String _url;
	private String _usuario;
	private String _passwd;
	private PreparedStatement _sentencia;	

	public BDatos() {
		BasicConfigurator.configure();
		log.info("Contructor clase BDatos");
	}
	
	public BDatos(String driver){
		setDriver(driver);
		cargarDriver();
	}

    private void cargarDriver(){
        try {
            Class.forName(this.getDriver());
        } catch (ClassNotFoundException ex) {
        	log.error("No se encuentra el controlador");
        }		
	}
    
    public Boolean conectar(){
    	Boolean resp=false;
    	try {    	
    		_conexion=DriverManager.getConnection(this.getUrl(), this.getUsuario(), this.getPasswd());
			resp=true;
		} catch (SQLException e) {			
			log.error("No se pudo conectar");			
			log.error(e.getMessage());
		}
    	return resp;
    }
    
    public PreparedStatement crearSentencia(String sql){
        try {
            this.setSentencia(getConexion().prepareStatement(sql));
            this.debug(sql);
            return _sentencia;
        } catch (SQLException ex) {
        	log.error("Sentencia Mal creada");
            return null;
        }
    }
    
    protected ResultSet consultar(PreparedStatement sentencia){
        ResultSet resp=null;
        try {
            resp= sentencia.executeQuery();
        } catch (SQLException ex) {
        	log.error("No se ejecuto la consulta");
        }
        return resp;
    }

    protected int actualizar(PreparedStatement sentencia){
        int _resp=0;
        try {
             _resp=sentencia.executeUpdate();             
        } catch (SQLException ex) {
        	log.error("No se pudo Actualizar "+ ex.getMessage());
        }
        return _resp;
        
    }	
    
    public void debug(String sql){
    	log.debug("Sentencia="+sql);
    }

	public String getDriver() {
		return _driver;
	}

	public void setDriver(String _driver) {
		this._driver = _driver;
	}

	public static Connection getConexion() {
		return _conexion;
	}


	public String getUrl() {
		return _url;
	}

	public void setUrl(String _url) {
		this._url = _url;
	}

	public String getUsuario() {
		return _usuario;
	}

	public void setUsuario(String _usuario) {
		this._usuario = _usuario;
	}

	public String getPasswd() {
		return _passwd;
	}

	public void setPasswd(String _passwd) {
		this._passwd = _passwd;
	}

	public PreparedStatement getSentencia() {
		return _sentencia;
	}

	public void setSentencia(PreparedStatement _sentencia) {
		this._sentencia = _sentencia;
	}


	
	
}
