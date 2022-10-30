//Actualización 30/10

package cliente;

/*
 * Creamos el menu  en la que se mostrará para inicializar la búsqueda,
	añadir un libro o salir del menú.
 */
public abstract class Procesos {
	/*
	 * Se trata de un metodo en el que establecemos el menú para poder consultar,
	 * añadir y salir del menú.
	 */
	public static void menu1(){
		System.out.println("MENU");
		System.out.println("1. Consultar libro por ISBN");
		System.out.println("2. Consultar libro por nombre");
		System.out.println("3. Consultar libro por autor");
		System.out.println("4. Añadir libro");
		System.out.println("0. Salir");
		System.out.println("");		
	}
	
}