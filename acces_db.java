import java.sql.*;


public class acces_db {
    public static void main(String[] args) {
        try {
            // Conexión con la BD
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "root");
            // Creamos un objeto para enviar sentencias SQL a la BD
            Statement st = con.createStatement();
            // Ejecutamos la consulta SQL y obtenemos el resultado en ResultSet
            ResultSet rs = st.executeQuery("SELECT * FROM CLIENT");
            // Recorremos los resultados obtenidos y mostramos sus campos
            while (rs.next()) {
                String nombre = rs.getString(2);
                String edad = rs.getString(4);
                System.out.println(nombre + ": " + edad);
            }
            // Cerramos la conexión
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la bd: " + e.getErrorCode() + "-" + e.getMessage());
        }
    }
}
