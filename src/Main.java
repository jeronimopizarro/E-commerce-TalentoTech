import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Aplicacion en consola, e-commerce, crear, obtener, modificar y eliminar productos.
        int opcionElegida = 0;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Producto> productos = new ArrayList<>();
        final int SALIR = 7;

        do {
            opcionElegida = ElegirOpcionMenuPrincipal(scanner);
            switch (opcionElegida) {
                case 1:
                    crearProducto(scanner, productos);
                    break;
                case 2:
                    obtenerProductos(productos);
                    break;
                case 3: //Buscar/Actualizar producto
                    buscarPorNombre(scanner, productos);
                    break;
                case 4: //Eliminar producto
                    break;
                case 5: //Crear un pedido
                    break;
                case 6: //Listar pedidos
                    break;

            }
        } while (opcionElegida != SALIR);

    }

    private static void buscarPorNombre(Scanner scanner, ArrayList<Producto> productos) {
        System.out.println("Ingrese el nombre del producto: ");
        String busqueda = scanner.next();
        for (Producto producto : productos) {
            if (producto.getNombre().toLowerCase().contains(busqueda.toLowerCase())) {
                System.out.println(producto);
            }
        }
    }

    private static void crearProducto(Scanner scanner, ArrayList<Producto> productos) {
        System.out.println("Ingrese el nombre del producto");
        String nombre = scanner.next();
        System.out.println("Ingrese el precio del producto");
        double precio = scanner.nextDouble();
        System.out.println("Ingrese el stock del producto");
        int stock = scanner.nextInt();
        Producto producto = new Producto(nombre, precio, stock);
        productos.add(producto);
        System.out.println("Producto agregado correctamente");
    }

    private static void obtenerProductos(ArrayList<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados");
        } else {
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }

    private static int ElegirOpcionMenuPrincipal(Scanner scanner) {
        int opcionElegida;
        System.out.println("Elija la opcion que desea ejecutar:");
        System.out.println("1. Agregar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar/Actualizar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Crear pedido");
        System.out.println("6. listar pedidos");
        System.out.println("7. Salir");
        opcionElegida = scanner.nextInt();
        return opcionElegida;
    }
}
