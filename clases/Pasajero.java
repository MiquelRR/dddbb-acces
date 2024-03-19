package clases;

public class Pasajero {
    public Pasajero(String nombre, String document) {
        this.nombre = nombre;
        this.document = document;
        Accesdb.agrega("Pasajeros", new Object[] {"nombre_pasajero", nombre, "numero_pasaporte", document});
    }
    private String nombre;
    private String document;
    public String getNombre() {
        return nombre;
    }
    public String getDocument() {
        return document;
    }    
}
