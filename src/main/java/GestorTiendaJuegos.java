
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;


public class GestorTiendaJuegos {
    /**
     * Función principal de la aplicación.
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        ArrayList<Cliente> listaClientes = new ArrayList<>();

        do {
            try {
                System.out.println(menuPrincipal());
                opcion = sc.nextInt();
                sc.nextLine();
                int opcionCliente = 0;

                switch (opcion) {
                    case 1:
                        do {
                            try {
                                System.out.println(GestorClientes.menuClientes());
                                opcionCliente = sc.nextInt();
                                sc.nextLine();
                                GestorClientes.switchCliente(opcionCliente, listaClientes);
                            } catch (InputMismatchException e) {
                                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                sc.nextLine();
                            }
                        } while (opcionCliente != 7);
                        break;
                    case 2:
                        System.out.println("Opción 2\n");
                        break;
                    case 3:
                        System.out.println("Opción 3\n");
                        break;
                    case 4:
                        System.out.println("Opción 4\n");
                        break;
                    case 5:
                        System.out.println("¡Gracias por usar nuestro programa de gestión de tiendas de juegos!\n");
                        break;
                    default:
                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                sc.nextLine();
            }
        } while (opcion != 5);

        sc.close();
    }

    /**
     * Función para construir el menú principal de la aplicación.
     * @return menu String que contiene el menú principal de la aplicación para imprimir.
     */
    public static String menuPrincipal(){
        String menu = "### MENÚ PRINCIPAL ###\n\n" +
                "1. Gestión de clientes\n" +
                "2. Gestión de inventario\n" +
                "3. Realizar venta\n" +
                "4. Mostrar ventas\n" +
                "5. Salir\n\n" +
                "Elige una opción:";
        return menu;
    }

}
