package iesmm.pmdm.socialdrivemm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAOImpl extends SQLiteOpenHelper implements DAO {
    public static final String DATABASE_NAME = "SocialDrive.db"; // /data/data/iesmm.pmdm.pmdm_t5_sqlite/databases/alumnos.db
    private static final int VERSION = 13;
    public static final String TABLE_USUARIO = "usuario";
    public static final String COL_1 = "id_usuario";
    public static final String COL_2 = "usuario";
    public static final String COL_3 = "password";

    public static final String TABLE_MARCADOR = "marcador";
    public static final String COL1_1 = "id_marcador";
    public static final String COL1_2 = "latitud";
    public static final String COL1_3 = "longitud";
    public static final String COL1_4 = "date_time";
    public static final String COL1_5 = "fecha";

    public DAOImpl(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE " + TABLE_USUARIO + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(20), SURNAME VARCHAR(50), MARKS INTEGER) ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    @Override
    public Cursor getAllData() {
        return null;
    }

    @Override
    public boolean insert(String idUsuario, String latitud, String longitud, String tipo) {
        return false;
    }

    @Override
    public boolean update(String idUsuario, String latitud, String longitud, String tipo) {
        return false;
    }

    @Override
    public int delete(String idUsuario) {
        return 0;
    }
}
