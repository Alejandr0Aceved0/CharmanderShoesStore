package com.example.tiendadezapatos.ui.productos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiendadezapatos.R;
import com.example.tiendadezapatos.ui.productos.holders.ProductoViewHolder;
import com.example.tiendadezapatos.ui.productos.model.ProductoModel;

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

    /*public class ProductoViewHolder extends RecyclerView.ViewHolder {

        private String ARCHIVO_SHARED_PREFERENCES = "DATOS_USUARIO";

        private String KEY_CORREO = "correo";
        private String KEY_PASS = "pass";
        private String KEY_ROL = "admin";

        ImageView imageVProducto;
        TextView txtTituloProducto, txtDescripcionProducto, txtPrecioProducto;
        ImageButton btnEditarProducto, btnEliminarProducto;
        LinearLayout lnOpcionesAdmin;

        int posicion;

        public ProductoViewHolder(@NonNull View itemView, List<ProductoModel>) {
            super(itemView);
            imageVProducto = itemView.findViewById(R.id.imgVProducto);
            txtTituloProducto = itemView.findViewById(R.id.txtTituloProducto);
            txtDescripcionProducto = itemView.findViewById(R.id.txtDescripcionProducto);
            txtPrecioProducto = itemView.findViewById(R.id.txtPrecioProducto);
            btnEditarProducto = itemView.findViewById(R.id.imgBtnEditarProducto);
            btnEliminarProducto = itemView.findViewById(R.id.imgBtnEliminarProducto);
            lnOpcionesAdmin = itemView.findViewById(R.id.lnOpcionesAdmin);

            Context context = itemView.getContext();
            //Se crea la variable getContext para obtener el contexto de la vista,
            //nose puede usar el solo "getSharedPreferences" pórque no estamos en ua actividad ni fragment.

            SharedPreferences sharedPreferences = context.getSharedPreferences(ARCHIVO_SHARED_PREFERENCES, context.MODE_PRIVATE);
            String rol = sharedPreferences.getString(KEY_ROL, "");

            if (rol.equals("admin")) {
                lnOpcionesAdmin.setVisibility(View.VISIBLE);
            }else{
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
        }
    }*/

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.txtTituloProducto.setText(productos.get(position).getNombre());
        holder.txtDescripcionProducto.setText(productos.get(position).getDescripcion());
        holder.txtPrecioProducto.setText("$"+ productos.get(position).getPrecio());
        holder.imageVProducto.setImageResource(productos.get(position).getImagen());
        holder.posicion = holder.getAdapterPosition();
    }
}
