package clases;

import java.util.ArrayList;
import java.util.List;

public class Ani {
    private int yob;
    private int xob;
    private char[][] sprite;
    private char color;
    private List<int[]> vectors = new ArrayList<>();

    public Ani(char[][] sprite, char color, List<int[]> vectors, int yob, int xob) {
        this.sprite = sprite;
        this.color = color;
        this.vectors = vectors;
        this.xob = xob;
        this.yob = yob;
    }
    //torna true si la animacio se ha acabat
    public Boolean aplica(){
        if(!this.vectors.isEmpty()){
            int[] mov = this.vectors.remove(0);
            this.yob += mov[0];
            this.xob += mov[1];
            return false;
        } else return true;
    }

    public List<int[]> getVectors() {
        return vectors;
    }

    public int getYob() {
        return yob;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }

    public int getXob() {
        return xob;
    }

    public void setXob(int xob) {
        this.xob = xob;
    }

    public char[][] getSprite() {
        return sprite;
    }

    public char getColor() {
        return color;
    }
}
