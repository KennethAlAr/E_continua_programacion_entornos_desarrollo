
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Collections;
import java.util.NoSuchElementException;

public class GestorJuegos {

    /**
     * Función para manejar las opciones del menú de Gestión de Inventario.
     * @param catalogoJuegos Lista de los clientes para poder gestionarlos.
     * @param sc Scanner para introducir datos.
     */
    public static void menuPrincipalJuegos(ArrayList<Juego> catalogoJuegos, Scanner sc) {
        int opcion = 0;
        do {
            try {
                System.out.println(stringMenuPrincipalJuegos());
                opcion = sc.nextInt();
                sc.nextLine();
                switch(opcion) {
                    case 1:
                        menuAnadirJuego(catalogoJuegos, sc);
                        break;
                    case 2:
                        menuEliminarJuego(catalogoJuegos, sc);
                        break;
                    case 3:
                        menuModificarJuego(catalogoJuegos, sc);
                        break;
                    case 4:
                        menuListarPorConsola(catalogoJuegos, sc);
                        break;
                    case 5:
                        System.out.println(listarJuegosAlfabetico(catalogoJuegos));
                        break;
                    case 6:
                        System.out.println(listarJuegosPorStock(catalogoJuegos));
                        break;
                    case 7:
                        System.out.println("Saliendo del sistema de gestión de inventario.\n");
                        break;
                    default:
                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                sc.nextLine();
            }
        } while (opcion != 7);

    }

    /**
     * Función para construir el menú de Gestión de Inventario.
     * @return String que contiene el menú de Gestión de Inventario para imprimir.
     */
    public static String stringMenuPrincipalJuegos() {
        return """
                ### GESTIÓN DE INVENTARIO ###
                
                1. Añadir juego al catálogo
                2. Eliminar juego del catálogo
                3. Modificar juego
                4. Buscar juego por consola
                5. Listado de juegos (Orden Alfabético)
                6. Listado de juegos (Orden por stock)
                7. Salir
                
                Elige una opción:""";
    }

    /**
     * Función para gestionar la adición de un juego nuevo al catálogo de juegos.
     * @param catalogoJuegos Lista de juegos donde se quiere añadir el juego nuevo.
     * @param sc Scanner para introducir datos.
     */
    public static void menuAnadirJuego(ArrayList<Juego> catalogoJuegos ,Scanner sc) {
        boolean interruptor = true;
        String nombre;
        do {
            System.out.println("Introduce el nombre del juego que quieres añadir al catálogo o escribe 'salir' para salir:");
            nombre = sc.nextLine();
            if (nombre.equalsIgnoreCase("salir")) {
                interruptor = false;
            } else if (!juegoExiste(nombre, catalogoJuegos)) {
                System.out.println("¿Cuál es el género del juego?");
                String genero = sc.nextLine();
                String pegi = menuEscogerPegi(sc);
                anadirJuego(nombre, genero, pegi, catalogoJuegos);
                menuSistemasNuevoJuego(nombre, catalogoJuegos, sc);
                interruptor = false;
            } else {
                System.out.println("El juego " + nombre + " ya existe en la base de datos.");
            }
        } while (interruptor);
        if (!nombre.equalsIgnoreCase("salir")) {
            System.out.println("Juego '" + seleccionarJuego(nombre, catalogoJuegos).getNombre() +
                    "' (" + seleccionarJuego(nombre, catalogoJuegos).getGenero() +
                    ", " + seleccionarJuego(nombre, catalogoJuegos).getPegi() +
                    ") añadido en catálogo para las siguientes consolas:");
            System.out.println(seleccionarJuego(nombre, catalogoJuegos).getSistemas());
        }
    }

    /**
     * Función para escoger la calificación PEGI de un juego con un switch.
     * @param sc Scanner para introducir datos.
     * @return String con la calificación PEGI del juego.
     */
    public static String menuEscogerPegi(Scanner sc) {
        String pegi = "";
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
                    break;
                case 2:
                    pegi = "PEGI-7";
                    break;
                case 3:
                    pegi = "PEGI-12";
                    break;
                case 4:
                    pegi = "PEGI-16";
                    break;
                case 5:
                    pegi = "PEGI-18";
                default:
                    System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
            sc.nextLine();
        }
        return pegi;
    }

