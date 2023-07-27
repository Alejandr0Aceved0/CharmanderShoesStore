package com.example.tiendadezapatos.ui.productos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiendadezapatos.R;
import com.example.tiendadezapatos.ui.productos.adapter.ProductoAdapter;
import com.example.tiendadezapatos.ui.productos.model.ProductoModel;

import java.util.LinkedList;
import java.util.List;

public class ProductosFragment extends Fragment {

    RecyclerView recyclerViewProducto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productos, container, false);

        recyclerViewProducto = view.findViewById(R.id.recyclerViewProductos);

        List<ProductoModel> productos = new LinkedList<>();

        productos.add(new ProductoModel("Nike Air", "Zapato deportivo para correr", 250000, R.drawable.zapato1));
        productos.add(new ProductoModel("Nike Air Interior", "Zapato deportivo y casual uso interior", 150000, R.drawable.zapato2));
        productos.add(new ProductoModel("Adidas Jordan", "Zapato color menta atractivo a la vista", 200000, R.drawable.zapato3));
        productos.add(new ProductoModel("PUMA", "Zapato para uso exterior", 180000, R.drawable.zapato4));
        productos.add(new ProductoModel("Nike Air", "Zapato deportivo para correr", 250000, R.drawable.zapato1));
        productos.add(new ProductoModel("Nike Air Interior", "Zapato deportivo y casual uso interior", 150000, R.drawable.zapato2));
        productos.add(new ProductoModel("Adidas Jordan", "Zapato color menta atractivo a la vista", 200000, R.drawable.zapato3));
        productos.add(new ProductoModel("PUMA", "Zapato para uso exterior", 180000, R.drawable.zapato4));

        ProductoAdapter adapter = new ProductoAdapter(productos);
        recyclerViewProducto.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewProducto.setAdapter(adapter);
        return view;
    }
}