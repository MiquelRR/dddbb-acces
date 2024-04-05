package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

//--------------------------------patrón Singleton 
public class PanellVols extends Pantalla {
    private static Scanner sc = new Scanner(System.in);
    private static PanellVols panell;// -------patrón Singleton
    private static int h = 120, v = 38, vf = (v - 4 - 2); // vf son les linees de la zona de dades
    private static int mig = 5 + vf / 2; // posicio de la linea central de la zona de dades

    private PanellVols() throws InterruptedException {
        super(h + 2, v + 2);

    }

    private static String[] asciiCap = new String[4];
    static {
        asciiCap[0] = "    .-^^^^^^^^^^^^^^^-.";
        asciiCap[1] = "  .'                   '.";
        asciiCap[2] = " /                       \\";
        asciiCap[3] = ";                         ;";
    }

    private static Map<Character, Integer> seientsLloc = new HashMap<Character, Integer>();
    static {
        seientsLloc.put('A', 1);
        seientsLloc.put('B', 5);
        seientsLloc.put('C', 9);
        seientsLloc.put('D', 15);
        seientsLloc.put('E', 19);
        seientsLloc.put('F', 23);
    }
    private static String finestres = "[                         ]";
    private static String[] asciiCua = new String[4];
    static {
        asciiCua[0] = ";                         ;";
        asciiCua[1] = " \\                       / ";
        asciiCua[2] = "  '._                 _.'";
        asciiCua[3] = "     ``-------------``";
    }
    private String peu = " Info: Tria una opció del menu [0..6] ";
    private static HashMap<Character, String> opcions = new HashMap<>();
    private static List<String> opval = Arrays.asList("0", "1", "2", "3", "4", "5", "6");
    private static List<String> sino = Arrays.asList("S", "s", "Si", "SI", "si", "N", "n", "NO", "No", "no");
    private static String[] capsa = new String[] { "MENU", "PASSATGERS", "OR: TOT DT: TOT" };
    private static int n = capsa.length;
    private static String selPassNom = "";
    private static String selPassDoc = "*";

    static {
        opcions.put(opval.get(0).charAt(0), "Alta Model d'Avió");
        opcions.put(opval.get(1).charAt(0), "Alta Vol");
        opcions.put(opval.get(2).charAt(0), "Alta Passatger");
        opcions.put(opval.get(3).charAt(0), "Reserva Vol");
        opcions.put(opval.get(4).charAt(0), "Modifica Reserva");
        opcions.put(opval.get(5).charAt(0), "Baixa Reserva");
        opcions.put(opval.get(6).charAt(0), "Ix de l'Aplicació");

    }
    private static List<String> paisos = new ArrayList<>();
    private static List<String> validOpts = new ArrayList<>();
    private static List<String> validCodis = new ArrayList<>();
    static {
        List<String[]> paiss = Accesdb.lligQuery(Accesdb.paisos);
        // paisos.clear();
        for (String[] pais : paiss) {
            paisos.add(pais[0]);
        }
    }
    private static List<String[]> aeroportsPais;
    private static List<String[]> volsMostrats;
    private static List<String[]> mapavio;
    private static List<String[]> reserves;
    private static int maxpl = 6 * (vf);

    private static int xa = 0, ya = 0;

    public Character getValidMenuChar() {
        return panell.getString("     --> ", opval).charAt(0);

    }

    public void menu() throws InterruptedException {
        borraZona(0);
        panell.situa(1, v, " ".repeat(h), 'w'); // borra peu
        int n = capsa.length;
        AniBloc peuBl = new AniBloc(v, 1 + 25, 1, h - 50);
        char[][] p = new char[][] { (" " + peu + " ").toCharArray() };
        peuBl.scrDreMi(p, 'g');
        panell.addAniBloc(peuBl);
        AniBloc[] capsBl = new AniBloc[n];
        // marcs
        panell.marc(0, 0, h + 2, v + 2, 'd', 'w');
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
        panell.setCursor(11, ((v - 6 - 10 - opcions.size()) / 2) + 5);
        for (char c : opcions.keySet()) {
            panell.situa("" + c, 'c');
        }

    }

    private void borraZona(int n) {
        // borrar zona
        int x = (h / 3) * n + 2;
        panell.setCursor(x, 5);
        for (int i = 5; i < v - 1; i++) {
            panell.situa(" ".repeat(h / 3 - 2), 'w');
        }
    }

