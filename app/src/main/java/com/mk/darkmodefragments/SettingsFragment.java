package com.mk.darkmodefragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class SettingsFragment extends Fragment {

    private LinearLayoutCompat llcTema;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        llcTema = view.findViewById(R.id.llTema);

        llcTema.setOnClickListener(v -> {
            // Crear un array con las opciones para los radio buttons
            String[] temas = {"Sistema", "Claro", "Oscuro"};

            // Verificar que el contexto no es nulo
            Context context = getContext();
            if (context != null) {
                // Obtener la preferencia de tema actual
                SharedPreferences sharedPreferences = context.getSharedPreferences("minka", Context.MODE_PRIVATE);
                int temaActual = sharedPreferences.getInt("tema", 0); // 0 es el valor por defecto (Sistema)

                // Crear un nuevo diálogo
                new MaterialAlertDialogBuilder(context)
                        .setTitle("Tema")
                        .setSingleChoiceItems(temas, temaActual, null)
                        .setPositiveButton("Aplicar", (dialogInterface, i) -> {
                            // Obtener la selección del usuario
                            int selectedPosition = ((AlertDialog) dialogInterface).getListView().getCheckedItemPosition();

                            // Actualizar el tema y la bandera temaCambiado
                            if (selectedPosition != -1) {
                                actualizarTema(selectedPosition, sharedPreferences);
                            }

                            // Desactivar el estado de "presionado" del LinearLayout
                            v.setPressed(false);
                        })
                        .setNegativeButton("Cancelar", (dialogInterface, i) -> {
                            // Desactivar el estado de "presionado" del LinearLayout
                            v.setPressed(false);
                        })
                        .show();
            }
        });

    }

    private void actualizarTema(int temaSeleccionado, SharedPreferences sharedPreferences) {
        // Guardar la preferencia de tema
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("tema", temaSeleccionado);
        editor.apply();

        // Aplicar el tema seleccionado
        switch (temaSeleccionado) {
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
