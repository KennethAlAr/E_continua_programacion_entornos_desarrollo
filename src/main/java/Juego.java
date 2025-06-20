import java.util.HashMap;

public class Juego {

    private String nombre;
    private String genero;
    private String pegi;
    private HashMap<String, EdicionJuego> edicionJuego;

    public Juego (String nombre, String genero, String pegi) {
        this.nombre = nombre;
        this.genero = genero;
        this.pegi = pegi;
        this.edicionJuego = new HashMap<>();
    }

    public String getNombre() {return this.nombre;}
    public String getGenero() {return this.genero;}
    public String getPegi() {return this.pegi;}

    public void anadirEdicion (String consola, EdicionJuego edicion) {
        this.edicionJuego.put(consola, edicion);
    }

}