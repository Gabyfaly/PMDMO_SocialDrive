package iesmm.pmdm.socialdrivemm;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
//import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;


public class InterfazUsuario extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String id;
    private DrawerLayout drawerLayout;
    private TextView texto;
    //Creacion del layout con el menu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        texto = this.findViewById(R.id.texto);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            String id = bundle.getString("idUsuario");
            texto.setText("Usuario con id: " + id);

        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:

                Bundle bundle1 = new Bundle();
                if(bundle1!=null){
                    Intent i = new Intent(InterfazUsuario.this, MapsActivity.class);
                    bundle1.putString("idUsuario",id);
                    i.putExtras(bundle1);
                    startActivity(i);
                    Toast.makeText(this, "idUsuario: "+id, Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(this, "Bundle vacio", Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_mapa:
                Intent i_mapa = new Intent(this,MapsActivity.class);
                startActivity(i_mapa);
                //Bundle que te lleve a la pagina de los mapas
                Toast.makeText(this,"Iniciando Mapas...",Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_lista:
                //Bundle que te lleve a la pagina de las listas
                Intent i_lista = new Intent(this, InterfazLista.class);
                startActivity(i_lista);
                Toast.makeText(this,"Iniciando lista...",Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_salir:
                finish();
                break;


        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
        super.onBackPressed();
    }
}
