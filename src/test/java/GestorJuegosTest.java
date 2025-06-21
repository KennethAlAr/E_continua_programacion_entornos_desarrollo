
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GestorJuegosTest {

    static ArrayList<Juego> catalogoJuegos;

    @BeforeEach
    public void configuracionInicial() {
        catalogoJuegos = new ArrayList<>();
        String nombre = "Zelda";
        String genero = "Aventura";
        String pegi = "PEGI-12";
        String consola = "Nintendo";
        double precio = 59.90;
        int stock = 100;
        String consola2 = "PC";
        double precio2 = 29.90;
        int stock2 = 200;
        Juego juego = new Juego(nombre, genero, pegi);
        EdicionJuego edicion = new EdicionJuego(consola, precio, stock);
        juego.anadirEdicion("Nintendo", edicion);
        EdicionJuego edicion2 = new EdicionJuego(consola2, precio2, stock2);
        juego.anadirEdicion("PC", edicion2);
        catalogoJuegos.add(juego);
    }

    @Test
    public void menuJuegos() {
        String menu = "### GESTIÓN DE INVENTARIO ###\n\n" +
                "1. Añadir juego al catálogo\n" +
                "2. Eliminar juego del catálogo\n" +
                "3. Modificar juego\n" +
                "4. Buscar juego por consola\n" +
                "5. Listado de juegos (Orden Alfabético)\n" +
                "6. Listado de juegos (Orden por stock)\n" +
                "7. Salir\n\n" +
                "Elige una opción:";
        assertEquals(menu, GestorJuegos.menuJuegos());
    }

    @Test
    public void eliminarJuego() {
        String nombre = "Zelda";
        assertEquals("Juego 'Zelda' eliminado del catálogo de juegos.", GestorJuegos.eliminarJuego(nombre, catalogoJuegos));
    }

    @Test
    public void eliminarJuegoNoExistente() {
        String nombre = "Tekken";
        assertEquals("El juego 'Tekken' no se encuentra en el catálogo de juegos.", GestorJuegos.eliminarJuego(nombre, catalogoJuegos));
    }

    @AfterEach
    public void limpiarCatalogo() {catalogoJuegos.clear();}

}
