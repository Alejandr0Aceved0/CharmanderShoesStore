package com.example.tiendadezapatos.ui.productos.fragmentos;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.tiendadezapatos.R;
import com.example.tiendadezapatos.ui.productos.model.ProductoModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgregarProductoFragment extends Fragment {

    private final int CODE_INTENT_CAMERA = 10;
    private final int CODE_permission_CAMERA = 20;
    private DatabaseReference mDatabase;

    EditText edNombreAgregarProducto, edPrecioAgregarProducto;
    MultiAutoCompleteTextView multiDescripcionAgregarProducto;
    Button btnAgregarProducto;
    ImageView imvTomarFotoProducto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agregar_producto, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        edNombreAgregarProducto = view.findViewById(R.id.edNombreAgregarProducto);
        edPrecioAgregarProducto = view.findViewById(R.id.edPrecioAgregarProducto);
        multiDescripcionAgregarProducto = view.findViewById(R.id.multiDescripcionAgregarProducto);
        btnAgregarProducto = view.findViewById(R.id.btnAgregarProducto);
        imvTomarFotoProducto = view.findViewById(R.id.imvTomarFotoProducto);

        imvTomarFotoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFotoProducto();
            }
        });


        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarProducto();
            }
        });

        return view;
    }

    private void tomarFotoProducto() {

        //validar permiso de camara
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //startActivityForResult inicializa una intencion con un codigo identificado, llamado CODE_INTENT_CAMERA
            startActivityForResult(cameraIntent, CODE_INTENT_CAMERA);
        } else {
            //lanzamos venta para obtener el permiso de camara en caso de que la app no tenga dicho permiso de camara
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CODE_INTENT_CAMERA);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CODE_INTENT_CAMERA:
                Bitmap miniatura = (Bitmap) data.getExtras().get("data");
                imvTomarFotoProducto.setImageBitmap(miniatura);
                break;
        }
    }

    private void validarProducto() {
        if (validarDatosProducto()) {
            AlertDialog.Builder confirmarAgregar = new AlertDialog.Builder(getContext())
                    .setTitle("Confirmar")
                    .setMessage("Esta seguro de agregar el prodcucto " + edNombreAgregarProducto.getText().toString())
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            agregarProducto();
                        }
                    })
                    .setNegativeButton("No", null)
                    .setCancelable(false); //BLOQUEA QUE AL DAR CLIC FUERA DE LA ALERTA NO SE DESAPAREZCA DICHA ALERTA;
            confirmarAgregar.show();
        }
    }

    private void agregarProducto() {
        String nombre = edNombreAgregarProducto.getText().toString();
        String descripcion = multiDescripcionAgregarProducto.getText().toString();
        long precio = Long.parseLong(edPrecioAgregarProducto.getText().toString());
        String urlImg = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/0d265602-b5fa-46c1-8eb6-b3e56c7f52b9/calzado-air-jordan-1-mid-tXSJ73.png"; // a futuro se trae la imagen desde firebase

        ProductoModel productoModel = new ProductoModel(nombre, descripcion, precio, urlImg);

        mDatabase.child("productos").push().setValue(productoModel);
    }


    private boolean validarDatosProducto() {

        if (edNombreAgregarProducto.getText().toString().isEmpty()) {
            edNombreAgregarProducto.setError("Campo Obligatorio");
            return false;
        }

        if (multiDescripcionAgregarProducto.getText().toString().isEmpty()) {
            multiDescripcionAgregarProducto.setError("Campo Obligatorio");
            return false;
        }

        if (edPrecioAgregarProducto.getText().toString().isEmpty()) {
            edPrecioAgregarProducto.setError("Campo Obligatorio");
            return false;
        }

        return true;
    }
}