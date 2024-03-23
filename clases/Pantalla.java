package clases;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;

public class Pantalla {
    private static Scanner sc = new Scanner(System.in);
    private int hor;
    private int ver;
    private int cx; // cursor x
    private int cy; // cursor y
    private char[][] chars;
    private char[][] cols;
    private char ultcol;
    private List<AniBloc> animacions = new ArrayList<>();

    public Pantalla(int hor, int ver) {
        this.hor = hor;
        this.ver = ver;
        this.chars = new char[hor][ver];
        this.cols = new char[hor][ver];
        this.ultcol = 'w';
        this.cx = 0;
        this.cy = 0;
        this.borra();

    }

    final public static Map<Character, String> colors = Map.of(
            'x', "\u001B[0m", // reset
            'r', "\u001B[31m", // roig
            'v', "\u001B[32m", // vert
            'g', "\u001B[33m", // groc
            'b', "\u001B[34m", // blau
            'l', "\u001B[35m", // lila
            'c', "\u001B[36m", // cyan
            'w', "\u001B[37m", // blanc
            'n', "\u001B[90m" // gris
    );

    final private static Map<Character, char[]> quadre = new HashMap<>();
    static {
        quadre.put('b', new char[] { '█', '█', '█', '█', '█', '█' }); // bloc
        quadre.put('l', new char[] { '┌', '┐', '┘', '└', '│', '─', }); // linea
        quadre.put('d', new char[] { '╔', '╗', '╝', '╚', '║', '═', }); // doble
    }

    private boolean instant(AniBloc bloc) {
        boolean viu = bloc.frame();
        int y = bloc.getIny(), x = bloc.getInx();
        for (int i = 0; i < bloc.getAlt(); i++) {
            for (int j = 0; j < bloc.getAmple(); j++) {
                posa(x + j, y + i, bloc.getFondo()[i][j], bloc.getColor()[i][j]);

            }
        }
        return viu;
    }

