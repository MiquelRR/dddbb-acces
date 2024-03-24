package clases;

import java.util.HashMap;

//--------------------------------patrón Singleton 
public class PanellVols extends Pantalla {
    private static PanellVols panell;// -------patrón Singleton
    private static String[] capsa = new String[] { " MENU ", " AEROPORTS ", " VOLS " }; // Capçaleres
    private static String peu = " Info: Tria una opció del menu [0..6] ";
    private final static int h = 120, v = 35;
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
    

    private PanellVols() throws InterruptedException {
        super(h + 2, v + 2);

    }
    

    public void menu(HashMap<Character, String> opcions) throws InterruptedException {
        int n = capsa.length;
        panell.marc(0, 0, h + 2, v + 2, 'd', 'w');
        AniBloc peuBl = new AniBloc(v, 1+25, 1, h-50);
        char[][] p = new char[][]{peu.toCharArray()};
        peuBl.scrDreMi(p, 'g');
        panell.addAniBloc(peuBl);
        AniBloc[] capsBl = new AniBloc[n];
        for (int i = 0; i < n; i++) {
            panell.marc(1 + (h / n) * i, 1, (h / n), v - 1, 'l', 'b');
            panell.marc(2 + (h / n) * i, 2, (h / n) - 2, 3, 'd', 'c');
            capsBl[i] = new AniBloc(3, 3 + (h / n) * i, 1, (h / n) - 4);
            char[][] q = new char[][]{capsa[i].toCharArray()};
            capsBl[i].scrEsMi(q, 'g');
            panell.addAniBloc(capsBl[i]);
        }
        AniBloc menuBloc= new AniBloc(5, 2, v-6-10, (h/n)-2);
        char[][] menuchmp = map2char(opcions);
        menuBloc.suraFinsMig(menuchmp, 'w');     
        panell.addAniBloc(menuBloc); 
        panell.mostraAnim(10);
        panell.cursor(11,((v-6-10-opcions.size())/2)+5);
        for (char c : opcions.keySet()) {
            panell.situa(""+c,'c');            
        }

    }

    private static char[][] map2char(HashMap<Character, String> mapa){
        int maxl=0, y=0;
        for (String st :mapa.values()) {
            maxl=(st.length()>maxl)?st.length():maxl;
            y++;
        }
        maxl+=6;
        char[][] res=new char[y+2][maxl];
        res[0]= String.format("%-" + maxl + "s", "  ").toCharArray();
        int i=1;
        for (char c :mapa.keySet()) {
            String cad=" ("+c+") "+mapa.get(c);
            res[i]= String.format("%-" + maxl + "s", cad).toCharArray();
            i++;
        }
        res[i]= String.format("%-" + maxl + "s", "  ").toCharArray();
        return res;
    }

    public static PanellVols getPanell() throws InterruptedException {// -------patrón Singleton
        if (panell == null) {
            panell = new PanellVols();
        }
        return panell;

    }

}
