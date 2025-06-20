
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
                                System.out.println(menuClientes());
                                opcionCliente = sc.nextInt();
                                sc.nextLine();
                                opcionesCliente(opcionCliente, listaClientes);
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

    /**
     * Función para construir el menú de Gestión de Clientes.
     * @return menu String que contiene el menú de Gestión de Clientes para imprimir.
     */
    public static String menuClientes() {
        String menu = "### GESTIÓN DE CLIENTES ###\n\n" +
                "1. Alta de cliente nuevo\n" +
                "2. Baja de cliente existente\n" +
                "3. Modificar datos de cliente\n" +
                "4. Buscar cliente por DNI\n" +
                "5. Listado de clientes (Orden Alfabético)\n" +
                "6. Listado de clientes (Orden por importe de Ventas)\n" +
                "7. Salir\n\n" +
                "Elige una opción:";
        return menu;
    }

    /**
     * Función para manejar las opciones del menú de Gestión de Clientes
     * @param opcion Opción escogida para el menú
     * @param listaClientes Lista de los clientes para poder gestionarlos.
     */
    public static void opcionesCliente(int opcion, ArrayList<Cliente> listaClientes) {
        Scanner sc = new Scanner(System.in);
        String dni;

        switch (opcion) {
            case 1:
                do {
                    System.out.println("Introduce el DNI del nuevo cliente:");
                    dni = sc.nextLine().toUpperCase();
                    if (!dniValido(dni)) {
                        System.out.println("El DNI introducido no es válido, por favor, introduce un DNI válido.");
                        System.out.println("Recuerda introducir los 8 números y la letra sin guión.");
                    }
                } while (!dniValido(dni));

                if (!clienteExiste(dni, listaClientes)) {
                    System.out.println("Introduce el nombre del cliente:");
                    String nombre = sc.nextLine();
                    System.out.println("Introduce su teléfono:");
                    String telefono = sc.nextLine();
                    System.out.println("Introduce su correo electrónico:");
                    String email = sc.nextLine();
                    System.out.println(altaCliente(nombre, dni, telefono, email, listaClientes));
                } else {
                    System.out.println("El cliente con DNI " + dni + " ya existe en la base de datos.\n");
                }
                break;
            case 2:
                System.out.println("Introduce el DNI del cliente que deseas dar de baja.");
                dni = sc.nextLine().toUpperCase();
                System.out.println(bajaCliente(dni, listaClientes));
                break;
            case 3:
                System.out.println("Ingresa el DNI del cliente para modificar sus datos:");
                dni = sc.nextLine().toUpperCase();
                if (clienteExiste(dni, listaClientes)) {
                    for (Cliente c : listaClientes) {
                        if (dni.equals(c.getDni())) {
                            boolean activador = true;
                            while (activador) {
                                try {
                                    System.out.println("Cliente " + c.getNombre());
                                    System.out.println("¿Qué dato quieres modificar?\n" +
                                            "1. Nombre\n" +
                                            "2. Teléfono\n" +
                                            "3. Correo electrónico\n");
                                    int opcionModificar = sc.nextInt();
                                    sc.nextLine();
                                    switch (opcionModificar) {
                                        case 1:
                                            System.out.println("Introduce el nuevo nombre:");
                                            String nombre = sc.nextLine();
                                            c.modificarNombre(nombre);
                                            break;
                                        case 2:
                                            System.out.println("Introduce el nuevo teléfono:");
                                            String telefono = sc.nextLine();
                                            c.modificarTelefono(telefono);
                                            break;
                                        case 3:
                                            System.out.println("Introduce el nuevo correo electrónico:");
                                            String email = sc.nextLine();
                                            c.modificarEmail(email);
                                            break;
                                        default:
                                            System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                    }
                                    System.out.println("Escribe 'Si' si quieres modificar algún otro dato, sino escribe cualquier otra cosa.");
                                    String opcionModificarMas = sc.nextLine();
                                    if (!opcionModificarMas.equals("Si")) {
                                        activador = false;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                                    sc.nextLine();
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("El DNI introducido no coincide con ningún cliente de la base de datos.");
                }
                break;
            case 4:
                System.out.println("Ingresa el DNI del cliente que quieres ver:");
                dni = sc.nextLine().toUpperCase();
                System.out.println(buscarPorDni(dni, listaClientes));
                break;
            case 5:
                System.out.println(listarAlfabetico(listaClientes));
                break;
            case 6:
                listarPorVentas(listaClientes);
                break;
            case 7:
                System.out.println("Saliendo del sistema de gestión de clientes.\n");
                break;
            default:
                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
        }

    }

    /**
     * Función para dar de alta un cliente nuevo.
     * @param nombre String con el nombre del nuevo cliente.
     * @param dni String con el dni del nuevo cliente.
     * @param telefono String con el teléfono del nuevo cliente.
     * @param email String con el correo electrónico del nuevo cliente.
     * @param listaClientes Lista donde se guardará el nuevo cliente.
     * @return String con mensaje de éxito al crear el nuevo cliente.
     */
    public static String altaCliente(String nombre, String dni, String telefono, String email, ArrayList<Cliente> listaClientes) {
            Cliente cliente = new Cliente(nombre, dni, telefono, email);
            listaClientes.add(cliente);
            return "Nuevo cliente " + nombre + " con DNI " + dni + " dado de alta correctamente.\n";
    }

    /**
     * Función para dar de baja un cliente existente.
     * @param dni String con el dni del cliente existente.
     * @param listaClientes Lista de donde se eliminará el cliente existente.
     * @return Mensaje de éxito en caso de eliminar el cliente o, en caso contrario, mensaje de error si el cliente no existe.
     */
    public static String bajaCliente(String dni, ArrayList<Cliente> listaClientes) {
        if (clienteExiste(dni, listaClientes)) {
            for (Cliente c : listaClientes) {
                if (dni.equals(c.getDni())) {
                    listaClientes.remove(c);
                    return ("Cliente " + c.getNombre() + " con DNI " + c.getDni() + " eliminado de la base de datos correctamente.");
                }
            }
        }
        return("El DNI introducido no coincide con ningún cliente de la base de datos.");
    }

    /**
     * Función para buscar un cliente en una lista de clientes por su DNI.
     * @param dni String con el dni del cliente que queremos encontrar.
     * @param listaClientes Lista de clientes donde queremos encontrar al cliente por su DNI.
     * @return Mensaje con la información del cliente encontrado o, en caso de no existir el DNI en la lista, mensaje de error.
     */
    public static String buscarPorDni(String dni, ArrayList<Cliente> listaClientes) {
        if (clienteExiste(dni, listaClientes)) {
            for (Cliente c : listaClientes) {
                if (c.getDni().equals(dni)) {
                    String cliente = ("\nCliente: " + c.getNombre() +
                            "\nDNI: " + c.getDni() +
                            "\nTeléfono: " + c.getTelefono() +
                            "\nCorreo electrónico: " + c.getEmail() +
                            "\nImporte total de ventas: " + String.format("%.2f", c.getImporteVentas())+ "€.");
                    return cliente;
                }
            }
        }
        return "El DNI introducido no coincide con ningún cliente de la base de datos.";
    }

    /**
     * Función que imprime la lista de clientes con sus datos en orden alfabético.
     * @param listaClientes Lista de los clientes que se quiere ordenar y listar.
     */
    public static String listarAlfabetico(ArrayList<Cliente> listaClientes) {
        String mensaje = "Listado de clientes (Orden alfabético)\n";
        ArrayList<String> nombresID = new ArrayList<>();
        for (Cliente c : listaClientes) {
            nombresID.add(c.getNombre().toLowerCase() + "::" + c.getDni());
        }
        Collections.sort(nombresID);
        for (String nombre : nombresID) {
            for (Cliente c : listaClientes) {
                if ((c.getNombre().toLowerCase() + "::"+ c.getDni()).equals(nombre)) {
                    mensaje += c.getNombre() + ", DNI: " + c.getDni() + ", teléfono: " + c.getTelefono()
                            + ", correo electrónico: " + c.getEmail() + ", valor en ventas: " + String.format("%.2f", c.getImporteVentas()) + "€.\n";
                }
            }
        }
        return mensaje;
    }

    /**
     * Función que ordena e imprime la lista de clientes según el importe de las ventas realizadas a dicho cliente (orden ascendente).
     * @param listaClientes Lista de los clientes que se quiere ordenar y listar.
     */
    public static String listarPorVentas(ArrayList<Cliente> listaClientes) {
        String mensaje = "Listado de clientes (Orden por importe de ventas)\n";
        for (int i = 0; i < listaClientes.size() - 1; i++) {
            for (int j = i + 1; j < listaClientes.size(); j++) {
                if (listaClientes.get(i).getImporteVentas() > listaClientes.get(j).getImporteVentas()) {
                    Cliente c = listaClientes.get(i);
                    listaClientes.set(i, listaClientes.get(j));
                    listaClientes.set(j, c);
                }
            }
        }
        for (Cliente c : listaClientes) {
            mensaje += String.format("%.2f", c.getImporteVentas()) + "€, " + c.getNombre() +
                    ", DNI: " + c.getDni() + ", teléfono: " + c.getTelefono() + ", correo electrónico: " + c.getEmail() + ".\n";
        }
        return mensaje;
    }

    /**
     * Función para comprobar si el cliente existe en la base de datos.
     * @param dni DNI que se quiere comprobar si existe en la base de datos.
     * @param listaClientes Lista de los clientes donde se quiere comprobar si existe el cliente.
     * @return True si el cliente ya existe y false en caso contrario.
     */
    public static boolean clienteExiste(String dni, ArrayList<Cliente> listaClientes) {
        for (Cliente c : listaClientes) {
            if (c.getDni().equals(dni)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Función para comprobar que el DNI introducido es válido (método de distinción entre clientes)
     * @param dni DNI del cliente, debe contener 8 números y 1 letra, sin separador.
     * @return True en caso de que el DNI sea válido y false en caso contrario.
     */
    public static boolean dniValido(String dni) {
        if (dni.length() == 9) {
            if (Character.isLetter(dni.charAt(8))) {
                for (int i = 0; i < 8; i++) {
                    if (!Character.isDigit(dni.charAt(i))) {
                        return false;
                    }
                }
            } else {
                return false;
            }
            return true;
            }
        return false;
    }

}
