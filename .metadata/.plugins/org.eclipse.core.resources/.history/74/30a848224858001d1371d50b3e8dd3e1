package biblioteca;
import java.util.*;

public class Autor {
		private String nombre;
		private String apellido;
		private ArrayList<Libro> bibliografia;

		
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
		
		public String bibliografiaToString() {
			String string="///";
			int count=1;
			for (Libro element: this.bibliografia) {
				string = string + "Libro" + count + ": " + "ISBN: " + element.getIsbn() +  " Nombre:" + element.getNombre() + "///";
				count++;
			}
			return string;
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
		public void addLibro(Libro libro){
			this.bibliografia.add(libro);
		}
		
		@Override
		public String toString() {
			return "Autor [nombre=" + nombre + ", apellido=" + apellido + "]\n +"
					+ this.bibliografiaToString();
		}
		
}