    private void composa(char c) {
        int x = 0, y = 0;
        // resalta opcio triada impresio en cyan
        if (opcions.keySet().contains(c)) {
            panell.situa(14, 12 + c - 48, opcions.get(c), 'c'); // Y SENSE PARAMETRITZAR
        }
        // borrar zona
        if (capsa[1].startsWith("INFO:") || capsa[1].equals("MODELS AVIONS") || capsa[1].startsWith("PASSATGERS")
                || capsa[1].equals("SEIENTS AVIÓ"))
            borraZona(1);
        if (capsa[2].equals("PAÏSOS") || capsa[2].startsWith("AEROPORTS") || capsa[2].startsWith("OR:"))
            borraZona(2);
        if (capsa[1].equals("SEIENTS AVIÓ")) {
            int files = Integer.parseInt(mapavio.get(mapavio.size() - 1)[1].substring(1));
            y = mig - files / 2 - asciiCap.length;
            x = (h / 2) - (asciiCap[asciiCap.length - 1].length() / 2);
            panell.setCursor(x, y);
            for (String linea : asciiCap) {
                panell.situa(linea, 'w');
            }
            y += 3;
            char filant = '0';
            char color = 'w';
            validOpts.clear();
            for (String[] plaza : mapavio) {
                if (filant != plaza[1].charAt(2)) {
                    y++;
                    filant = plaza[1].charAt(2);
                    panell.situa(x, y, finestres, 'c');
                }
                if (plaza[4].equals("no")) {
                    color = 'w';
                    validOpts.add(plaza[1]);
                } else {
                    if (selPassDoc.equals(plaza[2])) {
                        color = 'c';
                    } else {
                        color = 'n';
                    }

                }

                situa(x + seientsLloc.get(plaza[1].charAt(0)), y, plaza[1], color);
            }
            panell.setCursor(x, ++y);
            for (String linea : asciiCua) {
                panell.situa(linea, 'w');
            }
            // selPassDoc="*";
        }
        String[] cp = capsa[2].split(" "); // separa capçalera 2
        if (cp[0].equals("OR:")) {
            int idx = 1; // index per a validar
            x = h / n * 2 + 3;
            y = 5;
            panell.setCursor(x, y);
            volsMostrats = Accesdb.lligTaula("Vuelos");

            if (!cp[1].equals("TOT")) {// filtra per orige
                volsMostrats = volsMostrats.stream().filter(regVol -> cp[1].equals(regVol[1]))
                        .collect(Collectors.toList());
                idx = 2;
            }
            if (!cp[3].equals("TOT")) {// afig filtre per destinacio
                volsMostrats = volsMostrats.stream().filter(regVol -> cp[3].equals(regVol[2]))
                        .collect(Collectors.toList());
                // idx = 2;
            }

            if (validCodis != null)
                validCodis.clear();

            panell.situa("  ORITGE      DESTINACIÓ     DATA", 'c');
            List<Integer> volsPassatger = Accesdb.lligQuery2Integers(String.format(Accesdb.volsPas, selPassDoc));
            List<Integer> volsCompletsIdx = new ArrayList<>();
            for (String[] r : volsMostrats) {
                String or_pais = Accesdb.lligReg(String.format(Accesdb.paisdecCodi, r[1]))[0];
                or_pais = String.format("%-8s", or_pais).substring(0, 8);
                String de_pais = Accesdb.lligReg(String.format(Accesdb.paisdecCodi, r[2]))[0];
                de_pais = String.format("%-8s", de_pais).substring(0, 8);
                boolean hiHaPlaces = Integer.parseInt(
                        Accesdb.lligQuery(String.format(Accesdb.lliures, Integer.parseInt(r[0]))).get(0)[0]) > 0;
                char color;
                if (volsPassatger.contains(Integer.parseInt(r[0]))) {
                    color = 'g';
                    volsCompletsIdx.add(volsMostrats.indexOf(r));
                } else {
                    if (hiHaPlaces) {
                        color = 'w';
                        validCodis.add(r[idx]);
                    } else {
                        color = 'r';
                        volsCompletsIdx.add(volsMostrats.indexOf(r));
                    }
                }
                panell.situa(r[1] + "-" + or_pais + " " + r[2] + "-" + de_pais + " " + r[3], color);

            }
            // treim de la llista de vols els no sel·leccionables, se ordena la llista
            // alreves per eliminar sense pegues
            Collections.sort(volsCompletsIdx, Collections.reverseOrder());
            for (int i : volsCompletsIdx) {
                volsMostrats.remove(i);
            }

        }

        if (capsa[2].equals("PAÏSOS")) {
            x = h / n * 2 + 2;
            y = 5;
            for (String pais : paisos) {
                panell.situa(x, y, pais, 'w');
                if (y == v - 2) {
                    y = 4;
                    x += 12;
                }
                y++;
            }
        }
        if (capsa[1].startsWith("INFO:")) {
            selPassDoc = capsa[1].split(" ")[1];
            x = h / 3 + 3;
            y = 5;
            panell.situa(x, y, "Nom:", 'c');
            panell.situa(h * 2 / 3 - 9, y, "Document", 'c');
            panell.situa(h * 2 / 3 - 9 - 5, y++, "res", 'n');
            selPassNom = Accesdb.lligReg(String.format(Accesdb.pasatger, selPassDoc))[1];
            panell.situa(x, y, selPassNom, 'w');
            String res = Accesdb.lligReg(String.format(Accesdb.contaVolsPas, selPassDoc))[0];
            panell.situa(h * 2 / 3 - 9 - 5, y, String.format("%3s", res), 'n');
            panell.situa(h * 2 / 3 - 10, y++, String.format("%10s", selPassDoc), 'w');
            panell.situa(x, ++y, "Reserves :", 'c');
            y++;
            reserves = Accesdb.lligQuery(String.format(Accesdb.reservesPas, selPassDoc));
            Integer idx = 1;
            if (validOpts.size() > 0)
                validOpts.clear();
            for (String[] rese : reserves) {
                boolean hiHaPlaces = Integer.parseInt(
                        Accesdb.lligQuery(String.format(Accesdb.lliures, Integer.parseInt(rese[0]))).get(0)[0]) > 0;
                char color = (!hiHaPlaces && c == '4') ? 'n' : 'c';
                panell.situa(x + 33, ++y, rese[6], 'g');
                panell.situa(x + 4, y, rese[1] + "-" + rese[2] + " " + rese[3] + "-" + rese[4], 'w');
                panell.situa(x, y++, "#" + idx, color);
                panell.situa(x + 4, y, rese[5], 'w');
                if (!hiHaPlaces) {
                    panell.situa(x + 20, y, "COMPLET", 'r');
                }
                y++;
                if (color != 'n')
                    validOpts.add(idx.toString());
                idx++;
            }
        }

        if (capsa[2].startsWith("AEROPORTS")) {
            x = h / n * 2 + 5;
            y = 5;
            panell.setCursor(x, y);
            for (String[] registre : aeroportsPais) {
                panell.situa(registre[0] + "  " + registre[3], 'w');
            }
        }

        // Nou Passatger Nom
        if (capsa[1].startsWith("PASSATGERS")) {
            x = h / 3 + 3;
            y = 5;
            panell.situa(x, y, "Nom:", 'c');
            panell.situa(h * 2 / 3 - 9, y, "Document", 'c');
            panell.situa(h * 2 / 3 - 9 - 5, y, "res", 'n');
            // soy un pringao, Patxi, a este no le he hecho hashmap, pero quiero ir probando
            List<String[]> pass;
            if (capsa[1].equals("PASSATGERS ACTIUS")) {
                pass = Accesdb.lligQuery(Accesdb.passatgersActius);

            } else {
                pass = Accesdb.lligTaula("Pasajeros");
            }
            if (validOpts != null)
                validOpts.clear();
            for (String[] pas : pass) {
                y++;
                panell.situa(x, y, pas[1], 'w');
                String res = Accesdb.lligReg(String.format(Accesdb.contaVolsPas, pas[0]))[0];
                panell.situa(h * 2 / 3 - 9 - 5, y, String.format("%3s", res), 'n');
                panell.situa(h * 2 / 3 - 10, y, String.format("%10s", pas[0]), 'w');
                validOpts.add(pas[0]);
            }
            y++;
            ya = y;
            xa = x;
        }
        // nou Passatger document
        if (capsa[1].equals("INTRODUEIX EL DOCUMENT"))
            xa = h * 2 / 3 - 10;

        // nou avio Nom Model
        if (capsa[1].equals("MODELS AVIONS")) {
            x = h / 3 + 3;
            y = 6;
            panell.situa(x, y, "Afig nou avió:", 'c');
            panell.situa(h * 2 / 3 - 7, y, "Places", 'c');
            if (validOpts != null)
                validOpts.clear();
            ;
            int i = 0; // omplim llista validadora
            for (String v : Avio.flota.keySet()) {
                panell.situa(x, ++y, v, 'w');
                panell.situa(h * 2 / 3 - 4, y, String.format("%3d", Avio.flota.get(v)), 'w');
                validOpts.add(v);
            }
            y++;
            xa = x;
            ya = y;
        }
        // nou avio places
        if (capsa[1].equals("PLACES PER PASSATGERS"))
            xa = h * 2 / 3 - 4;

        // rescriu les capçaleres i marcs
        panell.marc(0, 0, h + 2, v + 2, 'd', 'w');
        for (int i = 0; i < n; i++) {
            panell.marc(1 + (h / n) * i, 1, (h / n), v - 1, 'l', 'b');
            panell.marc(2 + (h / n) * i, 2, (h / n) - 2, 3, 'd', 'c');
            panell.situa(3 + ((h / n) * i), 3, " ".repeat(((h / n) - 4)), 'w');
            panell.situa(3 + ((h / n) - 4 - capsa[i].length()) / 2 + ((h / n) * i), 3, capsa[i], 'g');
        }
        // rescriu el peu
        panell.situa(1, v, " ".repeat(h), 'n');
        panell.situa((h - peu.length()) / 2, v, peu, 'g');

        panell.setUltcol('w');
        panell.setCursor(xa, ya);
        // return new int[] { xa, ya };

    }

