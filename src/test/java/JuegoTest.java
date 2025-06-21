
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JuegoTest {

    private Juego juego;
    private EdicionJuego edicion;
    private EdicionJuego edicion2;

    @BeforeEach
    public void configuracionInicial() {
        String nombre = "Zelda";
        String genero = "Aventura";
        String pegi = "PEGI-12";
        juego = new Juego(nombre, genero, pegi);
        String consola = "Nintendo";
        double precio = 59.90;
        int stock = 10;
        edicion = new EdicionJuego(consola, precio, stock);
        juego.anadirEdicion(consola, edicion);
        String consola2 = "PC";
        double precio2 = 39.90;
        int stock2 = 20;
        edicion2 = new EdicionJuego(consola2, precio2, stock2);
        juego.anadirEdicion(consola2, edicion2);
    }

    @Test
    public void getNombre() {
        assertEquals("Zelda", juego.getNombre());
    }

    @Test
    public void getGenero() {
        assertEquals("Aventura", juego.getGenero());
    }

    @Test
    public void getPegi() {
        assertEquals("PEGI-12", juego.getPegi());
    }

    @Test
    public void getEdicionJuego() {
        String consola = "Nintendo";
        String consola2 = "PC";
        HashMap<String, EdicionJuego> HashMapEsperado = new HashMap<>();
        HashMapEsperado.put(consola, edicion);
        HashMapEsperado.put(consola2, edicion2);
        assertEquals(HashMapEsperado, juego.getEdicionJuego());
    }

    @Test
    public void getConsolas() {
        ArrayList<String> consolas = new ArrayList<>();
        consolas.add("PC");
        consolas.add("Nintendo");
        assertEquals(consolas, juego.getConsolas());
    }



}
