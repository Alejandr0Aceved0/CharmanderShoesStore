package com.example.tiendadezapatos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    EditText edCorreo, edContrasena;
    TextView txtOlvidoPass;
    Button btnLogin, btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        Toast.makeText(getBaseContext(), "LOGEADO", Toast.LENGTH_SHORT).show();
    }
}