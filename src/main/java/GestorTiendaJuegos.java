
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;


public class GestorTiendaJuegos {
    /**
     * Función principal de la aplicación.
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        ArrayList<Juego> catalogoJuegos = new ArrayList<>();
        ArrayList<Venta> historialVentas = new ArrayList<>();
        configuracionInicial(catalogoJuegos, listaClientes, historialVentas);

        do {
            try {
                System.out.println(menuPrincipal());
                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        GestorClientes.menuPrincipalClientes(listaClientes, sc);
                        break;
                    case 2:
                        GestorJuegos.menuPrincipalJuegos(catalogoJuegos, sc);
                        break;
                    case 3:
                        GestorVentas.sistemaVentas(historialVentas, listaClientes, catalogoJuegos, sc);
                        break;
                    case 4:
                        menuMostrarVentas(listaClientes, historialVentas, sc);
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
     * @return String que contiene el menú principal de la aplicación para imprimir.
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

    /**
     * Función para gestionar el menú para mostrar las ventas realizadas.
     * @param historialVentas Lista de ventas que se quiere mostrar.
     * @param sc Scanner para introducir datos.
     */
    public static void menuMostrarVentas(ArrayList<Cliente> listaClientes, ArrayList<Venta> historialVentas, Scanner sc) {
        int opcion = 0;
        do {
            try {
                System.out.println(stringMenuMostrarVentas());
                opcion = sc.nextInt();
                sc.nextLine();
                switch(opcion) {
                    case 1:
                        System.out.println(mostrarTodasVentas(historialVentas));
                        break;
                    case 2:
                        System.out.println(mostrarVentasCliente(listaClientes, historialVentas, sc));
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

    /**
     * Función para construir el menú principal de la gestión de muestra de ventas.
     * @return String que contiene el menú principal de la gestión de muestra de ventas para imprimir
     */
    public static String stringMenuMostrarVentas() {
        String menu = """
                ### MOSTRAR VENTAS ###
                
                1. Mostrar todas las ventas
                2. Mostrar ventas de cliente
                3. Salir
                
                Elige una opción:""";
        return menu;
    }

    /**
     * Función para mostrar todas las ventas de una lista de ventas.
     * @param historialVentas Lista de ventas que se quiere mostrar al completo.
     * @return String con la lista de las facturas de las ventas mostradas.
     */
    public static String mostrarTodasVentas(ArrayList<Venta> historialVentas) {
        String mensaje = "###HISTORIAL DE TODAS LAS VENTAS###\n";
        if (historialVentas.isEmpty()) {
            mensaje += "No hay ventas en el historial de ventas.";
        } else {
            for (Venta venta : historialVentas) {
                mensaje += GestorVentas.stringTicketCompra(venta, historialVentas) + "\n";
                mensaje +="----------\n";
            }
        }
        return mensaje;
    }

    /**
     * Función para listar todas las compras realizadas por un cliente.
     * @param listaClientes Lista de clientes donde se encuentra el cliente que se quiere mostrar.
     * @param historialVentas Lista de Ventas donde se encuentran las ventas hechas por el cliente.
     * @param sc Scanner para introducir datos.
     * @return String con la información de las ventas del cliente o, en caso de no tener ninguna venta, mensaje indicándolo.
     */
    public static String mostrarVentasCliente(ArrayList<Cliente> listaClientes, ArrayList<Venta> historialVentas, Scanner sc) {
        String mensaje = "";
        boolean interruptor = true;
        do {
            System.out.println("Introduce el DNI del cliente para ver el historial de sus compras o escribe 'salir' para salir.");
            System.out.println(GestorClientes.listarAlfabetico(listaClientes));
            String dni = sc.nextLine();
            if (dni.equalsIgnoreCase("salir")) {
                mensaje = "Saliendo de la búsqueda de compras por DNI.";
                interruptor = false;
            } else if (GestorClientes.clienteExiste(dni, listaClientes)) {
                int contador = 0;
                for (Cliente c : listaClientes) {
                    if (c.getDni().equals(dni)) {
                        mensaje = "Cliente: " + c.getNombre() + " - " + c.getDni() + " - " + c.getTelefono() + " - " + c.getEmail() + "\n"
                                + "Importe total de las ventas: " + String.format("%.2f", c.getImporteVentas()) + "€\n----------\n";
                    }
                }
                for (Venta venta : historialVentas) {
                    if (venta.getCliente().getDni().equals(dni)) {
                        mensaje += GestorVentas.stringTicketCompra(venta, historialVentas) + "\n----------\n";
                        contador += 1;
                    }
                }
                if (contador == 0) {
                    mensaje = "El cliente no tiene ninguna compra realizada.";
                }
                interruptor = false;
            } else {
                System.out.println("El DNI introducido no coincide con ningún cliente de la base de datos.");
            }
        } while (interruptor);
        return mensaje;
    }

    /**
     * Configuración inicial de prueba para poner contenido en la base de datos de clientes, catálogo de juegos e historial de ventas.
     * @param catalogoJuegos Cátalogo donde se quieren introducir los juegos.
     * @param listadoClientes Lista donde se quieren incluir los clientes.
     * @param historialVentas Historial donde se quieren incluir las ventas.
     */
    public static void configuracionInicial(ArrayList<Juego> catalogoJuegos, ArrayList<Cliente> listadoClientes, ArrayList<Venta> historialVentas) {

        //Carga en el catálogo inicial de juegos.
        Juego juego1 = new Juego("The Legend of Zelda: TOTK", "Aventura", "PEGI-12");
        EdicionJuego edicionJuego1_1 = new EdicionJuego("Nintendo", 59.99, 20);
        juego1.anadirEdicion(edicionJuego1_1);

        Juego juego2 = new Juego ("Halo Infinite", "Shooter", "PEGI-16");
        EdicionJuego edicionJuego2_1 = new EdicionJuego("XBOX", 49.99, 15);
        juego2.anadirEdicion(edicionJuego2_1);

        Juego juego3 = new Juego ("God of War: Ragnarok", "Acción", "PEGI-18");
        EdicionJuego edicionJuego3_1 = new EdicionJuego("Play Station", 69.99, 10);
        juego3.anadirEdicion(edicionJuego3_1);

        Juego juego4 = new Juego("Elden Ring", "RPG", "PEGI-18");
        EdicionJuego edicionJuego4_1 = new EdicionJuego("PC", 54.99, 12);
        EdicionJuego edicionJuego4_2 = new EdicionJuego("Play Station", 59.99, 8);
        juego4.anadirEdicion(edicionJuego4_1);
        juego4.anadirEdicion(edicionJuego4_2);

        Juego juego5 = new Juego("Mario Kart 8 Deluxe", "Carreras", "PEGI-3");
        EdicionJuego edicionJuego5_1 = new EdicionJuego("Nintendo", 44.99, 25);
        juego5.anadirEdicion(edicionJuego5_1);

        Juego juego6 = new Juego("Minecraft", "Sandbox", "PEGI-7");
        EdicionJuego edicionJuego6_1 = new EdicionJuego("PC", 26.95, 40);
        EdicionJuego edicionJuego6_2 = new EdicionJuego("XBOX", 29.99, 22);
        juego6.anadirEdicion(edicionJuego6_1);
        juego6.anadirEdicion(edicionJuego6_2);

        Juego juego7 = new Juego("FIFA 24", "Deportes", "PEGI-3");
        EdicionJuego edicionJuego7_1 = new EdicionJuego("PC", 49.99, 30);
        EdicionJuego edicionJuego7_2 = new EdicionJuego("XBOX", 54.99, 20);
        EdicionJuego edicionJuego7_3 = new EdicionJuego("Play Station", 54.99, 18);
        juego7.anadirEdicion(edicionJuego7_1);
        juego7.anadirEdicion(edicionJuego7_2);
        juego7.anadirEdicion(edicionJuego7_3);

        Juego juego8 = new Juego("Animal Crossing: NH", "Simulación", "PEGI-3");
        EdicionJuego edicionJuego8_1 = new EdicionJuego("Nintendo", 39.99, 17);
        juego8.anadirEdicion(edicionJuego8_1);

        Juego juego9 = new Juego("Horizon Forbidden West", "Aventura", "PEGI-16");
        EdicionJuego edicionJuego9_1 = new EdicionJuego("Play Station", 59.99, 12);
        juego9.anadirEdicion(edicionJuego9_1);

        Juego juego10 = new Juego("Forza Horizon 5", "Carreras", "PEGI-3");
        EdicionJuego edicionJuego10_1 = new EdicionJuego("XBOX", 64.99, 14);
        EdicionJuego edicionJuego10_2 = new EdicionJuego("PC", 59.99, 10);
        juego10.anadirEdicion(edicionJuego10_1);
        juego10.anadirEdicion(edicionJuego10_2);

        Juego juego11 = new Juego("Resident Evil Village", "Terror", "PEGI-18");
        EdicionJuego edicionJuego11_1 = new EdicionJuego("PC", 49.99, 9);
        EdicionJuego edicionJuego11_2 = new EdicionJuego("Play Station", 52.99, 6);
        juego11.anadirEdicion(edicionJuego11_1);
        juego11.anadirEdicion(edicionJuego11_2);

        Juego juego12 = new Juego("Super Smash Bros. Ultimate", "Lucha", "PEGI-12");
        EdicionJuego edicionJuego12_1 = new EdicionJuego("Nintendo", 54.99, 21);
        juego12.anadirEdicion(edicionJuego12_1);

        Juego juego13 = new Juego("Call of Duty: MWIII", "Shooter", "PEGI-18");
        EdicionJuego edicionJuego13_1 = new EdicionJuego("XBOX", 69.99, 13);
        EdicionJuego edicionJuego13_2 = new EdicionJuego("PC", 64.99, 10);
        juego13.anadirEdicion(edicionJuego13_1);
        juego13.anadirEdicion(edicionJuego13_2);

        catalogoJuegos.add(juego1);
        catalogoJuegos.add(juego2);
        catalogoJuegos.add(juego3);
        catalogoJuegos.add(juego4);
        catalogoJuegos.add(juego5);
        catalogoJuegos.add(juego6);
        catalogoJuegos.add(juego7);
        catalogoJuegos.add(juego8);
        catalogoJuegos.add(juego9);
        catalogoJuegos.add(juego10);
        catalogoJuegos.add(juego11);
        catalogoJuegos.add(juego12);
        catalogoJuegos.add(juego13);


        //Carga en el listado inicial de clientes.
        Cliente cliente1 = new Cliente("Juan Pérez", "12345678A", "600000001", "juanperez@email.com");
        cliente1.aumentarImporteVentas(99.98);

        Cliente cliente2 = new Cliente("Laura Gómez", "23456789B", "600000002", "lauragomez@email.com");
        cliente2.aumentarImporteVentas(404.84);

        Cliente cliente3 = new Cliente("Marta Sánchez", "34567890C", "600000003", "martasanchez@email.com");
        cliente3.aumentarImporteVentas(487.92);

        Cliente cliente4 = new Cliente("Pedro Martínez", "45678901D", "600000004", "pedrom@email.com");
        cliente4.aumentarImporteVentas(186.92);

        Cliente cliente5 = new Cliente("Juan Pérez", "56789012E", "600000005", "juanperez2@email.com");
        cliente5.aumentarImporteVentas(249.96);

        Cliente cliente6 = new Cliente("Ana López", "67890123F", "600000006", "analopez@email.com");
        cliente6.aumentarImporteVentas(169.97);

        Cliente cliente7 = new Cliente("Daniel Ruiz", "78901234G", "600000007", "danielruiz@email.com");
        cliente7.aumentarImporteVentas(169.97);

        Cliente cliente8 = new Cliente("María Torres", "89012345H", "600000008", "mariatorres@email.com");
        cliente8.aumentarImporteVentas(0.00);

        Cliente cliente9 = new Cliente("David Romero", "90123456J", "600000009", "davidromero@email.com");
        cliente9.aumentarImporteVentas(589.90);

        Cliente cliente10 = new Cliente("Lucía Navarro", "10234567K", "600000010", "lucianavarro@email.com");
        cliente10.aumentarImporteVentas(187.97);

        listadoClientes.add(cliente1);
        listadoClientes.add(cliente2);
        listadoClientes.add(cliente3);
        listadoClientes.add(cliente4);
        listadoClientes.add(cliente5);
        listadoClientes.add(cliente6);
        listadoClientes.add(cliente7);
        listadoClientes.add(cliente8);
        listadoClientes.add(cliente9);
        listadoClientes.add(cliente10);

        //Carga en el listado inicial de ventas.
        ArrayList<HashMap<Juego, EdicionJuego>> lista1 = new ArrayList<>();
        lista1.add(new HashMap<>() {{ put(juego9, juego9.getEdicionJuego().get(0)); }});
        lista1.add(new HashMap<>() {{ put(juego4, juego4.getEdicionJuego().get(0)); }});
        lista1.add(new HashMap<>() {{ put(juego7, juego7.getEdicionJuego().get(2)); }});
        lista1.add(new HashMap<>() {{ put(juego10, juego10.getEdicionJuego().get(0)); }});
        lista1.add(new HashMap<>() {{ put(juego11, juego11.getEdicionJuego().get(1)); }});

        ArrayList<HashMap<Juego, EdicionJuego>> lista2 = new ArrayList<>();
        lista2.add(new HashMap<>() {{ put(juego3, juego3.getEdicionJuego().get(0)); }});
        lista2.add(new HashMap<>() {{ put(juego11, juego11.getEdicionJuego().get(1)); }});
        lista2.add(new HashMap<>() {{ put(juego10, juego10.getEdicionJuego().get(0)); }});

        ArrayList<HashMap<Juego, EdicionJuego>> lista3 = new ArrayList<>();
        lista3.add(new HashMap<>() {{ put(juego12, juego12.getEdicionJuego().get(0)); }});
        lista3.add(new HashMap<>() {{ put(juego10, juego10.getEdicionJuego().get(1)); }});
        lista3.add(new HashMap<>() {{ put(juego13, juego13.getEdicionJuego().get(1)); }});
        lista3.add(new HashMap<>() {{ put(juego3, juego3.getEdicionJuego().get(0)); }});

        ArrayList<HashMap<Juego, EdicionJuego>> lista4 = new ArrayList<>();
        lista4.add(new HashMap<>() {{ put(juego6, juego6.getEdicionJuego().get(0)); }});
        lista4.add(new HashMap<>() {{ put(juego11, juego11.getEdicionJuego().get(1)); }});
        lista4.add(new HashMap<>() {{ put(juego2, juego2.getEdicionJuego().get(0)); }});
        lista4.add(new HashMap<>() {{ put(juego9, juego9.getEdicionJuego().get(0)); }});

        ArrayList<HashMap<Juego, EdicionJuego>> lista5 = new ArrayList<>();
        lista5.add(new HashMap<>() {{ put(juego8, juego8.getEdicionJuego().get(0)); }});
        lista5.add(new HashMap<>() {{ put(juego1, juego1.getEdicionJuego().get(0)); }});

        ArrayList<HashMap<Juego, EdicionJuego>> lista6 = new ArrayList<>();
        lista6.add(new HashMap<>() {{ put(juego7, juego7.getEdicionJuego().get(2)); }});
        lista6.add(new HashMap<>() {{ put(juego4, juego4.getEdicionJuego().get(0)); }});
        lista6.add(new HashMap<>() {{ put(juego9, juego9.getEdicionJuego().get(0)); }});

        ArrayList<HashMap<Juego, EdicionJuego>> lista7 = new ArrayList<>();
        lista7.add(new HashMap<>() {{ put(juego3, juego3.getEdicionJuego().get(0)); }});

        ArrayList<HashMap<Juego, EdicionJuego>> lista8 = new ArrayList<>();
        lista8.add(new HashMap<>() {{ put(juego7, juego7.getEdicionJuego().get(2)); }});
        lista8.add(new HashMap<>() {{ put(juego1, juego1.getEdicionJuego().get(0)); }});
        lista8.add(new HashMap<>() {{ put(juego4, juego4.getEdicionJuego().get(1)); }});
        lista8.add(new HashMap<>() {{ put(juego13, juego13.getEdicionJuego().get(1)); }});
        lista8.add(new HashMap<>() {{ put(juego3, juego3.getEdicionJuego().get(0)); }});

        ArrayList<HashMap<Juego, EdicionJuego>> lista9 = new ArrayList<>();
        lista9.add(new HashMap<>() {{ put(juego1, juego1.getEdicionJuego().get(0)); }});
        lista9.add(new HashMap<>() {{ put(juego8, juego8.getEdicionJuego().get(0)); }});

        ArrayList<HashMap<Juego, EdicionJuego>> lista10 = new ArrayList<>();
        lista10.add(new HashMap<>() {{ put(juego9, juego9.getEdicionJuego().get(0)); }});
        lista10.add(new HashMap<>() {{ put(juego13, juego13.getEdicionJuego().get(0)); }});

        ArrayList<HashMap<Juego, EdicionJuego>> lista11 = new ArrayList<>();
        lista11.add(new HashMap<>() {{ put(juego3, juego3.getEdicionJuego().get(0)); }});
        lista11.add(new HashMap<>() {{ put(juego2, juego2.getEdicionJuego().get(0)); }});
        lista11.add(new HashMap<>() {{ put(juego13, juego13.getEdicionJuego().get(1)); }});
        lista11.add(new HashMap<>() {{ put(juego7, juego7.getEdicionJuego().get(2)); }});
        lista11.add(new HashMap<>() {{ put(juego8, juego8.getEdicionJuego().get(0)); }});

        ArrayList<HashMap<Juego, EdicionJuego>> lista12 = new ArrayList<>();
        lista12.add(new HashMap<>() {{ put(juego6, juego6.getEdicionJuego().get(0)); }});
        lista12.add(new HashMap<>() {{ put(juego9, juego9.getEdicionJuego().get(0)); }});

        ArrayList<HashMap<Juego, EdicionJuego>> lista13 = new ArrayList<>();
        lista13.add(new HashMap<>() {{ put(juego11, juego11.getEdicionJuego().get(1)); }});
        lista13.add(new HashMap<>() {{ put(juego13, juego13.getEdicionJuego().get(0)); }});
        lista13.add(new HashMap<>() {{ put(juego6, juego6.getEdicionJuego().get(0)); }});

        ArrayList<HashMap<Juego, EdicionJuego>> lista14 = new ArrayList<>();
        lista14.add(new HashMap<>() {{ put(juego5, juego5.getEdicionJuego().get(0)); }});
        lista14.add(new HashMap<>() {{ put(juego9, juego9.getEdicionJuego().get(0)); }});
        lista14.add(new HashMap<>() {{ put(juego10, juego10.getEdicionJuego().get(0)); }});

        ArrayList<HashMap<Juego, EdicionJuego>> lista15 = new ArrayList<>();
        lista15.add(new HashMap<>() {{ put(juego13, juego13.getEdicionJuego().get(1)); }});

        Venta venta1 = new Venta(cliente9, lista11, "02/01/2025");
        Venta venta2 = new Venta(cliente9, lista8, "08/05/2025");
        Venta venta3 = new Venta(cliente4, lista9, "03/05/2025");
        Venta venta4 = new Venta(cliente4, lista12, "14/02/2025");
        Venta venta5 = new Venta(cliente2, lista13, "23/06/2025");
        Venta venta6 = new Venta(cliente2, lista4, "27/02/2025");
        Venta venta7 = new Venta(cliente2, lista15, "22/04/2025");
        Venta venta8 = new Venta(cliente3, lista1, "01/04/2025");
        Venta venta9 = new Venta(cliente3, lista10, "14/04/2025");
        Venta venta10 = new Venta(cliente3, lista7, "02/04/2025");
        Venta venta11 = new Venta(cliente10, lista2, "02/02/2025");
        Venta venta12 = new Venta(cliente6, lista6, "30/04/2025");
        Venta venta13 = new Venta(cliente1, lista5, "07/06/2025");
        Venta venta14 = new Venta(cliente7, lista14, "19/04/2025");
        Venta venta15 = new Venta(cliente5, lista3, "18/02/2025");

        historialVentas.add(venta1);
        historialVentas.add(venta2);
        historialVentas.add(venta3);
        historialVentas.add(venta4);
        historialVentas.add(venta5);
        historialVentas.add(venta6);
        historialVentas.add(venta7);
        historialVentas.add(venta8);
        historialVentas.add(venta9);
        historialVentas.add(venta10);
        historialVentas.add(venta11);
        historialVentas.add(venta12);
        historialVentas.add(venta13);
        historialVentas.add(venta14);
        historialVentas.add(venta15);
    }

}