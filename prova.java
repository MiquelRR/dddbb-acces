import java.util.Arrays;
import java.util.List;

import clases.Pantalla;

public class prova {
    
    static Pantalla p = new Pantalla(50, 30);
    public static void main(String[] args) {
        Integer edad =0;
        String nom = "anònim";
        p.marc();
        p.cursor(1, 10);
        p.situa("------------");
        p.getLocalDate();
        p.situa("Donde estudias:");
        String curso=p.getString();
        p.getLocalDate("desde Cuando:");
        nom=p.getString("Como te llamas :");
        p.situa("¿Cuantos años tienes?");
        edad=p.getInteger();
        String especialidad ;
        List<String> lista = Arrays.asList("DAM","DAW","HPGL","NADA","ASMR","COSAS");
        p.cursor(36,2);
        p.situa("ELIGE:");
        //p.situa(lista);
        especialidad=p.getString("--->:",lista);
        p.cursor(9,6);
        edad =p.getInteger("Cuantos añitos tienes :");
        p.situa("--------------------");
        p.cursor(10,20);
        p.situa(nom+", de "+edad+" años de edad\ncursando "+curso,'v');
        p.situa(especialidad);
        p.mostra();
    }
}
