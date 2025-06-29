
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GestorJuegosTest {

    private ArrayList<Juego> catalogoJuegos;

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
        juego.anadirEdicion(edicion);
        EdicionJuego edicion2 = new EdicionJuego(consola2, precio2, stock2);
        juego.anadirEdicion(edicion2);
        catalogoJuegos.add(juego);
    }

    @Test
    public void stringMenuPrincipalJuegos() {
        String menu = """
                ### GESTIÓN DE INVENTARIO ###
                
                1. Añadir juego al catálogo
                2. Eliminar juego del catálogo
                3. Modificar juego
                4. Buscar juego por consola
                5. Listado de juegos (Orden Alfabético)
                6. Listado de juegos (Orden por stock)
                7. Salir
                
                Elige una opción:""";
        assertEquals(menu, GestorJuegos.stringMenuPrincipalJuegos());
    }

    @Test
    public void anadirJuego() {
        String nombre = "Tekken";
        String genero = "Lucha";
        String pegi = "PEGI-16";
        GestorJuegos.anadirJuego(nombre, genero, pegi, catalogoJuegos);
        assertEquals("Tekken" , catalogoJuegos.getLast().getNombre());
        assertEquals("Lucha", catalogoJuegos.getLast().getGenero());
        assertEquals("PEGI-16", catalogoJuegos.getLast().getPegi());
    }

    @Test
    public void eliminarJuego() {
        String nombre = "Zelda";
        assertEquals("Juego 'Zelda' eliminado del catálogo de juegos.", GestorJuegos.eliminarJuego(nombre, catalogoJuegos));
    }

    @Test
    public void eliminarJuegoNoExistente() {
        String nombre = "Tekken";
        assertThrows(NoSuchElementException.class, () -> {
            GestorJuegos.eliminarJuego(nombre, catalogoJuegos);
        });
    }

    @Test
    public void listarPorConsola() {
        String consola = "Nintendo";
        assertEquals("Zelda - Aventura - PEGI-12 - 59,90€ - 100ud.\n", GestorJuegos.listarPorConsola(consola, catalogoJuegos));
    }

    @Test
    public void listarPorConsolaSinJuegos() {
        String consola = "Play Station";
        assertEquals("No hay juegos disponibles para este sistema.", GestorJuegos.listarPorConsola(consola, catalogoJuegos));
    }

    @Test
    public void listarJuegosAlfabetico() {
        String  lista = "Zelda - Aventura - PEGI-12\nPC - 29,90€ - 200ud.\nNintendo - 59,90€ - 100ud.\n----------\n";
        String  lista2 = "Zelda - Aventura - PEGI-12\nNintendo - 59,90€ - 100ud.\nPC - 29,90€ - 200ud.\n----------\n";
        assertTrue(lista.equals(GestorJuegos.listarJuegosAlfabetico(catalogoJuegos))
                || lista2.equals(GestorJuegos.listarJuegosAlfabetico(catalogoJuegos)));
    }

    @Test
    public void listarJuegosAlfabeticoSinJuegos() {
        catalogoJuegos.clear();
        assertEquals("No hay juegos en el catálogo.", GestorJuegos.listarJuegosAlfabetico(catalogoJuegos));
    }

    @Test
    public void listarJuegosPorStock() {
        String lista = "100ud - Zelda - Nintendo\n200ud - Zelda - PC\n";
        assertEquals(lista, GestorJuegos.listarJuegosPorStock(catalogoJuegos));
    }

    @Test
    public void listarJuegosPorStockSinJuegos() {
        catalogoJuegos.clear();
        assertEquals("No hay juegos en el catálogo.", GestorJuegos.listarJuegosAlfabetico(catalogoJuegos));
    }

    @Test
    public void juegoExiste() {
        String nombre = "Zelda";
        assertTrue(GestorJuegos.juegoExiste(nombre, catalogoJuegos));
    }

    @Test
    public void juegoNoExiste() {
        String nombre = "Tekken";
        assertFalse(GestorJuegos.juegoExiste(nombre, catalogoJuegos));
    }

    @Test
    public void seleccionarJuego() {
        String nombre = "Zelda";
        assertEquals("Zelda", GestorJuegos.seleccionarJuego(nombre, catalogoJuegos).getNombre());
        assertEquals("Aventura", GestorJuegos.seleccionarJuego(nombre, catalogoJuegos).getGenero());
        assertEquals("PEGI-12", GestorJuegos.seleccionarJuego(nombre, catalogoJuegos).getPegi());
    }

    @Test
    public void seleccionarJuegoNoExiste() {
        String nombre = "Tekken";
        assertThrows(NoSuchElementException.class, () -> {GestorJuegos.seleccionarJuego(nombre, catalogoJuegos);});
    }

    @AfterEach
    public void limpiarCatalogo() {catalogoJuegos.clear();}

}
