import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Collections;
import java.util.NoSuchElementException;

public class GestorJuegos {

    /**
     * Función para manejar las opciones del menú de Gestión de Inventario.
     * @param opcion Opción escogida para el menú.
     * @param catalogoJuegos Lista de los clientes para poder gestionarlos.
     */
    public static void switchJuegos(int opcion, ArrayList<Juego> catalogoJuegos) {
        Scanner sc = new Scanner(System.in);
        String nombre;
        boolean activador;

        switch(opcion) {
            case 1:
                System.out.println("Indica el nombre del juego que quieres añadir al catálogo:");
                nombre = sc.nextLine();
                if (!juegoExiste(nombre, catalogoJuegos)) {
                    System.out.println("¿Cuál es el género del juego?");
                    String genero = sc.nextLine();
                    String pegi = "";
                    activador = true;
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
                                    if (precio >= 0f) {
                                        activador = false;
                                    } else {
                                        System.out.println("El precio no puede ser negativo.");
                                    }
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
                                    if (stock >= 0) {
                                        activador = false;
                                    } else {
                                        System.out.println("El stock no puede ser negativo.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Unidades no válidas, por favor, ingresa un número de unidades válido.");
                                    sc.nextLine();
                                }
                            } while (activador);
                            EdicionJuego edicionJuego = new EdicionJuego(consola, precio, stock);
                            catalogoJuegos.getLast().anadirEdicion(edicionJuego);
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
                                    if (precio >= 0f) {
                                        activador = false;
                                    } else {
                                        System.out.println("El precio no puede ser negativo.");
                                    }
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
                                    if (stock >= 0) {
                                        activador = false;
                                    } else {
                                        System.out.println("El stock no puede ser negativo.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Unidades no válidas, por favor, ingresa un número de unidades válido.");
                                    sc.nextLine();
                                }
                            } while (activador);
                            EdicionJuego edicionJuego = new EdicionJuego(consola, precio, stock);
                            catalogoJuegos.getLast().anadirEdicion(edicionJuego);
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
                                    if (precio >= 0f) {
                                        activador = false;
                                    } else {
                                        System.out.println("El precio no puede ser negativo.");
                                    }
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
                                    if (stock >= 0) {
                                        activador = false;
                                    } else {
                                        System.out.println("El stock no puede ser negativo.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Unidades no válidas, por favor, ingresa un número de unidades válido.");
                                    sc.nextLine();
                                }
                            } while (activador);
                            EdicionJuego edicionJuego = new EdicionJuego(consola, precio, stock);
                            catalogoJuegos.getLast().anadirEdicion(edicionJuego);
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
                                    if (precio >= 0f) {
                                        activador = false;
                                    } else {
                                        System.out.println("El precio no puede ser negativo.");
                                    }
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
                                    if (stock >= 0) {
                                        activador = false;
                                    } else {
                                        System.out.println("El stock no puede ser negativo.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Unidades no válidas, por favor, ingresa un número de unidades válido.");
                                    sc.nextLine();
                                }
                            } while (activador);
                            EdicionJuego edicionJuego = new EdicionJuego(consola, precio, stock);
                            catalogoJuegos.getLast().anadirEdicion(edicionJuego);
                        }
                    }
                    System.out.println("Juego '" + catalogoJuegos.getLast().getNombre() + "' (" + catalogoJuegos.getLast().getGenero() + ", " + catalogoJuegos.getLast().getPegi() + ") añadido en catálogo para las siguientes consolas:");
                    System.out.println(catalogoJuegos.getLast().getSistemas());
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
                System.out.println("¿Qué juego quieres modificar?");
                nombre = sc.nextLine();
                activador = true;
                if (juegoExiste(nombre, catalogoJuegos)) {
                    int opcionModificar = 0;
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
                            opcionModificar = sc.nextInt();
                            sc.nextLine();
                            if (opcionModificar > 0 && opcionModificar < 6) {
                                activador = false;
                            } else {
                                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                            sc.nextLine();
                        }
                    } while (activador);
                    switch(opcionModificar) {
                        case 1:
                            activador = true;
                            do {
                                System.out.println("Modificando el nombre del juego:\n¿Cuál es el nuevo nombre de '" + nombre + "'?");
                                String nuevoNombre = sc.nextLine();
                                if (!juegoExiste(nuevoNombre, catalogoJuegos)) {
                                    System.out.println(seleccionarJuego(nombre, catalogoJuegos).modificarNombre(nuevoNombre));
                                    activador = false;
                                } else {
                                    System.out.println("Ya existe un juego con ese nombre en el catálogo, por favor, elige otro.");
                                }
                            } while (activador);
                            break;
                        case 2:
                            System.out.println("Modificando el género del juego:\n¿Cuál es el nuevo género de '" + nombre + "'?");
                            String nuevoGenero = sc.nextLine();
                            System.out.println(seleccionarJuego(nombre, catalogoJuegos).modificarGenero(nuevoGenero));
                            break;
                        case 3:
                            activador = true;
                            String nuevoPegi = "";
                            do {
                                try {
                                    System.out.println("""
                                            Modificando la calificación PEGI del juego:
                                            ¿Cuál es la nueva calificación PEGI?
                                            1. PEGI-3
                                            2. PEGI-7
                                            3. PEGI-12
                                            4. PEGI-16
                                            5. PEGI-18
                                            """);
                                    int opcionPegi = sc.nextInt();
                                    sc.nextLine();
                                    if (opcionPegi > 0 && opcionPegi < 6) {
                                        activador = false;
                                        if (opcionPegi == 1) {
                                            nuevoPegi = "PEGI-3";
                                        } else if (opcionPegi == 2) {
                                            nuevoPegi = "PEGI-7";
                                        } else if (opcionPegi == 3) {
                                            nuevoPegi = "PEGI-12";
                                        } else if (opcionPegi == 4) {
                                            nuevoPegi = "PEGI-16";
                                        } else {
                                            nuevoPegi = "PEGI-18";
                                        }
                                    } else {
                                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                    sc.nextLine();
                                }
                            } while (activador);
                            System.out.println(seleccionarJuego(nombre, catalogoJuegos).modificarPegi(nuevoPegi));
                            break;
                        case 4:
                            int opcionSistema = 0;
                            if (seleccionarJuego(nombre, catalogoJuegos).getNumeroSistemas() >= 4) {
                                activador = true;
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
                                        opcionSistema = sc.nextInt();
                                        sc.nextLine();
                                        if (opcionSistema > 0 && opcionSistema < 4) {
                                            activador = false;
                                        } else {
                                            System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                        sc.nextLine();
                                    }
                                } while (activador);
                                switch(opcionSistema) {
                                    case 1:
                                        String sistemaModificar;
                                        do {
                                            System.out.println("¿Qué sistema quieres modificar? (Escribe 'Salir' si no quieres eliminar ningún sistema)");
                                            for (String consola: seleccionarJuego(nombre, catalogoJuegos).getConsolas()) {
                                                System.out.println(consola);
                                            }
                                            sistemaModificar = sc.nextLine();
                                            if (seleccionarJuego(nombre, catalogoJuegos).existeConsola(sistemaModificar)) {//
                                                int opcionModificarSistema = 0;
                                                do {
                                                    try {
                                                        System.out.println("Modificando sistema '" + sistemaModificar + "'.");
                                                        System.out.println("""
                                                                1. Modificar precio
                                                                2. Modificar stock
                                                                3. Salir
                                                                """);
                                                        opcionModificarSistema = sc.nextInt();
                                                        sc.nextLine();
                                                        if (!(opcionModificarSistema == 1 || opcionModificarSistema == 2 || opcionModificarSistema == 3)) {
                                                            System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                                        }
                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                                        sc.nextLine();
                                                    }
                                                } while (!(opcionModificarSistema == 1 || opcionModificarSistema == 2 || opcionModificarSistema == 3));
                                                switch(opcionModificarSistema) {
                                                    case 1:
                                                        activador = true;
                                                        do {
                                                            try {
                                                                System.out.println("El precio actual de '" + nombre + "' en " + sistemaModificar + "es de " + seleccionarJuego(nombre, catalogoJuegos).getPrecio(sistemaModificar) + "€.");
                                                                System.out.println("¿Cuál es el precio nuevo?");
                                                                double precio = sc.nextDouble();
                                                                sc.nextLine();
                                                                if (precio >= 0f) {
                                                                    seleccionarJuego(nombre, catalogoJuegos).seleccionarEdicion(sistemaModificar).modificarPrecio(precio);
                                                                    System.out.println("Precio de '" + nombre + "' en " + sistemaModificar + " actualizado a " + precio + "€.");
                                                                    activador = false;
                                                                } else {
                                                                    System.out.println("El precio no puede ser negativo.");
                                                                }
                                                            } catch (InputMismatchException e) {
                                                                System.out.println("Precio no válido, por favor, introduce un precio válido.\n");
                                                                sc.nextLine();
                                                            }
                                                        } while (activador);
                                                        break;
                                                    case 2:
                                                        activador = true;
                                                        do {
                                                            try {
                                                                System.out.println("El stock actual de '" + nombre + "' en " + sistemaModificar + " es de " + seleccionarJuego(nombre, catalogoJuegos).getStock(sistemaModificar) + "ud.");
                                                                System.out.println("¿Cuál es el nuevo stock?");
                                                                int stock = sc.nextInt();
                                                                sc.nextLine();
                                                                if (stock >= 0) {
                                                                    seleccionarJuego(nombre, catalogoJuegos).seleccionarEdicion(sistemaModificar).modificarStock(stock);
                                                                    System.out.println("Stock de '" + nombre + "' en " + sistemaModificar + " actualizado a " + stock + "ud.");
                                                                    activador = false;
                                                                } else {
                                                                    System.out.println("El stock no puede ser negativo.");
                                                                }
                                                            } catch (InputMismatchException e) {
                                                                System.out.println("Stock no válido, por favor, introduce un stock válido.\n");
                                                                sc.nextLine();
                                                            }
                                                        } while (activador);
                                                        break;
                                                    case 3:
                                                        sistemaModificar = "salir";
                                                        break;
                                                }
                                            } else if (sistemaModificar.equalsIgnoreCase("salir")){
                                                break;
                                            } else {
                                                System.out.println("El sistema no existe o no coincide exactamente con el nombre del sistema.");
                                            }
                                        } while (!seleccionarJuego(nombre, catalogoJuegos).seleccionarEdicion(sistemaModificar).getConsola().equals(sistemaModificar) || sistemaModificar.equalsIgnoreCase("salir"));
                                        break;
                                    case 2:
                                        String sistemaEliminar;
                                        activador = true;
                                        do {
                                            System.out.println("¿Qué sistema quieres eliminar? (Escribe 'Salir' si no quieres eliminar ningún sistema)");
                                            for (String consola: seleccionarJuego(nombre, catalogoJuegos).getConsolas()) {
                                                System.out.println(consola);
                                            }
                                            sistemaEliminar = sc.nextLine();
                                            if (seleccionarJuego(nombre, catalogoJuegos).existeConsola(sistemaEliminar)) {
                                                seleccionarJuego(nombre, catalogoJuegos).eliminarEdicion(sistemaEliminar);
                                                System.out.println("Sistema eliminado.");
                                                activador = false;
                                            } else if (sistemaEliminar.equalsIgnoreCase("salir")){
                                                activador = false;
                                            } else {
                                                System.out.println("El sistema no existe o no coincide exactamente con el nombre del sistema.");
                                            }
                                        } while (activador);
                                        break;
                                    case 3:
                                        break;
                                }
                            } else if (seleccionarJuego(nombre, catalogoJuegos).getNumeroSistemas() <= 0){
                                activador = true;
                                do {
                                    try {
                                        System.out.println("""
                                                1. Añadir un nuevo sistema
                                                2. Salir
                                                """);
                                        opcionSistema = sc.nextInt();
                                        sc.nextLine();
                                        if (opcionSistema > 0 && opcionSistema < 3) {
                                            activador = false;
                                        } else {
                                            System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                        sc.nextLine();
                                    }
                                } while (activador);
                                switch(opcionSistema) {
                                    case 1:
                                        activador = true;
                                        do {
                                            System.out.println("""
                                                    Escribe el nombre del sistema que quieres añadir:
                                                    XBOX
                                                    Nintendo
                                                    Play Station
                                                    PC
                                                    """);
                                            String nuevoSistema = sc.nextLine().toLowerCase();
                                            String[] listaSistemas = {"xbox", "nintendo", "play station", "pc"};
                                            if (Arrays.asList(listaSistemas).contains(nuevoSistema)) {
                                                activador = false;
                                                String consola = switch (nuevoSistema) {
                                                    case "xbox" -> "XBOX";
                                                    case "nintendo" -> "Nintendo";
                                                    case "play station" -> "Play Station";
                                                    default -> "PC";
                                                };
                                                boolean activadorPrecio = true;
                                                double precio = 0f;
                                                do {
                                                    try {
                                                        System.out.println("¿Qué precio tiene el juego '" + nombre + "' en " + consola +"?");
                                                        precio = sc.nextDouble();
                                                        sc.nextLine();
                                                        if (precio >= 0f) {
                                                            activadorPrecio = false;
                                                        } else {
                                                            System.out.println("El precio no puede ser negativo.");
                                                        }
                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Importe no válido, por favor, ingresa un importe válido.");
                                                        sc.nextLine();
                                                    }
                                                } while (activadorPrecio);
                                                boolean activadorStock = true;
                                                int stock = 0;
                                                do {
                                                    try {
                                                        System.out.println("¿Cuantás unidades del juego '" + nombre + "' en " + consola + " quieres añadir al stock?");
                                                        stock = sc.nextInt();
                                                        sc.nextLine();
                                                        if (stock >= 0) {
                                                            activadorStock = false;
                                                        } else {
                                                            System.out.println("El stock no puede ser negativo.");
                                                        }
                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Unidades no válidas, por favor, ingresa un número de unidades válido.");
                                                        sc.nextLine();
                                                    }
                                                } while (activadorStock);
                                                EdicionJuego edicionJuego = new EdicionJuego(consola, precio, stock);
                                                seleccionarJuego(nombre, catalogoJuegos).anadirEdicion(edicionJuego);
                                            } else {
                                                System.out.println("Sistema no válido, por favor escribe el nombre de un sistema de la lista.");
                                            }
                                        } while(activador);
                                        break;
                                    case 2:
                                        break;
                                }
                            } else {
                                activador = true;
                                do {
                                    try {
                                        System.out.println("""
                                                1. Modificar un sistema existente
                                                2. Eliminar un sistema existente
                                                3. Añadir un nuevo sistema
                                                4. Salir
                                                """);
                                        opcionSistema = sc.nextInt();
                                        sc.nextLine();
                                        if (opcionSistema > 0 && opcionSistema < 5) {
                                            activador = false;
                                        } else {
                                            System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                        sc.nextLine();
                                    }
                                } while (activador);
                                switch(opcionSistema) {
                                    case 1:
                                        String sistemaModificar;
                                        do {
                                            System.out.println("¿Qué sistema quieres modificar? (Escribe 'Salir' si no quieres eliminar ningún sistema)");
                                            for (String consola: seleccionarJuego(nombre, catalogoJuegos).getConsolas()) {
                                                System.out.println(consola);
                                            }
                                            sistemaModificar = sc.nextLine();
                                            if (seleccionarJuego(nombre, catalogoJuegos).existeConsola(sistemaModificar)) {
                                                int opcionModificarSistema = 0;
                                                do {
                                                    try {
                                                        System.out.println("Modificando sistema '" + sistemaModificar + "'.");
                                                        System.out.println("""
                                                                1. Modificar precio
                                                                2. Modificar stock
                                                                3. Salir
                                                                """);
                                                        opcionModificarSistema = sc.nextInt();
                                                        sc.nextLine();
                                                        if (!(opcionModificarSistema == 1 || opcionModificarSistema == 2 || opcionModificarSistema == 3)) {
                                                            System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                                        }
                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                                        sc.nextLine();
                                                    }
                                                } while (!(opcionModificarSistema == 1 || opcionModificarSistema == 2 || opcionModificarSistema == 3));
                                                switch(opcionModificarSistema) {
                                                    case 1:
                                                        activador = true;
                                                        do {
                                                            try {
                                                                System.out.println("El precio actual de '" + nombre + "' en " + sistemaModificar + "es de " + seleccionarJuego(nombre, catalogoJuegos).getPrecio(sistemaModificar) + "€.");
                                                                System.out.println("¿Cuál es el precio nuevo?");
                                                                double precio = sc.nextDouble();
                                                                sc.nextLine();
                                                                if (precio >= 0f) {
                                                                    seleccionarJuego(nombre, catalogoJuegos).seleccionarEdicion(sistemaModificar).modificarPrecio(precio);
                                                                    System.out.println("Precio de '" + nombre + "' en " + sistemaModificar + " actualizado a " + precio + "€.");
                                                                    activador = false;
                                                                } else {
                                                                    System.out.println("El precio no puede ser negativo.");
                                                                }
                                                            } catch (InputMismatchException e) {
                                                                System.out.println("Precio no válido, por favor, introduce un precio válido.\n");
                                                                sc.nextLine();
                                                            }
                                                        } while (activador);
                                                        break;
                                                    case 2:
                                                        activador = true;
                                                        do {
                                                            try {
                                                                System.out.println("El stock actual de '" + nombre + "' en " + sistemaModificar + " es de " + seleccionarJuego(nombre, catalogoJuegos).getStock(sistemaModificar) + "ud.");
                                                                System.out.println("¿Cuál es el nuevo stock?");
                                                                int stock = sc.nextInt();
                                                                sc.nextLine();
                                                                if (stock >= 0) {
                                                                    seleccionarJuego(nombre, catalogoJuegos).seleccionarEdicion(sistemaModificar).modificarStock(stock);
                                                                    System.out.println("Stock de '" + nombre + "' en " + sistemaModificar + " actualizado a " + stock + "ud.");
                                                                    activador = false;
                                                                } else {
                                                                    System.out.println("El stock no puede ser negativo.");
                                                                }
                                                            } catch (InputMismatchException e) {
                                                                System.out.println("Stock no válido, por favor, introduce un stock válido.\n");
                                                                sc.nextLine();
                                                            }
                                                        } while (activador);
                                                        break;
                                                    case 3:
                                                        sistemaModificar = "salir";
                                                        break;
                                                }
                                            } else if (sistemaModificar.equalsIgnoreCase("salir")){
                                                break;
                                            } else {
                                                System.out.println("El sistema no existe o no coincide exactamente con el nombre del sistema.");
                                            }
                                        } while (!seleccionarJuego(nombre, catalogoJuegos).existeConsola(sistemaModificar) || sistemaModificar.equalsIgnoreCase("salir"));
                                        break;
                                    case 2:
                                        String sistemaEliminar;
                                        activador = true;
                                        do {
                                            System.out.println("¿Qué sistema quieres eliminar? (Escribe 'Salir' si no quieres eliminar ningún sistema)");
                                            for (String consola: seleccionarJuego(nombre, catalogoJuegos).getConsolas()) {
                                                System.out.println(consola);
                                            }
                                            sistemaEliminar = sc.nextLine();
                                            if (seleccionarJuego(nombre, catalogoJuegos).existeConsola(sistemaEliminar)) {
                                                seleccionarJuego(nombre, catalogoJuegos).eliminarEdicion(sistemaEliminar);
                                                System.out.println("Sistema eliminado.");
                                                activador = false;
                                            } else if (sistemaEliminar.equalsIgnoreCase("salir")){
                                                activador = false;
                                            } else {
                                                System.out.println("El sistema no existe o no coincide exactamente con el nombre del sistema.");
                                            }
                                        } while (activador);
                                        break;
                                    case 3:
                                        activador = true;
                                        do {
                                            System.out.println("Escribe el nombre del sistema que quieres añadir:");
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
                                            if (listaSistemas.contains(nuevoSistema)) {
                                                activador = false;
                                                String consola = switch (nuevoSistema) {
                                                    case "xbox" -> "XBOX";
                                                    case "nintendo" -> "Nintendo";
                                                    case "play station" -> "Play Station";
                                                    default -> "PC";
                                                };
                                                boolean activadorPrecio = true;
                                                double precio = 0f;
                                                do {
                                                    try {
                                                        System.out.println("¿Qué precio tiene el juego '" + nombre + "' en " + consola +"?");
                                                        precio = sc.nextDouble();
                                                        sc.nextLine();
                                                        if (precio >= 0f) {
                                                            activadorPrecio = false;
                                                        } else {
                                                            System.out.println("El precio no puede ser negativo.");
                                                        }
                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Importe no válido, por favor, ingresa un importe válido.");
                                                        sc.nextLine();
                                                    }
                                                } while (activadorPrecio);
                                                boolean activadorStock = true;
                                                int stock = 0;
                                                do {
                                                    try {
                                                        System.out.println("¿Cuantás unidades del juego '" + nombre + "' en " + consola + " quieres añadir al stock?");
                                                        stock = sc.nextInt();
                                                        sc.nextLine();
                                                        if (stock >= 0) {
                                                            activadorStock = false;
                                                        } else {
                                                            System.out.println("El stock no puede ser negativo.");
                                                        }
                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Unidades no válidas, por favor, ingresa un número de unidades válido.");
                                                        sc.nextLine();
                                                    }
                                                } while (activadorStock);
                                                EdicionJuego edicionJuego = new EdicionJuego(consola, precio, stock);
                                                seleccionarJuego(nombre, catalogoJuegos).anadirEdicion(edicionJuego);
                                            } else {
                                                System.out.println("Sistema no válido, por favor escribe el nombre de un sistema de la lista.");
                                            }
                                        } while(activador);
                                        break;
                                    case 4:
                                        break;
                                }
                            }
                            break;
                        case 5:
                            System.out.println("Saliendo del sistema de modificación de juegos.");
                            break;
                    }
                } else {
                    System.out.println("El juego '" + nombre + "' no se encuentra en el catálogo de juegos.");
                }
                break;
            case 4:
                activador = true;
                int opcionListar = 0;
                do {
                    try {
                        System.out.println("""
                                Buscando juego por consola:
                                ¿Qué consola quieres listar?
                                1. XBOX
                                2. Nintendo
                                3. Play Station
                                4. PC
                                """);
                        opcionListar = sc.nextInt();
                        sc.nextLine();
                        if (opcionListar > 0 && opcionListar < 5) {
                            activador = false;
                        } else {
                            System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                        sc.nextLine();
                    }
                } while (activador);
                switch (opcionListar) {
                    case 1:
                        System.out.println(listarPorConsola("XBOX", catalogoJuegos));
                        break;
                    case 2:
                        System.out.println(listarPorConsola("Nintendo", catalogoJuegos));
                        break;
                    case 3:
                        System.out.println(listarPorConsola("Play Station", catalogoJuegos));
                        break;
                    case 4:
                        System.out.println(listarPorConsola("PC", catalogoJuegos));
                        break;
                }
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
