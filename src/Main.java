import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                    listarProductos(productos);
                    break;
                case 3:
                    ArrayList<Producto> productosEncontrados = filtrarProductosPorNombre(scanner, productos);
                    if (productosEncontrados == null || productosEncontrados.isEmpty()) {
                        System.out.println("No se encontraron productos con ese nombre.");
                        break;
                    }

                    scanner.nextLine(); //Esto esta raro, tengo que limpiar el buffer (chatgpt)

                    verificarModificacion(scanner, productosEncontrados); //Se le pregunta al usuario si de verdad quiere eliminarlo
                    break;
                case 4:
                    Producto productoAEliminar = obtenerProductoPorId(scanner, productos);
                    verificarEliminacion(productoAEliminar, scanner, productos);
                    break;
                case 5: //Crear un pedido
                    crearPedido(productos, scanner);
                    break;
                case 6: //Listar pedidos
                    break;

            }
        } while (opcionElegida != SALIR);

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

    private static void listarProductos(ArrayList<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados");
        } else {
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }

    private static ArrayList<Producto> filtrarProductosPorNombre(Scanner scanner, ArrayList<Producto> productos) {
        return obtenerProductosPorNombre(scanner, productos);
    }

    private static ArrayList<Producto> obtenerProductosPorNombre(Scanner scanner, ArrayList<Producto> productos) {
        System.out.println("Ingrese el nombre del producto: ");
        String busqueda = scanner.next();
        ArrayList<Producto> encontrados = new ArrayList<>();

        for (Producto producto : productos) {
            if (producto.getNombre().toLowerCase().contains(busqueda.toLowerCase())) {
                System.out.println(producto);
                encontrados.add(producto);
            }
        }
        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron productos con ese nombre.");
            return null;
        }
        return encontrados;
    }

    private static void verificarModificacion(Scanner scanner, ArrayList<Producto> productosEncontrados) {
        System.out.println("¿Desea modificar algún producto? (s/n): ");
        String respuesta = scanner.nextLine();


        if (respuesta.equalsIgnoreCase("s")) {
            Producto productoSeleccionado = obtenerProductoPorId(scanner, productosEncontrados);

            if (productoSeleccionado != null) {
                modificarDatoDelProducto(scanner, productoSeleccionado);
                System.out.println("Producto actualizado correctamente.");
            } else {
                System.out.println("No se encontró el producto con ese ID.");
            }
        }else{
            System.out.println("No se modificó ningún producto.");
        }
    }

    private static Producto obtenerProductoPorId(Scanner scanner, ArrayList<Producto> encontrados) {
        // Elegir cual vamos a modificar por el ID
        System.out.println("Ingrese el ID del producto:");
        int idModificar = scanner.nextInt();
        scanner.nextLine();
        Producto productoSeleccionado = null;
        for (Producto p : encontrados) {
            if (p.getId() == idModificar) {
                productoSeleccionado = p;
                break;
            }
        }
        return productoSeleccionado;
    }

    private static void modificarDatoDelProducto(Scanner scanner, Producto productoSeleccionado) {
        int opcionModificar = menuModificar(scanner);
        switch (opcionModificar) {
            case 1:
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                productoSeleccionado.setNombre(nuevoNombre);
                break;
            case 2:
                System.out.print("Nuevo precio: ");
                double nuevoPrecio = scanner.nextDouble();
                productoSeleccionado.setPrecio(nuevoPrecio);
                break;
            case 3:
                System.out.print("Nuevo stock: ");
                int nuevoStock = scanner.nextInt();
                productoSeleccionado.setStock(nuevoStock);
                break;
            default:
                System.out.println("Opción inválida");

        }
    }

    private static int menuModificar(Scanner scanner) {
        System.out.println("¿Que dato desea modificar?");
        System.out.println("1. Nombre del producto");
        System.out.println("2. Precio del producto");
        System.out.println("3. Stock del producto");
        int opcionModificar = scanner.nextInt();
        scanner.nextLine();
        return opcionModificar;
    }


    private static void verificarEliminacion(Producto productoAEliminar, Scanner scanner, ArrayList<Producto> productos) {
        if (productoAEliminar != null) {
            System.out.println("¿Estas seguro que desea eliminar este producto? (s/n):");
            String resp = scanner.nextLine();
            if (resp.equalsIgnoreCase("s")) {
                productos.remove(productoAEliminar);
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("Eliminación cancelada.");
            }
        }
    }

    private static void crearPedido(ArrayList<Producto> productos, Scanner scanner) {
        Pedido pedido = new Pedido();
        String continuar = "";

        do {
            listarProductos(productos); // le mustro al usuario la lista de productos, asi elije

            Producto productoSeleccionado = obtenerProductoPorId(scanner, productos);
            if (productoSeleccionado == null) {
                System.out.println("Producto no encontrado. Intente nuevamente.");
                continue;
            }

            System.out.println("Ingrese la cantidad deseada:");
            int cantidad = scanner.nextInt();
            scanner.nextLine();

            if (cantidad > productoSeleccionado.getStock()) {
                System.out.println("Stock insuficiente. Stock disponible: " + productoSeleccionado.getStock());
                continue;
            }

            // Agregamos el producto al pedido y le descontamos el stock
            pedido.agregarLinea(productoSeleccionado, cantidad);
            productoSeleccionado.setStock(productoSeleccionado.getStock() - cantidad);

            System.out.println("Producto agregado al pedido.");
            System.out.println("¿Desea agregar otro producto? (s/n)");
            continuar = scanner.nextLine();

        } while (continuar.equalsIgnoreCase("s"));

        System.out.println("Pedido creado correctamente. Total: $" + pedido.obtenerTotal());
    }


}
