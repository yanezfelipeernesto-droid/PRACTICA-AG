package agenda;

import java.util.ArrayList;
import java.util.Objects;

public class Agenda {
    private ArrayList<Contacto> contactos;
    private int capacidad;

    public Agenda() {
        this(10);
    }

    public Agenda(int capacidad) {
        this.capacidad = capacidad;
        this.contactos = new ArrayList<>();
    }

    /**
     * Añade un nuevo contacto a la agenda.
     *
     * <p>El contacto se añade solo si:
     * <ul>
     *   <li>La agenda no está llena</li>
     *   <li>No existe otro contacto con el mismo nombre y apellido</li>
     * </ul>
     *
     * @param c El contacto a añadir (no debe ser null)
     * @return true si el contacto se añadió correctamente, false en caso contrario
     * @throws NullPointerException si el contacto es null
     */
    public boolean añadirContacto(Contacto c) {
        // Validación de entrada
        Objects.requireNonNull(c, "El contacto no puede ser null");

        if (agendaLlena()) {
            System.out.println("La agenda está llena. Capacidad máxima: " + capacidad);
            return false;
        }

        if (existeContacto(c)) {
            System.out.printf("El contacto '%s %s' ya existe.%n",
                    c.getNombre(), c.getApellido());
            return false;
        }

        contactos.add(c);
        System.out.printf("Contacto '%s %s' añadido. Espacios libres: %d%n",
                c.getNombre(), c.getApellido(), espaciosLibres());
        return true;
    }

    public boolean existeContacto(Contacto c) {
        return contactos.contains(c);
    }

    public void listarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
        } else {
            System.out.println("Lista de contactos:");
            for (Contacto c : contactos) {
                System.out.println(c);
            }
        }
    }

    /**
     * Busca un contacto por nombre y apellido
     */
    public void buscaContacto(String nombre, String apellido) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)) {
                System.out.println("Teléfono de " + nombre + " " + apellido + ": " + c.getTelefono());
                return;
            }
        }
        System.out.println("No se encontró ningún contacto con ese nombre y apellido.");
    }

    public void eliminarContacto(Contacto c) {
        if (contactos.remove(c)) {
            System.out.println("Contacto eliminado correctamente.");
        } else {
            System.out.println("El contacto no existe, no se pudo eliminar.");
        }
    }

    public boolean agendaLlena() {
        return contactos.size() >= capacidad;
    }

    public int espaciosLibres() {
        return capacidad - contactos.size();
    }

    // Para la UI
    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public int espacioLibre() {
        return espaciosLibres();
    }
}