    public void alliberaSeient() {
        int rs = triaReservaPassatger('5', "Anul·la");
        if (rs > -1) {
            String id_plaza = reserves.get(rs)[7];
            String alliberada = reserves.get(rs)[6];
            Accesdb.modifica(String.format(Accesdb.llibera, id_plaza));
            peu = "Info: plaza alliberada " + alliberada;
            composa('x');
            mostra();
        } else
            peu = "Advertència: no hi han passatgers amb reserves";

    }

    private int triaReservaPassatger(char menuopt, String accioText) {
        selPassDoc = "*";
        peu = "Info: Únicament són seleccionables els passatgers que tenen reserves";
        String document = "";
        capsa[1] = "PASSATGERS ACTIUS";
        capsa[2] = "OR: TOT DT: TOT";
        composa(menuopt);
        int rs = -1;
        if (validOpts.size() > 0) {
            panell.setCursor(7, 22);
            document = panell.getString("Passatger DOCUMENT :", validOpts).strip();
            validOpts.clear();
            capsa[1] = "INFO: " + document;
            composa(menuopt);
            if (validOpts.size() > 0) {
                peu = "Info: ingresa una reserva entre " + validOpts.get(0) + " i "
                        + validOpts.get(validOpts.size() - 1) + ((menuopt == '4') ? " excepte vols complets" : "");
                composa(menuopt);
                mostra();
                panell.setCursor(7, 23);
                rs = Integer.parseInt(panell.getString(accioText + " reserva #", validOpts)) - 1;
            }
        }
        return rs;
    }

