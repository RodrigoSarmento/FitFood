package com.example.fitfood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterReceita extends BaseAdapter implements Filterable {
    private List<ItemsGridViewReceita> itemsGridViewReceitas;
    private List<ItemsGridViewReceita> itemsGridViewsFilteredReceita;
    private Context context;
    private Activity activity;

    public CustomAdapterReceita(List<ItemsGridViewReceita> itemsGridViewReceitas, Context context, Activity activity) {
        this.itemsGridViewReceitas = itemsGridViewReceitas;
        this.itemsGridViewsFilteredReceita = itemsGridViewReceitas;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return itemsGridViewsFilteredReceita.size();
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
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_grid_receita, null);
        ImageView imageView = view.findViewById(R.id.item_grid_receita_image);
        TextView nome = view.findViewById(R.id.item_grid_receita_nome);

        imageView.setImageResource(itemsGridViewsFilteredReceita.get(position).getImage());
        nome.setText(itemsGridViewsFilteredReceita.get(position).getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent( activity, ItemViewActivityReceita.class).putExtra("item", itemsGridViewsFilteredReceita.get(position)));
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
                    filterResults.count = itemsGridViewReceitas.size();
                    filterResults.values = itemsGridViewReceitas;
                }else{
                    String searchStr = constraint.toString().toLowerCase();
                    List<ItemsGridViewReceita> resultData = new ArrayList<>();

                    for(ItemsGridViewReceita itemsGridView : itemsGridViewReceitas){
                        if(itemsGridView.getName().contains(searchStr) || itemsGridView.getModoDePreparo().contains(searchStr)){
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
                itemsGridViewsFilteredReceita = (List<ItemsGridViewReceita>) results.values;
                notifyDataSetChanged();
            }
        };

        return filter;
    }
}
