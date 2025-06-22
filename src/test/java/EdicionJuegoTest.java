
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EdicionJuegoTest {

    private EdicionJuego edicionJuego;

    @BeforeEach
    public void configuracionInicial() {
        String consola = "Nintendo";
        double precio = 39.90;
        int stock = 10;
        edicionJuego = new EdicionJuego(consola, precio, stock);
    }

    @Test
    public void getConsola() {
        String consola = "Nintendo";
        assertEquals(consola, edicionJuego.getConsola());
    }

    @Test
    public void getPrecio() {
        double precio = 39.90;
        assertEquals(precio, edicionJuego.getPrecio());
    }

    @Test
    public void getStock(){
        int stock = 10;
        assertEquals(stock, edicionJuego.getStock());
    }

    @Test
    public void modificarPrecio() {
        double nuevoPrecio = 59.90;
        edicionJuego.modificarPrecio(nuevoPrecio);
        assertEquals(nuevoPrecio, edicionJuego.getPrecio());
    }

    @Test
    public void modificarStock() {
        int nuevoStock = 20;
        edicionJuego.modificarStock(nuevoStock);
        assertEquals(nuevoStock, edicionJuego.getStock());
    }

}
