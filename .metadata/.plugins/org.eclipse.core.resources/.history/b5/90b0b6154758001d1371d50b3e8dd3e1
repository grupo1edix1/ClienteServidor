package servidor;
import biblioteca.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class HiloBiblioteca implements Runnable{
	private static int numCliente = 0;
	private Thread hilo;
	private Socket socketAlCliente;
	private Biblioteca biblioteca;
	
	public HiloBiblioteca(Socket socketAlCliente, Biblioteca biblioteca) {
		numCliente++;
		hilo = new Thread(this, "Cliente_" + numCliente);
		this.socketAlCliente = socketAlCliente;
		hilo.start();
		this.biblioteca=biblioteca;
	}
			

			
	public void run(){
		System.out.println("Estableciendo comunicacion con " + hilo.getName());
		PrintStream salida = null;
		InputStreamReader entrada = null;
		BufferedReader entradaBuffer = null;
		
		try {
			
			salida = new PrintStream(socketAlCliente.getOutputStream());
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
			entradaBuffer = new BufferedReader(entrada);
			
			String texto = "";
			boolean continuar = true;
			
			while (continuar) {
				texto = entradaBuffer.readLine();
				String[] order = texto.split("-");
				int instruction = Integer.parseInt(order[0]);
				switch(instruction) {
				case 1:
					String isbn = order[1];
					try {
					System.out.println("SERVIDOR: El resultado: " + biblioteca.findIsbn(isbn).toString());
					salida.println(biblioteca.findIsbn(isbn).toString());
					}catch(Exception e) {
						salida.println("No se encontró el libro");
					}
					break;					
				case 2:
					String titulo = order[1];					
					System.out.println("SERVIDOR: El resultado: " + biblioteca.findTitulo(titulo).toString());
					salida.println(biblioteca.findTitulo(titulo).toString());
					break;					
				case 3:
					String nombre = order[1];
					String apellido = order[2];					
					System.out.println("SERVIDOR: El resultado: " + biblioteca.findAutor(nombre, apellido).bibliografiaToString());
					salida.println(biblioteca.findAutor(nombre, apellido).bibliografiaToString());
					break;
				case 4:					
					System.out.println("Recibiendo información de Cliente_" + numCliente);
					
					salida.println(biblioteca.permiso());
					
					String texto2 = entradaBuffer.readLine();
					order = texto2.split("-");
					String isbnAdd = order[1];
					String tituloAdd = order[2];
					Libro libro;
					Autor autor;
					if (order.length>3) {
						String nombreAdd = order[3];
						String apellidoAdd = order[4];
						if (biblioteca.findAutor(nombreAdd, apellidoAdd) != null) {
							autor = biblioteca.findAutor(nombreAdd,apellidoAdd);
							libro = new Libro(tituloAdd, isbnAdd, autor);
							autor.addLibro(libro);
						}else {
							autor = new Autor(nombreAdd, apellidoAdd);
							libro = new Libro(tituloAdd, isbnAdd, autor);
							autor.addLibro(libro);
						}
					}else {
						libro = new Libro(tituloAdd, isbnAdd);
					}
					
					biblioteca.addLibro(libro);					
					System.out.println("Libro añadido:" + biblioteca.findIsbn(isbnAdd).toString());
					break;
				default:
					salida.println("No válido");
					break;
				}
			}
		
	} catch (IOException e) {
		System.err.println("SERVIDOR: Error de entrada/salida");
		e.printStackTrace();
	} catch (Exception e) {
		System.err.println("SERVIDOR: Error");
		e.printStackTrace();
	}
		
	}

}
