//Actualización 30/10
package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

// Clase cliente en la que se utilizara ek menu para controlar la interaccion con la biblioteca.
public class SocketClienteBiblioteca {
	
	// Se determina puerto e IP.
	public static final int PUERTO = 2019;
	public static final String IP_SERVER = "localhost";
	
	
	public static void main(String[] args) {
		System.out.println("APLICACIÓN BIBLIOTECA");
		System.out.println("-------------------");
		
		// Para encapsular el puerto IP del servidor
		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
		
		// Usamos try paera con scanner para cerrar automaticamente 
		try (Scanner scan = new Scanner(System.in)){
			
		
			System.out.println("CLIENTE: Esperando a que el servidor acepte la conexión");
			//Inicializamos el socket y conectamos con la direccion.
			Socket socketAlServidor = new Socket();
			socketAlServidor.connect(direccionServidor);
			System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER + 
					" por el puerto " + PUERTO);
			
			// Inicializamos las entradas y salidas entre cliente y servidor 
			InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
			BufferedReader entradaBuffer = new BufferedReader(entrada);
			
			PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
			String in ="";
			boolean continuar = true;
			
			// Menú repetitivo
			do {
			// mostramos pantalla menú y pedimos instrucción 	
				Procesos.menu1();
				in = scan.nextLine();
				// según instruccion realizamos una de las siguentes acciones: 1.buscar libro poor ISBN 2. buscar libro por titulo 3. buscar libro por autor 4.añadir libro,  0 salir
				switch(Integer.parseInt(in)) {
				// Salir
				case 0:
					continuar = false;
					break;
				
				// Búsqueda por ISBN 
				// Recoge datos en formato: "1-ISBN" y se envían al servidor
				// Muestra resultado enviado por el servidor 	
					
				case 1:
					System.out.println("Introduzca ISBN");
					String isbn = scan.nextLine();
					salida.println("1" +"-"+ isbn);
					System.out.println("CLIENTE: Esperando respuesta:...... ");				
					String respuestaIsbn = entradaBuffer.readLine();
					System.out.println("CLIENTE: Servidor responde, este es el libro: " + respuestaIsbn);
					break;
					
					// Búsqueda por  Titulo
					// Recoge datos en formato: "2-Titulo" y se envían al servidor
					// Muestra resultado enviado por el servidor 	
				case 2:
					System.out.println("Introduzca Titulo ");
					String titulo = scan.nextLine();
					salida.println("2" +"-"+ titulo);
					System.out.println("CLIENTE: Esperando respuesta...... ");				
					String respuestaTitulo = entradaBuffer.readLine();
					System.out.println("CLIENTE: Servidor responde, este es el libro: " + respuestaTitulo);
					break;
					
					// Búsqueda por  Autor
					// Recoge datos en formato: "3-nombreAutor-apellidoAutor" y se envían al servidor
					// Muestra resultado enviado por el servidor 
				case 3:
					System.out.println("Introduzca Nombre del autor");
					String nombre = scan.nextLine();
					System.out.println("Introduzca Apellido del autor");
					String apellido = scan.nextLine();
					salida.println("3" +"-"+ nombre +"-"+ apellido);
					System.out.println("CLIENTE: Esperando respuesta...... ");				
					String respuestaAutor = entradaBuffer.readLine();
					System.out.println("CLIENTE: Servidor responde, estos son los libros del autor: " + respuestaAutor);
					break;
					
					// Incluir libro nuevo
				case 4:
					
					// se envia la instruccion indicada al servidor
					salida.println("4");
					System.out.println("Esperando respuesta SERVIDOR");
					
					//Espera respuesta del servidor.
					String permiso = entradaBuffer.readLine();
					System.out.println(permiso);
					
					//Tras recibir el permiso del servidor se procede a introducir los datos del libro.
					System.out.println("Introduzca ISBN");
					String isbnIN = scan.nextLine();
					System.out.println("Introduzca Título");
					String tituloIN = scan.nextLine();						
					String res;
					
					// Se pregunta si tiene autor o no y dependiendo respueta se completan los datos y se envía al servidor en formato: 4-ISBN-Titulo-NombreAutor-ApellidoAutor.
					do{
						System.out.println("¿Tiene autor? S/N");
						 res = scan.nextLine();					
					}while((!res.equalsIgnoreCase("S")) && (!res.equalsIgnoreCase("N")));
						
					if (res.equalsIgnoreCase("S")) {
						System.out.println("Introduzca Nombre del autor");
						String nombreAutorIN = scan.nextLine();
						System.out.println("Introduzca Apellido del autor");
						String apellidoAutorIN = scan.nextLine();
						salida.println("4" + "-" + isbnIN +"-"+ tituloIN +"-"+ nombreAutorIN + "-" + apellidoAutorIN);
					} else{
						salida.println("4" + "-" + isbnIN +"-"+ tituloIN);
					}
					System.out.println(entradaBuffer.readLine());
					
					break;
				default:
					System.out.println("Instrucción invalida");
				break;			
				
				}				
			}while(continuar);
			socketAlServidor.close();
		} catch (UnknownHostException e) {
			System.err.println("CLIENTE: No encuentro el servidor en la dirección" + IP_SERVER);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("CLIENTE: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("CLIENTE: Error -> " + e);
			e.printStackTrace();
		}
		
		System.out.println("CLIENTE: Fin del programa");
	}
					

	}
