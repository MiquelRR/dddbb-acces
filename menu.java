import java.util.HashMap;
import java.util.Scanner;

import clases.*;

public class menu {
    static String[] menu = { "OPCIONS", "AEROPORTS", "VOLS" };
    static Character selOpt = 'x'; // opcio a resaltar
    static String resposta="";
    static HashMap<Character, String> opcions = new HashMap<>();
    static {
        opcions.put('0', "Alta Model Avió");
        opcions.put('1', "Alta Vol");
        opcions.put('2', "Alta Passatger");
        opcions.put('3', "Reserva Vol");
        opcions.put('4', "Modifica Reserva");
        opcions.put('5', "Baixa Reserva");
        opcions.put('6', "Ix de l'Aplicació");
    }
    static int h = 100, v = 35; // mesures de l'interior del requadre gran
    static Scanner sc = new Scanner(System.in);

    public static String mostra(Pantalla p) {
        int n = menu.length;
        int fins=h/2/n;
        p.borra();
        p.marc();
        int y = 6;
        //opcions
        for (Character c : opcions.keySet()) {
            p.situa(3, y, "(" + c + ")", 'c');
            p.situa(7, y, "-", 'w');
            p.situa(8, y, opcions.get(c), (selOpt == c) ? 'c' : 'w');
            y++;
        }
        //marcs
        for (int i = 0; i < n; i++) {
            p.marc(1 + (h / n) * i, 1, (h / n), v, 'l', 'b');
            p.marc(2 + (h / n) * i, 2, (h / n) - 2, 3, 'd', 'c');
            p.situa(3 + ((h / n) - 4 - menu[i].length()) / 2 + ((h / n) * i), 3, menu[i]);
        }
        //avions
        if(menu[1].equals("MODELS AVIONS")){
            int x=h/3+3;
            y=6;
            p.situa(x,y,"Afig nou avió:",'c');
            for(String v : Avio.flota.keySet()){
                y++;
                p.situa(x,y,v,'w');
                p.situa(h*2/3-5,y,Avio.flota.get(v)+"pl",'b');
            }
            y++;
            
            if (resposta.length()>0){
                
                p.situa(x,y,resposta,'w');
                x=h*2/3-5;
    
            }
            fins=x;
        }
        
        y++;
        p.mostraFins(fins, y);
        return sc.nextLine();
    };
    public static void nouAvio(Pantalla p){
        menu[1]="MODELS AVIONS";
        String modelnou="$";
        do{
        modelnou=mostra(p);
        resposta=modelnou;
        }while(Avio.flota.keySet().contains(modelnou));
        int places=-1;
        while (places<0 || places > 99){
            resposta=mostra(p);
            try {
                places=Integer.parseInt(resposta.strip());
            } catch (Exception e) {
                places=-1;
            }
            resposta="";
        }
        Avio model = new Avio(modelnou,places);

        menu[1]="AEROPORTS";
        selOpt='x';

    };

    public static void main(String[] args) {
        Pantalla menu = new Pantalla(h + 2, v + 2);
        while (selOpt != '6') {
            if (selOpt=='0') nouAvio(menu);
            String entrada = mostra(menu);
            selOpt=entrada.charAt(0);
        }
        mostra(menu);

    }

}
