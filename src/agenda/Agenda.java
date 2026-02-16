package agenda;

import java.util.ArrayList;

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

    public void añadirContacto(Contacto c) {
        if (agendaLlena()) {
            System.out.println("La agenda está llena. No se puede añadir más contactos.");
        } else if (existeContacto(c)) {
            System.out.println("El contacto ya existe (mismo nombre y apellido).");
        } else {
            contactos.add(c);
            System.out.println("Contacto añadido correctamente.");
        }
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
     * Busca un contacto por nombre y apellido.
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