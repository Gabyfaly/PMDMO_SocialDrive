package iesmm.pmdm.socialdrivemm;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Usuario> usuarios = new ArrayList();
    Usuario u;
    int contadorLinea=0;
    int posicion = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = this.findViewById(R.id.boton_iniciar_sesion);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Comprobar lo introducido en los campos.

                String username = ((TextView) findViewById(R.id.input_usuario)).getText().toString();
                String password = ((TextView) findViewById(R.id.input_contrasena)).getText().toString();


                if (getAccess(username,password)){
                    //Contruccion del conjunto de datos a transmitir
                    Bundle bundle = new Bundle();
                    bundle.putString("email", username);
                    //bundle.putString("telefono", usuarios.get(posicion).getTelefono());
                    //bundle.putString("nombre", usuarios.get(posicion).getNombre());

                    //Llamada del Intent
                    Intent i = new Intent(getApplicationContext(),InterfazUsuario.class);
                    i.putExtras(bundle);

                    startActivity(i);

                }else
                    Snackbar.make(view, "Error en acceso", Snackbar.LENGTH_LONG).show();

            }
        });
    }

    /**
     * https://medium.com/android-beginners/android-snackbar-example-tutorial-a40aae0fc620
     * Devuelve cierto si se confirma que el gmail y el password son correctos
     * y se localizan en el fichero
     * @param username
     * @param password
     * @return devuelve cierto si son correctos y falso en caso contrario
     */
    private boolean getAccess(String username, String password) {
        boolean res=false;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("users.csv")));
            String linea=br.readLine();

            while (linea!=null){
                String[] campos=linea.split(":");
                String nombre = campos[0].toString();
                String contra =  campos[1].toString();
                String emailBueno = campos[2].toString();
                String telefono = campos[3].toString();

                //u = new Usuario(nombre,emailBueno,telefono,contra);
                usuarios.add(u);

                if (!username.equals("") && !password.equals("")){
                    if (username.equals(emailBueno) && password.equals(contra)){
                        res = true;
                        posicion=contadorLinea;
                    }
                }else {
                    res = false;
                }
                //Volver a leer otra linea
                linea= br.readLine();
                contadorLinea++;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return res;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}