    /**
     * Función para gestionar las ediciones de un juego nuevo.
     * @param nombre Nombre del juego del cual se quieren añadir las ediciones.
     * @param catalogoJuegos Lista de juegos donde se encuentra el juego.
     * @param sc Scanner para introducir datos.
     */
    public static void menuSistemasNuevoJuego(String nombre, ArrayList<Juego> catalogoJuegos, Scanner sc) {
        ArrayList<Integer> numerosEdiciones = new ArrayList<>();
        boolean interruptor = true;
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
                interruptor = false;
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida, por favor, elige una opción u opciones de las disponibles. Recuerda separarlas con guiones si son mas de una.\n");
            } catch (IllegalArgumentException e) {
                System.out.println("Alguna de las opciones no es válida, por favor, elige una o varias opciones de las disponibles.");
            }
        } while (interruptor);
        for (int numero : numerosEdiciones) {
            if (numero == 1) {
                String consola = "XBOX";
                anadirSistema(nombre, consola, catalogoJuegos, sc);
            } else if (numero == 2) {
                String consola = "Nintendo";
                anadirSistema(nombre, consola, catalogoJuegos, sc);
            } else if (numero == 3) {
                String consola = "Play Station";
                anadirSistema(nombre, consola, catalogoJuegos, sc);
            } else {
                String consola = "PC";
                anadirSistema(nombre, consola, catalogoJuegos, sc);
            }
        }
    }

    /**
     * Función para añadir un nuevo sistema a un juego.
     * @param nombre Nombre del juego al cual se le quiere añadir un sistema nuevo.
     * @param consola Nombre del sistema que se quiere añadir al juego.
     * @param catalogoJuegos Lista de juegos donde se encuentra el juego que estamos gestionando.
     * @param sc Scanner para introducir datos.
     * @see #anadirSistema(String, ArrayList, Scanner)
     */
    public static void anadirSistema (String nombre, String consola, ArrayList<Juego> catalogoJuegos, Scanner sc) {
        boolean interruptor = true;
        double precio = 0f;
        do {
            try {
                System.out.println("¿Qué precio tiene el juego '" + nombre + "' en " + consola +"?");
                precio = sc.nextDouble();
                sc.nextLine();
                if (precio >= 0f) {
                    interruptor = false;
                } else {
                    System.out.println("El precio no puede ser negativo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Importe no válido, por favor, ingresa un importe válido.");
                sc.nextLine();
            }
        } while (interruptor);
        interruptor = true;
        int stock = 0;
        do {
            try {
                System.out.println("¿Cuantás unidades del juego '" + nombre + "' en " + consola + " quieres añadir al stock?");
                stock = sc.nextInt();
                sc.nextLine();
                if (stock >= 0) {
                    interruptor = false;
                } else {
                    System.out.println("El stock no puede ser negativo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Unidades no válidas, por favor, ingresa un número de unidades válido.");
                sc.nextLine();
            }
        } while (interruptor);
        EdicionJuego edicionJuego = new EdicionJuego(consola, precio, stock);
        seleccionarJuego(nombre, catalogoJuegos).anadirEdicion(edicionJuego);
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
     * Función para gestionar la eliminación de un juego de un catálogo de juegos.
     * @param catalogoJuegos Lista de juegos de donde se quiere eliminar el juego.
     * @param sc Scanner para introducir datos.
     */
    public static void menuEliminarJuego(ArrayList<Juego> catalogoJuegos, Scanner sc) {
        boolean interruptor = true;
        do {
            System.out.println("Introduce el nombre del juego que quieres eliminar o escribe 'salir' para salir:");
            String nombre = sc.nextLine();
            if (nombre.equalsIgnoreCase("salir")) {
                interruptor = false;
            } else {
                try {
                    System.out.println(eliminarJuego(nombre, catalogoJuegos));
                    interruptor = false;
                } catch (NoSuchElementException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (interruptor);
    }

    /**
     * Función para eliminar un juego de una lista de cátalogo de juegos.
     * @param nombre Nombre del juego que se quiere eliminar.
     * @param catalogoJuegos Lista de donde se eliminará el juego.
     * @return Mensaje de éxito si el juego se ha podido eliminar o, en caso contrario, mensaje de error.
     * @throws NoSuchElementException si el juego no existe en el catálogo de juegos.
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
        throw new NoSuchElementException("El juego '" + nombre + "' no se encuentra en el catálogo de juegos.");
    }

    /**
     * Función para gestionar la modificación de los parámetros de un juego.
     * @param catalogoJuegos Lista de juegos donde se encuentra el juego que se quiere modificar.
     * @param sc Scanner para introducir datos.
     */
    public static void menuModificarJuego(ArrayList<Juego> catalogoJuegos, Scanner sc) {
        boolean interruptor = true;
        do {
            System.out.println("Introduce el nombre del juego que quieres modificar o escribe 'salir' para salir:");
            String nombre = sc.nextLine();
            if (nombre.equalsIgnoreCase("salir")) {
                interruptor = false;
            } else if (juegoExiste(nombre, catalogoJuegos)) {
                int opcion = 0;
                do {
                    try {
                        System.out.println("""
                                ¿Qué quieres modificar?
                                1. Nombre
                                2. Género
                                3. Calificación PEGI
                                4. Sistemas disponibles
                                5. Salir
                                """
                        );
                        opcion = sc.nextInt();
                        sc.nextLine();
                        switch(opcion) {
                            case 1:
                                nombre = modificarNombreJuego(nombre, catalogoJuegos, sc);
                                break;
                            case 2:
                                modificarGeneroJuego(nombre, catalogoJuegos, sc);
                                break;
                            case 3:
                                seleccionarJuego(nombre, catalogoJuegos).modificarPegi(menuEscogerPegi(sc));
                                break;
                            case 4:
                                menuGestionSistemas(nombre, catalogoJuegos, sc);
                                break;
                            case 5:
                                interruptor = false;
                                break;
                            default:
                                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                        sc.nextLine();
                    }
                } while (opcion != 5);
            } else {
                System.out.println("El juego '" + nombre + "' no se encuentra en el catálogo de juegos.");
            }
        }while (interruptor);
    }

    /**
     * Función para modificar el nombre de un juego existente.
     * @param nombre Nombre del juego que se quiere modificar.
     * @param catalogoJuegos Lista de juegos donde se encuentra el juego que se quiere modificar.
     * @param sc Scanner para introducir datos.
     * @return Nuevo nombre asignado al cliente.
     */
    public static String modificarNombreJuego(String nombre, ArrayList<Juego> catalogoJuegos, Scanner sc) {
        boolean interruptor = true;
        String nuevoNombre;
        do {
            System.out.println("Modificando el nombre del juego:\n¿Cuál es el nuevo nombre de '" + nombre + "'?");
            nuevoNombre = sc.nextLine();
            if (!juegoExiste(nuevoNombre, catalogoJuegos)) {
                System.out.println(seleccionarJuego(nombre, catalogoJuegos).modificarNombre(nuevoNombre));
                interruptor = false;
            } else {
                System.out.println("Ya existe un juego con ese nombre en el catálogo, por favor, elige otro nombre.");
            }
        } while (interruptor);
        return nuevoNombre;
    }

    /**
     * Función para modificar el género de un juego.
     * @param nombre Nombre del juego que se quiere modificar.
     * @param catalogoJuegos Lista de juegos donde se encuentra el juego que se quiere modificar.
     * @param sc Scanner para introducir datos.
     */
    public static void modificarGeneroJuego(String nombre, ArrayList<Juego> catalogoJuegos, Scanner sc) {
        System.out.println("Modificando el género del juego:\n¿Cuál es el nuevo género de '" + nombre + "'?");
        String nuevoGenero = sc.nextLine();
        System.out.println(seleccionarJuego(nombre, catalogoJuegos).modificarGenero(nuevoGenero));
    }

    /**
     * Función para gestionar las opciones de los sistemas de un juego.
     * @param nombre Nombre del juego del cual se quieren gestionar los sistemas.
     * @param catalogoJuegos Lista de juegos donde se encuentra el juego.
     * @param sc Scanner para introducir datos.
     */
    public static void menuGestionSistemas(String nombre, ArrayList<Juego> catalogoJuegos, Scanner sc) {
        int opcion = 0;
        if (seleccionarJuego(nombre, catalogoJuegos).getNumeroSistemas() >= 4) {
            if (seleccionarJuego(nombre, catalogoJuegos).getNumeroSistemas() >= 4) {
                do {
                    try {
                        System.out.println("El juego " + nombre + " está disponible en los siguientes sistemas:");
                        System.out.println(seleccionarJuego(nombre, catalogoJuegos).getSistemas());
                        System.out.println("¿Qué deseas hacer?");
                        System.out.println("""
                                1. Modificar un sistema existente
                                2. Eliminar un sistema existente
                                3. Salir
                                """);
                        opcion = sc.nextInt();
                        sc.nextLine();
                        switch (opcion) {
                            case 1:
                                menuModificarSistema(nombre, catalogoJuegos, sc);
                                menuGestionSistemas(nombre, catalogoJuegos, sc);
                                opcion = 3;
                                break;
                            case 2:
                                eliminarSistema(nombre, catalogoJuegos, sc);
                                menuGestionSistemas(nombre, catalogoJuegos, sc);
                                opcion = 3;
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                        sc.nextLine();
                    }
                } while (opcion != 3);
            }
        } else if (seleccionarJuego(nombre, catalogoJuegos).getNumeroSistemas() == 0) {
            do {
                try {
                    System.out.println("El juego " + nombre + " está disponible en los siguientes sistemas:");
                    System.out.println(seleccionarJuego(nombre, catalogoJuegos).getSistemas());
                    System.out.println("¿Qué deseas hacer?");
                    System.out.println("""
                                1. Añadir un sistema nuevo
                                2. Salir
                                """);
                    opcion = sc.nextInt();
                    sc.nextLine();
                    switch (opcion) {
                        case 1:
                            anadirSistema(nombre, catalogoJuegos, sc);
                            menuGestionSistemas(nombre, catalogoJuegos, sc);
                            opcion = 2;
                            break;
                        case 2:
                            break;
                        default:
                            System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                    sc.nextLine();
                }
            } while (opcion != 2);
        } else {
            do {
                try {
                    System.out.println("El juego " + nombre + " está disponible en los siguientes sistemas:");
                    System.out.println(seleccionarJuego(nombre, catalogoJuegos).getSistemas());
                    System.out.println("¿Qué deseas hacer?");
                    System.out.println("""
                                1. Modificar un sistema existente
                                2. Eliminar un sistema existente
                                3. Añadir un sistema nuevo
                                4. Salir
                                """);
                    opcion = sc.nextInt();
                    sc.nextLine();
                    switch (opcion) {
                        case 1:
                            menuModificarSistema(nombre, catalogoJuegos, sc);
                            menuGestionSistemas(nombre, catalogoJuegos, sc);
                            opcion = 4;
                            break;
                        case 2:
                            eliminarSistema(nombre, catalogoJuegos, sc);
                            menuGestionSistemas(nombre, catalogoJuegos, sc);
                            opcion = 4;
                            break;
                        case 3:
                            anadirSistema(nombre, catalogoJuegos, sc);
                            menuGestionSistemas(nombre, catalogoJuegos, sc);
                            opcion = 4;
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                    sc.nextLine();
                }
            } while (opcion != 4);
        }
    }

    /**
     * Función para gestionar la modificación de un sistema en un juego.
     * @param nombre Nombre del juego que se quiere modificar.
     * @param catalogoJuegos Lista de juegos donde se encuentra el juego que se quiere modificar.
     * @param sc Scanner para introducir datos.
     */
    public static void menuModificarSistema(String nombre, ArrayList<Juego> catalogoJuegos, Scanner sc) {
        boolean interruptor = true;
        do {
            System.out.println("Introduce el nombre del sistema que quieres modificar o escribe 'salir' para salir.");
            for (String consola : seleccionarJuego(nombre, catalogoJuegos).getConsolas()) {
                System.out.println(consola);
            }
            String sistemaModificar = sc.nextLine();
            if (sistemaModificar.equalsIgnoreCase("salir")) {
                interruptor = false;
            } else if (seleccionarJuego(nombre, catalogoJuegos).existeConsola(sistemaModificar)) {//
                int opcion = 0;
                interruptor = false;
                do {
                    try {
                        System.out.println("Modificando sistema '" + sistemaModificar + "'.");
                        System.out.println("""
                                1. Modificar precio
                                2. Modificar stock
                                3. Salir
                                """);
                        opcion = sc.nextInt();
                        sc.nextLine();
                        switch (opcion) {
                            case 1:
                                modificarPrecio(nombre, sistemaModificar, catalogoJuegos, sc);
                                break;
                            case 2:
                                modificarStock(nombre, sistemaModificar, catalogoJuegos, sc);
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                        sc.nextLine();
                    }
                } while (opcion != 3);
            } else {
                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
            }
        } while (interruptor);
    }

    /**
     * Función para modificar el precio de un sistema en un juego.
     * @param nombre Nombre del juego que se quiere modificar.
     * @param consola Nombre del sistema que se quiere modificar.
     * @param catalogoJuegos Lista de juegos donde se encuentra el juego que se quiere modificar.
     * @param sc Scanner para introducir datos.
     */
    public static void modificarPrecio(String nombre, String consola, ArrayList<Juego> catalogoJuegos, Scanner sc) {
        boolean interruptor = true;
        do {
            try {
                System.out.println("El precio actual de '" + nombre + "' en " + consola + "es de " + String.format("%.2f", seleccionarJuego(nombre, catalogoJuegos).getPrecio(consola)) + "€.");
                System.out.println("¿Cuál es el precio nuevo?");
                double nuevoPrecio = sc.nextDouble();
                sc.nextLine();
                if (nuevoPrecio >= 0f) {
                    seleccionarJuego(nombre, catalogoJuegos).seleccionarEdicion(consola).modificarPrecio(nuevoPrecio);
                    System.out.println("Precio de '" + nombre + "' en " + consola + " actualizado a " + String.format("%.2f", nuevoPrecio) + "€.");
                    interruptor = false;
                } else {
                    System.out.println("El precio no puede ser negativo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Precio no válido, por favor, introduce un precio válido.\n");
                sc.nextLine();
            }
        } while (interruptor);
    }

    /**
     * Función para modificar el stock de un sistema en un juego.
     * @param nombre Nombre del juego que se quiere modificar.
     * @param consola Nombre del sistema que se quiere modificar.
     * @param catalogoJuegos Lista de juegos donde se encuentra el juego que se quiere modificar.
     * @param sc Scanner para introducir datos.
     */
    public static void modificarStock(String nombre, String consola, ArrayList<Juego> catalogoJuegos, Scanner sc) {
        boolean interruptor = true;
        do {
            try {
                System.out.println("El stock actual de '" + nombre + "' en " + consola + "es de " + seleccionarJuego(nombre, catalogoJuegos).getStock(consola) + "€.");
                System.out.println("¿Cuál es el nuevo stock?");
                int nuevoStock = sc.nextInt();
                sc.nextLine();
                if (nuevoStock >= 0) {
                    seleccionarJuego(nombre, catalogoJuegos).seleccionarEdicion(consola).modificarStock(nuevoStock);
                    System.out.println("Stock de '" + nombre + "' en " + consola + " actualizado a " + nuevoStock + "ud.");
                    interruptor = false;
                } else {
                    System.out.println("El stock no puede ser negativo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Stock no válido, por favor, introduce un precio válido.\n");
                sc.nextLine();
            }
        } while (interruptor);
    }

    /**
     * Función para eliminar un sistema existente de un juego.
     * @param nombre Nombre del juego del cual se quiere eliminar un sistema.
     * @param catalogoJuegos Lista de juegos donde se encuentra el juego.
     * @param sc Scanner para introducir los datos.
     */
    public static void eliminarSistema(String nombre, ArrayList<Juego> catalogoJuegos, Scanner sc) {
        String sistemaEliminar;
        boolean interruptor = true;
        do {
            System.out.println("Introduce el nombre del sistema que quieres eliminar o escribe 'salir' para salir.");
            for (String consola: seleccionarJuego(nombre, catalogoJuegos).getConsolas()) {
                System.out.println(consola);
            }
            sistemaEliminar = sc.nextLine();
            if (sistemaEliminar.equalsIgnoreCase("salir")){
                interruptor = false;
            } else if (seleccionarJuego(nombre, catalogoJuegos).existeConsola(sistemaEliminar)) {
                seleccionarJuego(nombre, catalogoJuegos).eliminarEdicion(sistemaEliminar);
                System.out.println("Sistema eliminado.");
                interruptor = false;
            } else {
                System.out.println("El sistema no existe o no coincide exactamente con el nombre del sistema.");
            }
        } while (interruptor);
    }

    /**
     * Función para añadir un sistema a un juego ya existente.
     * @param nombre Nombre del juego al cual se le quiere añadir un sistema.
     * @param catalogoJuegos Lista de juegos donde se encuentra el juego al cual se le quiere añadir un sistema.
     * @param sc Scanner para introducir datos.
     * @see #anadirSistema(String, String, ArrayList, Scanner)
     */
    public static void anadirSistema(String nombre,ArrayList<Juego> catalogoJuegos, Scanner sc) {
        boolean interruptor = true;
        do {
            System.out.println("Introduce el nombre del sistema que quieres añadir o escribe 'salir' para salir:");
            ArrayList<String> listaSistemasActuales = seleccionarJuego(nombre, catalogoJuegos).getConsolas();
            ArrayList<String> listaSistemas = new ArrayList<>();
            listaSistemas.add("XBOX");
            listaSistemas.add("Nintendo");
            listaSistemas.add("Play Station");
            listaSistemas.add("PC");
            listaSistemas.removeAll(listaSistemasActuales);
            for (int i = 0; i < listaSistemas.size(); i++) {
                System.out.println(listaSistemas.get(i));
                listaSistemas.set(i, listaSistemas.get(i).toLowerCase());
            }
            String nuevoSistema = sc.nextLine().toLowerCase();
            if (nuevoSistema.equalsIgnoreCase("salir")) {
                interruptor = false;
            }else if (listaSistemas.contains(nuevoSistema)) {
                interruptor = false;
                String consola = switch (nuevoSistema) {
                    case "xbox" -> "XBOX";
                    case "nintendo" -> "Nintendo";
                    case "play station" -> "Play Station";
                    default -> "PC";
                };
                anadirSistema(nombre, consola, catalogoJuegos, sc);
            } else {
                System.out.println("El sistema no existe o no coincide exactamente con el nombre del sistema.");
            }
        } while (interruptor);
    }

    /**
     * Función para gestionar la opción de listar juegos por consola.
     * @param catalogoJuegos Lista de juegos para hacer el listado por consola.
     * @param sc Scanner para introducir datos.
     */
    public static void menuListarPorConsola(ArrayList<Juego> catalogoJuegos, Scanner sc) {
        int opcion = 0;
        do {
            try {
                System.out.println("""
                                Buscando juego por consola:
                                ¿Qué consola quieres listar?
                                1. XBOX
                                2. Nintendo
                                3. Play Station
                                4. PC
                                5. Salir
                                """);
                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        System.out.println("Lista de juegos disponibles para XBOX:");
                        System.out.println(listarPorConsola("XBOX", catalogoJuegos));
                        break;
                    case 2:
                        System.out.println("Lista de juegos disponibles para Nintendo:");
                        System.out.println(listarPorConsola("Nintendo", catalogoJuegos));
                        break;
                    case 3:
                        System.out.println("Lista de juegos disponibles para Play Station:");
                        System.out.println(listarPorConsola("Play Station", catalogoJuegos));
                        break;
                    case 4:
                        System.out.println("Lista de juegos disponibles para PC:");
                        System.out.println(listarPorConsola("PC", catalogoJuegos));
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                sc.nextLine();
            }
        } while (opcion != 5);
    }

    /**
     * Función para obtener un String con el listado de todos los juegos de una consola.
     * @param consola Nombre de la consola de la cual se quieren obtener los juegos disponibles.
     * @param catalogoJuegos Catálogo de donde se quieren consultar los juegos disponibles para la consola.
     * @return String con el listado de todos los juegos disponibles para la consola o, en caso de no tener ningún juego, mensaje de error.
     */
    public static String listarPorConsola(String consola, ArrayList<Juego> catalogoJuegos) {
        String listado = "";
        int numeroConsolas = 0;
        for (Juego j : catalogoJuegos) {
            if (j.getConsolas().contains(consola)) {
                listado += j.getNombre() + " - " + j.getGenero() + " - " + j.getPegi() + " - " + String.format("%.2f", j.getPrecio(consola)) + "€ - " + j.getStock(consola) + "ud.\n";
                numeroConsolas ++;
            }
        }
        if (numeroConsolas == 0) {
            listado += "No hay juegos disponibles para este sistema.";
        }
        return listado;
    }

    /**
     * Función para listar todos los juegos del catálogo junto a sus atributos en orden alfabético.
     * @param catalogoJuegos Catálogo de juegos que se quiere listar.
     * @return Lista de todos los juegos junto a sus atributos en orden alfabético o, en caso de no haber ningún juego, mensaje de error.
     */
    public static String listarJuegosAlfabetico(ArrayList<Juego> catalogoJuegos) {
        String listado = "";
        ArrayList<String> nombreJuegos = new ArrayList<>();
        int numeroJuegos = 0;
        for (Juego j : catalogoJuegos) {
            nombreJuegos.add(j.getNombre());
        }
        Collections.sort(nombreJuegos);
        for (String nombre : nombreJuegos) {
            for (Juego j : catalogoJuegos) {
                if (j.getNombre().equals(nombre)) {
                    numeroJuegos ++;
                    listado += j.getNombre() + " - " + j.getGenero() + " - " + j.getPegi() + "\n";
                    if (j.getNumeroSistemas() > 0) {
                        listado += j.getSistemas();
                    }
                    listado += "----------\n";
                }
            }
        }
        if (numeroJuegos == 0) {
            listado += "No hay juegos en el catálogo.";
        }
        return listado;
    }

    /**
     * Método para listar todos los juegos de un catálogo de juegos según su stock por sistema.
     * @param catalogoJuegos Catálogo de juegos que se quiere listar.
     * @return Lista de todos los juegos en orden ascendente por stock por sistema o, en caso de no haber ningún juego, mensaje de error.
     */
    public static String listarJuegosPorStock(ArrayList<Juego> catalogoJuegos) {
        String listado = "";
        ArrayList<String> stockJuegos = new ArrayList<>();
        int numeroJuegos = 0;
        for (Juego j : catalogoJuegos) {
            for (EdicionJuego edicion : j.getEdicionJuego()) {
                String codigo = edicion.getStock() + " -:- " + j.getNombre() + " -:- " + edicion.getConsola();
                stockJuegos.add(codigo);
            }
        }
        for (int i = 0; i < stockJuegos.size() - 1; i++) {
            for (int j = i+1; j < stockJuegos.size(); j++) {
                int precio1 = Integer.parseInt(stockJuegos.get(i).split(" -:- ")[0]);
                int precio2 = Integer.parseInt(stockJuegos.get(j).split(" -:- ")[0]);

                if (precio1 > precio2) {
                    String auxiliar = stockJuegos.get(i);
                    stockJuegos.set(i, stockJuegos.get(j));
                    stockJuegos.set(j, auxiliar);
                }
            }
        }
        for (String juego : stockJuegos) {
            String nombre = juego.split(" -:- ")[1];
            String consola = juego.split(" -:- ")[2];
            listado += seleccionarJuego(nombre, catalogoJuegos).getStock(consola) + "ud - " +
                    seleccionarJuego(nombre, catalogoJuegos).getNombre() + " - " +
                    seleccionarJuego(nombre, catalogoJuegos).seleccionarEdicion(consola).getConsola() + "\n";
            numeroJuegos ++;
        }
        if (numeroJuegos == 0) {
            listado += "No hay juegos en el catálogo.";
        }
        return listado;
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

    /**
     * Función para seleccionar un juego según su nombre de una lista de juegos.
     * @param nombre Nombre del juego que se quiere seleccionar.
     * @param catalogoJuegos Lista de juegos de donde se quiere seleccionar el juego.
     * @return Juego que coincide con el nombre del juego.
     * @throws NoSuchElementException en caso de no encontrarse el juego en la lista de juegos.
     */
    public static Juego seleccionarJuego (String nombre, ArrayList<Juego> catalogoJuegos) {
        for (Juego j : catalogoJuegos) {
            if (j.getNombre().equals(nombre)) {
                return j;
            }
        }
        throw new NoSuchElementException("El juego '" + nombre + "' no se encuentra en el catálogo de juegos.");
    }

}
