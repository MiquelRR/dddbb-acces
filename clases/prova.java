package clases;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class prova {
    public static LocalDate getDataUsuari() {
        Scanner sc = new Scanner(System.in);
        LocalDate data = null;
        while (data == null) {
            String entrada = sc.nextLine();
            try {
                data = LocalDate.parse(entrada);
            } catch (DateTimeParseException e) {
                data = null;
            }
        }

        return data;
    }
    public static void main(String[] args) {
        System.out.print("Dame una fecha   :");
        LocalDate fecha= getDataUsuari();
    }
}
