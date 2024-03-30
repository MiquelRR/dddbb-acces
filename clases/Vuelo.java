package clases;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Vuelo {
    final static List<String> seients = new ArrayList<>();
    static{
        Character letra='A';
        int num=1;
        String idAsiento;
        for (int i = 0; i < 252; i++) {
            idAsiento=letra.toString()+String.format("%02d",num);
            seients.add(idAsiento);
            letra++;
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
    public static List<String[]> generaPlaces(int p){
        List<String[]> lista = new ArrayList<>();
        List<String> idAsiento= new ArrayList<>();
        int elimina=0;
        int n=p;
        if(p%6!=0){
            n=((p / 6) + 1) * 6;
            elimina=n-p;
        }
        String mig=String.format("%02d",n/12);
        String cua=String.format("%02d",n/6);
        idAsiento = new ArrayList<>(seients.subList(0, n));
        if (elimina>0) idAsiento.remove("C01");
        if (elimina>1) idAsiento.remove("D01");
        if (elimina>2) idAsiento.remove("C"+mig);
        if (elimina>3) idAsiento.remove("D"+mig);
        if (elimina>4) idAsiento.remove("C"+cua);
        for (String reg : idAsiento) {
                   lista.add(new String[]{"x",reg,"","","no"});     
        }
        return lista;

    }
    
    public Vuelo(Integer places, String origen, String destino, LocalDate fecha) { 
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.places=places;
        int idVol=Accesdb.agrega("Vuelos", new Object[] {"origen",origen,"destino", destino, "fecha", fecha.toString()});
        List<String[]> lista= generaPlaces(places);
        for (String[] id:lista) {
            Accesdb.agrega("Plazas",new Object[]{"id_asiento", id[1], "id_vuelo", idVol, "ocupado", "no"});
            //quiza esto es lento ✈   ✈️      
        }   

    }
    

}
