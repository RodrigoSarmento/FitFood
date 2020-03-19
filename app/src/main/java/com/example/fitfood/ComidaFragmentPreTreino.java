package com.example.fitfood;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class ComidaFragmentPreTreino extends Fragment {

    // VARIÁVEIS PARA O GRIDVIEW
    int[] images_comida = {R.drawable.batata_doce, R.drawable.brownie_banana, R.drawable.crepioca_requeijao, R.drawable.panqueca_banana};
    int[] images_info = {R.drawable.batata_doce, R.drawable.brownie_banana, R.drawable.crepioca_requeijao, R.drawable.panqueca_banana};
    String[] names = {"Batata Doce", "Brownie de Banana", "Crepioca de Requeijão", "Panqueca de Banana"};
    String[] desc = {"batata doce", "brownie", "crepioca", "panqueca"};
    String[] ingrediente = {};
    GridView gridView;
    List<ItemsGridViewComida> itemsList = new ArrayList<>();
    CustomAdapterComida customAdapterComida;

    public ComidaFragmentPreTreino() {
        setHasOptionsMenu(true);
    }

    // FIM VARIÁVEIS PARA O GRIDVIEW
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comida_pre_treino, container, false);

        // PARADAS DO GRIDVIEW
        gridView = view.findViewById(R.id.grid_view_comida_pre_treino);

        for (int i = 0; i < names.length; i++) {
            ItemsGridViewComida itemsGridViewComida = new ItemsGridViewComida(names[i], desc[i], " ",images_comida[i], images_info[i]);
            itemsList.add(itemsGridViewComida);
        }
        customAdapterComida = new CustomAdapterComida(itemsList, getContext(), getActivity());
        gridView.setAdapter(customAdapterComida);
        // FIM DAS PARADAS DO GRIDVIEW
        //Set info button click

        return view;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search_bar, menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customAdapterComida.getFilter().filter(newText);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search_view) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}