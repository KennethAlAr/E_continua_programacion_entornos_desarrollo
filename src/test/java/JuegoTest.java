
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
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
        juego.anadirEdicion(edicion);
        String consola2 = "PC";
        double precio2 = 39.90;
        int stock2 = 20;
        edicion2 = new EdicionJuego(consola2, precio2, stock2);
        juego.anadirEdicion(edicion2);
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
        ArrayList<EdicionJuego> arrayListEsperado = new ArrayList<>();
        arrayListEsperado.add(edicion);
        arrayListEsperado.add(edicion2);
        assertEquals(arrayListEsperado, juego.getEdicionJuego());
    }

    @Test
    public void getConsolas() {
        ArrayList<String> consolas = new ArrayList<>();
        consolas.add("Nintendo");
        consolas.add("PC");
        assertEquals(consolas, juego.getConsolas());
    }

    @Test
    public void getPrecio() {
        String consola = "Nintendo";
        assertEquals(59.90 ,juego.getPrecio(consola));
    }

    @Test
    public void getStock() {
        String consola = "Nintendo";
        assertEquals(10, juego.getStock(consola));
    }

    @Test
    public void getSistemas() {
        String sistemas = "PC - 39,90€ - 20ud.\nNintendo - 59,90€ - 10ud.\n";
        String sistemas2 = "Nintendo - 59,90€ - 10ud.\nPC - 39,90€ - 20ud.\n";
        assertTrue(sistemas.equals(juego.getSistemas())
                || sistemas2.equals(juego.getSistemas()));
    }

    @Test
    public void getSistemasSinSistemas() {
        String sistemas = "Este juego no tiene sistemas disponibles.";
        juego.eliminarEdicion("Nintendo");
        juego.eliminarEdicion("PC");
        assertEquals(sistemas, juego.getSistemas());
    }

    @Test
    public void getNumeroSistemas() {
        int numero = 2;
        assertEquals(numero, juego.getNumeroSistemas());
    }

    @Test
    public void anadirEdicion () {
        String consola = "XBOX";
        EdicionJuego edicionJuego = new EdicionJuego(consola, 49.90, 30);
        juego.anadirEdicion(edicionJuego);
        String sistemas = "Nintendo - 59,90€ - 10ud.\nPC - 39,90€ - 20ud.\nXBOX - 49,90€ - 30ud.\n";
        assertEquals(3, juego.getNumeroSistemas());
        assertEquals(sistemas, juego.getSistemas());
    }

    @Test
    public void eliminarEdicion() {
        String consola = "Nintendo";
        juego.eliminarEdicion(consola);
        String sistemas = "PC - 39,90€ - 20ud.\n";
        assertEquals(1, juego.getNumeroSistemas());
        assertEquals(sistemas, juego.getSistemas());
    }

    @Test
    public void modificarNombre() {
        String nuevoNombre = "The Legend of Zelda";
        assertEquals("Nombre modificado a 'The Legend of Zelda'.", juego.modificarNombre(nuevoNombre));
    }

    @Test
    public void modificarGenero() {
        String nuevoGenero = "Acción/Aventura";
        assertEquals("Género modificado a 'Acción/Aventura'.", juego.modificarGenero(nuevoGenero));
    }

    @Test
    public void modificarPegi() {
        String nuevoPegi = "PEGI-3";
        assertEquals("Calificación PEGI modificada a 'PEGI-3'.", juego.modificarPegi(nuevoPegi));
    }

    @Test
    public void getSistemasConStock() {
        edicion2.modificarStock(0);
        ArrayList<EdicionJuego> sistemasConStock = new ArrayList<>();
        sistemasConStock.add(edicion);
        assertEquals(sistemasConStock, juego.getSistemasConStock());
    }

    @Test
    public void tieneStock() {
        assertTrue(juego.tieneStock());
    }

    @Test
    public void noTieneStock() {
        edicion.modificarStock(0);
        edicion2.modificarStock(0);
        assertFalse(juego.tieneStock());
    }

    @Test
    public void seleccionarEdicion() {
        String consola = "Nintendo";
        assertEquals(edicion, juego.seleccionarEdicion(consola));
    }

    @Test
    public void seleccionarEdicionSinEdiciones() {
        String consola = "XBOX";
        assertThrows(NoSuchElementException.class, () -> {
            juego.seleccionarEdicion(consola);
        });
    }

    @Test
    public void existeConsola() {
        String consola = "Nintendo";
        assertTrue(juego.existeConsola(consola));
    }

    @Test
    public void noExisteConsola() {
        String consola = "XBOX";
        assertFalse(juego.existeConsola(consola));
    }

}
