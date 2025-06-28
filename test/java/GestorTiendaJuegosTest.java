
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GestorTiendaJuegosTest {

    static ArrayList<Venta> historialVentas;

    @BeforeEach
    public void configuracionInicial() {
        historialVentas = new ArrayList<>();
        Cliente cliente = new Cliente("Kenneth", "12345678A", "666123123", "kenneth@email.com");
        Juego juego = new Juego("Zelda", "Aventura", "PEGI-12");
        EdicionJuego edicionJuego = new EdicionJuego("Nintendo", 59.99, 10);
        HashMap<Juego, EdicionJuego> articulo = new HashMap<>();
        articulo.put(juego, edicionJuego);
        ArrayList<HashMap<Juego, EdicionJuego>> articulosVenta = new ArrayList<>();
        articulosVenta.add(articulo);
        Venta venta = new Venta(cliente,articulosVenta, "01/01/2025");
        historialVentas.add(venta);
    }

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

    @Test
    public void stringMenuMostrarVentas() {
        String menu = """
                ### MOSTRAR VENTAS ###
                
                1. Mostrar todas las ventas
                2. Mostrar ventas de cliente
                3. Salir
                
                Elige una opción:""";
        assertEquals(menu, GestorTiendaJuegos.stringMenuMostrarVentas());
    }

    @Test
    public void mostrarTodasVentas () {
        String mensaje = """
                ###HISTORIAL DE TODAS LAS VENTAS###
                FV-202501-001
                Cliente: Kenneth - 12345678A
                Fecha de la venta: 01/01/2025
                 - Zelda - Nintendo - 59,99€
                Importe total: 59,99€
                ----------
                """;
        assertEquals(mensaje, GestorTiendaJuegos.mostrarTodasVentas(historialVentas));
    }

    @Test
    public void mostrarTodasVentasSinVentas() {
        historialVentas.clear();
        String mensaje = """
                ###HISTORIAL DE TODAS LAS VENTAS###
                No hay ventas en el historial de ventas.""";
        assertEquals(mensaje, GestorTiendaJuegos.mostrarTodasVentas(historialVentas));
    }

}
