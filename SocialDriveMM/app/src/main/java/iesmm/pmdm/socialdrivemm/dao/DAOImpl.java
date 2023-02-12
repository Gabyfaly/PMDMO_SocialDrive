package iesmm.pmdm.socialdrivemm.dao;

import android.database.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import iesmm.pmdm.socialdrivemm.Conexion.ConnectionHelper;
import iesmm.pmdm.socialdrivemm.Marcador;


public class DAOImpl implements DAO {





    /**
     * Este método devuelve una Lista de marcadores para obtener todos los insertados
     * en la tabla marcador de la base de datos socialdrivemm, mediante nuestra clase
     * ConnectionHelper creamos conexión con la base de datos local.
     *
     * @return Lista de objetos Marcador con los datos guardados en la base de datos
     */

    public List<Marcador> getAllMarcadores() {
        List<Marcador> result = new ArrayList<>();
        try (Connection conexion = ConnectionHelper.getConnection()) {
            String consulta = "SELECT * FROM marcador";
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Marcador marcador = new Marcador();
                        marcador.setIdUsuario_marcador(resultSet.getString("idUsuario_marcador"));
                        marcador.setLatitud(resultSet.getString("latitud"));
                        marcador.setLongitud(resultSet.getString("longitud"));
                        marcador.setTipo(resultSet.getString("tipo"));
                        marcador.setFecha(resultSet.getDate("fecha"));
                        result.add(marcador);
                    }
                }
            }
        } catch (SQLException e) {
            // Manejar la excepción
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Método para insertar los datos de los marcadores creados en el mapa.
     *
     * @param idUsuario_marcador id usuario
     * @param latitud latitud del marcador
     * @param longitud longitud del marcador
     * @param tipo tipo multa,radar,control
     * @param fecha fecha y hora del marcador
     */
    public void insertarMarcador(String idUsuario_marcador, String latitud, String longitud, String tipo, Date fecha) {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            conexion = ConnectionHelper.getConnection();
            preparedStatement = conexion.prepareStatement("INSERT INTO marcador (idUsuario_marcador, latitud, longitud, tipo, fecha) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, idUsuario_marcador);
            preparedStatement.setString(2, latitud);
            preparedStatement.setString(3, longitud);
            preparedStatement.setString(4, tipo);
            preparedStatement.setDate(5, (java.sql.Date) fecha);
            preparedStatement.executeUpdate();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateMarcador(Marcador marcador) {
        Connection conexion = null;
        PreparedStatement statement = null;
        try {
            // Establece la conexión con la base de datos
            conexion = ConnectionHelper.getConnection();

            // Crea la sentencia SQL para actualizar el registro
            String sql = "UPDATE marcadores SET latitud = ?, longitud = ?, tipo = ?, fecha = ? WHERE idUsuario_marcador = ?";
            statement = conexion.prepareStatement(sql);

            // Establece los valores de los parámetros
            statement.setString(1, marcador.getLatitud());
            statement.setString(2, marcador.getLongitud());
            statement.setString(3, marcador.getTipo());
            //statement.setDate(4, marcador.getFecha());
            statement.setString(5, marcador.getIdUsuario_marcador());

            // Ejecuta la consulta
            statement.executeUpdate();
        } catch (SQLException | java.sql.SQLException e) {
            // Maneja la excepción
            e.printStackTrace();
        } finally {
            // Cierra la conexión y la sentencia
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException | java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean delete(String idMarcador) {
        Connection conexion = null;
        PreparedStatement statement = null;
        try {
            // Establece la conexión con la base de datos
            conexion = ConnectionHelper.getConnection();

            // Crea la sentencia SQL para borrar el registro
            statement = conexion.prepareStatement("DELETE FROM marcadores WHERE id_marcador = ?");
            statement.setString(1, idMarcador);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException | java.sql.SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


