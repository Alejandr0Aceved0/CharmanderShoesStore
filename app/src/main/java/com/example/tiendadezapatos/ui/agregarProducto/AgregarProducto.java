package com.example.tiendadezapatos.ui.agregarProducto;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
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

import com.example.tiendadezapatos.R;

public class AgregarProducto extends Fragment {

    private final int CODE_INTENT_CAMERA = 10;
    private final int CODE_permission_CAMERA = 20;
    EditText edNombreAgregarProducto, edPrecioAgregarProducto;
    MultiAutoCompleteTextView multiDescripcionAgregarProducto;
    Button btnAgregarProducto;
    ImageView imvTomarFotoProducto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agregar_producto, container, false);

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
                agregarProducto();
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
        }else{
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

    private void agregarProducto() {

    }
}