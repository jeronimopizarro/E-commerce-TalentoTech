public class LineaPedido {
    private Producto producto;
    private int cantidad;
    private double precio;

    public LineaPedido(Producto producto, int cantidad, double precio) {
        this.precio = precio;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public double calcularSubtotal() {
        return cantidad * precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
