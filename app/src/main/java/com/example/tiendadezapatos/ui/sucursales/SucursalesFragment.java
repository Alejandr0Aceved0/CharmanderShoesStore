package com.example.tiendadezapatos.ui.sucursales;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiendadezapatos.R;
import com.example.tiendadezapatos.ui.sucursales.adapter.SucursalAdapter;
import com.example.tiendadezapatos.ui.sucursales.model.SucursalModel;

import java.util.LinkedList;
import java.util.List;

public class SucursalesFragment extends Fragment {

    RecyclerView recyclerViewSucursales;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sucursales, container, false);

        List<SucursalModel> sucursales = new LinkedList<>();
        sucursales.add(new SucursalModel(R.drawable.banner_1));
        sucursales.add(new SucursalModel(R.drawable.banner_2));
        sucursales.add(new SucursalModel(R.drawable.banner_3));

        recyclerViewSucursales = view.findViewById(R.id.recyclerViewSucursales);
        recyclerViewSucursales.setLayoutManager(new LinearLayoutManager(getContext()));

        SucursalAdapter adapter = new SucursalAdapter(sucursales);
        recyclerViewSucursales.setAdapter(adapter);

        return view;
    }
}