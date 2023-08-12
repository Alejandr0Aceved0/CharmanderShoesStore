package com.example.tiendadezapatos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiendadezapatos.R;

public class LoginActivity extends AppCompatActivity {

    private String ARCHIVO_SHARED_PREFERENCES = "DATOS_USUARIO";

    private String KEY_CORREO = "correo";
    private String KEY_PASS = "pass";
    private String KEY_ROL = "admin";

    EditText edCorreo, edContrasena;
    TextView txtOlvidoPass;
    Button btnLogin, btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciarSesionSharedPreferences();

        edCorreo = findViewById(R.id.txtCorreo);
        edContrasena = findViewById(R.id.txtPass);
        txtOlvidoPass = findViewById(R.id.txtOlvidoPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistro = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificarDatos()){
                    iniciarSesion();
                }
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

    }

    private boolean verificarDatos() {
        if (edCorreo.getText().toString().isEmpty()){
            edCorreo.setError("Este campo es requerido");
            Toast.makeText(getBaseContext(), "INGRESE CORREO", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edContrasena.getText().toString().isEmpty()){
            edContrasena.setError("Este campo es requerido");
            Toast.makeText(getBaseContext(), "INGRESE CONTRASENA", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void iniciarSesion() {

        String correo = edCorreo.getText().toString();
        String pass = edContrasena.getText().toString();

        if (pass.equals("hola")){

            //SE GUARDAN DATOS DE LOGIN EN ARCHIVOP XML
            SharedPreferences shared = getSharedPreferences(ARCHIVO_SHARED_PREFERENCES, MODE_PRIVATE);
            SharedPreferences.Editor editor = shared.edit();
            editor.putString(KEY_CORREO, correo);
            editor.putString(KEY_PASS, pass);
            editor.putString(KEY_ROL, "admin");
            editor.apply(); //.apply() guarda los datos seteados por putString

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getBaseContext(), "PASS INCORRECTO", Toast.LENGTH_SHORT).show();
        }
    }

    private void registrarUsuario() {
        Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivity(intent);
    }

    private void iniciarSesionSharedPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(ARCHIVO_SHARED_PREFERENCES, MODE_PRIVATE);

        String correo = sharedPreferences.getString(KEY_CORREO, null);
        String pass = sharedPreferences.getString(KEY_PASS, null);
        String rol = sharedPreferences.getString(KEY_ROL, null);

        if (correo != null && pass != null && rol != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }


    }
}