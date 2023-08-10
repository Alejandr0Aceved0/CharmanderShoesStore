package com.example.tiendadezapatos.ui.sucursales.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiendadezapatos.R;
import com.example.tiendadezapatos.ui.sucursales.model.SucursalModel;

import java.util.List;

public class SucursalAdapter extends RecyclerView.Adapter<SucursalAdapter.ViewHolder> {

    List<SucursalModel> sucursales;

    public SucursalAdapter(List<SucursalModel> sucursales) {
        this.sucursales = sucursales;
    }

    @Override
    public int getItemCount() {
        return sucursales.size();
    }

    @NonNull
    @Override
    public SucursalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_sucursal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SucursalAdapter.ViewHolder holder, int position) {
        holder.imgCardViewSucursal.setImageResource(sucursales.get(position).getImgBanner());
        holder.position = holder.getAdapterPosition();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCardViewSucursal;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCardViewSucursal = itemView.findViewById(R.id.imgCardViewSucursal);
        }
    }
}
