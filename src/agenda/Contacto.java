package agenda;

public class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;

    /**
     * Constructor con validación: teléfono mínimo 10 caracteres.
     * @param nombre Nombre del contacto (obligatorio)
     * @param apellido Apellido del contacto (obligatorio)
     * @param telefono Teléfono (mínimo 10 caracteres)
     * @throws IllegalArgumentException si algún campo es inválido
     */
    public Contacto(String nombre, String apellido, String telefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        if (telefono == null || telefono.length() < 10) {
            throw new IllegalArgumentException("El teléfono debe tener al menos 10 caracteres.");
        }
        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        this.telefono = telefono.trim();
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        this.apellido = apellido.trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.length() < 10) {
            throw new IllegalArgumentException("El teléfono debe tener al menos 10 caracteres.");
        }
        this.telefono = telefono.trim();
    }

    /**
     * Dos contactos son iguales si tienen el mismo nombre y apellido (ignorando mayúsculas).
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contacto otro = (Contacto) obj;
        return this.nombre.equalsIgnoreCase(otro.nombre) &&
                this.apellido.equalsIgnoreCase(otro.apellido);
    }

    /**
     * HashCode coherente con equals.
     */
    @Override
    public int hashCode() {
        return (nombre.toLowerCase() + apellido.toLowerCase()).hashCode();
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " " + apellido + " | Teléfono: " + telefono;
    }
}