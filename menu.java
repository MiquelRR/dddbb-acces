import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import clases.*;

public class menu {
    static String[] menu = { "MENU", "AEROPORTS", "VOLS" };
    static Character selOpt = 'x'; // opcio a resaltar
    static String resposta = "", mostrar="";
    static HashMap<Character, String> opcions = new HashMap<>();
    static {
        opcions.put('0', "Alta Model d'Avió");
        opcions.put('1', "Alta Vol");
        opcions.put('2', "Alta Passatger");
        opcions.put('3', "Reserva Vol");
        opcions.put('4', "Modifica Reserva");
        opcions.put('5', "Baixa Reserva");
        opcions.put('6', "Ix de l'Aplicació");
    }
    static String infoM="Info: Tria una opció del menu [0.."+(opcions.size()-1)+"]";
    static String info=infoM;
    static int h = 120, v = 35; // mesures de l'interior del requadre gran
    static Scanner sc = new Scanner(System.in);

    public static String mostra(Pantalla p) {
        int n = menu.length;
        int x = h/6;
        p.borra();
        p.marc();
        int y = 6;
        // opcions
        for (Character c : opcions.keySet()) {
            p.situa(3, y, "(" + c + ")", 'c');
            p.situa(7, y, opcions.get(c), (selOpt == c) ? 'c' : 'w');
            y++;
        }
        // menu
        if (menu[0].equals("MENU")){
            y++;
        p.situa(10,y,"->",'g');
        x=12;
        }
       
        // marcs
        for (int i = 0; i < n; i++) {
            p.marc(1 + (h / n) * i, 1, (h / n), v - 1, 'l', 'b');
            p.marc(2 + (h / n) * i, 2, (h / n) - 2, 3, 'd', 'c');
            p.situa(3 + ((h / n) - 4 - menu[i].length()) / 2 + ((h / n) * i), 3, menu[i]);
        }
        // avions
        if (menu[1].equals("MODELS AVIONS")) {
            x = h / 3 + 3;
            y = 6;
            p.situa(x, y, "Afig nou avió:", 'c');
            p.situa(h * 2 / 3 - 7, y, "Places", 'c');
            for (String v : Avio.flota.keySet()) {
                y++;
                p.situa(x, y, v, 'w');
                p.situa(h * 2 / 3 - 3, y, Avio.flota.get(v) + "", 'n');
            }
            y++;
            if (mostrar.length()==0){
            info = "Info: Enter per a tabular, màxim 99 seients";
            } else{
                p.situa(x, y, mostrar, 'w');
                x = h * 2 / 3 - 3;
            }

        }

        //passatgers
        if (menu[1].equals("PASSATGERS")) {
            x = h / 3 + 3;
            y = 6;
            p.situa(x, y, "Afig Passatger:", 'c');
            p.situa(h * 2 / 3 - 9, y, "Document", 'c');
            //soy un pringao, Patxi, a este no le he hecho hashmap, pero quiero ir probando
            List<String[]> pass = Accesdb.lligTaula("Pasajeros");
            for (String[] pas : pass) {
                y++;
                p.situa(x, y, pas[2], 'w');
                p.situa(h * 2 / 3 -10, y, pas[1] + "", 'n');
            }
            y++;
            if (mostrar.length()==0){
            info = "Info: Escriu el nom del passatger, enter i el document";
            } else{
                p.situa(x, y, mostrar, 'w');
                x = h * 2 / 3 - 10;
            }
        }
     
        y++;
               
        if (!menu[0].equals("EIXIDA")){
            p.situa((h - info.length()) / 2, v, info, 'g');
            p.mostraFins(x, y);
            return sc.nextLine();}
        else{
            info = "Info: Tot està guardat correctament a la bbdd";
            p.situa((h - info.length()) / 2, v, info, 'g');
            p.mostra();
            return "";}
    };

    public static void nouAvio(Pantalla p) {
        menu[0] = "OPCIONS";
        menu[1] = "MODELS AVIONS";
        String modelnou = "$";
        mostrar="";
        do {
            modelnou = mostra(p).trim();;
            mostrar= modelnou;
        } while (Avio.flota.keySet().contains(modelnou));
        int places = -1;
        while (places < 0 || places > 99) {
            resposta = mostra(p);
            try {
                places = Integer.parseInt(resposta.strip());
            } catch (Exception e) {
                places = -1;
            }
        }
        mostrar = "";
        Avio model = new Avio(modelnou, places);
        info="Info: Afegit model d'avió "+modelnou+" amb "+places+" places";
        menu[1] = "AEROPORTS";
        menu[0] = "MENU";
        selOpt = 'x';
    };

    public static void acaba(Pantalla p) {
        menu[0] = "EIXIDA";
        mostra(p);
    }

    public static void nouPassatger(Pantalla p) {
        menu[0] = "OPCIONS";
        menu[1] = "PASSATGERS";
        String nouPas = "$";
        do {
            nouPas = mostra(p).trim();
            mostrar = nouPas;
        } while (nouPas.length()<2);
        String doc="";
        while (doc.length() < 3 || doc.length() > 15) {
            doc = mostra(p).toUpperCase().trim();
        }
        mostrar = "";
        Pasajero pas = new Pasajero(nouPas, doc);
        info="Info: Afegit passatger "+nouPas+" amb document "+doc;
        menu[1] = "AEROPORTS";
        menu[0] = "MENU";
        selOpt = 'x';

    }

    public static void main(String[] args) {
        Pantalla menu = new Pantalla(h + 2, v + 2);
        while (selOpt != '6') {
            if (selOpt == '0')
                nouAvio(menu);
            if (selOpt == '2')
                nouPassatger(menu);
            String entrada = mostra(menu)+"x"; //evita out of range
            selOpt = entrada.charAt(0);
        }
        acaba(menu);

    }

}
