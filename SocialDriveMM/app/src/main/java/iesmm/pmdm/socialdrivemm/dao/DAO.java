package iesmm.pmdm.socialdrivemm.dao;

import android.database.Cursor;

public interface DAO {
    public Cursor getAllData();
    public boolean insert(String name, String surname, String marks);
    public boolean update(String id, String name, String surname, String marks);
    public int delete(String id);
}
