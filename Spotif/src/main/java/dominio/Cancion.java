package dominio;

public class Cancion {
	
	private int id_cancion;
	private String titulo;
	private int id_album;
	private double precio;


	public Cancion(int id_cancion, String titulo, int id_album, double precio) {
		super();
		this.id_cancion = id_cancion;
		this.titulo = titulo;
		this.id_album = id_album;
		this.precio = precio;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getId_cancion() {
		return id_cancion;
	}

	public void setId_cancion(int id_cancion) {
		this.id_cancion = id_cancion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getId_album() {
		return id_album;
	}

	public void setId_album(int id_album) {
		this.id_album = id_album;
	}

	@Override
	public String toString() {
		return "Cancion [id_cancion=" + id_cancion + ", titulo=" + titulo + ", id_album=" + id_album + ", precio="
				+ precio + "]";
	}

	
	
	
	
}
