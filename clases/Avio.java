package clases;
import java.util.HashMap;
import java.util.List;

public class Avio {
    public static HashMap<String,Integer> flota = new HashMap<>();
    static{
        List<String[]> llista = Accesdb.lligTaula("Tipo_de_Avion");
        for (String[] linea: llista) {
            flota.put(linea[1],Accesdb.toInt(linea[2]));
        }
    }
    private String nom;
    private Integer places;

    public Integer getPlaces() {
        return places;
    }

    public Avio(String nom, Integer places){
        this.nom =nom;
        this.places=places;
        Accesdb.agrega("Tipo_de_Avion", new Object[] {"modelo",nom,"asientos",places});
        flota.put(nom,places);
    }
    
}


