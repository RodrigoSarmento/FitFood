package com.example.fitfood;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class ReceitaFragmentAlmoco extends Fragment {

    // VARIÁVEIS PARA O GRIDVIEW
    int[] images = {R.drawable.batata_doce, R.drawable.brownie_banana, R.drawable.crepioca_requeijao, R.drawable.panqueca_banana};
    String[] names = {"Batata Doce", "Brownie de Banana", "Crepioca de Requeijão", "Panqueca de Banana"};
    String[] desc = {"batata doce", "brownie", "crepioca", "panqueca"};
    GridView gridView;
    List<ItemsGridView> itemsList = new ArrayList<>();
    CustomAdapter customAdapter;

    // FIM VARIÁVEIS PARA O GRIDVIEW
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_receita_almoco, container, false);

        // PARADAS DO GRIDVIEW
        gridView = view.findViewById(R.id.grid_view_receita_almoco);

        for (int i = 0; i < names.length; i++) {
            ItemsGridView itemsGridView = new ItemsGridView(names[i], desc[i], images[i]);
            itemsList.add(itemsGridView);
        }
        customAdapter = new CustomAdapter(itemsList, getContext());
        gridView.setAdapter(customAdapter);
        // FIM DAS PARADAS DO GRIDVIEW

        return view;

    }

    public class CustomAdapter extends BaseAdapter {
        private List<ItemsGridView> itemsGridViews;
        private List<ItemsGridView> itemsGridViewsFiltered;
        private Context context;

        public CustomAdapter(List<ItemsGridView> itemsGridViews, Context context) {
            this.itemsGridViews = itemsGridViews;
            this.itemsGridViewsFiltered = itemsGridViews;
            this.context = getContext();
        }

        @Override
        public int getCount() {
            return itemsGridViewsFiltered.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            View view = inflater.inflate(R.layout.item_grid, null);
            ImageView imageView = view.findViewById(R.id.receita_imageView);
            TextView textView_Name = view.findViewById(R.id.receita_textView_Name);
            TextView textView_Desc = view.findViewById(R.id.receita_textView_Desc);

            imageView.setImageResource(itemsGridViewsFiltered.get(position).getImage());
            textView_Name.setText(itemsGridViewsFiltered.get(position).getName());
            textView_Desc.setText(itemsGridViewsFiltered.get(position).getDesc());

            return view;
        }
    }
}
