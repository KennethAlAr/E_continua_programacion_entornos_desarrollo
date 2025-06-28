
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Representa una venta del historial de ventas.
 * @author Kenneth Alonso
 * @version 1.0
 */
public class Venta {

    private Cliente cliente;
    private ArrayList<HashMap<Juego, EdicionJuego>> articulosVenta;
    private String fecha;

    //Constructor
    /**
     * Constructor de la clase Venta.
     * @param cliente Cliente al cual se le hace la venta.
     * @param articulosVenta Lista de articulos que componen la venta.
     * @param fecha Fecha en la cual se produce la venta.
     */
    public Venta (Cliente cliente, ArrayList<HashMap<Juego, EdicionJuego>> articulosVenta, String fecha) {
        this.cliente = cliente;
        this.articulosVenta = articulosVenta;
        this.fecha = fecha;
    }

    //Getters
    /**
     * Devuelve el Cliente de la venta.
     * @return Cliente de la venta.
     */
    public Cliente getCliente() {return this.cliente;}

    /**
     * Devuelve la lista de artículos (Juego : EdicionJuego) de la venta.
     * @return Lista de artículos de la venta.
     */
    public ArrayList<HashMap<Juego, EdicionJuego>> getArticulosVenta() {return this.articulosVenta;}

    /**
     * Devuelve la fecha de la venta.
     * @return Fecha de la venta.
     */
    public String getFecha() {return this.fecha;}

    /**
     * Método para obtener el valor total de los artículos de una venta.
     * @return double con el valor total de la venta.
     */
    public double getImporteVenta() {
        double importe = 0;
        for (HashMap<Juego, EdicionJuego> articulo : articulosVenta) {
            for (EdicionJuego edicionJuego : articulo.values()) {
                importe += edicionJuego.getPrecio();
            }
        }
        return importe;
    }

    /**
     * Método para obtener el resumen de una venta donde se indica el cliente, la fecha y los artículos de la venta.
     * @return Mensaje con el resumen de la venta.
     */
    public String resumenVenta() {
        String mensaje = "Cliente: " + cliente.getNombre() + " - " + cliente.getDni() +
                "\nFecha de la venta: " + fecha + "\n";
        for (HashMap<Juego, EdicionJuego> articulo : articulosVenta) {
                for (Juego juego : articulo.keySet()) {
                    mensaje += " - " + juego.getNombre() + " - " + articulo.get(juego).getConsola() + " - " + String.format("%.2f" , articulo.get(juego).getPrecio()) + "€\n";
            }
        }
        mensaje += "Importe total: " + String.format("%.2f" , getImporteVenta()) + "€";
        return mensaje;
    }

    //Setters
    /**
     * Método para añadir una EdicionJuego de un Juego a la lista de la compra.
     * @param juego Juego que se quiere añadir a la lista de la compra.
     * @param edicionJuego EdicionJuego que se quiere añadir a la lista de la compra.
     */
    public void anadirArticulo(Juego juego, EdicionJuego edicionJuego) {
        HashMap<Juego, EdicionJuego> articulo = new HashMap<>();
        articulo.put(juego, edicionJuego);
        articulosVenta.add(articulo);
    }

}