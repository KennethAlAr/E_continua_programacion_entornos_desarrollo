
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Representa un juego del catálogo de juegos.
 * @author Kenneth Alonso
 * @version 1.0
 */
public class Juego {

    private String nombre;
    private String genero;
    private String pegi;
    private ArrayList<EdicionJuego> listaEdicionJuego;

    //Constructor
    /**
     * Constructor de la clase Juego.
     * @param nombre Nombre del juego.
     * @param genero Género del juego.
     * @param pegi Calificación PEGI del juego.
     */
    public Juego (String nombre, String genero, String pegi) {
        this.nombre = nombre;
        this.genero = genero;
        this.pegi = pegi;
        this.listaEdicionJuego = new ArrayList<>();
    }

    //Getters

    /**
     * Devuelve el nombre del juego.
     * @return Nombre del juego.
     */
    public String getNombre() {return this.nombre;}

    /**
     * Devuelve el género del juego.
     * @return Género del juego.
     */
    public String getGenero() {return this.genero;}

    /**
     * Devuelve la calificación PEGI del juego.
     * @return Calificación PEGI del juego.
     */
    public String getPegi() {return this.pegi;}

    /**
     * Devuelve una lista con las ediciones del juego.
     * @return Lista con ediciones del juego.
     */
    public ArrayList<EdicionJuego> getEdicionJuego() {return this.listaEdicionJuego;}

    /**
     * Devuelve el número de sistemas disponibles para el juego.
     * @return Número de sistemas disponibles.
     */
    public int getNumeroSistemas() {return listaEdicionJuego.size();}

    /**
     * Método para obtener el nombre de todos los sistemas para los que está disponible un juego.
     * @return ArrayList de String con el nombre de todos los sistemas disponibles para el juego.
     */
    public ArrayList<String> getConsolas() {
        ArrayList<String> consolas = new ArrayList<>();
        for (EdicionJuego edicion : listaEdicionJuego) {
            consolas.add(edicion.getConsola());
        }
        return consolas;
    }

    /**
     * Método para obtener el precio de un juego para un sistema.
     * @param consola Nombre del sistema del cual se quiere obtener el precio del juego.
     * @return double con el precio del juego para el sistema.
     */
    public double getPrecio(String consola) {
        double precio = seleccionarEdicion(consola).getPrecio();
        return precio;
    }

    /**
     * Método para obtener el stock de un juego para un sistema.
     * @param consola Nombre del sistema del cual se quiere obtener el precio del juego.
     * @return int con el número de unidades en stock del juego para el sistema.
     */
    public int getStock(String consola) {
        int stock = seleccionarEdicion(consola).getStock();
        return stock;
    }

    /**
     * Método para obtener un listado de todos los sistemas disponibles para un juego junto a su precio y su stock.
     * @return String con un listado de los sistemas disponibles junto al precio y el stock o, en caso de no tener sistemas disponibles, mensaje de error.
     */
    public String getSistemas() {
        String sistemas = "";
        if (!listaEdicionJuego.isEmpty()) {
            for (EdicionJuego edicion : listaEdicionJuego)
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
        for (EdicionJuego edicionJuego : listaEdicionJuego) {
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
        for (EdicionJuego edicionJuego : listaEdicionJuego) {
            stock += edicionJuego.getStock();
        }
        return (stock != 0);
    }

    /**
     * Método para seleccionar una edición de juego según el nombre del sistema.
     * @param consola nombre del sistema que se quiere seleccionar.
     * @return EdicionJuego seleccionada según el nombre del sistema.
     */
    public EdicionJuego seleccionarEdicion(String consola) {
        for (EdicionJuego edicion : listaEdicionJuego) {
            if (edicion.getConsola().equals(consola)) {
                return edicion;
            }
        }
        throw new NoSuchElementException("No existe el juego para ese sistema en la base de datos.");
    }

    /**
     * Método para saber si la lista de ediciones de juego contiene la consola solicitada.
     * @param consola Nombre de la consola solicitada
     * @return True en caso de existir y false en caso de no existir.
     */
    public boolean existeConsola(String consola) {
        for (EdicionJuego edicion : listaEdicionJuego) {
            if (edicion.getConsola().equals(consola)) {
                return true;
            }
        }
        return false;
    }

    //Setters
    /**
     * Método para añadir una edición de sistema a un juego.
     * @param edicion EdicionJuego que se quiere añadir al juego.
     */
    public void anadirEdicion (EdicionJuego edicion) {
        this.listaEdicionJuego.add(edicion);
    }

    /**
     * Método para eliminar una edición de sistema para un juego.
     * @param consola Nombre del sistema que se quiere eliminar.
     */
    public void eliminarEdicion(String consola) {
        EdicionJuego edicionEliminar = seleccionarEdicion(consola);
        this.listaEdicionJuego.remove(edicionEliminar);
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
     * @param pegi Nueva calificación PEGI del juego.
     * @return Mensaje de éxito
     */
    public String modificarPegi(String pegi) {
        this.pegi = pegi;
        return "Calificación PEGI modificada a '" + pegi + "'.";
    }

}