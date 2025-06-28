
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VentaTest {

    private ArrayList<Venta> historialVentas;

    @BeforeEach
    public void configuracionInicial() {
        historialVentas = new ArrayList<>();
        Cliente cliente = new Cliente("Kenneth", "12345678A", "666123123", "kenneth@email.com");
        Juego juego = new Juego("Zelda", "Aventura", "PEGI-12");
        EdicionJuego edicionJuego = new EdicionJuego("Nintendo", 59.90, 10);
        HashMap<Juego, EdicionJuego> articulo = new HashMap<>();
        articulo.put(juego, edicionJuego);
        ArrayList<HashMap<Juego, EdicionJuego>> listaCompra = new ArrayList<>();
        listaCompra.add(articulo);
        Venta venta = new Venta(cliente, listaCompra, "01/01/2025");
        historialVentas.add(venta);
    }

    @Test
    public void getCliente() {
        String nombre = "Kenneth";
        String dni = "12345678A";
        String telefono = "666123123";
        String email = "kenneth@email.com";
        assertEquals(nombre, historialVentas.getLast().getCliente().getNombre());
        assertEquals(dni, historialVentas.getLast().getCliente().getDni());
        assertEquals(telefono, historialVentas.getLast().getCliente().getTelefono());
        assertEquals(email, historialVentas.getLast().getCliente().getEmail());
    }

    @Test
    public void getArticulosVenta() {
        String nombre = "Zelda";
        String genero = "Aventura";
        String pegi = "PEGI-12";
        String consola = "Nintendo";
        double precio = 59.90;
        int stock = 10;
        for (Juego juego : historialVentas.getLast().getArticulosVenta().getLast().keySet()) {
            assertEquals(nombre, juego.getNombre());
            assertEquals(genero, juego.getGenero());
            assertEquals(pegi, juego.getPegi());
        }
        for (EdicionJuego edicion : historialVentas.getLast().getArticulosVenta().getLast().values()) {
            assertEquals(consola, edicion.getConsola());
            assertEquals(precio, edicion.getPrecio());
            assertEquals(stock, edicion.getStock());
        }
    }

    @Test
    public void getFecha() {
        String fecha = "01/01/2025";
        assertEquals(fecha, historialVentas.getLast().getFecha());
    }

    @Test
    public void getImporteVenta() {
        double precio = 59.90;
        assertEquals(precio, historialVentas.getLast().getImporteVenta());
    }

    @Test
    public void resumentVenta() {
        String mensaje = """
                Cliente: Kenneth - 12345678A
                Fecha de la venta: 01/01/2025
                 - Zelda - Nintendo - 59,90€
                Importe total: 59,90€""";
        assertEquals(mensaje, historialVentas.getLast().resumenVenta());
    }

    @Test
    public void anadirArticulo() {
        Juego juego = new Juego("FIFA", "Deporte", "PEGI-3");
        EdicionJuego edicion = new EdicionJuego("PC", 39.90, 50);
        Cliente cliente = new Cliente("Kenneth", "12345678A", "666123123", "kenneth@email.com");
        ArrayList<HashMap<Juego, EdicionJuego>> listaCompra = new ArrayList<>();
        Venta venta = new Venta(cliente, listaCompra, "01/01/2025");
        venta.anadirArticulo(juego, edicion);
        Juego juegoEnListaCompra = null;
        for (Juego j : venta.getArticulosVenta().getLast().keySet()) {
            juegoEnListaCompra = j;
        }
        assertEquals(juego, juegoEnListaCompra);
        assertEquals(edicion, venta.getArticulosVenta().getLast().get(juego));
    }

}