    public void editaSeient() {
        selPassDoc = "*";
        int rs = triaReservaPassatger('4', "Modifica seient");
        if (rs > -1) {
            String id_vol = reserves.get(rs)[0];
            String id_plaza = reserves.get(rs)[7];
            String document = reserves.get(rs)[8];
            String alliberada = reserves.get(rs)[6];
            mapavio = Accesdb.lligQuery(String.format(Accesdb.placesVol, id_vol));

            capsa[1] = "SEIENTS AVIÓ";
            peu = "Info: tria un seient lliure entre els codis de color blanc";
            composa('4');
            mostra();
            panell.setCursor(7, 24);
            String pl = panell.getString("Nou seient :", validOpts);

            Accesdb.modifica(String.format(Accesdb.ocupa, document, id_vol, pl));
            Accesdb.modifica(String.format(Accesdb.llibera, id_plaza));
            peu = "Info: plaza alliberada " + alliberada + ", nou seient " + pl;
            // INNEFICIENT: deuria d'actualitzar mapavio
            // mapavio = Accesdb.lligQuery(String.format(Accesdb.placesVol, id_vol));
            // composa('x');
            // mostra();
            capsa[1] = "INFO: " + document;
        } else
            peu = "Advertència: no es possible fer canvis de lloc";

    }

