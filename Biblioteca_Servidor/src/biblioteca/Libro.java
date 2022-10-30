//Actualización 30/10
package biblioteca;

public class Libro {
	private String nombre;
	private String isbn;
	private Autor autor;
	
	public Libro() {
		this.nombre="nombre";
		this.isbn="XXXXXXXXXX";
		this.autor=new Autor();
	}
	
	public Libro(String nombre,String isbn,Autor autor) {
		this.nombre=nombre;
		this.isbn=isbn;
		this.autor=autor;
		this.autor.addLibro(this);
	}
	
	public Libro(String nombre,String isbn) {
		this.nombre=nombre;
		this.isbn=isbn;
	}
	
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Libro [nombre=" + nombre + ", isbn=" + isbn + ", autor=" + autor.toString() + "]";
	}
	
	

}
