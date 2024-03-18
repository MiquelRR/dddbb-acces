package clases;

import java.util.ArrayList;
import java.util.List;

public class AniBloc {
    private int iny = 0;
    private int inx = 0;
    private int alt;
    private int ample;
    private char[][] fondo;
    private char[][] color;
    private List<Ani> qAnis = new ArrayList<>();

    public AniBloc(int iny, int inx, int alt, int ample) {
        this.iny = iny;
        this.inx = inx;
        this.ample = ample;
        this.alt = alt;
        this.fondo = omple(alt, ample, ' ');
        this.color = omple(alt, ample, 'w');

    }

   

      //acomoda sprite al ample de la finestra amb ' '
    public char[][] fitAmple(char[][] mat){
        if (mat[0].length<this.ample){
            char[][] nova = omple(mat.length,this.ample,' ');
            int x=(this.ample-mat[0].length)/2;
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    nova[i][j+x]=mat[i][j];
                }
            }
            return nova;
        } else return mat; 
    }

    //acomoda sprite al ample de la finestra amb ' '
    public char[][] fitAlt(char[][] mat){
        if (mat.length<this.alt){
            char[][] nova = omple(this.alt,mat[0].length,' ');
            int y=(this.alt-mat.length)/2;
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    nova[i+y][j]=mat[i][j];
                }
            }
            return nova;
        } else return mat; 
    }
    
     // buida fons i posiciona objecte centrat
    public void setInmovil(char[][] spr, char color, int retard) {
        List<int[]> vectors = new ArrayList<>();
        for (int i = 0; i < retard; i++) {
            vectors.add(new int[] { 0, 0 });
        }
        char[][] sprite=this.fitAlt(this.fitAmple(spr));
        int yob = (this.alt - sprite.length) / 2;
        int xob = (this.ample - sprite[0].length) / 2;
        Ani anim = new Ani(sprite, color, vectors,yob,xob);
        this.qAnis.add(anim);
    }

    public void cauFinsMig(char[][] spr, char color) {
        List<int[]> vectors = new ArrayList<>();
        char[][] sprite=this.fitAmple(spr);
        int xob =0;
        int yob = sprite.length * -1;
        int espai = (this.alt-sprite.length) /2;
        // baixada fins enmig
        // fin baix
        for (int i = 0; i < this.alt; i++) {
            vectors.add(new int[] { 1, 0 });
        }
        for (int i = 0; i < espai; i++) { // rebot
            vectors.add(new int[] { -1, 0 });
        }
        Ani anim = new Ani(sprite, color, vectors,yob,xob);
        this.qAnis.add(anim);

    }
    public void suraFinsMig(char[][] spr, char color){
        List<int[]> vectors = new ArrayList<>();
        char[][] sprite=this.fitAmple(spr);
        int xob = 0;
        int yob = this.alt;
        int espai = (this.alt-sprite.length) /2;
        for (int i = 0; i < this.alt; i++) {
            vectors.add(new int[] { -1, 0 });
        }
        for (int i = 0; i < espai; i++) { // rebot
            vectors.add(new int[] { 1, 0 });
        }
        Ani anim = new Ani(sprite, color, vectors,yob,xob);
        this.qAnis.add(anim);
        
    }

    public void scrollEsquerre(char[][] spr, char color){
        char[][] sprite= this.fitAlt(spr);
        List<int[]> vectors = new ArrayList<>();
        int yob= (this.alt-sprite.length)/2;
        int xob= this.ample;
        for (int i = 0; i < this.ample; i++) {
            vectors.add(new int[] { 0, -1 });            
        }
        Ani anim = new Ani(sprite, color, vectors,yob,xob);
        this.qAnis.add(anim);
    }

    public void scrollDret(char[][] spr, char color){
        char[][] sprite= this.fitAlt(spr);
        List<int[]> vectors = new ArrayList<>();
        int yob= (this.alt-sprite.length)/2;
        int xob=-1*sprite[0].length;
        //mourá a la dreta
        for (int i = 0; i < this.ample; i++) {
            vectors.add(new int[] { 0, 1 });            
        }
        //rebota
        for (int i = 0; i < this.ample-sprite[0].length; i++) {
            vectors.add(new int[] { 0, -1 });
        }
        //o acaba scroll si es mes llarg
        for (int i = 0; i < sprite[0].length-this.ample; i++) {
            vectors.add(new int[] { 0, 1 });
        }
        Ani anim = new Ani(sprite, color, vectors,yob,xob);
        this.qAnis.add(anim);
    }

    public void scrDreMi(char[][] spr, char color){
        char[][] sprite= this.fitAlt(spr);
        List<int[]> vectors = new ArrayList<>();
        int yob= (this.alt-sprite.length)/2;
        int xob=-1*sprite[0].length;
        int espai=(this.ample-sprite[0].length)/2;
        for (int i = 0; i < this.ample; i++) {
            vectors.add(new int[] { 0, 1 });            
        }
        for (int i = 0; i < espai; i++) {
            vectors.add(new int[] { 0, -1 });  
        }
        Ani anim = new Ani(sprite, color, vectors,yob,xob);
        this.qAnis.add(anim);
    }
    public void scrEsMi(char[][] spr, char color){
        char[][] sprite= this.fitAlt(spr);
        List<int[]> vectors = new ArrayList<>();
        int yob= (this.alt-sprite.length)/2;
        int xob=this.ample;
        int espai=(this.ample-sprite[0].length)/2;
        for (int i = 0; i < this.ample; i++) {
            vectors.add(new int[] { 0, -1 });            
        }
        for (int i = 0; i < espai; i++) {
            vectors.add(new int[] { 0, 1 });  
        }
        Ani anim = new Ani(sprite, color, vectors,yob,xob);
        this.qAnis.add(anim);
    }
    public void scrFi(char dir,char[][] spr, char color){
        char[][] sprite= this.fitAlt(spr);
        List<int[]> vectors = new ArrayList<>();
        int yob= (this.alt-sprite.length)/2;
        int[] v;
        int xob;
        if (dir=='>'){
            xob=-1*sprite[0].length;
            v=new int[] { 0, 1 };
        } else{
            xob=this.ample;
            v=new int[] { 0, -1 };
        }
        for (int i = 0; i < this.ample+sprite[0].length; i++) {
            vectors.add(v);            
        }
        Ani anim = new Ani(sprite, color, vectors,yob,xob);
        this.qAnis.add(anim);
    }

    // selecciona un increment dels vectors i aplica l'imatge al fons en la nova posició
    public boolean frame() {
        if (!this.qAnis.isEmpty()) {
            if(this.qAnis.get(0).aplica()) {
                this.qAnis.remove(0);
                if(!this.qAnis.isEmpty()){
                    this.qAnis.get(0).aplica(); // no poden haver llistes buides
                } 
            }
        } 
        if(!this.qAnis.isEmpty()){
        posiciona(this.qAnis.get(0).getYob(), this.qAnis.get(0).getXob(),
        this.qAnis.get(0).getSprite(), this.qAnis.get(0).getColor());
        return true;
        } return false;
    }

    private void posiciona(int idy, int idx, char[][] obj, char color) {
        for (int i = 0; i < obj.length; i++) {
            for (int j = 0; j < obj[0].length; j++) {
                posaPix(idy+i, idx+j, obj[i][j], color);

            }
            
        }

    }
    private void posaPix(int y, int x, char c, char col){
        if (y>=0 && y< this.alt && x>=0 && x<this.ample){
            this.fondo[y][x]=c;
            this.color[y][x]=col;
        }
    }

    private char[][] omple(int alt, int ample, char c) {
        char[][] res = new char[alt][ample];
        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < ample; j++) {
                res[i][j] = c;
            }
        }
        return res;
    }

    public int getIny() {
        return iny;
    }

    public int getInx() {
        return inx;
    }

    public char[][] getFondo() {
        return fondo;
    }

    public char[][] getColor() {
        return color;
    }

    public int getAlt() {
        return this.alt;
    }


    public int getAmple() {
        return this.ample;
    }

}
