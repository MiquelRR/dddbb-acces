package clases;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Vuelo {
    public static HashMap<String, Aeropuerto> destinos = new HashMap<>();
    static {
        List<String[]> entrada = Accesdb.lligTaula("Aeropuertos");
        for (String[] linea : entrada) {
            Aeropuerto dest = new Aeropuerto(linea[0], linea[1], linea[2], linea[3]);
            destinos.put(linea[0],dest);
        }
    }
    public static LocalDate getDataUsuari() {
        Scanner sc = new Scanner(System.in);
        LocalDate data = null;
        while (data == null) {
            String entrada = sc.nextLine();
            try {
                data = LocalDate.parse(entrada);
            } catch (DateTimeParseException e) {
                data = null;
            }
        }
        sc.close();

        return data;
    }
    Aeropuerto origen ;
    Aeropuerto destino;
    Avio nave;
    LocalDate fecha;
    public Vuelo(Avio nave, Aeropuerto origen, Aeropuerto destino, LocalDate fecha) { 
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.nave=nave;
        int idVol=Accesdb.agrega("Vuelos", new Object[] {"origen",origen.getCodigo(),"destino", destino.getCodigo(), "fecha", fecha.toString()});
        for (int i = 1; i <= nave.getPlaces(); i++) {
            Accesdb.agrega("Plazas",new Object[]{"idasiento", i, "idvuelo", idVol, "ocupado", "no"});
            //quiza esto es lento ✈   ✈️      
        }   

    }
    

}
