import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestorJuegos {

    /**
     * Función para manejar las opciones del menú de Gestión de Inventario.
     * @param opcion Opción escogida para el menú.
     * @param catalogoJuegos Lista de los clientes para poder gestionarlos.
     */
    public static void switchJuegos(int opcion, ArrayList<Juego> catalogoJuegos) {
        Scanner sc = new Scanner(System.in);

        switch(opcion) {
            case 1:
                System.out.println("Indica el nombre del juego que quieres añadir al catálogo:\n");
                String nombre = sc.nextLine();
                if (!juegoExiste(nombre, catalogoJuegos)) {
                    System.out.println("¿Cuál es el género del juego?");
                    String genero = sc.nextLine();
                    String pegi = "";
                    boolean activador = true;
                    while (activador) {
                        try {
                            System.out.println("¿Cuál es la calificación PEGI del juego?\n" +
                                    "1. PEGI-3\n" +
                                    "2. PEGI-7\n" +
                                    "3. PEGI-12\n" +
                                    "4. PEGI-16\n" +
                                    "5. PEGI-18\n");
                            int opcionPegi = sc.nextInt();
                            sc.nextLine();
                            switch (opcionPegi) {
                                case 1:
                                    pegi = "PEGI-3";
                                    activador = false;
                                    break;
                                case 2:
                                    pegi = "PEGI-7";
                                    activador = false;
                                    break;
                                case 3:
                                    pegi = "PEGI-12";
                                    activador = false;
                                    break;
                                case 4:
                                    pegi = "PEGI-16";
                                    activador = false;
                                    break;
                                case 5:
                                    pegi = "PEGI-18";
                                    activador = false;
                                    break;
                                default:
                                    System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                            sc.nextLine();
                        }
                    }
                    Juego juego = new Juego (nombre, genero, pegi);
                    activador = true;
                    do {
                        try {
                            System.out.println("¿Para qué sistemas está disponible? (Puedes elegir más de uno separando las opciones con guiones)\n" +
                                    "1. XBOX\n" +
                                    "2. Nintendo\n" +
                                    "3. Play Station\n" +
                                    "4. PC\n");
                            String ediciones = sc.nextLine();
                            String[] stringsEdiciones = ediciones.split("-");
                            int[] numerosEdiciones = new int[ediciones.length()];
                            for (int i = 0; i < stringsEdiciones.length; i++) {
                                int numero = Integer.parseInt(stringsEdiciones[i]);
                                numerosEdiciones[i] = numero;
                            }
//                            int[] opcionesValidas = {1, 2, 3, 4};
//                            for (int numero : numerosEdiciones) {
//                                for (int opcionValida : opcionesValidas) {
//                                    if numero
//                                }
//                            } Estamos intentando asegurarnos que los números introducidos están entre el 1 y el 4.
                        } catch (NumberFormatException e) {
                            System.out.println("Opción no válida, por favor, elige una opción u opciones de las disponibles. Recuerda separarlas con guiones si son mas de una.\n");
                        }
                    } while (activador);

                } else {
                    System.out.println("El juego " + nombre + " ya existe en la base de datos.");
                }
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                System.out.println("Saliendo del sistema de gestión de inventario.\n");
                break;
            default:
                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
        }
    }

    /**
     * Función para construir el menú de Gestión de Inventario.
     * @return menu String que contiene el menú de Gestión de Inventario para imprimir.
     */
    public static String menuJuegos() {
        String menu = "### GESTIÓN DE INVENTARIO ###\n\n" +
                "1. Añadir juego al catálogo\n" +
                "2. Eliminar juego del catálogo\n" +
                "3. Modificar stock de juego\n" +
                "4. Buscar juego por consola\n" +
                "5. Listado de juegos (Orden Alfabético)\n" +
                "6. Listado de juegos (Orden por stock)\n" +
                "7. Salir\n\n" +
                "Elige una opción:";
        return menu;
    }

    /**
     * Función para saber si un juego existe en una lista de juegos según el nombre del mismo.
     * @param nombre Nombre del juego que se quiere comprobar si existe en el catálogo.
     * @param catalogoJuegos Catálogo de juegos donde se quiere comprobar si existe el juego.
     * @return True si el juego ya existe y false en caso contrario.
     */
    public static boolean juegoExiste(String nombre, ArrayList<Juego> catalogoJuegos) {
        for (Juego j : catalogoJuegos) {
            if (j.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

}
