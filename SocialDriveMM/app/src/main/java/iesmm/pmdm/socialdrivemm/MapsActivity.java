package iesmm.pmdm.socialdrivemm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import iesmm.pmdm.socialdrivemm.dao.DAOImpl;
import iesmm.pmdm.socialdrivemm.databinding.ActivityMapsBinding;
import iesmm.pmdm.socialdrivemm.model.Marcador;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener, GoogleMap.OnMapClickListener, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private DrawerLayout drawerLayout;
    private DAOImpl dao;
    private String idUsuario;
    private boolean valueReturn=false;
    private int idMarcador=1;
    private List<Marcador> marcadores;
    //Obtener la hora actual
    Calendar calendar = Calendar.getInstance();
    Date fecha = calendar.getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dao = new DAOImpl();

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getLocalizacion();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Bundle bundle2 = getIntent().getExtras();
        if(bundle2!=null) {
            idUsuario  = bundle2.getString("idUsuario");
        }


    }

    //Obtencion de los permisos para obtener la ubicacion actual.
    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permiso == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Asociacion de los escuchadores necesarios.
        mMap.setOnMapClickListener(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        LocationManager locationManager = (LocationManager) MapsActivity.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                //Obtener mi ubicacion
                LatLng miUbicacion = new LatLng(location.getLatitude(),location.getLongitude());
                //Mover la camara a la ubicacion actual
                mMap.moveCamera(CameraUpdateFactory.newLatLng(miUbicacion));
                //Añadimos efectos
                CameraPosition cameraPosition = new CameraPosition.Builder().target(miUbicacion).zoom(18).bearing(90).tilt(45).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        };
        //Actualizacion de datos y le pasamos el escuchador
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);

    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        try{
            Geocoder geocoder = new Geocoder(this);
            List<Address> direcciones = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            Address direccion = direcciones.get(0);
            String calle = direccion.getAddressLine(0);


        //Cuando pulses cree el alertDialog para introducir el tipo
        showAlertDialog();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("TIPO");
        alertDialog.setMessage("Introduzca la incidencia");

        EditText tipo = new EditText(getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        tipo.setLayoutParams(lp);
        alertDialog.setView(tipo);
        //Obtenemos el objeto Address




        alertDialog.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        String infoTipo = tipo.getText().toString();

                        //Introduce en la base de datos los datos del usuario


                        //INTRODUCIR TAMBIEN LA CALLE
                        if (infoTipo.equalsIgnoreCase("Radar")){
                            mMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude,latLng.longitude)).title(calle).icon(BitmapDescriptorFactory.fromResource(R.drawable.radar)));
                           dao.insertarMarcador(new Marcador("1","1","34","32",infoTipo));
                            Toast.makeText(getApplicationContext(),"Radar insertado correctamente",Toast.LENGTH_LONG).show();

                        }else if(infoTipo.equalsIgnoreCase("multa")) {
                            mMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude, latLng.longitude)).title(calle).icon(BitmapDescriptorFactory.fromResource(R.drawable.multa)));

                        }else if(infoTipo.equalsIgnoreCase("control")) {
                            mMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude, latLng.longitude)).title(calle).icon(BitmapDescriptorFactory.fromResource(R.drawable.control)));

                        }else
                            Toast.makeText(MapsActivity.this, "Introduzca un tipo de incidencia correcto", Toast.LENGTH_SHORT).show();
                    }

                });

        alertDialog.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();

        //GEOCODING DE LA POSICION
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlertDialog() {

    }




    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }



    // Do something with the data
    // coming from the AlertDialog
    private void sendDialogDataToActivity(String data)
    {
        Toast.makeText(this,"",Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent i_interfaz = new Intent(this,InterfazUsuario.class);
                startActivity(i_interfaz);
                Toast.makeText(this,"Volviendo a la interfaz principal",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_mapa:
                Intent i_mapa = new Intent(this,MapsActivity.class);
                startActivity(i_mapa);
                //Bundle que te lleve a la pagina de los mapas
                Toast.makeText(this,"Iniciando Mapas...",Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_lista:
                //Bundle que te lleve a la pagina de las listas
                Intent i_lista = new Intent(this,InterfazLista.class);
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



}
