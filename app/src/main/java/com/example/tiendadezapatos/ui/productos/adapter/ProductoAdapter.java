package com.example.tiendadezapatos.ui.productos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiendadezapatos.R;
import com.example.tiendadezapatos.ui.productos.holders.ProductoViewHolder;
import com.example.tiendadezapatos.ui.productos.model.ProductoModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoViewHolder> {

    private List<ProductoModel> productos;

    public ProductoAdapter(List<ProductoModel> productos) {
        this.productos = productos;
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_producto, parent, false);
        return new ProductoViewHolder(view, productos);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.txtTituloProducto.setText(productos.get(position).getNombre());
        holder.txtDescripcionProducto.setText(productos.get(position).getDescripcion());
        holder.txtPrecioProducto.setText("$"+ productos.get(position).getPrecio());
        holder.imageVProducto.setImageResource(productos.get(position).getImagen());
        Picasso.get()
                .load(productos.get(position).getUriImg())
                .error(R.drawable.poke_photo)
                .into(holder.imageVProducto);
        holder.posicion = holder.getAdapterPosition();
    }
}
