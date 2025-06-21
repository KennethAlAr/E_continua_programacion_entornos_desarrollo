
public class EdicionJuego {

    private String consola;
    private double precio;
    private int stock;

    public EdicionJuego (String consola, double precio, int stock) {
        this.consola = consola;
        this.precio = precio;
        this.stock = stock;
    }

    public String getConsola() {return this.consola;}
    public double getPrecio() {return this.precio;}
    public int getStock() {return this.stock;}

    public void modificarPrecio(double nuevoPrecio) {this.precio = nuevoPrecio;}
    public void modificarStock(int nuevoStock) {this.stock = nuevoStock;}

}
