
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
        ArrayList<Venta> historialVentas = new ArrayList<>();
        configuracionInicial(catalogoJuegos, listaClientes);

        do {
            try {
                System.out.println(menuPrincipal());
                opcion = sc.nextInt();
                sc.nextLine();
                int opcionCliente = 0;

                switch (opcion) {
                    case 1:
                        GestorClientes.menuPrincipalClientes(listaClientes, sc);
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
                        GestorVentas.sistemaVentas(historialVentas, listaClientes, catalogoJuegos);
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

    /**
     * Configuración inicial de prueba para poner contenido en la base de datos de clientes y catálogo de juegos.
     * @param catalogoJuegos Cátalogo donde se quieren introducir los juegos.
     * @param listadoClientes Lista donde se quieren incluir los clientes.
     */
    public static void configuracionInicial(ArrayList<Juego> catalogoJuegos, ArrayList<Cliente> listadoClientes) {

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
        cliente1.aumentarImporteVentas(1520.75);

        Cliente cliente2 = new Cliente("Laura Gómez", "23456789B", "600000002", "lauragomez@email.com");
        cliente2.aumentarImporteVentas(785.40);

        Cliente cliente3 = new Cliente("Marta Sánchez", "34567890C", "600000003", "martasanchez@email.com");
        cliente3.aumentarImporteVentas(1320.00);

        Cliente cliente4 = new Cliente("Pedro Martínez", "45678901D", "600000004", "pedrom@email.com");
        cliente4.aumentarImporteVentas(1987.90);

        Cliente cliente5 = new Cliente("Juan Pérez", "56789012E", "600000005", "juanperez2@email.com");
        cliente5.aumentarImporteVentas(910.25);

        Cliente cliente6 = new Cliente("Ana López", "67890123F", "600000006", "analopez@email.com");
        cliente6.aumentarImporteVentas(1199.99);

        Cliente cliente7 = new Cliente("Daniel Ruiz", "78901234G", "600000007", "danielruiz@email.com");
        cliente7.aumentarImporteVentas(450.60);

        Cliente cliente8 = new Cliente("María Torres", "89012345H", "600000008", "mariatorres@email.com");
        cliente8.aumentarImporteVentas(1635.10);

        Cliente cliente9 = new Cliente("David Romero", "90123456J", "600000009", "davidromero@email.com");
        cliente9.aumentarImporteVentas(1300.00);

        Cliente cliente10 = new Cliente("Lucía Navarro", "10234567K", "600000010", "lucianavarro@email.com");
        cliente10.aumentarImporteVentas(1770.00);

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

    }

}