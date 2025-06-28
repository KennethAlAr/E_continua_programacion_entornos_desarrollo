
/**
 * Representa una edición de un juego del catálogo de juegos.
 * @author Kenneth Alonso
 * @version 1.0
 */
public class EdicionJuego {

    private String consola;
    private double precio;
    private int stock;

    //Constructor
    /**
     * Constructor de la clase EdicionJuego.
     * @param consola Nombre de la consola de la edición del juego.
     * @param precio Precio de la edición del juego.
     * @param stock Stock de la edición del juego.
     */
    public EdicionJuego (String consola, double precio, int stock) {
        this.consola = consola;
        this.precio = precio;
        this.stock = stock;
    }

    //Getters
    /**
     * Devuelve el nombre de la consola de la edición del juego.
     * @return Nombre de la consola de la edición del juego.
     */
    public String getConsola() {return this.consola;}

    /**
     * Devuelve el precio de la edición del juego.
     * @return Precio de la edición del juego.
     */
    public double getPrecio() {return this.precio;}

    /**
     * Devuelve el stock de la edición del juego.
     * @return Stock de la edición del juego.
     */
    public int getStock() {return this.stock;}

    //Setters
    /**
     * Método para modificar el precio de un juego para un sistema.
     * @param nuevoPrecio Nuevo precio para el juego en el sistema.
     */
    public void modificarPrecio(double nuevoPrecio) {this.precio = nuevoPrecio;}

    /**
     * Método para modificar el stock de un juego para un sistema.
     * @param nuevoStock Nuevo stock para el juego en el sistema.
     */
    public void modificarStock(int nuevoStock) {this.stock = nuevoStock;}

    /**
     * Método para reducir el stock de un juego en un sistema en 1.
     */
    public void reducirStock() {this.stock -= 1;}

    /**
     * Método para aumentar el stock de un juego en un sistema en 1.
     */
    public void aumentarStock() {this.stock += 1;}
}
