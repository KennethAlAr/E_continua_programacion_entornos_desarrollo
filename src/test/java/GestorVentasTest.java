
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GestorVentasTest {

    private ArrayList<Venta> historialVentas = new ArrayList<>();

    @BeforeEach
    public void configuracionInicial() {
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
    public void getJuego() {
        GestorVentas.getJuego(historialVentas.getLast().getArticulosVenta().getLast());
        String nombre = "Zelda";
        String genero = "Aventura";
        String pegi = "PEGI-12";
        assertEquals(nombre, GestorVentas.getJuego(historialVentas.getLast().getArticulosVenta().getLast()).getNombre());
        assertEquals(genero, GestorVentas.getJuego(historialVentas.getLast().getArticulosVenta().getLast()).getGenero());
        assertEquals(pegi, GestorVentas.getJuego(historialVentas.getLast().getArticulosVenta().getLast()).getPegi());
    }

    @Test
    public void getJuegoNoExiste() {
        historialVentas.getLast().getArticulosVenta().clear();
        assertThrows(NoSuchElementException.class, () -> {
            GestorVentas.getJuego(historialVentas.getLast().getArticulosVenta().getLast());
        });
    }

    @Test
    public void stringTicketCompra() {
        String mensaje = """
                FV-202501-001
                Cliente: Kenneth - 12345678A
                Fecha de la venta: 01/01/2025
                 - Zelda - Nintendo - 59,99€
                Importe total: 59,99€""";
        assertEquals(mensaje, GestorVentas.stringTicketCompra(historialVentas.getLast(), historialVentas));
    }

    @AfterEach
    public void limpiarLista() {
        historialVentas.clear();
    }

}
