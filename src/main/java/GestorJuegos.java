import java.util.ArrayList;
import java.util.Arrays;
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
        String nombre;

        switch(opcion) {
            case 1:
                System.out.println("Indica el nombre del juego que quieres añadir al catálogo:");
                nombre = sc.nextLine();
                if (!juegoExiste(nombre, catalogoJuegos)) {
                    System.out.println("¿Cuál es el género del juego?");
                    String genero = sc.nextLine();
                    String pegi = "";
                    boolean activador = true;
                    while (activador) {
                        try {
                            System.out.println("""
                                    ¿Cuál es la calificación PEGI del juego?
                                    1. PEGI-3
                                    2. PEGI-7
                                    3. PEGI-12
                                    4. PEGI-16
                                    5. PEGI-18""");
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
                    anadirJuego(nombre, genero, pegi, catalogoJuegos);
                    // Bucle que solicita al usuario los sistemas en los que está disponible el juego.
                    // El usuario puede introducir múltiples opciones separadas por guiones (por ejemplo, "1-3-4").
                    // Del string obtenido del input del usuario se crea una lista de los números separados por un guion.
                    // Se convierte cada elemento de la lista a integer y se valida que esté dentro de las opciones válidas (1 a 4).
                    // Si todo es correcto se almacena en la lista numerosEdiciones para gestionar cada una de las ediciones más adelante.
                    // Si hay algún error (número no válido o formato incorrecto), se lanza una excepción y se vuelve a pedir el input al usuario.
                    ArrayList<Integer> numerosEdiciones = new ArrayList<>();
                    activador = true;
                    do {
                        try {
                            numerosEdiciones.clear();
                            System.out.println("""
                                    ¿Para qué sistemas está disponible? (Puedes elegir más de uno separando las opciones con guiones)
                                    1. XBOX
                                    2. Nintendo
                                    3. Play Station
                                    4. PC""");
                            String ediciones = sc.nextLine();
                            String[] stringsEdiciones = ediciones.split("-");
                            for (String stringsEdicion : stringsEdiciones) {
                                int numero = Integer.parseInt(stringsEdicion);
                                numerosEdiciones.add(numero);
                            }
                            Integer[] opcionesValidas = {1, 2, 3, 4};
                            for (int numero : numerosEdiciones) {
                                boolean numeroEnLista = Arrays.asList(opcionesValidas).contains(numero);
                                if (!numeroEnLista) {
                                    throw new IllegalArgumentException();
                                }
                            }
                            activador = false;
                        } catch (NumberFormatException e) {
                            System.out.println("Opción no válida, por favor, elige una opción u opciones de las disponibles. Recuerda separarlas con guiones si son mas de una.\n");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Alguna de las opciones no es válida, por favor, elige una o varias opciones de las disponibles.");
                        }
                    } while (activador);
                    // Una vez tenemos los sistemas para los que está disponible el juego entramos en cada opción
                    // según el input del usuario para poder poner precio específico y stock disponible según sistema.
                    for (int numero: numerosEdiciones) {
                        if (numero == 1) {
                            String consola = "XBOX";
                            activador = true;
                            double precio = 0f;
                            do {
                                try {
                                    System.out.println("¿Qué precio tiene el juego '" + nombre + "' en Xbox?");
                                    precio = sc.nextDouble();
                                    sc.nextLine();
                                    activador = false;
                                } catch (InputMismatchException e) {
                                    System.out.println("Importe no válido, por favor, ingresa un importe válido.");
                                    sc.nextLine();
                                }
                            } while (activador);
                            activador = true;
                            int stock = 0;
                            do {
                                try {
                                    System.out.println("¿Cuantás unidades del juego '" + nombre + "' en Xbox quieres añadir al stock?");
                                    stock = sc.nextInt();
                                    sc.nextLine();
                                    activador = false;
                                } catch (InputMismatchException e) {
                                    System.out.println("Unidades no válidas, por favor, ingresa un número de unidades válido.");
                                    sc.nextLine();
                                }
                            } while (activador);
                            EdicionJuego edicionJuego = new EdicionJuego(consola, precio, stock);
                            catalogoJuegos.getLast().anadirEdicion(consola, edicionJuego);
                        }
                        if (numero == 2) {
                            String consola = "Nintendo";
                            activador = true;
                            double precio = 0f;
                            do {
                                try {
                                    System.out.println("¿Qué precio tiene el juego '" + nombre + "' en Nintendo?");
                                    precio = sc.nextDouble();
                                    sc.nextLine();
                                    activador = false;
                                } catch (InputMismatchException e) {
                                    System.out.println("Importe no válido, por favor, ingresa un importe válido.");
                                    sc.nextLine();
                                }
                            } while (activador);
                            activador = true;
                            int stock = 0;
                            do {
                                try {
                                    System.out.println("¿Cuantás unidades del juego '" + nombre + "' en Nintendo quieres añadir al stock?");
                                    stock = sc.nextInt();
                                    sc.nextLine();
                                    activador = false;
                                } catch (InputMismatchException e) {
                                    System.out.println("Unidades no válidas, por favor, ingresa un número de unidades válido.");
                                    sc.nextLine();
                                }
                            } while (activador);
                            EdicionJuego edicionJuego = new EdicionJuego(consola, precio, stock);
                            catalogoJuegos.getLast().anadirEdicion(consola, edicionJuego);
                        }
                        if (numero == 3) {
                            String consola = "Play Station";
                            activador = true;
                            double precio = 0f;
                            do {
                                try {
                                    System.out.println("¿Qué precio tiene el juego '" + nombre + "' en Play Station?");
                                    precio = sc.nextDouble();
                                    sc.nextLine();
                                    activador = false;
                                } catch (InputMismatchException e) {
                                    System.out.println("Importe no válido, por favor, ingresa un importe válido.");
                                    sc.nextLine();
                                }
                            } while (activador);
                            activador = true;
                            int stock = 0;
                            do {
                                try {
                                    System.out.println("¿Cuantás unidades del juego '" + nombre + "' en Play Station quieres añadir al stock?");
                                    stock = sc.nextInt();
                                    sc.nextLine();
                                    activador = false;
                                } catch (InputMismatchException e) {
                                    System.out.println("Unidades no válidas, por favor, ingresa un número de unidades válido.");
                                    sc.nextLine();
                                }
                            } while (activador);
                            EdicionJuego edicionJuego = new EdicionJuego(consola, precio, stock);
                            catalogoJuegos.getLast().anadirEdicion(consola, edicionJuego);
                        }
                        if (numero == 4) {
                            String consola = "PC";
                            activador = true;
                            double precio = 0f;
                            do {
                                try {
                                    System.out.println("¿Qué precio tiene el juego '" + nombre + "' en PC?");
                                    precio = sc.nextDouble();
                                    sc.nextLine();
                                    activador = false;
                                } catch (InputMismatchException e) {
                                    System.out.println("Importe no válido, por favor, ingresa un importe válido.");
                                    sc.nextLine();
                                }
                            } while (activador);
                            activador = true;
                            int stock = 0;
                            do {
                                try {
                                    System.out.println("¿Cuantás unidades del juego '" + nombre + "' en PC quieres añadir al stock?");
                                    stock = sc.nextInt();
                                    sc.nextLine();
                                    activador = false;
                                } catch (InputMismatchException e) {
                                    System.out.println("Unidades no válidas, por favor, ingresa un número de unidades válido.");
                                    sc.nextLine();
                                }
                            } while (activador);
                            EdicionJuego edicionJuego = new EdicionJuego(consola, precio, stock);
                            catalogoJuegos.getLast().anadirEdicion(consola, edicionJuego);
                        }
                    }
                    System.out.println("Juego '" + catalogoJuegos.getLast().getNombre() + "' (" + catalogoJuegos.getLast().getGenero() + ", " + catalogoJuegos.getLast().getPegi() + ") añadido en catálogo para las siguientes consolas:");
                    for (String consola : catalogoJuegos.getLast().getConsolas()) {
                        System.out.println(consola + " - " + String.format("%.2f", catalogoJuegos.getLast().getPrecio(consola)) + "€ - " + (catalogoJuegos.getLast().getStock(consola)) + "ud.");
                    }
                } else {
                    System.out.println("El juego " + nombre + " ya existe en la base de datos.");
                }
                break;
            case 2:
                System.out.println("¿Qué juego quieres eliminar del catálogo?");
                nombre = sc.nextLine();
                System.out.println(eliminarJuego(nombre, catalogoJuegos));
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
        String menu = """
                ### GESTIÓN DE INVENTARIO ###
                
                1. Añadir juego al catálogo
                2. Eliminar juego del catálogo
                3. Modificar juego
                4. Buscar juego por consola
                5. Listado de juegos (Orden Alfabético)
                6. Listado de juegos (Orden por stock)
                7. Salir
                
                Elige una opción:""";
        return menu;
    }

    /**
     * Función para crear y añadir un juego a una lista de juegos.
     * @param nombre Nombre del juego que se quiere crear.
     * @param genero Género del juego que se quiere crear.
     * @param pegi PEGI del juego que se quiere crear.
     * @param catalogoJuegos Lista de juegos donde se quiere añadir el nuevo juego creado.
     */
    public static void anadirJuego (String nombre, String genero, String pegi, ArrayList<Juego> catalogoJuegos) {
        Juego j = new Juego(nombre, genero, pegi);
        catalogoJuegos.add(j);
    }

    /**
     * Función para eliminar un juego de una lista de cátalogo de juegos.
     * @param nombre Nombre del juego que se quiere eliminar.
     * @param catalogoJuegos Lista de donde se eliminará el juego.
     * @return Mensaje de éxito si el juego se ha podido eliminar o, en caso contrario, mensaje de error.
     */
    public static String eliminarJuego(String nombre, ArrayList<Juego> catalogoJuegos) {
        if (juegoExiste(nombre, catalogoJuegos)) {
            for (Juego j : catalogoJuegos) {
                if (j.getNombre().equals(nombre)) {
                    catalogoJuegos.remove(j);
                    return "Juego '" + nombre + "' eliminado del catálogo de juegos.";
                }
            }
        }
        return "El juego '" + nombre + "' no se encuentra en el catálogo de juegos.";
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
