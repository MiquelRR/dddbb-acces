package clases;

public class prt {
    public static void main(String[] args) {
        String cadenaOriginal = "Hola  amigo verfeedñlkjk5555555";
        int longitudDeseada = 10;

        // Rellena la cadena con espacios a la derecha hasta que tenga la longitud deseada
        String cadenaFormateada = String.format("%-" + longitudDeseada + "s", cadenaOriginal);

        // Alternativamente, puedes usar el método format() de String:
        // String cadenaFormateada = String.format("%-" + longitudDeseada + "s", cadenaOriginal);

        // Imprime la cadena formateada
        System.out.println("Cadena original: '" + cadenaOriginal + "'");
        System.out.println("Cadena formateada: '" + cadenaFormateada + "'");
    }
}
