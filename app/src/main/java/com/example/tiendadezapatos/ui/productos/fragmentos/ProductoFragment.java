package com.example.tiendadezapatos.ui.productos.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ActionTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.TouchListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.tiendadezapatos.R;
import com.example.tiendadezapatos.ui.productos.adapter.ProductoAdapter;
import com.example.tiendadezapatos.ui.productos.model.ProductoModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class ProductoFragment extends Fragment {

    DatabaseReference mDatabase;

    ImageSlider imageSlider;
    RecyclerView recyclerViewProducto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_productos, container, false);

        recyclerViewProducto = view.findViewById(R.id.recycler_view_productos);
        imageSlider = view.findViewById(R.id.image_slider_banner);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        List<SlideModel> imageSliders = new LinkedList<>();
        imageSliders.add(new SlideModel("https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/8463e545-701f-496d-a794-3bf38910d604/calzado-de-running-en-carretera-infinityrn-4-ftm0ff.png", ScaleTypes.CENTER_CROP));
        imageSliders.add(new SlideModel(R.drawable.banner_2,  ScaleTypes.FIT));
        imageSliders.add(new SlideModel(R.drawable.banner_3, "banner 3", ScaleTypes.FIT));
        imageSliders.add(new SlideModel("R.drawable.banner_3",  ScaleTypes.FIT));
        imageSlider.setImageList(imageSliders);

        imageSlider.setTouchListener(new TouchListener() {
            @Override
            public void onTouched(@NonNull ActionTypes actionTypes, int i) {
                Toast.makeText(getContext(), "CLICKEADO", Toast.LENGTH_SHORT).show();
            }
        });

        mostrarProductos();
        return view;
    }

    private void mostrarProductos() {
        DatabaseReference productosResponse = mDatabase.child("productos");
        productosResponse.addValueEventListener(new ValueEventListener() {//EventListener significa que se estará ejecutando e segundo plano, escuchando si se agregan o eliminan elementos en productos en Firebase
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //CAPTURA CAMBIOS EN EL DATABASE
                List<ProductoModel> productos = new LinkedList<>();
                for (DataSnapshot childrenProductos : dataSnapshot.getChildren()){
                    System.out.println("NOS LLEGA ESTA DATA CHILDREN: "+ childrenProductos);
                    ProductoModel product = childrenProductos.getValue(ProductoModel.class);
                    product.setId(childrenProductos.getKey()); //ID DEL JSON OBJETO, COD ALEATORIO
                    product.setImagen(R.drawable.zapato3);
                    productos.add(product);
                }
                ProductoAdapter adapter = new ProductoAdapter(productos);
                recyclerViewProducto.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerViewProducto.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}