import java.time.LocalDate;
import java.util.HashMap;

import clases.*;

public class menu2 {
    

    public static void main(String[] args) throws InterruptedException {
        PanellVols p = PanellVols.getPanell();
        char opcio;
        do {
            p.menu();
            opcio = p.getValidMenuChar();
            switch (opcio) {
                case '0':
                    p.nouAvio();
                    break;
                case '1':
                    p.altaVol();
                    break;
                case '2':
                    p.altaPas();
                    break;
                case'3':
                    p.reservaVol();
                case '6':
                    p.fi();
            }
        } while (opcio != '6');
    }
}
