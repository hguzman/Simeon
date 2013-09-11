package com.simeon.controladores;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;


import com.csvreader.CsvReader;
import com.simeon.datos.DaoGuia;
import com.simeon.datos.DaoPersona;
import com.simeon.modelo.Guias;
import com.simeon.modelo.Persona;

@ManagedBean(name="fileUploadController")
@ViewScoped
public class FileUploadController {
//	private String destination = "/home/hguzman/glassfish-3.1.2/glassfish/domains/domain1/eclipseApps/MetroEnvios/archivos/";
	private String destination = "/home/hguzman/workspace/Simeon/WebContent/WEB-INF/archivos/";
	static final Logger log=Logger.getLogger(FileUploadController.class);
	private Integer _registros=0;
	private Integer _personasCargadas=0;
	private Integer _perRechazadas=0;	
	private Integer _guiasCargadas=0;
	private Integer _guiasRechazadas=0;
	private Boolean _deshabilitado=false;

	
    public void upload(FileUploadEvent event) {
    	BasicConfigurator.configure();
    	
        FacesMessage msg = new FacesMessage("Ok! ", event.getFile().getFileName() + " fue subido con éxito.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            setDeshabilitado(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void copyFile(String fileName, InputStream in) {
        try {

            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

//            System.out.println("El nuevo fichero fue creado con éxito!");
            log.debug("El nuevo fichero fue creado con éxito!");
            
            CsvReader reader = null;
            try {
            	// instancio el objeto readerCSV
            	reader = new CsvReader(destination + fileName);
            	// asigno separador de valores punto y coma, si no lo cambian queda por defecto la coma
            	reader.setDelimiter(';');
            	// recorremos las filas del fichero
            	
            	DaoPersona daoPersona=new DaoPersona();
    			DaoGuia daoGuia=new DaoGuia();

//    			Integer cont=1,contPersona=1,contPerRechazadas=1,contGuiasRechazadas=1;

            	while (reader.readRecord()) {
            		setRegistros(getRegistros()+1);
            		
            		Persona persona=new Persona();
            		persona.setCodTipo(Integer.parseInt(reader.get(0)));
            		persona.setNumDocumento(Long.parseLong(reader.get(1)));
            		persona.setNombre(reader.get(2));
            		persona.setDireccion(reader.get(3));
            		persona.setBarrio(reader.get(4));
            		persona.setTelefono(reader.get(5));
            		persona.setCelular(reader.get(6));
            		persona.setCodDepartamento(Integer.parseInt(reader.get(7)));
            		persona.setCodMunicipio(Integer.parseInt(reader.get(8)));
            		
            		
            		if (daoPersona.insertar(persona)>0){
            			setPersonasCargadas(getPersonasCargadas()+1);
            		}else{
            			setPerRechazadas(getPerRechazadas()+1);
            		}

            		Guias guias=new Guias();
        			guias.setRemitente(1); // Solicitud metrotel
        			log.debug("Tipo="+Integer.parseInt(reader.get(0)));
        			log.debug("Num="+Long.parseLong(reader.get(1)));
        			guias.setDestinatario(daoPersona.obtenerId(Integer.parseInt(reader.get(0)), Long.parseLong(reader.get(1))));        			
        			guias.setPeso(0);
        			guias.setCodServicio(6);
        			guias.setCausaDevolucion(0);
        			guias.setImagen("/algo");

        			if (daoGuia.insertar(guias)>0) {
        				setGuiasCargadas(getGuiasCargadas()+1);
        			}else{
        				setGuiasRechazadas(getGuiasRechazadas()+1);
        			}
            		
            	
/*            		Guias guias=new Guias();
        			guias.setRemitente(3); // Solicitud metrotel
//        			guias.setNumDocumentoRemitente(8002042789L); // Nit Metrotel
//        			log.debug("NIT="+guias.getNumDocumentoRemitente());
        			guias.setCodTipoDestinatario(persona.getCodTipo());
        			guias.setNumDocumentoDestinatario(persona.getNumDocumento());
        			guias.setPeso(0);
        			guias.setCodServicio(6);
        			guias.setCausaDevolucion(0);
        			guias.setImagen("/algo");

        			if (daoGuia.insertar(guias)>0) {
        				setGuiasCargadas(getGuiasCargadas()+1);
        				log.debug("Se inserto la Guia="+ guias.getNumDocumentoDestinatario());
        			}else{
        				setGuiasRechazadas(getGuiasRechazadas()+1);
        			}
            		
            		log.debug("Columna 1 : "	+ reader.get(1) + " Columna 2: " + reader.get(2)+ " Columna 3: " + reader.get(3));
*/            	} // end while - recorrido del csv
            } catch (Exception ex) {
            	ex.printStackTrace();
            } finally {
            	reader.close();
            } // end try
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

	public Integer getRegistros() {
		return _registros;
	}

	public void setRegistros(Integer _registros) {
		this._registros = _registros;
	}

	public Integer getPersonasCargadas() {
		return _personasCargadas;
	}

	public void setPersonasCargadas(Integer _personasCargadas) {
		this._personasCargadas = _personasCargadas;
	}

	public Integer getPerRechazadas() {
		return _perRechazadas;
	}

	public void setPerRechazadas(Integer _perRechazadas) {
		this._perRechazadas = _perRechazadas;
	}

	public Integer getGuiasRechazadas() {
		return _guiasRechazadas;
	}

	public void setGuiasRechazadas(Integer _guiasRechazadas) {
		this._guiasRechazadas = _guiasRechazadas;
	}

	public Integer getGuiasCargadas() {
		return _guiasCargadas;
	}

	public void setGuiasCargadas(Integer _guiasCargadas) {
		this._guiasCargadas = _guiasCargadas;
	}

	public Boolean getDeshabilitado() {
		return _deshabilitado;
	}

	public void setDeshabilitado(Boolean _deshabilitado) {
		this._deshabilitado = _deshabilitado;
	}

    
}
