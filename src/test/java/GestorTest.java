
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GestorTest {

    static ArrayList<Cliente> listaClientes;

    @BeforeEach
    public void configuracionInicial() {
        listaClientes = new ArrayList<>();
        String nombre = "Kenneth";
        String dni = "12345678A";
        String telefono = "666123123";
        String email = "kenneth@email.com";
        GestorTiendaJuegos.altaCliente(nombre, dni, telefono, email, listaClientes);
    }

    @Test
    public void menuPrincipal() {
        String menu = "### MENÚ PRINCIPAL ###\n\n" +
                "1. Gestión de clientes\n" +
                "2. Gestión de inventario\n" +
                "3. Realizar venta\n" +
                "4. Mostrar ventas\n" +
                "5. Salir\n\n" +
                "Elige una opción:";
        assertEquals(menu, GestorTiendaJuegos.menuPrincipal());
    }

    @Test
    public void menuClientes() {
        String menu = "### GESTIÓN DE CLIENTES ###\n\n" +
                "1. Alta de cliente nuevo\n" +
                "2. Baja de cliente existente\n" +
                "3. Modificar datos de cliente\n" +
                "4. Buscar cliente por DNI\n" +
                "5. Listado de clientes (Orden Alfabético)\n" +
                "6. Listado de clientes (Orden por importe de Ventas)\n" +
                "7. Salir\n\n" +
                "Elige una opción:";
        assertEquals(menu, GestorTiendaJuegos.menuClientes());
    }

    @Test
    public void altaCliente() {
        String nombre = "Sara";
        String dni = "87654321B";
        String telefono = "666456456";
        String email = "sara@email.com";
        assertEquals(GestorTiendaJuegos.altaCliente(nombre, dni, telefono, email, listaClientes),
                "Nuevo cliente Sara con DNI 87654321B dado de alta correctamente.\n");
    }

    @Test
    public void bajaCliente() {
        String dni = "12345678A";
        assertEquals(GestorTiendaJuegos.bajaCliente(dni, listaClientes),
                "Cliente Kenneth con DNI 12345678A eliminado de la base de datos correctamente.");
    }

    @Test
    public void bajaClienteNoExistente() {
        String dni = "87654321B";
        assertEquals(GestorTiendaJuegos.bajaCliente(dni, listaClientes),
                "El DNI introducido no coincide con ningún cliente de la base de datos.");
    }

    @Test
    public void buscarPorDni() {
        String dni = "12345678A";
        assertEquals(GestorTiendaJuegos.buscarPorDni(dni, listaClientes),
                "\nCliente: Kenneth\nDNI: 12345678A\nTeléfono: 666123123\nCorreo electrónico: kenneth@email.com\nImporte total de ventas: 0,00€.");
    }

    @Test
    public void buscarPorDniNoExistente() {
        String dni = "87654321B";
        assertEquals(GestorTiendaJuegos.buscarPorDni(dni, listaClientes),
                "El DNI introducido no coincide con ningún cliente de la base de datos.");
    }

    @Test
    public void listarAlfabetico() {
        GestorTiendaJuegos.altaCliente("Sara", "87654321B", "666456456", "sara@email.com", listaClientes);
        GestorTiendaJuegos.altaCliente("Jordi", "78945612C", "666789789", "jordi@email.com", listaClientes);
        assertEquals(GestorTiendaJuegos.listarAlfabetico(listaClientes),
                "Listado de clientes (Orden alfabético)\n" +
                        "Jordi, DNI: 78945612C, teléfono: 666789789, correo electrónico: jordi@email.com, valor en ventas: 0,00€.\n" +
                        "Kenneth, DNI: 12345678A, teléfono: 666123123, correo electrónico: kenneth@email.com, valor en ventas: 0,00€.\n" +
                        "Sara, DNI: 87654321B, teléfono: 666456456, correo electrónico: sara@email.com, valor en ventas: 0,00€.\n");
    }

    @Test
    public void listarPorVentas() {
        GestorTiendaJuegos.altaCliente("Sara", "87654321B", "666456456", "sara@email.com", listaClientes);
        GestorTiendaJuegos.altaCliente("Jordi", "78945612C", "666789789", "jordi@email.com", listaClientes);
        double importe = 100.50;
        listaClientes.get(0).aumentarImporteVentas(500.50);
        listaClientes.get(1).aumentarImporteVentas(1000.60);
        listaClientes.get(2).aumentarImporteVentas(100.20);

        assertEquals(GestorTiendaJuegos.listarPorVentas(listaClientes),
                "Listado de clientes (Orden por importe de ventas)\n" +
                        "100,20€, Jordi, DNI: 78945612C, teléfono: 666789789, correo electrónico: jordi@email.com.\n" +
                        "500,50€, Kenneth, DNI: 12345678A, teléfono: 666123123, correo electrónico: kenneth@email.com.\n" +
                        "1000,60€, Sara, DNI: 87654321B, teléfono: 666456456, correo electrónico: sara@email.com.\n");
    }

    @Test
    public void clienteExiste() {
        String dni = "12345678A";
        assertEquals(GestorTiendaJuegos.clienteExiste(dni, listaClientes), true);
    }

    @Test
    public void clienteNoExiste() {
        String dni = "87654321B";
        assertEquals(GestorTiendaJuegos.clienteExiste(dni, listaClientes), false);
    }

    @Test
    public void dniValido() {
        String dni = "12345678A";
        assertEquals(GestorTiendaJuegos.dniValido(dni), true);
    }

    @Test
    public void dniLengthNoValido () {
        String dni = "1234A";
        assertEquals(GestorTiendaJuegos.dniValido(dni), false);
    }

    @Test
    public void dniNumeroNoValido() {
        String dni = "ABCDEFGHX";
        assertEquals(GestorTiendaJuegos.dniValido(dni), false);
    }

    @Test
    public void dniLetraNoValida() {
        String dni = "12345678*";
        assertEquals(GestorTiendaJuegos.dniValido(dni), false);
    }

    @AfterEach
    public void limpiarLista() {
        listaClientes.clear();
    }

}
