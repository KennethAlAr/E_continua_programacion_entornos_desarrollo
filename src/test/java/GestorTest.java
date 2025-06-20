
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GestorTest {

    @BeforeAll
    public static void configuracionInicial() {
        String nombre = "Kenneth";
        String dni = "12345678A";
        String telefono = "666123123";
        String email = "kenneth@email.com";
        Cliente c1 = new Cliente(nombre, dni, telefono, email);

        nombre = "Sara";
        dni = "87654321B";
        telefono = "666456456";
        email = "sara@email.com";
        Cliente c2 = new Cliente(nombre, dni, telefono, email);
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

}
