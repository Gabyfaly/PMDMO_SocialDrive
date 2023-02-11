package iesmm.pmdm.socialdrivemm;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListView extends AppCompatActivity {

    private ArrayAdapter adaptador;
    ArrayList<Marcador> listaMarcadores = new ArrayList<>(); //Creación del arraylist de marcadores.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);


    }

    private void addItemsInListView(ArrayList listaMarcadores) {
        //ListView lista = this.findViewById(R.id.listViewMarcadores);
    }
}
