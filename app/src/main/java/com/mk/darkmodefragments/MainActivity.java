package com.mk.darkmodefragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String SELECTED_ITEM = "arg_selected_item";

    private int mSelectedItem;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aplicarConfiguraciones();
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Recuperar el estado guardado del fragmento seleccionado
        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            MenuItem selectedMenuItem = bottomNavigationView.getMenu().findItem(mSelectedItem);
            selectFragment(selectedMenuItem);
        } else {
            // Si no hay un estado guardado, selecciona el primer fragmento
            selectFragment(bottomNavigationView.getMenu().getItem(0));
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            selectFragment(item);
            return true;
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Guardar el estado del fragmento seleccionado
        outState.putInt(SELECTED_ITEM, mSelectedItem);
        super.onSaveInstanceState(outState);
    }

    private void selectFragment(MenuItem item) {
        // Actualizar el elemento seleccionado
        mSelectedItem = item.getItemId();

        // Crear un nuevo fragmento basado en el elemento seleccionado
        Fragment frag;
        if (mSelectedItem == R.id.navigation_home) {
            frag = new HomeFragment();
        } else if (mSelectedItem == R.id.navigation_cart) {
            frag = new CartFragment();
        } else if (mSelectedItem == R.id.navigation_history) {
            frag = new HistoryFragment();
        } else if (mSelectedItem == R.id.navigation_settings) {
            frag = new SettingsFragment();
        } else {
            frag = new HomeFragment();
        }

        // Insertar el fragmento reemplazando cualquier fragmento existente
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container_frame_layout, frag).commit();
    }

    private void aplicarConfiguraciones() {
        SharedPreferences sharedPreferences = getSharedPreferences("minka", MODE_PRIVATE);

        int tema = sharedPreferences.getInt("tema", 0); // 0 es el valor por defecto (Sistema)

        switch (tema) {
            case 0: // Sistema
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1: // Claro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case 2: // Oscuro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }
    }
}
