
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;


public class GestorTiendaJuegos {
    /**
     * Función principal de la aplicación.
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        ArrayList<Juego> catalogoJuegos = new ArrayList<>();

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
                                GestorClientes.switchClientes(opcionCliente, listaClientes);
                            } catch (InputMismatchException e) {
                                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                sc.nextLine();
                            }
                        } while (opcionCliente != 7);
                        break;
                    case 2:
                        do {
                            try {
                                System.out.println(GestorJuegos.menuJuegos());
                                opcionCliente = sc.nextInt();
                                sc.nextLine();
                                GestorJuegos.switchJuegos(opcionCliente, catalogoJuegos);
                            } catch (InputMismatchException e) {
                                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                sc.nextLine();
                            }
                        } while (opcionCliente != 7);
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
        String menu = """
                ### MENÚ PRINCIPAL ###
                
                1. Gestión de clientes
                2. Gestión de inventario
                3. Realizar venta
                4. Mostrar ventas
                5. Salir
                
                Elige una opción:""";
        return menu;
    }

}