    public void reservaVol() {
        int y = 25;
        this.peu = "";
        String volId = null, document = "", or = "", dt = "";
        capsa[1] = "PASSATGERS";
        capsa[2] = "OR: TOT DT: TOT";
        composa('3');
        if (validOpts.size() > 0) {
            if (validCodis.size() > 0) {
                panell.setCursor(7, 22);
                document = panell.getString("Passatger DOCUMENT :", validOpts);
                selPassDoc = document;
                peu = "Info: en roig son vols complets, en groc son vols on ja está enregistrat el pasatger "
                        + selPassDoc;
                composa('3');
                mostra();
                if (validCodis.size() > 0) {
                    panell.setCursor(7, 23);
                    or = panell.getString("Aeroport Orige (CODI) : ", validCodis);
                    capsa[2] = "OR: " + or + " DT: TOT";
                    peu = "Info: aeroport orige " + or;
                    composa('3');

                    if (volsMostrats.size() > 1) {
                        panell.setCursor(7, 24);
                        dt = panell.getString("Aeroport Dest. (CODI) : ", validCodis);
                        capsa[2] = "OR: " + or + " DT: " + dt;
                        peu = "Info: aeroport orige " + or + " i destinacio " + dt;
                    } else {
                        capsa[1] = "PASSATGERS";
                        panell.situa("Aeroport Dest. (CODI) : " + volsMostrats.get(0)[3], 'w');
                    }
                    composa('3');
                    panell.setCursor(7, y);
                    for (String[] vol : volsMostrats) {
                        String res = panell.getString(vol[3] + " (s/n) : ", sino);
                        y++;
                        if (res.toUpperCase().charAt(0) == 'S') {
                            volId = vol[0];
                            break; // Gracias Patxi: ya tenemos permiso para usarlo!
                        }
                    }
                    composa('3');
                    mostra();
                } else
                    peu = "Advertència: el pasatger ja esta volant a tots els vols.";
            } else
                peu = "Advertència: cal fer altes de vols.";
        } else
            peu = "Advertència: cal tindre passatgers d'alta.";
        if (volId == null) {
            peu = "Advertència: Ningún vol sel·leccionat";
            capsa[2] = "OR: TOT DT: TOT";

        } else {
            peu = "Info: SEIENTS AVIÓ per exemple, A01";
            capsa[1] = "SEIENTS AVIÓ";
            mapavio = Accesdb.lligQuery(String.format(Accesdb.placesVol, volId));
            composa('3');
            panell.setCursor(7, y);
            String res = panell.getString(" Plaça desitjada : ", validOpts);
            peu = "Info: Reserva realitzada per " + document + " al seient " + res;
            Accesdb.modifica(String.format(Accesdb.ocupa, document, volId, res));
            for (String[] reg : mapavio) { // marcat en local el preview
                if (reg[1].equals(res)) {
                    reg[4] = "si";
                    reg[2] = document;
                }
            }
            composa('x');
            panell.mostra();
            capsa[1] = "ÚLTIMA RESERVA :" + res;
        }
        capsa[1] = "INFO: " + document;

    }

