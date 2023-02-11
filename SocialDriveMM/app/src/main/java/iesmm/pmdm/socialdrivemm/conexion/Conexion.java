package iesmm.pmdm.socialdrivemm.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection connectToDB() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/socialdrivemm", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}


