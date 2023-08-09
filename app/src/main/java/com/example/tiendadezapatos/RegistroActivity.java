package com.example.tiendadezapatos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class RegistroActivity extends AppCompatActivity {

    EditText edNombre, edCorreo, edPass, edConfirmPass, edTelefono, edFechaCumpleanos;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

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