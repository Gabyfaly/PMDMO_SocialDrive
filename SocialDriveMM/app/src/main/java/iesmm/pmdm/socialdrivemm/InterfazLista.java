package iesmm.pmdm.socialdrivemm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import iesmm.pmdm.socialdrivemm.dao.DAO;
import iesmm.pmdm.socialdrivemm.dao.DAOImpl;
import iesmm.pmdm.socialdrivemm.model.Marcador;

public class InterfazLista extends AppCompatActivity {
    private DAOImpl myDb;
    ListView lista;
    private DrawerLayout drawerLayout;
    private DAOImpl dao;
    Marcador m;
    private ArrayAdapter adaptador;
    ArrayList<Marcador> ubicaciones = new ArrayList();
    ArrayList<String> nombres = new ArrayList<>();
    List<Marcador> listaMarca = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        //ubicaciones.add(new Marcador(m.getLatitud(),m.getLongitud(),m.getTipo()));
        nombres.add("Calle Sorpepita");
        nombres.add("Gabriel");
        addItemsInListView(ubicaciones, nombres);
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