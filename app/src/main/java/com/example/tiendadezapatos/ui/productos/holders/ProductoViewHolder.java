package com.example.tiendadezapatos.ui.productos.holders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiendadezapatos.R;
import com.example.tiendadezapatos.ui.productos.activities.ActualizarProducto;
import com.example.tiendadezapatos.ui.productos.activities.DetallesProductoActivity;
import com.example.tiendadezapatos.ui.productos.model.ProductoModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ProductoViewHolder extends RecyclerView.ViewHolder {

    private String ARCHIVO_SHARED_PREFERENCES = "DATOS_USUARIO";

    private String KEY_CORREO = "correo";
    private String KEY_PASS = "pass";
    private String KEY_ROL = "admin";

    public ImageView imageVProducto;
    public TextView txtTituloProducto;
    public TextView txtDescripcionProducto;
    public TextView txtPrecioProducto;
    ImageButton btnEditarProducto, btnEliminarProducto;
    LinearLayout lnOpcionesAdmin;

    public int posicion;
    private DatabaseReference mDatabase;

    public ProductoViewHolder(@NonNull View itemView, List<ProductoModel> productos) {
        super(itemView);
        imageVProducto = itemView.findViewById(R.id.imgVProducto);
        txtTituloProducto = itemView.findViewById(R.id.txtTituloProducto);
        txtDescripcionProducto = itemView.findViewById(R.id.txtDescripcionProducto);
        txtPrecioProducto = itemView.findViewById(R.id.txtPrecioProducto);
        btnEditarProducto = itemView.findViewById(R.id.imgBtnEditarProducto);
        btnEliminarProducto = itemView.findViewById(R.id.imgBtnEliminarProducto);
        lnOpcionesAdmin = itemView.findViewById(R.id.lnOpcionesAdmin);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Context context = itemView.getContext();
        //Se crea la variable getContext para obtener el contexto de la vista,
        //nose puede usar el solo "getSharedPreferences" pórque no estamos en ua actividad ni fragment.

        SharedPreferences sharedPreferences = context.getSharedPreferences(ARCHIVO_SHARED_PREFERENCES, context.MODE_PRIVATE);
        String rol = sharedPreferences.getString(KEY_ROL, "");

        if (rol.equals("admin")) {
            lnOpcionesAdmin.setVisibility(View.VISIBLE);
        } else {
            lnOpcionesAdmin.setVisibility(View.GONE);
        }

        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context contexto = itemView.getContext();
                Intent intent = new Intent(contexto, DetallesProductoActivity.class);
                intent.putExtra("nombre", productos.get(posicion).getNombre());
                intent.putExtra("descripcion", productos.get(posicion).getDescripcion());
                intent.putExtra("imagen", productos.get(posicion).getUriImg());
                intent.putExtra("precio", productos.get(posicion).getPrecio());
                contexto.startActivity(intent);
            }
        });

        btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String posiciones = productos.get(posicion).getId();
                mDatabase.child(context.getString(R.string.db_name_productos)).child(posiciones).removeValue();
            }
        });

        btnEditarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActualizarProducto.class);
                intent.putExtra("id", productos.get(posicion).getId());
                intent.putExtra("nombre", productos.get(posicion).getNombre());
                intent.putExtra("descripcion", productos.get(posicion).getDescripcion());
                intent.putExtra("precio", productos.get(posicion).getPrecio());
                intent.putExtra("imagen", productos.get(posicion).getImagen());
//                intent.putExtra("uriImg", productos.get(posicion).getUriImg());
                context.startActivity(intent);
            }
        });
    }
}