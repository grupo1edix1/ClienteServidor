//Actualizaci�n 30/10

package cliente;

/*
 * Creamos el menu  en la que se mostrar� para inicializar la b�squeda,
	a�adir un libro o salir del men�.
 */
public abstract class Procesos {
	/*
	 * Se trata de un metodo en el que establecemos el men� para poder consultar,
	 * a�adir y salir del men�.
	 */
	public static void menu1(){
		System.out.println("MENU");
		System.out.println("1. Consultar libro por ISBN");
		System.out.println("2. Consultar libro por nombre");
		System.out.println("3. Consultar libro por autor");
		System.out.println("4. A�adir libro");
		System.out.println("0. Salir");
		System.out.println("");		
	}
	
}