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
    public HashMap<String, EdicionJuego> getEdicionJuego() {return this.edicionJuego;}

    public ArrayList<String> getConsolas() {
        ArrayList<String> consolas = new ArrayList<>(edicionJuego.keySet());
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

    public String getSistemas() {
        String sistemas = "";
        if (edicionJuego.size() > 0) {
            for (EdicionJuego edicion : edicionJuego.values())
                sistemas += edicion.getConsola() + " - " + String.format("%.2f", edicion.getPrecio()) + "€ - " + edicion.getStock() + "ud.\n";
        } else {
            return "Este juego no tiene sistemas disponibles.";
        }
        return sistemas;
    }

    public int getNumeroSistemas() {return edicionJuego.size();}

    public void anadirEdicion (String consola, EdicionJuego edicion) {
        this.edicionJuego.put(consola, edicion);
    }

    public void eliminarEdicion(String consola) {
        this.edicionJuego.remove(consola);
    }

    public String modificarNombre(String nombre) {
        this.nombre = nombre;
        return "Nombre modificado a '" + nombre + "'.";
    }

    public String modificarGenero(String genero) {
        this.genero = genero;
        return "Género modificado a '" + genero + "'.";
    }

    public String modificarPegi(String pegi) {
        this.pegi = pegi;
        return "Calificación PEGI modificada a '" + pegi + "'.";
    }

}