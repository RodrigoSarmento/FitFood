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
public class ComidaFragmentLanche extends Fragment {

    // VARIÁVEIS PARA O GRIDVIEW
    int[] images = {R.drawable.batata_doce, R.drawable.brownie_banana, R.drawable.crepioca_requeijao, R.drawable.panqueca_banana};
    String[] names;
    String[] desc = {"batata doce", "brownie", "crepioca", "panqueca"};
    GridView gridView;
    List<ItemsGridView> itemsList = new ArrayList<>();
    CustomAdapter customAdapter;

    public ComidaFragmentLanche() {
        setHasOptionsMenu(true);
    }

    // FIM VARIÁVEIS PARA O GRIDVIEW
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        names = getResources().getStringArray(R.array.recipes);
        View view = inflater.inflate(R.layout.fragment_receita_almoco, container, false);

        // PARADAS DO GRIDVIEW
        gridView = view.findViewById(R.id.grid_view_receita_almoco);

        for (int i = 0; i < names.length; i++) {
            ItemsGridView itemsGridView = new ItemsGridView(names[i], desc[i], images[i]);
            itemsList.add(itemsGridView);
        }
        customAdapter = new CustomAdapter(itemsList, getContext(), getActivity());
        gridView.setAdapter(customAdapter);
        // FIM DAS PARADAS DO GRIDVIEW


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