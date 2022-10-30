//Actualización 30/10
package biblioteca;
import java.util.*;

	/*
	 * Clase autor en la que se define el nombre, apellido y bibliografía.
	 * 
	 */
public class Autor {
	/*
	 * declaramos los atributos
	 */
		private String nombre;
		private String apellido;
		private ArrayList<Libro> bibliografia;

		
		/**
		 * 
		 */
		//Constructor
		public Autor() {
			this.nombre="Joseph";
			this.apellido="Conrad";
			this.bibliografia=new ArrayList<Libro>();
		}
		

		public Autor(String nombre, String apellido) {
			this.nombre=nombre;
			this.apellido=apellido;
			this.bibliografia=new ArrayList<Libro>();
		}
						
		//Getters
		
		public String getNombre() {
			return nombre;
		}
				
		public String getApellido() {
			return apellido;
		}
		
		public ArrayList<Libro> getBibliografia() {
			return bibliografia;
		}		

		//Setters
		public void setNombre(String nombre) {
			this.nombre=nombre;
		}
		
		public void setApellido(String apellido) {
			this.apellido = apellido;
		}
		
		public void setBibliografia(ArrayList<Libro> bibliografia) {
			this.bibliografia = bibliografia;
		}
		
		//Methods
		/*
		 * Metodo que nos permite añadir  un libro a la bibliografia.
		 */
		public void addLibro(Libro libro){
			this.bibliografia.add(libro);
		}
		
		/*
		 * dos metodos to string que nos devuelve el autor y otro la bibñiografía.
		 */
		@Override
		public String toString() {
			return "Autor: " + nombre + " " + apellido ;
		}
		
		public String toStringBio() {
			return "Autor: " + nombre +" " + apellido + "\n +"
					+ this.bibliografiaToString();
		}

		public String bibliografiaToString() {
			String string="///";
			int count=1;
			for (Libro element: this.bibliografia) {
				string = string + "Libro : " + count + ": " + "ISBN: " + element.getIsbn() +  " Nombre:" + element.getNombre() + "///";
				count++;
			}
			return string;
		}
		
}