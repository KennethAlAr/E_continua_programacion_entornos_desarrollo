
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * Representa el menú de gestión de los clientes y su base de datos.
 * @author Kenneth Alonso
 * @version 1.0
 */
public class GestorClientes {

    /**
     * Función para manejar las opciones del menú principal de Gestión de Clientes.
     * @param listaClientes Lista de los clientes para poder gestionarlos.
     * @param sc Scanner para introducir datos.
     */
    public static void menuPrincipalClientes(ArrayList<Cliente> listaClientes, Scanner sc) {
        int opcion = 0;
        do {
            try {
                System.out.println(stringMenuPrincipalClientes());
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                sc.nextLine();
            }
            switch (opcion) {
                case 1:
                    menuAltaCliente(listaClientes, sc);
                    break;
                case 2:
                    menuBajaCliente(listaClientes, sc);
                    break;
                case 3:
                    menuModificarCliente(listaClientes, sc);
                    break;
                case 4:
                    buscarPorDni(listaClientes, sc);
                    break;
                case 5:
                    System.out.println(listarAlfabetico(listaClientes));
                    break;
                case 6:
                    System.out.println(listarPorVentas(listaClientes));
                    break;
                case 7:
                    System.out.println("Saliendo del sistema de gestión de clientes.\n");
                    break;
                default:
                    System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
            }
        }while(opcion != 7);

    }

    /**
     * Función para construir el menú de Gestión de Clientes.
     * @return String que contiene el menú de Gestión de Clientes para imprimir.
     */
    public static String stringMenuPrincipalClientes() {
        String menu = """
                ### GESTIÓN DE CLIENTES ###
                
                1. Alta de cliente nuevo
                2. Baja de cliente existente
                3. Modificar datos de cliente
                4. Buscar cliente por DNI
                5. Listado de clientes (Orden Alfabético)
                6. Listado de clientes (Orden por importe de Ventas)
                7. Salir
                
                Elige una opción:""";
        return menu;
    }

