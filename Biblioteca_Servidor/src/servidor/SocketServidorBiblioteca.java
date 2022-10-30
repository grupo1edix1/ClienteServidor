//Actualización 30/10
package servidor;
import biblioteca.*;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

	// Clase socketservidor en la que crea un objeto por cada petición al servidor.
	public class SocketServidorBiblioteca {
	public static final int PUERTO = 2019;
	
	public static void main(String[] args) {
		System.out.println("INICIANDO SERVIDOR BIBLIOTECA");
		System.out.println("--------------------");
		
		// Inicializamos cuenta de peticiones
		int peticion = 0;
		// Inicializamos biblioteca
		Biblioteca biblioteca= new Biblioteca();
		
		// Se crea un hilo por cada petición al servidor y se cierra gracias al try
		try (ServerSocket servidor = new ServerSocket()){
			
			//Crea la dirección del socket que apunta al puerto.
			InetSocketAddress direccion = new InetSocketAddress(PUERTO);
			
			//Para determinar las peticiones desde el puerto 
			servidor.bind(direccion);
			
			System.out.println("SERVIDOR: Esperando peticion por el puerto " + PUERTO);
		
		   // Creamos el bucle para ternelo siempre activo 
			while (true) {
				// Acepta las peticiones al socket entrantes
				Socket socketAlCliente = servidor.accept();
				System.out.println("SERVIDOR: peticion numero " + ++peticion + " recibida");
				// se crea un  nuevo al hilo y la bibnlioteca común
				new HiloBiblioteca(socketAlCliente, biblioteca);
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
