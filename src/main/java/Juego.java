import java.util.ArrayList;
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
    public ArrayList<String> getConsolas() {
        ArrayList<String> consolas = new ArrayList<>();
        for (String consola : edicionJuego.keySet())
            consolas.add(consola);
        return consolas;
    }
    public double getPrecio(String consola) {
        double precio = this.edicionJuego.get(consola).getPrecio();
        return precio;
    }
    public int getStock(String consola) {
        int stock = this.edicionJuego.get(consola).getStock();
        return stock;
    }

    public void anadirEdicion (String consola, EdicionJuego edicion) {
        this.edicionJuego.put(consola, edicion);
    }

}