    /**
     * Función para gestionar el alta de los clientes nuevos.
     * @param listaClientes Lista donde se va a dar de alta el nuevo cliente.
     * @param sc Scanner para introducir datos.
     */
    public static void menuAltaCliente(ArrayList<Cliente> listaClientes, Scanner sc) {
        String dni;
        do {
            System.out.println("Introduce el DNI del nuevo cliente:");
            dni = sc.nextLine().toUpperCase();
            if (!dniValido(dni)) {
                System.out.println("El DNI introducido no es válido, por favor, introduce un DNI válido.");
                System.out.println("Recuerda introducir los 8 números y la letra sin guión.");
            }
        } while (!dniValido(dni));
        boolean interruptor = true;
        do {
            if (!clienteExiste(dni, listaClientes)) {
                System.out.println("Introduce el nombre del cliente:");
                String nombre = sc.nextLine();
                System.out.println("Introduce su teléfono:");
                String telefono = sc.nextLine();
                System.out.println("Introduce su correo electrónico:");
                String email = sc.nextLine();
                System.out.println(altaCliente(nombre, dni, telefono, email, listaClientes));
                interruptor = false;
            } else {
                System.out.println("El cliente con DNI " + dni + " ya existe en la base de datos.\n");
            }
        } while (interruptor);
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
     * Función para gestionar la baja de clientes existentes.
     * @param listaClientes Lista donde se va a dar de baja al cliente existente.
     * @param sc Scanner para introducir datos.
     */
    public static void menuBajaCliente(ArrayList<Cliente> listaClientes, Scanner sc) {
        boolean interruptor = true;
        do {
            try {
                System.out.println("Introduce el DNI del cliente que deseas dar de baja o escribe 'salir' para salir.");
                String dni = sc.nextLine().toUpperCase();
                if (dni.equalsIgnoreCase("salir")) {
                    interruptor = false;
                } else {
                    System.out.println(bajaCliente(dni, listaClientes));
                    interruptor = false;
                }
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        } while (interruptor);
    }

    /**
     * Función para dar de baja un cliente existente.
     * @param dni String con el dni del cliente existente.
     * @param listaClientes Lista de donde se eliminará el cliente existente.
     * @return Mensaje de éxito en caso de eliminar el cliente.
     * @throws NoSuchElementException si el cliente no existe en la base de datos.
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
        throw new NoSuchElementException("El DNI introducido no coincide con ningún cliente de la base de datos.");
    }

    /**
     * Función para gestionar la modificación de datos de un cliente existente.
     * @param listaClientes Lista donde se encuentra el cliente para modificar sus datos.
     * @param sc Scanner para introducir datos.
     */
    public static void menuModificarCliente(ArrayList<Cliente> listaClientes, Scanner sc) {
        boolean interruptor = true;
        do {
            System.out.println("Ingresa el DNI del cliente para modificar sus datos o escribe 'salir' para salir:");
            String dni = sc.nextLine().toUpperCase();
            if (dni.equalsIgnoreCase("salir")) {
                interruptor = false;
            } else if (clienteExiste(dni, listaClientes)) {
                Cliente cliente = seleccionarCliente(dni, listaClientes);
                do {
                    try {
                        System.out.println("Cliente " + cliente.getNombre());
                        System.out.println("""
                                ¿Qué dato quieres modificar?
                                1. Nombre
                                2. Teléfono
                                3. Correo electrónico
                                """);
                        int opcionModificar = sc.nextInt();
                        sc.nextLine();
                        switch (opcionModificar) {
                            case 1:
                                System.out.println("Introduce el nuevo nombre:");
                                String nombre = sc.nextLine();
                                cliente.modificarNombre(nombre);
                                break;
                            case 2:
                                System.out.println("Introduce el nuevo teléfono:");
                                String telefono = sc.nextLine();
                                cliente.modificarTelefono(telefono);
                                break;
                            case 3:
                                System.out.println("Introduce el nuevo correo electrónico:");
                                String email = sc.nextLine();
                                cliente.modificarEmail(email);
                                break;
                            default:
                                System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                        }
                        System.out.println("Escribe 'Si' si quieres modificar algún otro dato, sino escribe cualquier otra cosa.");
                        String opcionModificarMas = sc.nextLine();
                        interruptor = opcionModificarMas.equalsIgnoreCase("Si");
                    } catch (InputMismatchException e) {
                        System.out.println("Opción no válida, por favor, elige una opción de las disponibles.\n");
                        sc.nextLine();
                    }
                } while(interruptor);
            } else {
                System.out.println("El DNI introducido no coincide con ningún cliente de la base de datos.");
            }
        } while (interruptor);
    }

    /**
     * Función para buscar un cliente en una lista de clientes por su DNI.
     * @param listaClientes Lista de clientes donde queremos encontrar al cliente por su DNI.
     * @param sc Scanner para introducir datos.
     */
    public static void buscarPorDni(ArrayList<Cliente> listaClientes, Scanner sc) {
        System.out.println("Ingresa el DNI del cliente que quieres ver o escribe 'salir' para salir:");
        String dni = sc.nextLine().toUpperCase();
        boolean interruptor = true;
        do {
            if (dni.equalsIgnoreCase("salir")) {
                interruptor = false;
            } else if (clienteExiste(dni, listaClientes)) {
                Cliente cliente = seleccionarCliente(dni, listaClientes);
                String mensaje = ("\nCliente: " + cliente.getNombre() +
                        "\nDNI: " + cliente.getDni() +
                        "\nTeléfono: " + cliente.getTelefono() +
                        "\nCorreo electrónico: " + cliente.getEmail() +
                        "\nImporte total de ventas: " + String.format("%.2f", cliente.getImporteVentas()) + "€.");
                System.out.println(mensaje);
                interruptor = false;
            } else {
                System.out.println("El DNI introducido no coincide con ningún cliente de la base de datos.");
            }
        } while(interruptor);
    }

    /**
     * Función que imprime la lista de clientes con sus datos en orden alfabético.
     * @param listaClientes Lista de los clientes que se quiere ordenar y listar.
     * @return String con el listado de los clientes en orden alfabético.
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
     * @return String con el listado de clientes en orden de importe de ventas.
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

    /**
     * Método para seleccionar un cliente de una lista de clientes por su DNI.
     * @param dni DNI del cliente que se quiere seleccionar.
     * @param listaCliente Lista de donde se quiere seleccionar al cliente.
     * @return Cliente seleccionado.
     * @throws NoSuchElementException Si no hay ningún cliente en la lista que coincida con el dni.
     */
    public static Cliente seleccionarCliente(String dni, ArrayList<Cliente> listaCliente) {
        for (Cliente c : listaCliente) {
            if (dni.equals(c.getDni())) {
                return c;
            }
        }
        throw new NoSuchElementException ("No hay ningún cliente con el dni " + dni + " en la base de datos.");
    }

}
