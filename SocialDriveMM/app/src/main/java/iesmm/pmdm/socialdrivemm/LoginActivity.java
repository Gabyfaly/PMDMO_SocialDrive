package iesmm.pmdm.socialdrivemm;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
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
import java.sql.PreparedStatement;
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

                //Usuario usuario = new Usuario("user", "password");

                //Comprobación de los campos introducidos en los EditText con los usuarios de la base de datos.
                new LoginTask().execute(username, password);
                if(username.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this, "Campos vacios", Toast.LENGTH_SHORT).show();
                }


                /*if (checkValue(username,password)==true){
                    Toast.makeText(LoginActivity.this, "Usuario correcto", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(LoginActivity.this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();*/
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
            return checkValue(username, password);
        }


        public boolean checkValue(String usu, String cont) {
            boolean isValid = false;
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/socialdrivemm", "root", "root");
                String sql = "SELECT username,contrasenia FROM usuario";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String usuario = resultSet.getString("username");
                    String password = resultSet.getString("contrasenia");
                    if (usuario.equals(usu) && password.equals(cont)) {
                        isValid=true;
                    }
                }

                connection.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
            return isValid;
        }

        /*private boolean ValidarUsuario(String username, String password) {
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
        }*/

        @Override
        protected void onPostExecute(Boolean res) {
            if(res){
                Intent intent = new Intent(LoginActivity.this, InterfazUsuario.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this,"Iniciando la interfaz de usuario", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(LoginActivity.this,"Error de acceso: Usuario o Contraseña incorrectos", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(res);
        }
    }

    /*public boolean checkValue(String usu, String cont) {
        boolean isValid = false;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/socialdrivemm", "root", "root");
            String sql = "SELECT username,contrasenia FROM usuario";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String usuario = resultSet.getString("username");
                String password = resultSet.getString("contrasenia");
                if (usuario.equals(usu) && password.equals(cont)) {
                    Intent intent = new Intent(LoginActivity.this, InterfazUsuario.class);
                    startActivity(intent);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }*/


}