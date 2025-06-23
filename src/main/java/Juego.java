import java.util.ArrayList;
import java.util.HashMap;

public class Juego {

    private String nombre;
    private String genero;
    private String pegi;
    private HashMap<String, EdicionJuego> edicionJuego;

    //Constructor
    public Juego (String nombre, String genero, String pegi) {
        this.nombre = nombre;
        this.genero = genero;
        this.pegi = pegi;
        this.edicionJuego = new HashMap<>();
    }

    //Getters
    public String getNombre() {return this.nombre;}
    public String getGenero() {return this.genero;}
    public String getPegi() {return this.pegi;}
    public HashMap<String, EdicionJuego> getEdicionJuego() {return this.edicionJuego;}
    public int getNumeroSistemas() {return edicionJuego.size();}

    /**
     * Método para obtener el nombre de todos los sistemas para los que está disponible un juego.
     * @return ArrayList de String con el nombre de todos los sistemas disponibles para el juego.
     */
    public ArrayList<String> getConsolas() {
        ArrayList<String> consolas = new ArrayList<>(edicionJuego.keySet());
        return consolas;
    }

    /**
     * Método para obtener el precio de un juego para un sistema.
     * @param consola Nombre del sistema del cual se quiere obtener el precio del juego.
     * @return double con el precio del juego para el sistema.
     */
    public double getPrecio(String consola) {
        double precio = this.edicionJuego.get(consola).getPrecio();
        return precio;
    }

    /**
     * Método para obtener el stock de un juego para un sistema.
     * @param consola Nombre del sistema del cual se quiere obtener el precio del juego.
     * @return int con el número de unidades en stock del juego para el sistema.
     */
    public int getStock(String consola) {
        int stock = this.edicionJuego.get(consola).getStock();
        return stock;
    }

    /**
     * Método para obtener un listado de todos los sistemas disponibles para un juego junto a su precio y su stock.
     * @return String con un listado de los sistemas disponibles junto al precio y el stock o, en caso de no tener sistemas disponibles, mensaje de error.
     */
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

    /**
     * Método que devuelve un ArrayList con los sistemas disponibles que tienen stock.
     * @return Lista de EdicionJuego con los sistemas disponibles que tienen stock.
     */
    public ArrayList<EdicionJuego> getSistemasConStock() {
        ArrayList<EdicionJuego> sistemasConStock = new ArrayList<>();
        for (EdicionJuego edicionJuego : edicionJuego.values()) {
            if(edicionJuego.getStock() > 0) {
                sistemasConStock.add(edicionJuego);
            }
        }
        return sistemasConStock;
    }

    /**
     * Método para saber si un juego tiene stock en cualquier sistema.
     * @return True si tiene stock o false si no tiene.
     */
    public boolean tieneStock() {
        int stock = 0;
        for (EdicionJuego edicionJuego : edicionJuego.values()) {
            stock += edicionJuego.getStock();
        }
        if (stock == 0) {
            return false;
        } else {
            return true;
        }
    }

    //Setters

    /**
     * Método para añadir una edición de sistema a un juego.
     * @param consola Nombre del sistema que se quiere añadir.
     * @param edicion EdicionJuego que se quiere añadir al juego.
     */
    public void anadirEdicion (String consola, EdicionJuego edicion) {
        this.edicionJuego.put(consola, edicion);
    }

    /**
     * Método para eliminar una edición de sistema para un juego.
     * @param consola Nombre del sistema que se quiere eliminar.
     */
    public void eliminarEdicion(String consola) {
        this.edicionJuego.remove(consola);
    }

    /**
     * Método para cambiar el nombre de un juego.
     * @param nombre Nuevo nombre del juego.
     * @return Mensaje de éxito.
     */
    public String modificarNombre(String nombre) {
        this.nombre = nombre;
        return "Nombre modificado a '" + nombre + "'.";
    }

    /**
     * Método para cambiar el género de un juego.
     * @param genero Nuevo nombre del juego.
     * @return Mensaje de éxito
     */
    public String modificarGenero(String genero) {
        this.genero = genero;
        return "Género modificado a '" + genero + "'.";
    }

    /**
     * Método para modificar la calificación PEGI de un juego.
     * @param pegi
     * @return Mensaje de éxito
     */
    public String modificarPegi(String pegi) {
        this.pegi = pegi;
        return "Calificación PEGI modificada a '" + pegi + "'.";
    }

}