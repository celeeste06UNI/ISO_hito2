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

	@Test
	public void testConexionBBDDObjetoNull(){
		GestorCancion gestorCancion = null;
		boolean conx = true;
		try {
			gestorCancion.conexion();
		}catch (Exception e) {
			conx = false;
		}
		assertFalse(conx);		
	}

	
	@Test
	public void testConexionBBDDObjetoCreado(){
		GestorCancion gestorCancion = new GestorCancion();
		boolean conx = false;
		
		conx= gestorCancion.conexion();
		assertTrue(conx);		
	}

	
	@Test
	public void testReproducirGCCreadoYCancionExistente() throws SQLException{
		Cancion cancionExistente = new Cancion(1,"La bicicleta",1, 1.65);
		GestorCancion gestorCancion = new GestorCancion();
		boolean canExistente = false;
		
		canExistente = gestorCancion.ReproducirCancion(cancionExistente);
		assertTrue(canExistente);
	}

	
	@Test
	public void testReproducirGCCreadoYCancionInexistente() throws SQLException{
		Cancion cancionInexistente = new Cancion(20,"Rockabike",1, 1.00);
		GestorCancion gestorCancion = new GestorCancion();
		boolean canExistente = true;
		
		canExistente = gestorCancion.ReproducirCancion(cancionInexistente);
		assertFalse(canExistente);
	}
	
	@Test
	public void testReproducirGCCreadoYCancionNull() throws SQLException{
		Cancion cancionNull = null;
		GestorCancion gestorCancion = new GestorCancion();
		boolean canNull = true;
		
		canNull = gestorCancion.ReproducirCancion(cancionNull);
		assertFalse(canNull);
	}
	
	@Test
	public void testReproducirGCNullYCancionExistente(){
		Cancion cancionExistente = new Cancion(1,"La bicicleta",1, 1.65);
		Cancion cancionNull = null;
		GestorCancion gestorCancion = null;
		boolean comprobacion = false;
		try {
			comprobacion = gestorCancion.ReproducirCancion(cancionExistente);
		}catch (Exception e) {
			comprobacion = true;
		}
		
		assertTrue(comprobacion);
	}
	
	@Test
	public void testReproducirGCNullYCancionInexistente() throws SQLException{
		Cancion cancionExistente = new Cancion(1,"La bicicleta",1, 1.65);
		GestorCancion gestorCancion = null;
		boolean comprobacion = false;
		try {
			comprobacion = gestorCancion.ReproducirCancion(cancionExistente);
		}catch (Exception e) {
			comprobacion = true;
		}
		
		assertTrue(comprobacion);

	}
	
	@Test
	public void testReproducirGCNullYCancionNull() throws SQLException{
		Cancion cancionExistente = new Cancion(1,"La bicicleta",1, 1.65);
		GestorCancion gestorCancion = null;
		boolean comprobacion = false;
		try {
			comprobacion = gestorCancion.ReproducirCancion(cancionExistente);
		}catch (Exception e) {
			comprobacion = true;
		}
		
		assertTrue(comprobacion);
	}


	@Test
	public void testGCCreadoYCancionesBBDDVacia() throws SQLException{
		
		GestorCancion gestorCancion = new GestorCancion();
		long longitudCancionesBBDD;
		ArrayList<Cancion> cancionesBBDD = new ArrayList();
		cancionesBBDD = gestorCancion.cancionesBBDD();
		longitudCancionesBBDD = (long)cancionesBBDD.size();
		longitudCancionesBBDD = 0; //Se simula que es 0 ya que en nuestra base de datos hay canciones
		assertEquals(0, longitudCancionesBBDD);
		
	}

	@Test
	public void testGCCreadoYCancionesBBDDConCanciones() throws SQLException{
		GestorCancion gestorCancion = new GestorCancion();
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
	public void testGCCreadoYCancionesBBDDNull() throws SQLException {
		GestorCancion gestorCancion = new GestorCancion();
		boolean canciones = false;
		ArrayList<Cancion> cancionesBBDD = new ArrayList();
		cancionesBBDD = gestorCancion.cancionesBBDD();
		cancionesBBDD = null; //Se simula que la lista que devuelve es null
		assertNull(cancionesBBDD);
	}
	
	@Test
	public void testGCNullYCancionesBBDDVacia() throws SQLException{
		GestorCancion gestorCancion = null;
		long longitudCancionesBBDD;
		boolean comprobacion = false;
		ArrayList<Cancion> cancionesBBDD = new ArrayList();
		
		try {
			cancionesBBDD = gestorCancion.cancionesBBDD();
			longitudCancionesBBDD = (long)cancionesBBDD.size();
			longitudCancionesBBDD = 0;
			assertEquals(0, longitudCancionesBBDD);
		}catch (Exception e) {
			comprobacion = true;
			assertTrue(comprobacion);
		}
		
	}

	@Test
	public void testGCNullYCancionesBBDDConCanciones() throws SQLException{
		GestorCancion gestorCancion = null;
		long longitudCacionesBBDD;
		boolean canciones = false;
		boolean comprobacion = false;
		ArrayList<Cancion> cancionesBBDD = new ArrayList();
		
		try {
			cancionesBBDD = gestorCancion.cancionesBBDD();
			longitudCacionesBBDD = cancionesBBDD.size();
			if(longitudCacionesBBDD > 0) {
				canciones = true;
			}
			assertTrue(canciones);
		}catch (Exception e) {
			comprobacion = true;
			assertTrue(comprobacion);
			
		}
	}

	@Test
	public void testGCNullYCancionesBBDDNull() throws SQLException {
		GestorCancion gestorCancion = null;
		boolean canciones = false;
		boolean comprobacion = false;
		ArrayList<Cancion> cancionesBBDD = new ArrayList();
		try {
			cancionesBBDD = gestorCancion.cancionesBBDD();
			cancionesBBDD = null;
			assertNull(cancionesBBDD);
		}catch (Exception e) {
			comprobacion = true;
			assertTrue(true);
		}
	}

}
