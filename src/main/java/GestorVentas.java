
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GestorVentas {

    /**
     * Función para gestionar la creación de una nueva venta.
     * @param historialVentas Lista de ventas donde se añadirá la venta en caso de confirmarse.
     * @param listaClientes Lista de clientes donde se encuentra el cliente que hará la compra.
     * @param catalogoJuegos Lista de juegos donde se encuentran los juegos que se incluirán en la lista de la compra.
     * @param sc Scanner para introducir datos.
     */
    public static void sistemaVentas(ArrayList<Venta> historialVentas, ArrayList<Cliente> listaClientes, ArrayList<Juego> catalogoJuegos, Scanner sc) {

        boolean interruptor = true;
        String dni;
        Venta venta = null;
        ArrayList<HashMap<Juego, EdicionJuego>> listaCompra = new ArrayList<>();
        do {
            System.out.println("Ingresa el DNI del cliente que va a realizar la venta o escribe 'salir' para salir:");
            for (Cliente c : listaClientes) {
                System.out.println(c.getNombre() + " - " + c.getDni());
            }
            dni = sc.nextLine();
            if (dni.equalsIgnoreCase("salir")) {
                interruptor = false;
            } else {
                try {
                    Cliente cliente = GestorClientes.seleccionarCliente(dni, listaClientes);
                    interruptor = false;
                    System.out.println("Realizando venta para " + cliente.getNombre() + " con DNI " + cliente.getDni() + ".");
                    LocalDate hoy = LocalDate.now();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String fecha = hoy.format(formato);
                    venta = new Venta(cliente, listaCompra, fecha);
                } catch (NoSuchElementException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (interruptor);
        if (!dni.equalsIgnoreCase("salir")) {
            boolean salir = loopAnadirArticulo(venta, catalogoJuegos, sc);
            if (!salir) {
                confirmarVenta(venta, historialVentas, sc);
            }
        }
    }

    /**
     * Función para crear un loop para añadir artículos a la lista de la compra hasta que el cliente quiera.
     * @param venta Venta donde se añaden los artículos a la lista de la compra.
     * @param catalogoJuegos Lista de juegos donde se encuentran los juegos que se quieren añadir a la lista de la compra.
     * @param sc Scanner para introducir datos.
     * @return True si el cliente quiere salir de la función o, en caso contrario, false.
     */
    public static boolean loopAnadirArticulo(Venta venta, ArrayList<Juego> catalogoJuegos, Scanner sc) {
        int opcion = 0;
        boolean salir;
        do {
            salir = anadirJuegoListaCompra(venta, catalogoJuegos, sc);
            if (salir) {
                opcion = 2;
            } else {
                boolean opcionValida = false;
                do {
                    try {
                        System.out.println("""
                                ¿Quiéres añadir otro producto a la lista de la compra?
                                1. Si
                                2. No
                                """);
                        opcion = sc.nextInt();
                        sc.nextLine();
                        if (opcion == 1 || opcion == 2) {
                            opcionValida = true;
                        } else {
                            System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                        sc.nextLine();
                    }
                } while (!opcionValida);
            }
        } while (opcion != 2);
        return salir;
    }

    /**
     * Función para gestionar la inclusión de un artículo en la lista de la compra de una venta.
     * @param venta Venta que contiene la lista de la compra donde se va a añadir el artículo.
     * @param catalogoJuegos Lista de juegos donde se encuentra el juego que se va a añadir a la lista de la compra.
     * @param sc Scanner para ingresar datos.
     * @return True si el cliente quiere salir de la función o, en caso contrario, false.
     */
    public static boolean anadirJuegoListaCompra (Venta venta, ArrayList<Juego> catalogoJuegos, Scanner sc) {
        boolean interruptor = true;
        String nombre;
        Juego juego = null;
        boolean salir = false;
        do {
            System.out.println("Escribe el nombre del juego que se va a comprar o 'salir' para salir:");
            for (Juego j : catalogoJuegos) {
                if (j.tieneStock()) {
                    System.out.println(j.getNombre());
                }
            }
            nombre = sc.nextLine();
            if (nombre.equalsIgnoreCase("salir")) {
                interruptor = false;
                salir = true;
            } else if (!GestorJuegos.juegoExiste(nombre, catalogoJuegos)) {
                System.out.println("El juego no se encuentra en el catálogo de juegos.");
            } else if (GestorJuegos.seleccionarJuego(nombre, catalogoJuegos).getNumeroSistemas() == 0) {
                System.out.println("El juego no está disponible para ningún sistema.");
            } else {
                juego = GestorJuegos.seleccionarJuego(nombre, catalogoJuegos);
                if (!juego.tieneStock()) {
                    System.out.println("No hay stock del juego en ningún sistema.");
                } else {
                    interruptor = false;
                }
            }
        } while (interruptor);
        if (!nombre.equalsIgnoreCase("salir")) {
            interruptor = true;
            do {
                System.out.println("El juego '" + nombre + "' está disponible para los siguientes sistemas:");
                for (EdicionJuego edicionJuego : juego.getSistemasConStock()) {
                    System.out.println(edicionJuego.getConsola() + " - " + String.format("%.2f", edicionJuego.getPrecio()) + "€.");
                }
                System.out.println("Ingresa el nombre del sistema para el cual quieres el juego o escribe 'salir' para salir:");
                String sistema = sc.nextLine();
                ArrayList<String> listaSistemasDisponibles = new ArrayList<>();
                for (EdicionJuego edicionJuego : juego.getSistemasConStock()) {
                    listaSistemasDisponibles.add(edicionJuego.getConsola());
                }
                if (sistema.equalsIgnoreCase("salir")) {
                    interruptor = false;
                    salir = true;
                } else if (listaSistemasDisponibles.contains(sistema)) {
                    interruptor = false;
                    EdicionJuego edicionJuego = juego.seleccionarEdicion(sistema);
                    venta.anadirArticulo(juego, edicionJuego);
                    System.out.println("Juego '" + juego.getNombre() + "' para " + sistema + " añadido a la lista de la compra. Precio: "
                            + String.format("%.2f", juego.seleccionarEdicion(sistema).getPrecio()) + "€.");
                } else {
                    System.out.println("Sistema no válido, por favor escribe el nombre de un sistema de la lista.");
                }
            } while (interruptor);
        }
        return salir;
    }

    /**
     * Función para gestionar la confirmación de la venta hecha por el cliente.
     * @param venta Venta que se quiere confirmar.
     * @param historialVentas Lista de ventas donde se introducirá la venta en caso de confirmarse.
     * @param sc Scanner para ingresar datos.
     */
    public static void confirmarVenta(Venta venta, ArrayList<Venta> historialVentas, Scanner sc) {
        int opcion = 0;
        do {
            try {
                System.out.println("Resumen de la venta:");
                System.out.println(venta.resumenVenta());
                System.out.println("""
                            ¿Quieres confirmar la venta?
                            1. Si
                            2. No
                            """);
                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        historialVentas.add(venta);
                        venta.getCliente().aumentarImporteVentas(venta.getImporteVenta());
                        for (HashMap<Juego, EdicionJuego> articulo : venta.getArticulosVenta()) {
                            articulo.get(getJuego(articulo)).reducirStock();
                        }
                        System.out.println(stringTicketCompra(venta, historialVentas));
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
    }

    /**
     * Función para seleccionar el juego de un artículo de la lista de la compra.
     * @return Juego seleccionado.
     * @throws NoSuchElementException en caso de que el juego no se encuentre en la lista de la compra.
     */
    public static Juego getJuego(HashMap<Juego, EdicionJuego> articulo) {
        for (Juego j : articulo.keySet()) {
            return j;
        }
        throw new NoSuchElementException("El juego no se encuentra en la lista de la compra.");
    }

    /**
     * Función que devuelve un String con la factura de la venta.
     * @param venta Venta para la cual se hace la factura.
     * @param historialVentas Lista de ventas donde se encuentra la venta.
     * @return String con el ticket de la venta realizada con: Número de factura, cliente, fecha, lista de artículos y total de la venta.
     */
    public static String stringTicketCompra (Venta venta, ArrayList<Venta> historialVentas) {
        String mensaje = "";
        String numeroVenta;
        if (historialVentas.indexOf(venta) < 11) {
            numeroVenta = "00" + (historialVentas.indexOf(venta)+1);
        } else if (historialVentas.indexOf(venta) < 101) {
            numeroVenta = "0" + (historialVentas.indexOf(venta)+1);
        } else {
            numeroVenta = Integer.toString(historialVentas.indexOf(venta)+1);
        }
        String numeroFactura = "FV-" + venta.getFecha().substring(6)
                + venta.getFecha().substring(3,5) + "-" + numeroVenta + "\n";
        mensaje += numeroFactura + venta.resumenVenta();
        return mensaje;
    }

}