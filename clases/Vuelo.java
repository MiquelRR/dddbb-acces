package clases;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Vuelo {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    final static List<String> seients = new ArrayList();
    static{
        Character letra='A';
        int num=1;
        String idAsiento;
        for (int i = 0; i < 252; i++) {
            idAsiento=letra.toString()+String.format("%02d",num);
            seients.add(idAsiento);
            if (letra=='G'){
                letra='A';
                num++;
            }
        }
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
        int elimina=places%6;
        int n=places+elimina;
        String mig=String.format("%02d",n/12);
        String cua=String.format("%02d",n/6);
        List<String> idAsiento = seients.subList(0, n);
        if (elimina>0) idAsiento.remove("C01");
        if (elimina>1) idAsiento.remove("D01");
        if (elimina>2) idAsiento.remove("C"+mig);
        if (elimina>3) idAsiento.remove("D"+mig);
        if (elimina>4) idAsiento.remove("C"+cua);
        for (String id:idAsiento) {
            Accesdb.agrega("Plazas",new Object[]{"id_asiento", id, "id_vuelo", idVol, "ocupado", "no"});
            //quiza esto es lento ✈   ✈️      
        }   

    }
    

}
