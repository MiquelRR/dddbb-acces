package clases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Accesdb {

    private final static String bdcon="jdbc:mysql://localhost:3306/Reserva";
    private final static String us="root";
    private final static String pw="root";

    public static String[] lligReg(String query) { // retorna el primer registre d'una consulta
        String[] eixida = null;
        try {
            Connection con = DriverManager.getConnection(bdcon,us,pw);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int cols = rs.getMetaData().getColumnCount();
            eixida = new String[cols];
            if (rs.next()) {
                for (int i = 0; i < eixida.length; i++) {
                    eixida[i] = rs.getString(i + 1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la bd: " + e.getErrorCode() + "-" + e.getMessage());
        }
        return eixida;
    }

    public static List<String[]> lligTaula(String query) { // retorna la taula d'una consulta
        List<String[]> eixida = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(bdcon,us,pw);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int cols = rs.getMetaData().getColumnCount();
            
            while (rs.next()) {
                String[] registre = new String[cols];
                for (int i = 0; i < registre.length; i++) {
                    registre[i] = rs.getString(i + 1);
                }
                eixida.add(registre);
            }
        } catch (SQLException e) {
            System.out.println("Error en la bd: " + e.getErrorCode() + "-" + e.getMessage());
        }
        return eixida;
    }

}