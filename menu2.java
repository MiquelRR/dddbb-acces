import java.time.LocalDate;
import java.util.HashMap;

import clases.*;

public class menu2 {
    private static HashMap<Character, String> opcions = new HashMap<>();
    static {
        opcions.put('0', "Alta Model d'Avió");
        opcions.put('1', "Alta Vol");
        opcions.put('2', "Alta Passatger");
        opcions.put('3', "Reserva Vol");
        opcions.put('4', "Modifica Reserva");
        opcions.put('5', "Baixa Reserva");
        opcions.put('6', "Ix de l'Aplicació");
      
    }
    public static void main(String[] args) throws InterruptedException {
        PanellVols p = PanellVols.getPanell();
        String[] opval = new String[opcions.size()];
        int i=0;
        for (char c : opcions.keySet()) {
            opval[i]=""+c;
            i++;
        }
        p.menu(opcions);
        char triada=p.getString("     --> ",opval).charAt(0);
    }
}
