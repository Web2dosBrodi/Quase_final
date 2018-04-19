package com.mycompany.agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author luiz-
 */
public class ConnectionFactory {
    public Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/agendacomunitaria?"
                            + "useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
