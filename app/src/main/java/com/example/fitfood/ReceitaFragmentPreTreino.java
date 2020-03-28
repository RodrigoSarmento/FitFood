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
public class ReceitaFragmentPreTreino extends Fragment {

    // VARIÁVEIS PARA O GRIDVIEW
    int[] images = {R.drawable.brownie_banana, R.drawable.receita_pre_treino_shake_proteico, R.drawable.receita_pre_treino_shake_termogenico,
            R.drawable.receita_pre_treino_panqueca_cacau, R.drawable.receita_pre_treino_mingau_de_aveia_com_banana,
            R.drawable.receita_pre_treino_crepioca_salgada, R.drawable.receita_pre_treino_panqueca_de_couve, R.drawable.receita_pre_treino_pao_salgado_proteico};
    String[] names;
    String[] ingredientes;
    String[] modo_de_preparo;
    GridView gridView;
    List<ItemsGridViewReceita> itemsList = new ArrayList<>();
    CustomAdapterReceita customAdapter;
    // FIM VARIÁVEIS PARA O GRIDVIEW

    public ReceitaFragmentPreTreino() {
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        names = getResources().getStringArray(R.array.receitas_pre_treino_nome);
        ingredientes = getResources().getStringArray(R.array.receitas_pre_treino_ingredientes);
        modo_de_preparo = getResources().getStringArray(R.array.receitas_pre_treino_modo_de_preparo);

        View view = inflater.inflate(R.layout.fragment_receita_pre_treino, container, false);
        //finding grid view
        gridView = view.findViewById(R.id.grid_view_receita_pre_treino);
        //Adding all itens in the gridview list
        for (int i = 0; i < names.length; i++) {
            ItemsGridViewReceita itemsGridViewReceita = new ItemsGridViewReceita(names[i], ingredientes[i], modo_de_preparo[i], images[i]);
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

        if(id == R.id.search_view){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
