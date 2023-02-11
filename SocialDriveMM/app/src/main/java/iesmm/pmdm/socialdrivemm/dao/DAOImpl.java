package iesmm.pmdm.socialdrivemm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;


public class DAOImpl extends SQLiteOpenHelper implements DAO {
    public static final String DATABASE_NAME = "socialdrivemm.db"; // /data/data/iesmm.pmdm.pmdm_t5_sqlite/databases/alumnos.db
    private static final int VERSION = 13;
   /* public static final String TABLE_USUARIO = "usuario";
    public static final String COL_1 = "id_usuario";
    public static final String COL_2 = "usuario";
    public static final String COL_3 = "password";*/


    public static final String TABLE_MARCADOR = "marcador";
    public static final String COL1_1 = "idMarcador";
    public static final String COL1_2 = "idUsuario_marcador";
    public static final String COL1_3 = "latitud";
    public static final String COL1_4 = "longitud";
    public static final String COL1_5 = "tipo";
    public static final String COL1_6 = "fecha";


    public DAOImpl(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE" + TABLE_MARCADOR + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(20), SURNAME VARCHAR(50), MARKS INTEGER) ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MARCADOR);
        onCreate(db);
    }


    //Obtener todos los datos de la base de datos Marcador
    @Override
    public Cursor getAllDataMarcador() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_MARCADOR, null);
        return res;

    }

    @Override
    public boolean insert(String idMarcador, String idUsuario_marcador, String latitud, String longitud, String tipo, Date fecha) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Datos a insertar
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1_1, idMarcador);
        contentValues.put(COL1_2, idUsuario_marcador);
        contentValues.put(COL1_3, latitud);
        contentValues.put(COL1_4, longitud);
        contentValues.put(COL1_5, tipo);

        //Revisar
        contentValues.put(COL1_6, String.valueOf(fecha));
        long result = db.insert(TABLE_MARCADOR, null, contentValues);

        // Devuelve el row id, -1 si ha ocurrido algún error
        return result != -1;
    }

    @Override
    public boolean update(String idMarcador, String idUsuario_marcador, String latitud, String longitud, String tipo, Date fecha) {
        int nrows = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        // Datos a actualizar
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1_1, idMarcador);
        contentValues.put(COL1_2, idUsuario_marcador);
        contentValues.put(COL1_3, latitud);
        contentValues.put(COL1_4, longitud);
        contentValues.put(COL1_5, tipo);
        //Revisar
        contentValues.put(COL1_6, String.valueOf(fecha));

        nrows = db.update(TABLE_MARCADOR, contentValues, "idMarcador = ?", new String[]{idMarcador});

        return nrows > 0;
    }

    @Override
    public int delete(String idMarcador) {
        int result = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_MARCADOR + " WHERE idMarcador=" + idMarcador);
            result = 1;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
