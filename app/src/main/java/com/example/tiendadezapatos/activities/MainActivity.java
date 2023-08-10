package com.example.tiendadezapatos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tiendadezapatos.R;
import com.example.tiendadezapatos.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_productos, R.id.nav_servicios, R.id.nav_sucursales)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manu_nav_home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //SE OBTIENE EL ITEM DE INTERACCION DEL USUARIO
        int id = item.getItemId();
        if (id == R.id.item_compras){
            Toast.makeText(this, "CARRITO DE COMPRAS", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.item_nosotros) {
            Toast.makeText(this, "ITEM NOSOTROS", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.item_cerrar_sesion){
            Toast.makeText(this, "CERRAR SESION", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.item_compras:
                Toast.makeText(this, "CARRITO DE COMPRAS", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_nosotros:
                Toast.makeText(this, "ITEM NOSOTROS", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_cerrar_sesion:
                Intent intent   = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

//        Toast.makeText(this, "ERROR DE SELECCION", Toast.LENGTH_SHORT).show();
        return true;
    }
}