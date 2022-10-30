//Actualización 30/10
package biblioteca;

// Clase libro qu ealmacena nombre, isbn, autor.
public class Libro {
	private String nombre;
	private String isbn;
	private Autor autor;
	
	// Constructor
	public Libro() {
		this.nombre="nombre";
		this.isbn="XXXXXXXXXX";
		this.autor=new Autor();
	}
	
	// Constructor
	public Libro(String nombre,String isbn,Autor autor) {
		this.nombre=nombre;
		this.isbn=isbn;
		this.autor=autor;
		this.autor.addLibro(this);
	}
	
	// Constructor
	public Libro(String nombre,String isbn) {
		this.nombre=nombre;
		this.isbn=isbn;
	}
	
	// Métodos get and set
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

	// Método to string en el que se controla si hay algun autor 
	@Override
	public String toString() {
		String string = "Titulo: " + nombre + ", ISBN: " + isbn;
		if(this.autor!=null) {
			string= string + " " + autor.toString();
		}
		return string;
	}
	
	

}
