import clases.Pantalla;

public class prova {
    
    static Pantalla p = new Pantalla(30, 30);
    public static void main(String[] args) {
        p.marc();
        p.situa(10,7,"Pregunta:------------------------------",'v');
        p.situa(5,8,"Pregunta:------------------------------",'w');
        p.cursor(0, 4);
        Integer f=p.geInteger();
        System.out.println(f);
    }


}
