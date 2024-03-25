package clases;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

//--------------------------------patrón Singleton 
public class PanellVols extends Pantalla {
    private static PanellVols panell;// -------patrón Singleton
    private static int h = 120, v = 38;

    private PanellVols() throws InterruptedException {
        super(h + 2, v + 2);

    }

    private String peu = " Info: Tria una opció del menu [0..6] ";
    private static HashMap<Character, String> opcions = new HashMap<>();
    private static String[] opval = new String[] { "0", "1", "2", "3", "4", "5", "6" };
    private static String[] capsa = new String[] { "MENU", "MODELS AVIONS", "VOLS" };
    private static int n = capsa.length;
    static {
        opcions.put(opval[0].charAt(0), "Alta Model d'Avió");
        opcions.put(opval[1].charAt(0), "Alta Vol");
        opcions.put(opval[2].charAt(0), "Alta Passatger");
        opcions.put(opval[3].charAt(0), "Reserva Vol");
        opcions.put(opval[4].charAt(0), "Modifica Reserva");
        opcions.put(opval[5].charAt(0), "Baixa Reserva");
        opcions.put(opval[6].charAt(0), "Ix de l'Aplicació");

    }
    private static String[] paisos;
    static {
        List<String[]> paiss = Accesdb.lligQuery(Accesdb.paisos);
        paisos = new String[paiss.size()];
        int i = 0;
        for (String[] pais : paiss) {
            paisos[i++] = pais[0];
        }
    }
    private static List<String[]> aeroportsPais;
    private static String[] valCodis;

    private static int maxpl = 168;

    private int xa = 0, ya = 0, xp = 0, yp = 0;

    public Character getValidMenuChar() {
        return panell.getString("     --> ", opval).charAt(0);

    }

    public void menu() throws InterruptedException {
        int n = capsa.length;
        panell.marc(0, 0, h + 2, v + 2, 'd', 'w');
        AniBloc peuBl = new AniBloc(v, 1 + 25, 1, h - 50);
        char[][] p = new char[][] { (" " + peu + " ").toCharArray() };
        peuBl.scrDreMi(p, 'g');
        panell.addAniBloc(peuBl);
        AniBloc[] capsBl = new AniBloc[n];
        for (int i = 0; i < n; i++) {
            panell.marc(1 + (h / n) * i, 1, (h / n), v - 1, 'l', 'b');
            panell.marc(2 + (h / n) * i, 2, (h / n) - 2, 3, 'd', 'c');
            capsBl[i] = new AniBloc(3, 3 + (h / n) * i, 1, (h / n) - 4);
            char[][] q = new char[][] { (" " + capsa[i] + " ").toCharArray() };
            capsBl[i].scrEsMi(q, 'g');
            panell.addAniBloc(capsBl[i]);
        }
        AniBloc menuBloc = new AniBloc(5, 2, v - 6 - 10, (h / n) - 2);
        char[][] menuchmp = map2char(opcions);
        menuBloc.suraFinsMig(menuchmp, 'w');
        panell.addAniBloc(menuBloc);
        composa('x');
        panell.mostraAnim(10);
        panell.cursor(11, ((v - 6 - 10 - opcions.size()) / 2) + 5);
        for (char c : opcions.keySet()) {
            panell.situa("" + c, 'c');
        }

    }

