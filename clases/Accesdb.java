package clases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Accesdb {

    private final static String bdcon = "jdbc:mysql://localhost:3306/Reservas";
    //private final static String bdcon = "jdbc:mysql://localhost:33006/Reservas";
    private final static String us = "root";
    private final static String pw = "root";
    //private final static String pw = "pepito$";
    public final static String paisos="SELECT DISTINCT Pais FROM Aeropuertos ORDER BY Pais ASC;";
    public final static String prouCapacQuery="SELECT * FROM Tipo_de_Avion WHERE asientos >= %d ORDER BY asientos LIMIT 1;";
    public final static String aeroportsDe="SELECT * FROM Aeropuertos WHERE Pais = '%s';";
    public final static String paisdecCodi="SELECT Pais FROM Aeropuertos WHERE Codigo='%s'";
    public final static String placesVol="SELECT * FROM Plazas WHERE id_vuelo = %s;";
    public final static String ocupa="UPDATE Plazas SET id_pasajero = '%s', ocupado = 'si' WHERE id_vuelo = %s AND id_asiento = '%s';";
    public final static String contaVolsPas="SELECT COUNT(*) FROM Plazas WHERE id_pasajero = '%s';";
    public final static String volsPas="SELECT Vuelos.* FROM Vuelos INNER JOIN Plazas ON Vuelos.id_vuelo = Plazas.id_vuelo WHERE Plazas.id_pasajero = '%s';";
    public final static String volsNoPas="SELECT Vuelos.* FROM Vuelos LEFT JOIN Plazas ON Vuelos.id_vuelo = Plazas.id_vuelo AND Plazas.id_pasajero = '%s' WHERE Plazas.Id_plaza IS NULL";
    public final static String passatgersActius="SELECT DISTINCT Pasajeros.* FROM Pasajeros INNER JOIN Plazas ON Pasajeros.numero_pasaporte = Plazas.id_pasajero;";
    public final static String pasatger="SELECT numero_pasaporte,nombre_pasajero FROM Pasajeros WHERE numero_pasaporte='%s';";
    public final static String reservesPas="SELECT Vuelos.id_vuelo, Vuelos.origen, aeropuerto_origen.Pais AS pais_origen, Vuelos.destino, aeropuerto_destino.Pais AS pais_destino, Vuelos.fecha, Plazas.id_asiento, Plazas.id_plaza FROM Vuelos INNER JOIN Plazas ON Vuelos.id_vuelo = Plazas.id_vuelo INNER JOIN Aeropuertos AS aeropuerto_destino ON Vuelos.destino = aeropuerto_destino.Codigo INNER JOIN Aeropuertos AS aeropuerto_origen ON Vuelos.origen = aeropuerto_origen.Codigo WHERE Plazas.id_pasajero = '%s' ";
    public final static String volsambPlazaLLiure="SELECT DISTINCT id_vuelo FROM Plazas WHERE ocupado = 'no'";
    public final static String lliures="SELECT COUNT(*) FROM Plazas WHERE id_vuelo = %d AND ocupado = 'no'";
    public final static String llibera="UPDATE Plazas SET id_pasajero = null, ocupado='no' WHERE id_plaza = %s;";

    public static Scanner sc = new Scanner(System.in);

    public static void modifica(String query){
        try {
            Connection con = DriverManager.getConnection(bdcon, us, pw);
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la bd -modifica-: \n"+query+ "\n" + e.getErrorCode() + "-" + e.getMessage());
            sc.nextLine();
        }

    }

    public static String[] lligReg(String query) { // retorna el primer registre d'una consulta
        String[] eixida = null;
        try {
            Connection con = DriverManager.getConnection(bdcon, us, pw);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int cols = rs.getMetaData().getColumnCount();
            eixida = new String[cols];
            if (rs.next()) {
                for (int i = 0; i < eixida.length; i++) {
                    eixida[i] = rs.getString(i + 1);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(query);
            System.out.println("Error en la bd -lligReg-: " + e.getErrorCode() + "-" + e.getMessage());
            sc.nextLine();
        }
        
        return eixida;
    }

    public static int agrega(String taula, Object[] camp_valor_camp2_valor2_) {
        // agrega a la bbdd en la taula l'objecte {camp,1, valor1, camp2, valor2....}
        boolean clau = true;
        String taules = "";
        String valors = "";
        String sep = "";
        for (Object object : camp_valor_camp2_valor2_) {
            if (clau) {
                taules += object + ",";
            } else {
                sep = (object instanceof String) ? "'" : "";
                valors += sep + object + sep + ",";
            }
            clau = !clau;
        }
        taules = taules.substring(0, taules.length() - 1); // fora última ','
        valors = valors.substring(0, valors.length() - 1);
        String query = "INSERT INTO " + taula + " (" + taules + ") VALUES (" + valors + ");";
        int idInsertat = -1;
        try {
            Connection con = DriverManager.getConnection(bdcon, us, pw);
            Statement st = con.createStatement();
            st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                idInsertat = rs.getInt(1); // Obtener el valor de la clave generada
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(query);
            System.out.println("Error en la bd -agrega-: " + e.getErrorCode() + "-" + e.getMessage());
            return 0;

        }
        return idInsertat;

    }

    public static List<String[]> lligTaula(String taula) { // retorna la taula d'una consulta
        List<String[]> eixida = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(bdcon, us, pw);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM " + taula);
            int cols = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                String[] registre = new String[cols];
                for (int i = 0; i < registre.length; i++) {
                    registre[i] = rs.getString(i + 1);
                }
                eixida.add(registre);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("SELECT * FROM " + taula);
            System.out.println("Error en la bd -lligTaula- : " + e.getErrorCode() + "-" + e.getMessage());
            sc.nextLine();
        }
        return eixida;
    }

    public static List<String[]> lligQuery(String query) { // retorna la taula d'una consulta
        List<String[]> eixida = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(bdcon, us, pw);
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
            con.close();
        } catch (SQLException e) {
            System.out.println(query);
            System.out.println("Error en la bd -lligQuery-: " + e.getErrorCode() + "-" + e.getMessage());
            sc.nextLine();
        }
        return eixida;
    }

    public static Integer toInt(String entrada) { // vull deixar el tractament d'errors agrupat
        Integer eixida = null;
        try {
            eixida = Integer.parseInt(entrada);
        } catch (ClassCastException e) {
            //System.out.println("Error en convertir <" + entrada + "> en tipus Integer -" + e.getMessage());
           //sc.nextLine();
           return null;
        }
        return eixida;
    }

}