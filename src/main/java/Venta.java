
import java.util.HashMap;
import java.util.ArrayList;

public class Venta {

    private Cliente cliente;
    private ArrayList<HashMap<Juego, EdicionJuego>> articulosVenta;
    private String fecha;

    public Venta (Cliente cliente, ArrayList<HashMap<Juego, EdicionJuego>> articulosVenta, String fecha) {
        this.cliente = cliente;
        this.articulosVenta = articulosVenta;
        this.fecha = fecha;
    }

    //Getters
    public Cliente getCliente() {return this.cliente;}
    public ArrayList<HashMap<Juego, EdicionJuego>> getArticulosVenta() {return this.articulosVenta;}
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