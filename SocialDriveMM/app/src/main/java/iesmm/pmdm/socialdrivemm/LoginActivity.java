package iesmm.pmdm.socialdrivemm;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.navigation.NavigationView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import iesmm.pmdm.socialdrivemm.dao.DAO;
import iesmm.pmdm.socialdrivemm.dao.DAOImpl;

public class LoginActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    List<Usuario> usuarios = new ArrayList();
    Usuario u;
    int contadorLinea=0;
    int posicion = 0;
    private DAOImpl myDb;
    private DAO dao;
    private EditText usuarioText,contraseniaText;
    private Button btnInicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Declaración de variables.
        dao = new DAOImpl(this);
        usuarioText= findViewById(R.id.input_usuario);
        contraseniaText= findViewById(R.id.input_contrasena);
        btnInicio = findViewById(R.id.btn_login);


        //Acción del botón.
        btnInicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //Comprobación de lo introducido en los campos.
                String username = usuarioText.getText().toString();
                String password =contraseniaText.getText().toString();

                Usuario usuario = new Usuario("user", "password");

                //Comprobación de los campos introducidos en los EditText con los usuarios de la base de datos.
                new LoginTask().execute(username, password);

                /*if (usuario.checkUser(username, password)) {
                    //Contruccion del conjunto de datos a transmitir
                    Bundle bundle = new Bundle();
                    bundle.putString("usuario", username);

                    //Llamada al Intent
                    Intent intent = new Intent(LoginActivity.this, InterfazUsuario.class);
                    intent.putExtras(bundle);

                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this,"Error de acceso: Usuario o Contraseña incorrectos", Toast.LENGTH_LONG).show();
                }*/


            /* Código de prueba no válido

                if (getAccess(username,password)){
                    //Contruccion del conjunto de datos a transmitir
                    Bundle bundle = new Bundle();
                    bundle.putString("usuario", username);
                    //bundle.putString("telefono", usuarios.get(posicion).getTelefono());
                    //bundle.putString("nombre", usuarios.get(posicion).getNombre());

                    //Llamada del Intent
                    Intent i = new Intent(getApplicationContext(),InterfazUsuario.class);
                    i.putExtras(bundle);

                    startActivity(i);

                }else
                    Snackbar.make(view, "Error en acceso", Snackbar.LENGTH_LONG).show();
            */

            }
        });
    }


    /*


     * https://medium.com/android-beginners/android-snackbar-example-tutorial-a40aae0fc620
     * Devuelve cierto si se confirma que el gmail y el password son correctos
     * y se localizan en la base de datos.
     * @param username
     * @param password
     * @return devuelve cierto si son correctos y falso en caso contrario
     *

    private boolean getAccessUsuario(String username, String password) {
                Usuario user= new Usuario();


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
*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    //Clase privada para comprobar el usuario y contraseña de la base de datos mediante tareas asíncronas.

    private class LoginTask extends AsyncTask<String, Void, Boolean> {

        private static final String TAG = "LoginTask";

        @Override
        protected Boolean doInBackground(String... strings) {
            String username = strings[0];
            String password = strings[1];
            return ValidarUsuario(username, password);
        }

        private boolean ValidarUsuario(String username, String password) {
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/socialdrivemm", "root", "");
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM Usuario WHERE username='" + username + "' AND password='" + password + "'");
                return resultSet.next();
            } catch (SQLException | ClassNotFoundException e) {
                Log.e(TAG, "Error en el login", e);
                return false;
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    Log.e(TAG, "Error closing resources", e);
                }
            }
        }

        @Override
        protected void onPostExecute(Boolean res) {
            if(res){
                Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(LoginActivity.this,"Error de acceso: Usuario o Contraseña incorrectos", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(res);
        }
    }
}