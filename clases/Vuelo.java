package clases;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Vuelo {
    // NO ESTOY USANDO ESTO-----------------------------------------------------------
    /* public static HashMap<String, Aeropuerto> destinos = new HashMap<>();
    static {
        List<String[]> entrada = Accesdb.lligTaula("Aeropuertos");
        for (String[] linea : entrada) {
            Aeropuerto dest = new Aeropuerto(linea[0], linea[1], linea[2], linea[3]);
            destinos.put(linea[0],dest);
        }
    } */

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
    String origen ;
    String destino;
    Integer places;
    LocalDate fecha;
    public Vuelo(Integer places, String origen, String destino, LocalDate fecha) { 
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.places=places;
        int idVol=Accesdb.agrega("Vuelos", new Object[] {"origen",origen,"destino", destino, "fecha", fecha.toString()});
        Character letra='A';
        int num=1;
        String idAsiento;
        for (int i = 1; i <= places; i++) {
            idAsiento=letra.toString()+String.format("%02d",num);
            Accesdb.agrega("Plazas",new Object[]{"id_asiento", idAsiento, "id_vuelo", idVol, "ocupado", "no"});
            letra++;
            if (letra=='G'){
                letra='A';
                num++;
            }
            //quiza esto es lento ✈   ✈️      
        }   

    }
    

}
