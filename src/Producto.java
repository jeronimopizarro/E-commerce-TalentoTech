public class Producto {
    private static int contadorId = 0;
    private int id;
    private String nombre;
    private Double precio;
    private int stock;

    public Producto(String nombre, Double precio, int stock) {
        this.id = ++contadorId;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Producto.contadorId = contadorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | Precio: $%.2f | Stock: %d", id, nombre, precio, stock);
    }

}
