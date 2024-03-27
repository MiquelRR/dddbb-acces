package clases;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    private static String[] capsa = new String[] { "MENU", "MODELS AVIONS", "OR: TOTS DT: TOTS" };
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
    private static String[] validOpts;
    private static String[] validCodis;
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
    private static List<String[]> volsMostrats;

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
        if (opcions.keySet().contains(c)) {
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
        if (capsa[2].equals("PAÏSOS") || capsa[2].startsWith("AEROPORTS") || capsa[2].startsWith("OR:")) {
            x = h * 2 / 3 + 2;
            y = 5;
            panell.setCursor(x, y);
            for (int i = 5; i < v - 1; i++) {
                panell.situa(" ".repeat(h / 3 - 2));
            }
            y++;
            x += 2;
            panell.setCursor(x, y);
        }
        String[] cp = capsa[2].split(" "); // separa capçalera 2
        if (cp[0].equals("OR:")) {
            int idx = 1; // index per a validar
            x--;
            panell.setCursor(x, y);
            volsMostrats = Accesdb.lligTaula("Vuelos");
            if (!cp[1].equals("TOTS")) {
                volsMostrats = volsMostrats.stream().filter(arr -> cp[1].equals(arr[1])).collect(Collectors.toList());
                idx = 2;
            }
            if (!cp[3].equals("TOTS")) {
                volsMostrats = volsMostrats.stream().filter(arr -> cp[3].equals(arr[2])).collect(Collectors.toList());
                idx = 3;
            }
            validCodis = new String[volsMostrats.size()];
            int i = 0;
            panell.situa("  ORIGE       DESTINCIÓ     DATA", 'c');
            for (String[] r : volsMostrats) {
                String or_pais = Accesdb.lligReg(String.format(Accesdb.paisdecCodi, r[1]))[0];
                or_pais = String.format("%-8s", or_pais).substring(0, 8);
                String de_pais = Accesdb.lligReg(String.format(Accesdb.paisdecCodi, r[2]))[0];
                de_pais = String.format("%-8s", de_pais).substring(0, 8);
                panell.situa(r[1] + "-" + or_pais + " " + r[2] + "-" + de_pais + " " + r[3], 'w');
                validCodis[i++] = r[idx];
            }
        }

        if (capsa[2].equals("PAÏSOS")) {
            x--;
            y = 5;
            for (String pais : paisos) {
                panell.situa(x, y, pais);
                if (y == v - 2) {
                    y = 4;
                    x += 12;
                }
                y++;
            }
        }
        if (capsa[2].startsWith("AEROPORTS")) {
            for (String[] registre : aeroportsPais) {
                panell.situa(registre[0] + "  " + registre[3]);
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
            validOpts = new String[pass.size()];
            int i = 0; // omplim llista validadora
            for (String[] pas : pass) {
                y++;
                panell.situa(x, y, pas[2], 'w');
                panell.situa(h * 2 / 3 - 10, y, String.format("%10s", pas[1]), 'w');
                validOpts[i++] = pas[1];
            }
            y++;
            yp = y;
            panell.cursor(x, y);
        }
        // nou Passatger document
        if (capsa[1].equals("INTRODUEIX EL DOCUMENT")) {
            panell.cursor(h * 2 / 3 - 11, yp);
        }
        // nou avio Nom Model
        if (capsa[1].equals("MODELS AVIONS")) {
            x = h / 3 + 3;
            y = 6;
            panell.situa(x, y, "Afig nou avió:", 'c');
            panell.situa(h * 2 / 3 - 7, y, "Places", 'c');
            validOpts = new String[Avio.flota.size()];
            int i = 0; // omplim llista validadora
            for (String v : Avio.flota.keySet()) {
                panell.situa(x, ++y, v, 'w');
                panell.situa(h * 2 / 3 - 4, y, String.format("%3d", Avio.flota.get(v)), 'w');
                validOpts[i++] = v;
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
        if (capsa[2].split(" ")[0].equals("AEROPORTS")) {

        }
        return new int[] { x, y };

    }

    public void reservaVol() {
        peu = "";
        String volId = null;
        capsa[1] = "PASSATGERS";
        capsa[2] = "OR: TOTS DT: TOTS";
        composa('3');
        if (validOpts.length > 0) {
            if (validCodis.length > 0) {
                panell.cursor(7, 22);
                String model = panell.getString("Passatger DOCUMENT :", validOpts);
                panell.cursor(7, 23);
                String or = panell.getString("Aeroport Orige (CODI) : ", validCodis);
                capsa[2] = "OR: " + or + " DT: TOTS";
                peu = "Info: aeroport orige " + or;
                composa('3');
                if (volsMostrats.size() > 1) {
                    panell.cursor(7, 24);
                    String dt = panell.getString("Aeroport Dest. (CODI) : ", validCodis);
                    capsa[2] = "OR: " + or + " DT: " + dt;
                    peu = "Info: aeroport orige " + or + " i destinacio " + dt;
                } else {
                    panell.situa("Aeroport Dest. (CODI) : " + volsMostrats.get(0)[3]);
                }
                panell.cursor(7, 25);
                for (String[] vol : volsMostrats) {
                    String res = panell.getString(vol[3] + " (S/N) : ", new String[] { "S", "N" });
                    if (res.charAt(0) == 'S') {
                        volId = vol[0];
                        break;
                    }
                }
                composa('3');
            } else
                peu = ": cal fer altes de vols.";
        } else
            peu = ": cal tindre passatgers d'alta.";
        if (volId == null) {
            peu = "Advertència: Ningún vol sel·leccionat" + peu;
            
        } else {
            composa('3');
            /////////////////// TRIA SEIENT

        }
        composa('x');

    }

    public void altaVol() {
        capsa[1] = "MODELS AVIONS";
        capsa[2] = "PAÏSOS";
        peu = "";
        composa('1');
        panell.cursor(7, 22);
        if (validOpts.length > 0) {
            String model = panell.getString("Model d'avió : ", validOpts);
            Integer places = Avio.flota.get(model);
            // panell.mostra();
            panell.cursor(7, 23);
            String pais = panell.getString("Pais d'oritge : ", paisos);
            triaPais(pais);
            this.peu = "Info: Pais d'oritge " + pais;
            capsa[2] = "AEROPORTS " + pais.toUpperCase();
            panell.cursor(7, 22);
            panell.situa("PLACES PER A PASSATGERS : " + places, 'c');
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
            this.peu = "Info: Vol afegit " + data.toString() + " des de " + codiOrige + " fins " + codiDest + " amb un "
                    + model;
            capsa[2] = "VOLS";
            Vuelo vol = new Vuelo(places, codiOrige, codiDest, data);
        }
        peu="Advertència: cal donar d'alta modèls d'avió";
        composa('x');
    }

    private void triaPais(String pais) {
        aeroportsPais = Accesdb.lligQuery(String.format(Accesdb.aeroportsDe, pais));
        valCodis = new String[aeroportsPais.size()];
        int i = 0;
        for (String[] registre : aeroportsPais) {
            valCodis[i++] = registre[0];
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
        // capsa[1] = "MODELS AVIONS";
        Avio model = new Avio(nomAvio, places);
        this.peu = "Info: Afegit model d'avió " + nomAvio + " amb " + places + " places";
    }

    public void altaPas() {
        capsa[1] = "PASSATGERS";
        this.peu = "Info: Inserta el nom (max 20 car.), enter i el document";
        composa('2');
        String nomPass = panell.getString(20);
        capsa[1] = "INTRODUEIX EL DOCUMENT";
        this.peu = "Info: El document no es pot repetir (max 10 car.)";
        composa('2');
        String doc = panell.getString(10).toUpperCase();
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
