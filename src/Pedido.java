import java.util.ArrayList;

public class Pedido {
    private int id;
    private ArrayList<LineaPedido> detalles;

    public Pedido() {
        this.detalles = new ArrayList<>();
    }

    public double obtenerTotal() {
        double total = 0;
        for (LineaPedido linea : detalles) {
            total += linea.calcularSubtotal();
        }
        return total;
    }

    public void agregarLinea(Producto producto, int cantidad) {
        LineaPedido detalle = new LineaPedido(producto, cantidad, producto.getPrecio());
        detalles.add(detalle);
    }

    public ArrayList<LineaPedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<LineaPedido> detalles) {
        this.detalles = detalles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
