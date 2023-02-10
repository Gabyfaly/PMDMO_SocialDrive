package iesmm.pmdm.socialdrivemm.dao;

import android.database.Cursor;

public interface DAO {
    public Cursor getAllData();
    public boolean insert(String idUsuario,String latitud, String longitud, String tipo);
    public boolean update(String idUsuario,String latitud, String longitud, String tipo);
    public int delete(String idUsuario);
}