    private int[] composa(char c) {
        int x = 0, y = 0;
        // resalta opcio triada
        if (opcions.keySet().contains(c)){
        panell.situa(14, 12 + c - 48, opcions.get(c), 'c'); // Y SENSE PARAMETRITZAR
        }
        // rescriu les capçaleres
        for (int i = 0; i < n; i++) {
            panell.situa(3 + ((h / n) * i), 3, " ".repeat(((h / n) - 4)));
            panell.situa(3 + ((h / n) - 4 - capsa[i].length()) / 2 + ((h / n) * i), 3, capsa[i]);
        }
        // rescriu el peu
        panell.situa(1, v, " ".repeat(h), 'n');
        panell.situa((h - peu.length()) / 2, v, peu, 'g');
        panell.setUltcol('w');

        // borrar zona
        if (capsa[1].equals("MODELS AVIONS") || capsa[1].equals("PASSATGERS")) {
            x = h / 3 + 2;
            panell.setCursor(x, 5);
            for (int i = 5; i < v - 1; i++) {
                panell.situa(" ".repeat(h / 3 - 2));
            }
        }
        if (capsa[2].equals("PAÏSOS") || capsa[2].startsWith("AEROPORTS")|| capsa[2].equals("VOLS")) {
            x = h * 2 / 3 + 2;
            y=5;
            panell.setCursor(x, y);
            for (int i = 5; i < v - 1; i++) {
                panell.situa(" ".repeat(h / 3 - 2));
            }
            y++;
            x+=2;
            panell.setCursor(x, y);
        }
        if (capsa[2].equals("VOLS")) {
            x--;
            panell.setCursor(x, y);
            List<String[]> vols= Accesdb.lligTaula("Vuelos");
            panell.situa("  ORIGE       DESTINCIÓ     FECHA",'c');
            for (String[] r : vols) {
                String or_pais = Accesdb.lligReg(String.format(Accesdb.paisdecCodi,r[1]))[0];
                or_pais= String.format("%-8s", or_pais).substring(0,8);
                String de_pais = Accesdb.lligReg(String.format(Accesdb.paisdecCodi,r[2]))[0];
                de_pais = String.format("%-8s", de_pais).substring(0,8);
                panell.situa(r[1]+"-"+or_pais+" "+r[2]+"-"+de_pais+" "+r[3],'w');
            }
        }

        if (capsa[2].equals("PAÏSOS")) {
            x--;
            for (String pais : paisos) {
                panell.situa(x, y, pais);
                y++;
                if (y == v - 1) {
                    y = 5;
                    x += 12;
                }
            }
        }
        if (capsa[2].startsWith("AEROPORTS")) {
            for (String[] registre : aeroportsPais) {                             
                panell.situa(registre[0]+"  "+registre[3]);
            }
        }
        
        // Nou Passatger Nom
        if (capsa[1].equals("PASSATGERS")) {
            x = h / 3 + 3;
            y = 6;
            panell.situa(x, y, "Nom:", 'c');
            panell.situa(h * 2 / 3 - 9, y, "Document", 'c');
            // soy un pringao, Patxi, a este no le he hecho hashmap, pero quiero ir probando
            List<String[]> pass = Accesdb.lligTaula("Pasajeros");
            for (String[] pas : pass) {
                y++;
                panell.situa(x, y, pas[2], 'w');
                panell.situa(h * 2 / 3 - 10, y, pas[1] + "", 'w');
            }
            y++;
            yp = y;
            panell.cursor(x, y);
        }
        // nou Passatger document
        if (capsa[1].equals("INTRODUEIX EL DOCUMENT")) {
            panell.cursor(h * 2 / 3 - 12, yp);
        }
        // nou avio Nom Model
        if (capsa[1].equals("MODELS AVIONS")) {
            x = h / 3 + 3;
            y = 6;
            panell.situa(x, y, "Afig nou avió:", 'c');
            panell.situa(h * 2 / 3 - 7, y, "Places", 'c');
            for (String v : Avio.flota.keySet()) {
                y++;
                panell.situa(x, y, v, 'w');
                panell.situa(h * 2 / 3 - 4, y, String.format("%3d", Avio.flota.get(v)), 'w');
            }
            y++;
            xa = x;
            ya = y;
            panell.cursor(x, ya);
        }
        // nou avio places
        if (capsa[1].equals("PLACES PER PASSATGERS")) {
            panell.cursor(h * 2 / 3 - 4, ya);
        }
        if(capsa[2].split(" ")[0].equals("AEROPORTS")){
            

            
        }
        return new int[] { x, y };

    }
    public void reservaVol(){
        capsa[1] = "MODELS AVIONS";
        capsa[2] = "PAÏSOS";

    }

