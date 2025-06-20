
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GestorTiendaJuegosTest {

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

}
