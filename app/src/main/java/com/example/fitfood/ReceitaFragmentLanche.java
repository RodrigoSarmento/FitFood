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
public class ReceitaFragmentLanche extends Fragment {

    // VARIÁVEIS PARA O GRIDVIEW
    int[] images = {R.drawable.batata_doce, R.drawable.brownie_banana, R.drawable.crepioca_requeijao, R.drawable.panqueca_banana};
    String[] names = {"Batata Doce", "Brownie de Banana", "Crepioca de Requeijão", "Panqueca de Banana"};
    String[] desc = {"batata doce", "brownie", "crepioca", "panqueca"};
    String[] ingredientes = {"1 batata doce", "pó de chocolate e banana", "requeijao e n sei mais o q", "banana e ovo eu acho, sei n"};
    GridView gridView;
    List<ItemsGridViewReceita> itemsList = new ArrayList<>();
    CustomAdapterReceita customAdapter;
    // FIM VARIÁVEIS PARA O GRIDVIEW

    public ReceitaFragmentLanche() {
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_receita_lanche, container, false);

        //finding grid view
        gridView = view.findViewById(R.id.grid_view_receita_lanche);
        //Adding all itens in the gridview list
        for (int i = 0; i < names.length; i++) {
            ItemsGridViewReceita itemsGridViewReceita = new ItemsGridViewReceita(names[i], desc[i], ingredientes[i],images[i]);

            itemsList.add(itemsGridViewReceita);
        }
        customAdapter = new CustomAdapterReceita(itemsList, getContext(), getActivity());
        gridView.setAdapter(customAdapter);

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
                customAdapter.getFilter().filter(newText);
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
