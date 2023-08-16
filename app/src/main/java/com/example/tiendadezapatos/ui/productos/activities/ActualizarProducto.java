package com.example.tiendadezapatos.ui.productos.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

import com.example.tiendadezapatos.R;
import com.example.tiendadezapatos.ui.productos.model.ProductoModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActualizarProducto extends AppCompatActivity {

    EditText edNombreEditarProducto, edPrecioEditarProducto;
    MultiAutoCompleteTextView multiDescripcionEditarProducto;
    Button btnEditarProducto;
    ImageView imvEditarTomarFotoProducto;
    private DatabaseReference mDatabase;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        edNombreEditarProducto = findViewById(R.id.edNombreEditarProducto);
        edPrecioEditarProducto = findViewById(R.id.edPrecioEditarProducto);
        multiDescripcionEditarProducto = findViewById(R.id.multiDescripcionEditarProducto);
        btnEditarProducto = findViewById(R.id.btnEditarProducto);
        imvEditarTomarFotoProducto = findViewById(R.id.imvEditarTomarFotoProducto);

        btnEditarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarProducto();
            }
        });

        cargarDatos();
    }

    private void cargarDatos() {
        Bundle datos = getIntent().getExtras();
        String nombre = datos.getString("nombre");
        String descripcion = datos.getString("descripcion");
        long precio = datos.getLong("precio");
        int imagen = datos.getInt("imagen");

        id = datos.getString("id");;
        edNombreEditarProducto.setText(nombre);
        multiDescripcionEditarProducto.setText(descripcion);
        edPrecioEditarProducto.setText(String.valueOf(precio));
        imvEditarTomarFotoProducto.setImageResource(imagen);
    }

    private void validarProducto() {
        if (validarDatosProducto()) {
            AlertDialog.Builder confirmarEditar = new AlertDialog.Builder(this)
                    .setTitle("Confirmar")
                    .setMessage("¿Esta seguro de editar el prodcucto " + edNombreEditarProducto.getText().toString() + " ?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editarProducto();
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .setCancelable(false); //BLOQUEA QUE AL DAR CLIC FUERA DE LA ALERTA NO SE DESAPAREZCA DICHA ALERTA;
            confirmarEditar.show();
        }
    }

    private void editarProducto() {
        String nombre = edNombreEditarProducto.getText().toString();
        String descripcion = multiDescripcionEditarProducto.getText().toString();
        long precio = Long.parseLong(edPrecioEditarProducto.getText().toString());
        String urlImg = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/0d265602-b5fa-46c1-8eb6-b3e56c7f52b9/calzado-air-jordan-1-mid-tXSJ73.png"; // a futuro se trae la imagen desde firebase

        ProductoModel productoModel = new ProductoModel(nombre, descripcion, precio, urlImg);

        mDatabase.child("productos").child(id).setValue(productoModel);
    }

    private boolean validarDatosProducto() {

        if (edNombreEditarProducto.getText().toString().isEmpty()) {
            edNombreEditarProducto.setError("Campo Obligatorio");
            return false;
        }

        if (multiDescripcionEditarProducto.getText().toString().isEmpty()) {
            multiDescripcionEditarProducto.setError("Campo Obligatorio");
            return false;
        }

        if (edPrecioEditarProducto.getText().toString().isEmpty()) {
            edPrecioEditarProducto.setError("Campo Obligatorio");
            return false;
        }

        return true;
    }
}