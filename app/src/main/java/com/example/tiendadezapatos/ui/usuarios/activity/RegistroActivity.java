package com.example.tiendadezapatos.ui.usuarios.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tiendadezapatos.R;
import com.example.tiendadezapatos.activities.MainActivity;
import com.example.tiendadezapatos.ui.usuarios.model.UsuarioModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.TimeZone;

public class RegistroActivity extends AppCompatActivity {

    private String ARCHIVO_SHARED_PREFERENCES = "DATOS_USUARIO";

    private String KEY_CORREO = "correo";
    private String KEY_PASS = "pass";
    private String KEY_ROL = "admin";

    private DatabaseReference mDatabase;

    EditText edNombre, edCorreo, edPass, edConfirmPass, edTelefono, edFechaCumpleanos;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        edNombre = findViewById(R.id.edNombre);
        edCorreo = findViewById(R.id.edCorreo);
        edPass = findViewById(R.id.edPass);
        edConfirmPass = findViewById(R.id.edConfirmPass);
        edTelefono = findViewById(R.id.edTelefono);
        edFechaCumpleanos = findViewById(R.id.edFechaCumpleanos);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearUsuario();
            }
        });


        edFechaCumpleanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendarOptions();
            }
        });
    }

    private void showCalendarOptions() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dialogDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edFechaCumpleanos.setText(String.valueOf(year + "/" + month + "/" + dayOfMonth));
            }
        }, year, month, day);
        dialogDate.show();
    }

    private void crearUsuario() {

        if (validarUsuario()) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("Confirmar")
                    .setMessage("¿Está seguro de crear este usuario?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //TODO : llamar al endpoint o db para guardar usuario
                            // Write a message to the database

                            String nombre = edNombre.getText().toString();
                            String correo = edCorreo.getText().toString();
                            String pass = edPass.getText().toString();
                            String fechaCumple = edFechaCumpleanos.getText().toString();
                            String telefono = edTelefono.getText().toString();
                            String rol = "client";

                            UsuarioModel usuarioModel = new UsuarioModel(nombre, correo, pass, fechaCumple, telefono, rol);


                            mDatabase.child("usuarios").push().setValue(usuarioModel);

                            //SE GUARDAN DATOS DE LOGIN EN ARCHIVOP XML
                            SharedPreferences shared = getSharedPreferences(ARCHIVO_SHARED_PREFERENCES, MODE_PRIVATE);
                            SharedPreferences.Editor editor = shared.edit();
                            editor.putString(KEY_CORREO, correo);
                            editor.putString(KEY_PASS, pass);
                            editor.putString(KEY_ROL, "client");
                            editor.apply(); //.apply() guarda los datos seteados por putString

                            Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

            dialog.show();

        } else {
            Toast.makeText(this, "REVISA TUS DATOS", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarUsuario() {
        if (edNombre.getText().toString().isEmpty()) {
            edNombre.setError("Campo Obligatorio");
            return false;
        }

        if (edCorreo.getText().toString().isEmpty()) {
            edCorreo.setError("Campo Obligatorio");
            return false;
        }

        if (edPass.getText().toString().isEmpty()) {
            edPass.setError("Campo Obligatorio");
            return false;
        }

        if (edConfirmPass.getText().toString().isEmpty()) {
            edConfirmPass.setError("Campo Obligatorio");
            return false;
        }

        if (!edPass.getText().toString().equals(edConfirmPass.getText().toString())) {
            edPass.setError("Revisa tu contraseña");
            edConfirmPass.setError("Revisa tu contraseña");
            return false;
        }

        if (edTelefono.getText().toString().isEmpty()) {
            edTelefono.setError("Campo Obligatorio");
            return false;
        }

        if (edFechaCumpleanos.getText().toString().isEmpty()) {
            edFechaCumpleanos.setError("Campo Obligatorio");
            return false;
        }
        return true;
    }
}