
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GestorVentas {

    public static void sistemaVentas(ArrayList<Venta> historialVentas, ArrayList<Cliente> listaClientes, ArrayList<Juego> catalogoJuegos) {

        Scanner sc = new Scanner(System.in);
        boolean activador = true;
        String dni = "";
        Cliente cliente = null;
        Juego juego = null;
        String nombre = "";
        ArrayList<HashMap<Juego, EdicionJuego>> listaCompra = new ArrayList<>();
        boolean salir = false;
        boolean terminar = false;
        do {
            System.out.println("Ingresa el DNI del cliente que va a realizar la venta o escribe 'Salir' para salir:");
            dni = sc.nextLine();
            if (dni.equalsIgnoreCase("salir")) {
                activador = false;
            } else {
                try {
                    cliente = GestorClientes.seleccionarCliente(dni, listaClientes);
                    activador = false;
                } catch (NoSuchElementException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (activador);
        do {
            if (dni.equalsIgnoreCase("salir")) {
                salir = true;
            } else {
                activador = true;
                do {
                    System.out.println("Realizando venta para " + cliente.getNombre() + " con DNI " + cliente.getDni() + ".");
                    System.out.println("Escribe el nombre del juego que se va a comprar o 'salir' para salir:");
                    nombre = sc.nextLine();
                    if (nombre.equalsIgnoreCase("salir")) {
                        activador = false;
                    } else if (!GestorJuegos.juegoExiste(nombre, catalogoJuegos)) {
                        System.out.println("El juego no se encuentra en el catálogo de juegos.");
                    } else if (GestorJuegos.seleccionarJuego(nombre, catalogoJuegos).getNumeroSistemas() == 0) {
                        System.out.println("El juego no está disponible para ningún sistema.");
                    } else {
                        juego = GestorJuegos.seleccionarJuego(nombre, catalogoJuegos);
                        if (!juego.tieneStock()) {
                            System.out.println("No hay stock del juego en ningún sistema.");
                        } else {
                            activador = false;
                        }
                    }
                } while (activador);
                String sistema = "";
                if (nombre.equalsIgnoreCase("salir")) {
                    salir = true;
                } else if (juego == GestorJuegos.seleccionarJuego(nombre, catalogoJuegos)) {
                    activador = true;
                    do {
                        System.out.println("El juego '" + nombre + "' está disponible para los siguientes sistemas:");
                        for (EdicionJuego edicionJuego : juego.getSistemasConStock()) {
                            System.out.println(edicionJuego.getConsola() + " - " + String.format("%.2f", edicionJuego.getPrecio()) + "€.");
                        }
                        System.out.println("Ingresa el nombre del sistema para el cual quieres el juego:");
                        sistema = sc.nextLine();
                        ArrayList<String> listaSistemasDisponibles = new ArrayList<>();
                        for (EdicionJuego edicionJuego : juego.getSistemasConStock()) {
                            listaSistemasDisponibles.add(edicionJuego.getConsola());
                        }
                        if (listaSistemasDisponibles.contains(sistema)) {
                            activador = false;
                            HashMap<Juego, EdicionJuego> juegoEdicion = new HashMap<>();
                            juegoEdicion.put(juego, juego.seleccionarEdicion(sistema));
                            System.out.println("Juego '" + juego.getNombre() + "' para " + sistema + " añadido a la lista de la compra. Precio: "
                                    + String.format("%.2f", juego.seleccionarEdicion(sistema).getPrecio()) + "€.");
                            listaCompra.add(juegoEdicion);
                        } else {
                            System.out.println("Sistema no válido, por favor escribe el nombre de un sistema de la lista.");
                        }
                    } while (activador);
                }
            }
            if (!salir) {
                activador = true;
                do {
                    try {
                        System.out.println("""
                                ¿Quiéres añadir otro producto a la lista de la compra?
                                1. Si
                                2. No
                                """);
                        int opcionCompra = sc.nextInt();
                        sc.nextLine();
                        switch (opcionCompra) {
                            case 1:
                                activador = false;
                                salir = false;
                                break;
                            case 2:
                                activador = false;
                                terminar = true;
                                salir = true;
                                break;
                            default:
                                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                        sc.nextLine();
                    }
                } while (activador);
            }
        } while (!salir);
        if (terminar) {
            LocalDate hoy = LocalDate.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fecha = hoy.format(formato);
            Venta venta = new Venta(cliente, listaCompra, fecha);
            System.out.println("Resumen de la venta:");
            System.out.println(venta.resumenVenta());
            System.out.println();
            int confirmarVenta = 0;
            activador = true;
            do {
                try{
                    System.out.println("""
                            ¿Quieres confirmar la venta?
                            1. Si
                            2. No
                            """);
                    confirmarVenta = sc.nextInt();
                    sc.nextLine();
                    switch (confirmarVenta) {
                        case 1:
                            activador = false;
                            historialVentas.add(venta);
                            cliente.aumentarImporteVentas(venta.getImporteVenta());
                            for (HashMap<Juego, EdicionJuego> articulo : listaCompra) {
                                for (Juego j : articulo.keySet()) {
                                    for (EdicionJuego edicionJuego : j.getEdicionJuego()){
                                        if (articulo.values().contains(edicionJuego)) {
                                            edicionJuego.reducirStock();
                                        }
                                    }
                                }
                            }
                            break;
                        case 2:
                            activador = false;
                            System.out.println("Saliendo del sistema de ventas.");
                            break;
                        default:
                            System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                    sc.nextLine();
                }
            } while (activador);
        } else {
            System.out.println("Saliendo del sistema de ventas.");
        }
    }

}