package com.example.tiendadezapatos.ui.productos.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tiendadezapatos.R;
import com.squareup.picasso.Picasso;

public class DetallesProductoActivity extends AppCompatActivity {

    ImageView imgDetalleProducto;
    TextView txtNombre, txtDescripcion, txtPrecio, txtCantidad;
    Button btnResCantidad, btnSumCantidad, btnAgregarProducto;

    int cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);

        imgDetalleProducto = findViewById(R.id.imgDetalleProducto);
        txtNombre = findViewById(R.id.txtNombreDetalleProducto);
        txtDescripcion = findViewById(R.id.txtDescripcionDetalleProducto);
        txtPrecio = findViewById(R.id.txtPrecioDetalleProducto);
        txtCantidad = findViewById(R.id.txtCantidad);
        btnResCantidad = findViewById(R.id.btnResCantidad);
        btnSumCantidad = findViewById(R.id.btnSumCantidad);
        btnAgregarProducto = findViewById(R.id.btnComprarProducto);

        btnResCantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restarCantidad();

            }
        });

        btnSumCantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumarCantidad();
            }
        });

        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarProducto();
            }
        });

        cargarDatos();
    }

    private void cargarDatos() {
        Bundle datos = getIntent().getExtras();
        String nombre = datos.getString("nombre");
        String descripcion = datos.getString("descripcion");
        long precio = datos.getLong("precio");
        String imagen = datos.getString("imagen");


        txtNombre.setText(nombre);
        txtDescripcion.setText(descripcion);
        txtPrecio.setText(String.valueOf(precio));
        Picasso.get()
                .load(imagen)
                .error(R.drawable.poke_photo)
                .into(imgDetalleProducto);
    }

    private void sumarCantidad() {
        cantidad = Integer.parseInt(txtCantidad.getText().toString()) + 1;
        txtCantidad.setText(String.valueOf(cantidad));
    }

    private void restarCantidad() {
        cantidad = Integer.parseInt(txtCantidad.getText().toString()) - 1;
        if (cantidad < 0){
         cantidad ++;
        }
        txtCantidad.setText(String.valueOf(cantidad));
    }

    private void agregarProducto() {

    }
}