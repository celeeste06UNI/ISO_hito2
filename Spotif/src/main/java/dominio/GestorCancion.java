package dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import persistencia.ManejadorBD;

public class GestorCancion {
	
	private ManejadorBD manejador = new ManejadorBD();
	
	public GestorCancion() {
		
	
	}
	
	public boolean conexion() {
		boolean controlConexion;
		controlConexion = manejador.conexion();
		return controlConexion;
	}

	public ArrayList<Cancion> cancionesBBDD() throws SQLException {
		conexion();
		ArrayList <Cancion> canciones = new ArrayList();
		Cancion cancion;
		String SQLquery = "SELECT * FROM canciones";
		ResultSet resultados = manejador.leer(SQLquery);
		       
            if (resultados != null) { 
                while (resultados.next()) {
                	cancion = new Cancion(resultados.getInt("id_cancion"), resultados.getString("titulo"), resultados.getInt("id_album"),
                			resultados.getDouble("precio"));
                	canciones.add(cancion);                   
                }
                resultados.close();
            }
		return canciones;
	}
	
	
	public boolean ReproducirCancion(Cancion cancionBuscada)throws SQLException {
		conexion();
		boolean cancionEnBD = false;
		Cancion cancionBBDD;
		String SQLquery = "SELECT * FROM canciones";
		ResultSet resultados = manejador.leer(SQLquery);
		try {
			if (resultados != null) {
				while (resultados.next()) {
					cancionBBDD = new Cancion(resultados.getInt("id_cancion"), resultados.getString("titulo"),
							resultados.getInt("id_album"), resultados.getDouble("precio"));
					if(cancionBuscada == null) {
						cancionEnBD = false;
					}else if (cancionBBDD.getTitulo().equals(cancionBuscada.getTitulo())) {
							cancionEnBD = true;
					}
					
				}
				resultados.close();
			}
		}catch (Exception e) {
			cancionEnBD = false;
		}

		return cancionEnBD;
			
	}
}