//Actualización 30/10
package servidor;
import biblioteca.*;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class SocketServidorBiblioteca {
	public static final int PUERTO = 2019;
	
	public static void main(String[] args) {
		System.out.println("INICIANDO SERVIDOR BIBLIOTECA");
		System.out.println("--------------------");
		
		int peticion = 0;
		Biblioteca biblioteca= new Biblioteca();
		
		try (ServerSocket servidor = new ServerSocket()){
			
			InetSocketAddress direccion = new InetSocketAddress(PUERTO);
			servidor.bind(direccion);
			
			System.out.println("SERVIDOR: Esperando peticion por el puerto " + PUERTO);
		
		while (true) {
			Socket socketAlCliente = servidor.accept();
			System.out.println("SERVIDOR: peticion numero " + ++peticion + " recibida");
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
