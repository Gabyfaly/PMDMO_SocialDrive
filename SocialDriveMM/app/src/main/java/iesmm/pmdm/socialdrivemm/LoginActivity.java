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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import iesmm.pmdm.socialdrivemm.Conexion.ConnectionHelper;
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
        dao = new DAOImpl();
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

            }
        });
    }

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
                connection = ConnectionHelper.getConnection();
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
                    Log.e(TAG, "Error en el cierre de recursos", e);
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