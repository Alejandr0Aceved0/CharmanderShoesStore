package com.example.tiendadezapatos.ui.productos.fragmentos;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.tiendadezapatos.R;
import com.example.tiendadezapatos.ui.productos.model.ProductoModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AgregarProductoFragment extends Fragment {

    private final int CODE_INTENT_CAMERA = 11;
    private final int CODE_INTENT_GALERIA = 12;
    private final int CODE_permission_CAMERA = 20;

    private DatabaseReference mDatabase;
    FirebaseStorage storage;

    private String urlImage= null;

    EditText edNombreAgregarProducto, edPrecioAgregarProducto;
    MultiAutoCompleteTextView multiDescripcionAgregarProducto;
    Button btnAgregarProducto;
    ImageView imvTomarFotoProducto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agregar_producto, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference(); //BASE DE DATOS PLANO NO RELACION EN FORMATO JSON
        storage = FirebaseStorage.getInstance("gs://charmandershoesshop.appspot.com");

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
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext())
                .setTitle("OBTENER IMAGEN")
                .setMessage("¿De donde quiere tomar la foto?")
                .setPositiveButton("Cámara", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
                })
                .setNegativeButton("Galeria", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //validar permiso de camara
                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                                && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                            Intent galeriaIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                            startActivityForResult(galeriaIntent, CODE_INTENT_GALERIA);
                        } else {
                            //lanzamos venta para obtener el permiso de camara en caso de que la app no tenga dicho permiso de camara
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, CODE_INTENT_GALERIA);
                        }
                    }
                });
        alert.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CODE_INTENT_CAMERA:
                Bitmap miniatura = (Bitmap) data.getExtras().get("data");
                imvTomarFotoProducto.setImageBitmap(miniatura);
                break;

            case CODE_INTENT_GALERIA:
                assert data != null;
                Uri urlSeleccionGaleria = data.getData();
                imvTomarFotoProducto.setImageURI(urlSeleccionGaleria);

                // Create a storage reference from our app
                StorageReference storageRef = storage.getReference().child(getString(R.string.db_name_productos));
                StorageReference riversRef = storageRef.child(urlSeleccionGaleria.getLastPathSegment());

                UploadTask uploadTask = riversRef.putFile(urlSeleccionGaleria);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();

                        uri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                System.out.println("DIRECCION DE FOTO ES:" + uri);
                                urlImage = uri.toString();
                            }
                        });
                    }
                });
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
        String urlImg = urlImage;

        ProductoModel productoModel = new ProductoModel(nombre, descripcion, precio, urlImg);

        mDatabase.child("productos").push().setValue(productoModel);

        limpiarCampos();
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

        if (urlImage == null) {
            Toast.makeText(getContext(), "FOTO OBLIGATORIA", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void limpiarCampos(){
        urlImage = null;
        imvTomarFotoProducto.setImageResource(R.drawable.poke_photo);
        edNombreAgregarProducto.setText(null);
        multiDescripcionAgregarProducto.setText(null);
        edPrecioAgregarProducto.setText(null);
    }
}