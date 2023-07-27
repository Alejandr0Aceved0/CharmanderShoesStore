package com.example.tiendadezapatos.ui.productos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiendadezapatos.R;
import com.example.tiendadezapatos.ui.productos.model.ProductoModel;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<ProductoModel> productos;

    public ProductoAdapter(List<ProductoModel> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductoAdapter.ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        ImageView imageVProducto;
        TextView txtTituloProducto, txtDescripcionProducto, txtPrecioProducto;

        int posicion;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageVProducto = itemView.findViewById(R.id.imgVProducto);
            txtTituloProducto = itemView.findViewById(R.id.txtTituloProducto);
            txtDescripcionProducto = itemView.findViewById(R.id.txtDescripcionProducto);
            txtPrecioProducto = itemView.findViewById(R.id.txtPrecioProducto);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(),"Producto seleccionado: " + String.valueOf(txtTituloProducto), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ProductoViewHolder holder, int position) {
        holder.txtTituloProducto.setText(productos.get(position).getNombre());
        holder.txtDescripcionProducto.setText(productos.get(position).getDescripcion());
        holder.txtPrecioProducto.setText("$"+ productos.get(position).getPrecio());
        holder.imageVProducto.setImageResource(productos.get(position).getImagen());
        holder.posicion = holder.getAdapterPosition();
    }
}
