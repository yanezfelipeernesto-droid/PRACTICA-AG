/*package agenda;

import java.util.Scanner;

public class MenuConsola {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda(); // capacidad por defecto 10

        boolean salir = false;

        while (!salir) {

            imprimirMenu();
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {

                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Teléfono (mínimo 10 caracteres): ");
                    String telefono = scanner.nextLine();

                    try {
                        Contacto c = new Contacto(nombre, telefono);
                        agenda.añadirContacto(c);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    agenda.listarContactos();
                    break;

                case 3:
                    System.out.print("Nombre a buscar: ");
                    String nombreBuscar = scanner.nextLine();
                    agenda.buscaContacto(nombreBuscar);
                    break;

                case 4:
                    System.out.print("Nombre a eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    // Creamos un contacto temporal con un teléfono válido (el teléfono no importa para la comparación)
                    Contacto eliminar = new Contacto(nombreEliminar, "0000000000");
                    agenda.eliminarContacto(eliminar);
                    break;

                case 5:
                    System.out.println("¿Agenda llena? " + (agenda.agendaLlena() ? "Sí" : "No"));
                    System.out.println("Espacios libres: " + agenda.espaciosLibres());
                    break;

                case 6:
                    salir = true;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void imprimirMenu() {
        System.out.println("==== MENÚ AGENDA ====");
        System.out.println("1. Añadir contacto");
        System.out.println("2. Listar contactos");
        System.out.println("3. Buscar contacto");
        System.out.println("4. Eliminar contacto");
        System.out.println("5. Estado de agenda");
        System.out.println("6. Salir");
    }
}*/