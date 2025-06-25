
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GestorClientesTest {

    static ArrayList<Cliente> listaClientes;

    @BeforeEach
    public void configuracionInicial() {
        listaClientes = new ArrayList<>();
        String nombre = "Kenneth";
        String dni = "12345678A";
        String telefono = "666123123";
        String email = "kenneth@email.com";
        GestorClientes.altaCliente(nombre, dni, telefono, email, listaClientes);
    }

    @Test
    public void menuClientes() {
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
        assertEquals(menu, GestorClientes.menuClientes());
    }

    @Test
    public void altaCliente() {
        String nombre = "Sara";
        String dni = "87654321B";
        String telefono = "666456456";
        String email = "sara@email.com";
        assertEquals("Nuevo cliente Sara con DNI 87654321B dado de alta correctamente.\n",
                GestorClientes.altaCliente(nombre, dni, telefono, email, listaClientes));
    }

    @Test
    public void bajaCliente() {
        String dni = "12345678A";
        assertEquals("Cliente Kenneth con DNI 12345678A eliminado de la base de datos correctamente.",
                GestorClientes.bajaCliente(dni, listaClientes));
    }

    @Test
    public void bajaClienteNoExistente() {
        String dni = "87654321B";
        assertEquals("El DNI introducido no coincide con ningún cliente de la base de datos.",
                GestorClientes.bajaCliente(dni, listaClientes));
    }

    @Test
    public void buscarPorDni() {
        String dni = "12345678A";
        assertEquals("\nCliente: Kenneth\nDNI: 12345678A\nTeléfono: 666123123\nCorreo electrónico: kenneth@email.com\nImporte total de ventas: 0,00€.",
                GestorClientes.buscarPorDni(dni, listaClientes));
    }

    @Test
    public void buscarPorDniNoExistente() {
        String dni = "87654321B";
        assertEquals("El DNI introducido no coincide con ningún cliente de la base de datos.",
                GestorClientes.buscarPorDni(dni, listaClientes));
    }

    @Test
    public void listarAlfabetico() {
        GestorClientes.altaCliente("Sara", "87654321B", "666456456", "sara@email.com", listaClientes);
        GestorClientes.altaCliente("Jordi", "78945612C", "666789789", "jordi@email.com", listaClientes);
        assertEquals("""
                        Listado de clientes (Orden alfabético)
                        Jordi, DNI: 78945612C, teléfono: 666789789, correo electrónico: jordi@email.com, valor en ventas: 0,00€.
                        Kenneth, DNI: 12345678A, teléfono: 666123123, correo electrónico: kenneth@email.com, valor en ventas: 0,00€.
                        Sara, DNI: 87654321B, teléfono: 666456456, correo electrónico: sara@email.com, valor en ventas: 0,00€.
                        """,
                GestorClientes.listarAlfabetico(listaClientes));
    }

    @Test
    public void listarPorVentas() {
        GestorClientes.altaCliente("Sara", "87654321B", "666456456", "sara@email.com", listaClientes);
        GestorClientes.altaCliente("Jordi", "78945612C", "666789789", "jordi@email.com", listaClientes);
        listaClientes.get(0).aumentarImporteVentas(500.50);
        listaClientes.get(1).aumentarImporteVentas(1000.60);
        listaClientes.get(2).aumentarImporteVentas(100.20);

        assertEquals("""
                        Listado de clientes (Orden por importe de ventas)
                        100,20€, Jordi, DNI: 78945612C, teléfono: 666789789, correo electrónico: jordi@email.com.
                        500,50€, Kenneth, DNI: 12345678A, teléfono: 666123123, correo electrónico: kenneth@email.com.
                        1000,60€, Sara, DNI: 87654321B, teléfono: 666456456, correo electrónico: sara@email.com.
                        """,
                GestorClientes.listarPorVentas(listaClientes));
    }

    @Test
    public void clienteExiste() {
        String dni = "12345678A";
        assertTrue(GestorClientes.clienteExiste(dni, listaClientes));
    }

    @Test
    public void clienteNoExiste() {
        String dni = "87654321B";
        assertFalse(GestorClientes.clienteExiste(dni, listaClientes));
    }

    @Test
    public void dniValido() {
        String dni = "12345678A";
        assertTrue(GestorClientes.dniValido(dni));
    }

    @Test
    public void dniLengthNoValido () {
        String dni = "1234A";
        assertFalse(GestorClientes.dniValido(dni));
    }

    @Test
    public void dniNumeroNoValido() {
        String dni = "ABCDEFGHX";
        assertFalse(GestorClientes.dniValido(dni));
    }

    @Test
    public void dniLetraNoValida() {
        String dni = "12345678*";
        assertFalse(GestorClientes.dniValido(dni));
    }

    @Test
    public void seleccionarCliente() {
        String dni = "12345678A";
        assertEquals(listaClientes.getLast() ,GestorClientes.seleccionarCliente(dni, listaClientes));
    }

    @Test
    public void seleccionarClienteNoExiste() {
        String dni = "87654321B";
        assertThrows(NoSuchElementException.class, () -> {
            GestorClientes.seleccionarCliente(dni, listaClientes);
        });
    }

    @AfterEach
    public void limpiarLista() {
        listaClientes.clear();
    }

}