    public void altaVol() {
        capsa[1] = "MODELS AVIONS";
        capsa[2] = "PAÏSOS";
        peu = "";
        composa('1');
        panell.setCursor(7, 22);
        if (validOpts.size() > 0) {
            String model = panell.getString("Model d'avió : ", validOpts);
            Integer placesp = Avio.flota.get(model);
            // panell.mostra();
            panell.setCursor(7, 23);
            String pais = panell.getString("Pais d'oritge : ", paisos);
            triaPais(pais);
            this.peu = "Info: Pais d'oritge " + pais;
            capsa[2] = "AEROPORTS " + pais.toUpperCase();
            panell.setCursor(7, 22);
            panell.situa("PLACES PER A PASSATGERS : " + placesp, 'c');
            composa('1');
            panell.setCursor(7, 23);
            panell.situa(" ".repeat(31), 'w');
            panell.setCursor(7, 23);
            String codiOrige = panell.getString("Codi Aeroport Orige : ", validCodis);
            this.peu = "Info: Aeroport orige triat " + codiOrige;
            capsa[2] = "PAÏSOS";
            composa('1');
            if (validCodis.size() > 0)
                validCodis.clear();
            do {
                capsa[2] = "PAÏSOS";
                composa('1');
                panell.setCursor(7, 24);
                panell.situa(" ".repeat(32));
                panell.setCursor(7, 24);
                pais = panell.getString("Pais destinació : ", paisos);
                triaPais(pais);
                panell.composa('1');
                peu = "Info: Tria codi aeroport a " + pais;
                capsa[2] = "AEROPORTS " + pais.toUpperCase();
                panell.composa('1');
                if (validCodis.contains(codiOrige)) {
                    validCodis.remove(codiOrige);
                    if (validCodis.size() == 0) {
                        peu = "Advertència: vol circular impossible, tria un altre pais";
                        panell.composa('1');
                    }
                }
                panell.composa('1');
            } while (validCodis.size() == 0); // Hi han Paisos en un sol aeroport!
            composa('1');
            panell.setCursor(7, 24);
            panell.situa(" ".repeat(31), 'w');
            panell.setCursor(7, 24);
            String codiDest = panell.getString("Codi Aeroport destinació : ", validCodis);
            this.peu = "Info: Aeroport destinació triat " + codiDest;
            composa('1');
            panell.setCursor(7, 25);
            LocalDate data = panell.getLocalDate("Data de vol :");
            this.peu = "Info: Vol afegit " + data.toString() + " des de " + codiOrige + " fins " + codiDest + " amb un "
                    + model;
            capsa[2] = "OR: TOT DT: TOT";
            capsa[1] = "SEIENTS AVIÓ";
            if (mapavio != null)
                mapavio.clear();
            mapavio = Vuelo.generaPlaces(placesp);
            Vuelo vol = new Vuelo(placesp, codiOrige, codiDest, data);

        } else
            peu = "Advertència: cal donar d'alta modèls d'avió";

        composa('x');
        selPassDoc = "*";
    }

    private void triaPais(String pais) {
        aeroportsPais = Accesdb.lligQuery(String.format(Accesdb.aeroportsDe, pais));
        validCodis.clear();
        for (String[] registre : aeroportsPais) {
            validCodis.add(registre[0]);
        }

    }

    public void nouAvio() {
        capsa[1] = "MODELS AVIONS";
        this.peu = "Info: Enter per a tabular";
        composa('0');
        String nomAvio = panell.getStringExcept(validOpts);
        capsa[1] = "PLACES PER PASSATGERS";
        this.peu = "Info: No es poden repetir amb el mateix nom, mínim 10 Màxim " + maxpl + " places";
        composa('0');
        int places = panell.getInteger(10, maxpl);
        Avio model = new Avio(nomAvio, places); // objecte sense us mes que donar l'alta a la bbdd
        this.peu = "Info: Afegit model d'avió \"" + nomAvio + "\" amb " + places + " places";
        capsa[1] = "SEIENTS AVIÓ";
        if (mapavio != null)
            mapavio.clear();
        mapavio = Vuelo.generaPlaces(places);
        composa('x');
        selPassDoc = "*";
    }

    public void altaPas() {
        capsa[1] = "PASSATGERS";
        this.peu = "Info: Inserta el nom (max 20 car.), enter i el document";
        composa('2');
        String nomPass = panell.getString(20);
        capsa[1] = "INTRODUEIX EL DOCUMENT";
        this.peu = "Info: El document no es pot repetir (max 10 car.)";
        composa('2');
        String doc = "";
        do {
            doc = panell.getString(10).toUpperCase();
            panell.lineUp();
        } while (validOpts.contains(doc));
        Pasajero pas = new Pasajero(nomPass, doc);
        capsa[1] = "PASSATGERS";
        this.peu = "Info: Afegit passager " + nomPass + " amb document " + doc;
        selPassDoc = doc;
        capsa[1] = "INFO: " + doc;

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
