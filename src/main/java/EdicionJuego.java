
public class EdicionJuego {

    private String consola;
    private double precio;
    private int stock;

    //Constructor
    public EdicionJuego (String consola, double precio, int stock) {
        this.consola = consola;
        this.precio = precio;
        this.stock = stock;
    }

    //Getters
    public String getConsola() {return this.consola;}
    public double getPrecio() {return this.precio;}
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

}
