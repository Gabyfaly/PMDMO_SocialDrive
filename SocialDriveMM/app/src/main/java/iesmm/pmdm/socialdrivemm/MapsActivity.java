package iesmm.pmdm.socialdrivemm;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import iesmm.pmdm.socialdrivemm.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        //Te centre la camara en sevilla
        LatLng sevilla = new LatLng(37.396627843214546, -5.982869218981244);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sevilla));
    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        Geocoder geocoder = new Geocoder(this);
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

        alertDialog.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String infoTipo = tipo.getText().toString();
                        Toast.makeText(getApplicationContext(),infoTipo,Toast.LENGTH_LONG).show();
                        //INTRODUCIR TAMBIEN LA CALLE
                        if (infoTipo.equalsIgnoreCase("Radar")){
                            mMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude,latLng.longitude)).title(infoTipo).icon(BitmapDescriptorFactory.fromResource(R.drawable.radar)));
                        }else if(infoTipo.equalsIgnoreCase("multa")) {
                            mMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude, latLng.longitude)).title(infoTipo).icon(BitmapDescriptorFactory.fromResource(R.drawable.multa)));
                        }
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
}
