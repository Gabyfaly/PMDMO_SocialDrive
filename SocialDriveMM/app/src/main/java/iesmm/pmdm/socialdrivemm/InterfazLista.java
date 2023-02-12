package iesmm.pmdm.socialdrivemm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import iesmm.pmdm.socialdrivemm.dao.DAO;
import iesmm.pmdm.socialdrivemm.dao.DAOImpl;

public class InterfazLista extends AppCompatActivity {
    private DAOImpl myDb;
    ListView lista;
    private DAO dao;
    private ArrayAdapter adaptador;
    ArrayList<Marcador> ubicaciones = new ArrayList();
    ArrayList<String> nombres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        /*SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/mm/yyyy");
        Date fecha = null;
        try {
             fecha = formatoFecha.parse("15/05/2022");
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        ubicaciones.add(new Marcador("30","34","Multa"));
        nombres.add("Calle Sorpepita");
        addItemsInListView(ubicaciones, nombres);
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


    private void addItemsInListView(ArrayList <Marcador> marcadores, ArrayList<String> nombres) {
        //1.-Localizar el listView dentro del layout
        ListView lista =this.findViewById(R.id.listViewMarcadores);

        //2.- Creamos el adaptador de datos y vincular los datos que vamos a presentar en el listviewn
        adaptador = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, nombres);
        lista.setAdapter(adaptador);

        //Podemos personalizar el elemento.
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Mostrar el cuadro de dialogo cnd pulsamos un item.
                int posicion = i;
                androidx.appcompat.app.AlertDialog.Builder dialogo = new androidx.appcompat.app.AlertDialog.Builder(InterfazLista.this);
                dialogo.setTitle("Tipo: "+ubicaciones.get(posicion).getTipo());
                dialogo.setMessage("Latitud: "+ubicaciones.get(posicion).getLatitud()+"\nLongitud: "+ubicaciones.get(posicion).getLongitud());
                dialogo.setCancelable(false);
                dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogo, int i) {


                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogo, int i) {
                        dialogo.cancel();
                    }
                });
                dialogo.show();
            }
        });

    }
}