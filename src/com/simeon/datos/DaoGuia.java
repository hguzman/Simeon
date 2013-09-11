package com.simeon.datos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.simeon.modelo.Guias;

public class DaoGuia extends BDatos {
	static final Logger log=Logger.getLogger(DaoGuia.class);
	private String _sql;
	
	public DaoGuia() {
		BasicConfigurator.configure();
	}
	public Integer insertar(Guias objeto){
		Integer resp=0;
		_sql="insert into guias (remitente,destinatario,peso, cod_servicio,causa_devolucion,imagen) value(?,?,?,?,?,?);";
		PreparedStatement sentencia=this.crearSentencia(_sql);
		Guias guias=(Guias) objeto;
		
		try {
			sentencia.setInt(1, guias.getRemitente());
			sentencia.setInt(2, guias.getDestinatario());
			sentencia.setInt(3, guias.getPeso());
			sentencia.setInt(4, guias.getCodServicio());
			sentencia.setInt(5, guias.getCausaDevolucion());
			sentencia.setString(6, guias.getImagen());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resp=actualizar(sentencia);
		return resp;

	}
	
	public int actualizarImpresion(){
		Integer resp=0;
		_sql="update guias set impresa=true where impresa=false;";
		PreparedStatement sentencia=this.crearSentencia(_sql);
		resp=actualizar(sentencia);
		return resp;
	}

}