    protected void clear2() { // provoca mes parapadeig que print("\033[H")
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder processBuilder;
            if (os.contains("win")) { // Windows
                processBuilder = new ProcessBuilder("cmd", "/c", "cls");
            } else { // Unix/Linux/Mac
                processBuilder = new ProcessBuilder("clear");
            }
            Process process = processBuilder.inheritIO().start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void clear() {
        System.out.print("\033[H");
    }

    public void situa(int x, int y, String texte, char color) {
        int av = 0;
        for (char c : texte.toCharArray()) {
            if (c == '\n') {
                av = 0;
                y++;
            } else {
                this.posa(x + av, y, c, color);
                av++;
            }
        }
        // ojito al bug: sense this fa comportaments extranys
        this.cy = y + 1;
        this.cx = x;
    }

    // sobrecàrrega sense color
    public void situa(int x, int y, String texte) {
        this.situa(x, y, texte, this.ultcol);
    }

    public void cursor(int x, int y) {
        this.cx = x;
        this.cy = y;
    }

    // sobrecàrrega sense posició
    public void situa(String texte, char color) {
        this.situa(this.cx, this.cy, texte, color);
    }

    // sobrecàrrega sense posició ni color
    public void situa(String texte) {
        this.situa(this.cx, this.cy, texte, this.ultcol);
    }

    private void posa(int x, int y, char ch, char col) {
        if (x >= 0 && x < this.hor && y >= 0 && y < this.ver) {
            this.cols[x][y] = col;
            this.chars[x][y] = ch;
        }
        this.ultcol = col;
    }

    public void marc(int x, int y, int hor, int ver, char l, char col) {
        hor = (hor < 2) ? 2 : hor - 1;
        ver = (ver < 2) ? 2 : ver - 1;
        this.posa(x, y, quadre.get(l)[0], col);
        this.posa(x + hor, y, quadre.get(l)[1], col);
        this.posa(x + hor, y + ver, quadre.get(l)[2], col);
        this.posa(x, y + ver, quadre.get(l)[3], col);
        for (int i = x + 1; i < x + hor; i++) {
            this.posa(i, y, quadre.get(l)[5], col);
            this.posa(i, y + ver, quadre.get(l)[5], col);
        }
        for (int i = y + 1; i < y + ver; i++) {
            this.posa(x, i, quadre.get(l)[4], col);
            this.posa(x + hor, i, quadre.get(l)[4], col);
        }

    }

    public void marc() {
        this.marc(0, 0, this.hor, this.ver, 'l', this.ultcol);
    }

    public void addAniBloc(AniBloc bloc) {
        this.animacions.add(bloc);
    }

    public void mostraAnim(int vel) throws InterruptedException {
        boolean viu = true;
        while (viu) {
            viu = false;
            for (AniBloc aniBloc : animacions) {
                viu = this.instant(aniBloc) || viu;
            }
            mostra();
            Thread.sleep(vel);
        }
    }

    public void mostra() {
        mostraF(this.hor, this.ver);
        System.out.println();

    }

    public void mostraFins(int ho, int ve) {
        mostra();
        mostraF(ho, ve);
    }

    private void mostraF(int ho, int ve) {
        this.clear();
        String gran = "";
        for (int y = 0; y <= ve - 1; y++) {
            for (int x = 0; x < this.hor; x++) {
                if (cols[x][y] != this.ultcol) {
                    this.ultcol = cols[x][y];
                    gran += colors.get('x') + colors.get(ultcol);
                    // System.out.print(colors.get('x') + colors.get(ultcol));
                }
                gran += chars[x][y];
                // System.out.print(chars[x][y]);
            }
            gran += '\n';
            // System.out.println();
        }
        ho=(ho>this.hor)?this.hor:ho;
        if (ve < this.ver) {
            for (int x = 0; x < ho; x++) {
                if (cols[x][ve] != this.ultcol) {
                    this.ultcol = cols[x][ve - 1];
                    gran += colors.get('x') + colors.get(ultcol);
                    // System.out.print(colors.get('x') + colors.get(ultcol));
                }
                gran += chars[x][ve];
                // System.out.print(chars[x][y]);
            }
        }

        System.out.print(gran);
    }
    public void situa(String[] llista){
        for (String string : llista) {
            situa(string);            
        }
    }

    public void setCursor(int x, int y) {
        x = (x < 0) ? 0 : x;
        y = (y < 0) ? 0 : y;
        this.cx = (x > this.hor) ? this.hor : x;
        this.cy = (y > this.ver) ? this.ver : y;
    }

    public String getString(String[] valids) {
        mostraFins(this.cx, this.cy);
        String res = sc.nextLine();
        if (Arrays.asList(valids).contains(res)) {
            situa(res);
            return res;
        } else
            return getString(valids);
    }

    public String getString() {
        mostraFins(this.cx, this.cy);
        String res = sc.nextLine();
        situa(res);
        return res;
    }
    public String getString(String pregunta,String[] valids){
        int tempcx = this.cx;
        situa(pregunta);
        cursor(this.cx + pregunta.length(), this.cy - 1);
        String res = getString(valids);
        this.cx = tempcx;
        return res;
    }

    public String getString(String pregunta) {
        int tempcx = this.cx;
        situa(pregunta);
        cursor(this.cx + pregunta.length(), this.cy - 1);
        String res = getString();
        this.cx = tempcx;
        return res;
    }

    public Integer getInteger() {
        mostraFins(this.cx, this.cy);
        String userInput = sc.nextLine();
        try {
            int val = Integer.parseInt(userInput);
            situa(Integer.toString(val));
            return val;
        } catch (NumberFormatException e) {
            return getInteger();
        }
    }

    public LocalDate getLocalDate(String pregunta) {
        int tempcx = this.cx;
        situa(pregunta);
        cursor(this.cx + pregunta.length(), this.cy - 1);
        LocalDate val = getLocalDate(true);
        this.cx = tempcx;
        return val;
    }

    public LocalDate getLocalDate() {
        return getLocalDate(true);
    }

    public LocalDate getLocalDate(boolean help) {
        if (help) {
            char tempcol = ultcol;
            situa("AAAA-MM-DD", 'n');
            this.cy--;
            this.ultcol = tempcol;
        }
        mostraFins(this.cx, this.cy);
        String userInput = sc.nextLine();
        try {
            LocalDate val = LocalDate.parse(userInput);
            situa(val.toString());
            return val;
        } catch (DateTimeParseException e) {
            return getLocalDate(true);
        }

    }

    public Integer getInteger(String pregunta) {
        int tempcx = this.cx;
        situa(pregunta);
        cursor(this.cx + pregunta.length(), this.cy - 1);
        Integer val = getInteger();
        this.cx = tempcx;
        return val;
    }

    public void borra() {
        for (int y = 0; y < this.ver; y++) {
            for (int x = 0; x < this.hor; x++) {
                this.chars[x][y] = ' ';
                this.cols[x][y] = 'w';
            }
            System.out.println();
        }
    }

    public int getHor() {
        return hor;
    }

    public int getVer() {
        return ver;
    }

}