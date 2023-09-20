package com.mk.darkmodefragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aplicarConfiguraciones();
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        replaceFragment(new HomeFragment());
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.navigation_cart) {
                replaceFragment(new CartFragment());
            } else if (item.getItemId() == R.id.navigation_history) {
                replaceFragment(new HistoryFragment());
            } else if (item.getItemId() == R.id.navigation_settings) {
                replaceFragment(new SettingsFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment framFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_frame_layout, framFragment);
        fragmentTransaction.commit();
    }

    private void aplicarConfiguraciones() {
        // Crear o acceder a las SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("minka", MODE_PRIVATE);

        // Leer las configuraciones guardadas
        int tema = sharedPreferences.getInt("tema", 0); // 0 es el valor por defecto (Sistema)
        boolean sonido = sharedPreferences.getBoolean("sonidoActivado", true); // true es el valor por defecto
        boolean vibracion = sharedPreferences.getBoolean("vibracionActivada", true); // true es el valor por defecto

        // Aquí va tu código para aplicar las configuraciones
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