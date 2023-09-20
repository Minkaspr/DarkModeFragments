package com.mk.darkmodefragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
                // Crear un nuevo diálogo
                new MaterialAlertDialogBuilder(context)
                        .setTitle("Tema")
                        .setSingleChoiceItems(temas, -1, (dialogInterface, i) -> {
                            // Aquí puedes manejar la selección del usuario
                            // i es el índice de la opción seleccionada
                        })
                        .setPositiveButton("Aceptar", (dialogInterface, i) -> {
                            // Acción para el botón Aceptar
                            int selectedPosition = ((AlertDialog) dialogInterface).getListView().getCheckedItemPosition();
                            // Usar selectedPosition para obtener la opción seleccionada
                            if (selectedPosition != -1) {
                                String selectedOption = temas[selectedPosition];
                                Toast.makeText(context, "Seleccionaste: " + selectedOption, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
        });

    }
}