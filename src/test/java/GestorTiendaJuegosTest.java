
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GestorTiendaJuegosTest {

    @Test
    public void menuPrincipal() {
        String menu = """
                ### MENÚ PRINCIPAL ###
                
                1. Gestión de clientes
                2. Gestión de inventario
                3. Realizar venta
                4. Mostrar ventas
                5. Salir
                
                Elige una opción:""";
        assertEquals(menu, GestorTiendaJuegos.menuPrincipal());
    }

}