    public void altaVol() {
        capsa[1] = "MODELS AVIONS";
        capsa[2] = "PAÏSOS";
        String[] models = new String[Avio.flota.keySet().size()];
        int i = 0;
        for (String model : Avio.flota.keySet()) {
            models[i++] = model;
        }
        composa('1');
        panell.cursor(7, 22);
        String model = panell.getString("Model d'avió : ", models);
        Integer places = Avio.flota.get(model);
        // panell.mostra();
        panell.cursor(7, 23);
        String pais = panell.getString("Pais d'oritge : ", paisos);
        triaPais(pais);
        this.peu = "Info: Pais d'oritge " + pais;
        capsa[2] = "AEROPORTS " + pais.toUpperCase();
        panell.cursor(7, 22);
        panell.situa("PLACES PER A PASSATGERS : "+places,'c');        
        composa('1');
        panell.cursor(7, 23);
        panell.situa(" ".repeat(31));
        panell.cursor(7, 23);
        String codiOrige = panell.getString("Codi Aeroport Orige : ", valCodis);
        this.peu = "Info: Aeroport orige triat " + codiOrige;
        capsa[2] = "PAÏSOS";
        composa('1');
        panell.cursor(7, 24);
        pais = panell.getString("Pais destinació : ", paisos);
        triaPais(pais);
        this.peu = "Info: Pais destinació " + pais;
        capsa[2] = "AEROPORTS " + pais.toUpperCase();
        composa('1');
        panell.cursor(7, 24);
        panell.situa(" ".repeat(31));
        panell.cursor(7, 24);
        String codiDest = panell.getString("Codi Aeroport destinació : ", valCodis);
        this.peu = "Info: Aeroport destinació triat " + codiDest;
        composa('1');
        panell.cursor(7, 25);
        LocalDate data = panell.getLocalDate("Data de vol :");
        this.peu = "Info: Vol afegit "+data.toString()+" des de "+codiOrige+" fins "+codiDest+ " amb un "+model;
        capsa[2]="VOLS";
        Vuelo vol=new Vuelo(places,codiOrige,codiDest,data);
        composa('x');
    }
    private void triaPais(String pais){
        aeroportsPais = Accesdb.lligQuery(String.format(Accesdb.aeroportsDe,pais));
        valCodis = new String[aeroportsPais.size()];
        int i=0;
        for (String[] registre : aeroportsPais) {
            valCodis[i++]=registre[0];
        }
        
    }

    public void nouAvio() {
        capsa[1] = "MODELS AVIONS";
        this.peu = "Info: Enter per a tabular";
        composa('0');
        String nomAvio = panell.getString();
        capsa[1] = "PLACES PER PASSATGERS";
        this.peu = "Info: Màxim " + maxpl + " places";
        composa('0');
        int places = panell.getInteger(1, maxpl);
        capsa[1] = "MODELS AVIONS";
        Avio model = new Avio(nomAvio, places);
        this.peu = "Info: Afegit model d'avió " + nomAvio + " amb " + places + " places";
    }

    public void altaPas() {
        capsa[1] = "PASSATGERS";
        this.peu = "Info: Inserta el nom, enter i el document";
        composa('2');
        String nomPass = panell.getString();
        capsa[1] = "INTRODUEIX EL DOCUMENT";
        this.peu = "Info: el document no es pot repetir";
        composa('2');
        String doc = panell.getString().toUpperCase();
        Pasajero pas = new Pasajero(nomPass, doc);
        capsa[1] = "PASSATGERS";
        this.peu = "Info: Afegit passager " + nomPass + " amb document " + doc;
    }

    public void fi() {
        composa('6');
        panell.mostra();
    }

    private static char[][] map2char(HashMap<Character, String> mapa) {
        int maxl = 0, y = 0;
        for (String st : mapa.values()) {
            maxl = (st.length() > maxl) ? st.length() : maxl;
            y++;
        }
        maxl += 6;
        char[][] res = new char[y + 2][maxl];
        res[0] = String.format("%-" + maxl + "s", "  ").toCharArray();
        int i = 1;
        for (char c : mapa.keySet()) {
            String cad = " (" + c + ") " + mapa.get(c);
            res[i] = String.format("%-" + maxl + "s", cad).toCharArray();
            i++;
        }
        res[i] = String.format("%-" + maxl + "s", "  ").toCharArray();
        return res;
    }

    public static PanellVols getPanell() throws InterruptedException {// -------patrón Singleton
        if (panell == null) {
            panell = new PanellVols();
        }
        return panell;

    }

}
