package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClienteBiblioteca {
	
	public static final int PUERTO = 2019;
	public static final String IP_SERVER = "localhost";
	

	public static void main(String[] args) {
		System.out.println("APLICACI�N BIBLIOTECA");
		System.out.println("-------------------");
		
		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
		
		try (Scanner scan = new Scanner(System.in)){
			
			System.out.println("CLIENTE: Esperando a que el servidor acepte la conexi�n");
			Socket socketAlServidor = new Socket();
			socketAlServidor.connect(direccionServidor);
			System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER + 
					" por el puerto " + PUERTO);
			
			InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
			BufferedReader entradaBuffer = new BufferedReader(entrada);
			
			PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
			String in ="";
			boolean continuar = true;
			
			do {
				Procesos.menu1();
				in = scan.nextLine();
				switch(Integer.parseInt(in)) {
				case 0:
					continuar = false;
					break;
				case 1:
					System.out.println("Introduzca ISBN");
					String titulo = scan.nextLine();
					salida.println("1" +"-"+ titulo);
					System.out.println("CLIENTE: Esperando respuesta:...... ");				
					String respuestaTitulo = entradaBuffer.readLine();
					System.out.println("CLIENTE: Servidor responde, este es el libro: " + respuestaTitulo);
					break;
				case 2:
					System.out.println("Introduzca Titulo ");
					String isbn = scan.nextLine();
					salida.println("2" +"-"+ isbn);
					System.out.println("CLIENTE: Esperando respuesta...... ");				
					String respuestaIsbn = entradaBuffer.readLine();
					System.out.println("CLIENTE: Servidor responde, este es el libro: " + respuestaIsbn);
					break;
				case 3:
					System.out.println("Introduzca Nombre del autor");
					String nombre = scan.nextLine();
					System.out.println("Introduzca Apellido del autor");
					String apellido = scan.nextLine();
					salida.println("3" +"-"+ nombre +"-"+ apellido);
					System.out.println("CLIENTE: Esperando respuesta...... ");				
					String respuestaAutor = entradaBuffer.readLine();
					System.out.println("CLIENTE: Servidor responde, este es el libro: " + respuestaAutor);
					break;
				case 4:					
					entradaBuffer.readLine();					
					System.out.println("Introduzca ISBN");
					String isbnIN = scan.nextLine();
					System.out.println("Introduzca T�tulo");
					String tituloIN = scan.nextLine();						
					String res;
					do{
						System.out.println("�Tiene autor? S/N");
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
					System.out.println("Libro a�adido");
					
					break;
				default:
					System.out.println("Instrucci�n invalida");
				break;			
				
				}				
			}while(continuar);
			socketAlServidor.close();
		} catch (UnknownHostException e) {
			System.err.println("CLIENTE: No encuentro el servidor en la direcci�n" + IP_SERVER);
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
