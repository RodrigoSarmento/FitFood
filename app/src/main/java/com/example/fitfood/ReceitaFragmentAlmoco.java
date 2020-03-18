package com.example.fitfood;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;
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

    public ReceitaFragmentAlmoco() {
        setHasOptionsMenu(true);
    }

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

    public class CustomAdapter extends BaseAdapter implements Filterable {
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            View view = inflater.inflate(R.layout.item_grid, null);
            ImageView imageView = view.findViewById(R.id.receita_imageView);
            TextView textView_Name = view.findViewById(R.id.receita_textView_Name);
            TextView textView_Desc = view.findViewById(R.id.receita_textView_Desc);

            imageView.setImageResource(itemsGridViewsFiltered.get(position).getImage());
            textView_Name.setText(itemsGridViewsFiltered.get(position).getName());
            textView_Desc.setText(itemsGridViewsFiltered.get(position).getDesc());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent( getActivity(), ItemViewActivity.class).putExtra("item", itemsGridViewsFiltered.get(position)));
                }
            });

            return view;
        }

        @Override
        public Filter getFilter() {
            final Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    FilterResults filterResults = new FilterResults();

                    if(constraint == null || constraint.length() == 0){
                        filterResults.count = itemsList.size();
                        filterResults.values = itemsList;
                    }else{
                        String searchStr = constraint.toString().toLowerCase();
                        List<ItemsGridView> resultData = new ArrayList<>();

                        for(ItemsGridView itemsGridView : itemsList){
                            if(itemsGridView.getName().contains(searchStr) || itemsGridView.getDesc().contains(searchStr)){
                                resultData.add(itemsGridView);
                            }
                            filterResults.count = resultData.size();
                            filterResults.values = resultData;
                        }
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    itemsGridViewsFiltered = (List<ItemsGridView>) results.values;
                    notifyDataSetChanged();
                }
            };

            return filter;
        }
    }
}
