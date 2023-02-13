package iesmm.pmdm.socialdrivemm.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    private static final String url = "jdbc:mysql://192.168.56.1:3306/socialdrivemm";
    private static final String username = "root";
    private static final String password = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}

