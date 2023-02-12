package iesmm.pmdm.socialdrivemm;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import iesmm.pmdm.socialdrivemm.dao.DAO;
import iesmm.pmdm.socialdrivemm.dao.DAOImpl;

public class InterfazLista extends AppCompatActivity {
    private DAOImpl myDb;
    ListView lista;
    private DAO dao;

    private ArrayAdapter adaptador;
    ArrayList<Marcador> listaMarcadores = new ArrayList<>(); //Creación del arraylist de marcadores.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);


    }

    private void addItemsInListView(ArrayList listaMarcadores) {
         lista = findViewById(R.id.listViewMarcadores);
    }
// TODO Tienes que teminar tienes que recorrer la lista y meter los datos de los marcadores.

    public void viewData() {
        List<Marcador> res = dao.getAllMarcadores();
        /*if (res.getCount() > 0) {
            StringBuffer buffer = new StringBuffer();

            while (res.moveToNext()) {
                buffer.append("idMarcador :" + res.getString(0) + "\n");
                buffer.append("idUsuario_marcador :" + res.getString(1) + "\n");
                buffer.append("latitud :" + res.getString(2) + "\n");
                buffer.append("longitud :" + res.getString(3) + "\n\n");
                buffer.append("tipo :" + res.getString(4) + "\n\n");
                buffer.append("fecha :" + res.getString(5) + "\n\n");
            }

            showMessage("Datos", buffer.toString());
        } else
            showMessage("Error", "Ningún dato encontrado");

*/
    }



    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}