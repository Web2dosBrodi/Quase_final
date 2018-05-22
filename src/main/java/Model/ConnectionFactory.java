package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author White
 */
public class ConnectionFactory {

    private static Connection instancia;

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws ClassNotFoundException {
        if (instancia == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                 instancia = DriverManager.getConnection(
                        "jdbc:mysql://localhost/agendacomunitaria?"
                        + "useTimezone=true&serverTimezone=UTC", "root", "");
                 return instancia;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return instancia;
    }
}
