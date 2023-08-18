package com.example.tiendadezapatos.ui.productos.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tiendadezapatos.R;
import com.example.tiendadezapatos.ui.productos.helper.DbHelper;
import com.squareup.picasso.Picasso;

public class DetallesProductoActivity extends AppCompatActivity {

    ImageView imgDetalleProducto;
    TextView txtNombre, txtDescripcion, txtPrecio, txtCantidad;
    Button btnResCantidad, btnSumCantidad, btnAgregarProducto;

    int cantidad;
    String idFirebase;

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
        idFirebase = datos.getString("idFirebase");

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
        if (cantidad < 0) {
            cantidad++;
        }
        txtCantidad.setText(String.valueOf(cantidad));
    }

    private void agregarProducto() {
        if (cantidad >= 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this)
                    .setTitle("Agregar producto")
                    .setMessage("¿Está seguro de agregar " + cantidad + " productos a su carrito de compras?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DbHelper db = new DbHelper(getBaseContext());
                            db.agregarProducto(txtNombre.getText().toString(),
                                    txtDescripcion.getText().toString(),
                                    Long.parseLong(txtPrecio.getText().toString()),
                                    Integer.parseInt(String.valueOf(cantidad)),
                                    idFirebase);
                            DetallesProductoActivity.this.finish();
                        }
                    });
            alert.show();
        }
    }
}