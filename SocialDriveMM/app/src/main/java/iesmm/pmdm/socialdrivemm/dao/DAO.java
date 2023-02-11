package iesmm.pmdm.socialdrivemm.dao;

import android.database.Cursor;

import java.util.Date;

public interface DAO {
    public Cursor getAllDataMarcador();
    public boolean insert(String idMarcador, String idUsuario_marcador, String latitud, String longitud, String tipo, Date fecha);
    public boolean update(String idMarcador, String idUsuario_marcador, String latitud, String longitud, String tipo, Date fecha);
    public int delete(String idMarcador);
}
