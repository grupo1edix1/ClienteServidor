//Actualización 30/10
package servidor;
import biblioteca.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

// Clase HiloBiblioteca que implementa el Runnable para convertirlos en hilo
public class HiloBiblioteca implements Runnable{
	
	private static int numCliente = 0;
	private Thread hilo;
	private Socket socketAlCliente;
	private Biblioteca biblioteca;
	// Constructor se le pasa socket creado en el servidor y enlazamos la biblioteca para ternerla en común. inicializamos hilo.
	public HiloBiblioteca(Socket socketAlCliente, Biblioteca biblioteca) {
		numCliente++;
		hilo = new Thread(this, "Cliente_" + numCliente);
		this.socketAlCliente = socketAlCliente;
		hilo.start();
		this.biblioteca=biblioteca;
	}
			

	// Método principal del hilo		
	public void run(){
		System.out.println("Estableciendo comunicacion con " + hilo.getName());
		
		//Declaramos las variables de entradas y salidas entre el servidor y el cliente.
		PrintStream salida = null;
		InputStreamReader entrada = null;
		BufferedReader entradaBuffer = null;
		
		
		try {
		
			//Inicializamos las entradas y salidas con el socket.  
			salida = new PrintStream(socketAlCliente.getOutputStream());
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
			entradaBuffer = new BufferedReader(entrada);
			
			String texto = "";
			boolean continuar = true;
			
			// Creamos bucle hasta que cancele el cliente. 
			while (continuar) {
				//Leemos entrada recibida del cliente que estará construida de esta manera: [numero de instruccion] - [dato]-[dato1]-[dato2]...
				//El dato recibido divide por el "-" y según la interaccion en el menu del cliente podemos identificar la orden elegida y los datos requerido. 
				texto = entradaBuffer.readLine();
				String[] order = texto.split("-");
				int instruction = Integer.parseInt(order[0]);
				
				//Con el primer dato instrucción podemos elegir la orden apropiada
				switch(instruction) {
				
				//Busqueda por ISBN del libro, se envia resultado al cliente
				case 1:
					String isbn = order[1];
					try {
					System.out.println("SERVIDOR: El resultado: " + biblioteca.findIsbn(isbn).toString());
					salida.println(biblioteca.findIsbn(isbn).toString());
					}catch(NullPointerException e) {
						salida.println("No se encontró el libro");
					}
					break;	
					
				//Busqueda por titulo del libro, se envia resultado al cliente.
				case 2:
					String titulo = order[1];
					try {
					System.out.println("SERVIDOR: El resultado: " + biblioteca.findTitulo(titulo).toString());
					salida.println(biblioteca.findTitulo(titulo).toString());
					}catch(NullPointerException e) {
						salida.println("No se encontró el libro");
					}
					break;		
					
				//Busqueda por nombre y apellido de autor envía resultado al cliente.
				case 3:
					String nombre = order[1];
					String apellido = order[2];
					try {
					System.out.println("SERVIDOR: El resultado: " + biblioteca.findAutor(nombre, apellido).bibliografiaToString());
					salida.println(biblioteca.findAutor(nombre, apellido).bibliografiaToString());
					}catch(NullPointerException e) {
						salida.println("No se encontró el autor");
					}
					break;
				//Ingreso de libro a biblioteca.	
				case 4:					
					System.out.println("Recibiendo información de Cliente_" + numCliente);
					
					//Controlamos si un hilo está ingresando un libro y pone en espera al resto
					salida.println(biblioteca.permiso());
					
					// Extraemos cada dato del libro y segun si tiene autor o no ingresamos el libro con los datos apropiados a la biblioteca. 
					//Se comprueba si el ISBN existe. 
					//Tras añadir el libro o mostrar error se libera el proximo hilo en espera(notify() en métodos biblioteca.addLibro(libro), y  biblioteca.resetearCola();
					String texto2 = entradaBuffer.readLine();
					order = texto2.split("-");
					String isbnAdd = order[1];
					String tituloAdd = order[2];
					Libro libro;
					Autor autor;
					if (biblioteca.findIsbn(isbnAdd)==null) {
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
						salida.println("Libro añadido : " + biblioteca.findIsbn(isbnAdd).toString());
					}else {
						System.out.println("ISBN no válido");
						salida.println("ISBN no válido");
						biblioteca.resetearCola();
					}
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
