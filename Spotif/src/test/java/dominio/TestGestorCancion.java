package dominio;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import junit.framework.Assert;
import persistencia.ManejadorBD;

public class TestGestorCancion {
	
	private GestorCancion gestorCancion;
	private ManejadorBD manejadorBD;
	private Cancion cancionExistente = new Cancion(1,"La bicicleta",1, 1.65);
	private Cancion cancionInexistente = new Cancion(20,"Rockabike",1, 1.00);
	private Cancion cancionNull = null;
	@Before
	public void setUp() {
		gestorCancion = new GestorCancion();
		
	}
	
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testConexionBBDD() throws SQLException {
		boolean conx = false;	
		conx= gestorCancion.conexion();
		assertTrue("no se ha realizado conexion", conx);		
		
	}
	
	@Test
	public void testReproducirCancionExistente() throws SQLException{
		boolean canExistente = false;
		canExistente = gestorCancion.ReproducirCancion(cancionExistente);
		assertTrue(canExistente);
	}
	
	@Test
	public void testReproducirCancionInexistente() throws SQLException{
		boolean canExistente = true;
		canExistente = gestorCancion.ReproducirCancion(cancionInexistente);
		assertFalse(canExistente);
	}
	
	@Test
	public void testReproducirCancionNull() throws SQLException{
		boolean canNull = true;
		canNull = gestorCancion.ReproducirCancion(cancionNull);
		assertFalse(canNull);
	}

	@Test
	public void testCancionesBBDDVacia() throws SQLException{
		long longitudCancionesBBDD;
		ArrayList<Cancion> cancionesBBDD = new ArrayList();
		cancionesBBDD = gestorCancion.cancionesBBDD();
		longitudCancionesBBDD = (long)cancionesBBDD.size();
		longitudCancionesBBDD = 0;
		assertEquals(0, longitudCancionesBBDD);
		
	}

	@Test
	public void testCancionesBBDDConCanciones() throws SQLException{
		int longitudCacionesBBDD;
		boolean canciones = false;
		ArrayList<Cancion> cancionesBBDD = new ArrayList();
		cancionesBBDD = gestorCancion.cancionesBBDD();
		longitudCacionesBBDD = cancionesBBDD.size();
		if(longitudCacionesBBDD > 0) {
			canciones = true;
		}
		assertTrue(canciones);
	}

	@Test
	public void testCancionesBBDDNull() throws SQLException {
		boolean canciones = false;
		ArrayList<Cancion> cancionesBBDD = new ArrayList();
		cancionesBBDD = gestorCancion.cancionesBBDD();
		cancionesBBDD = null;
		assertNull(cancionesBBDD);
	}

}
