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

public class CustomAdapterComida extends BaseAdapter implements Filterable {
    private List<ItemsGridViewComida> itemsGridViewComida;
    private List<ItemsGridViewComida> itemsGridViewsFilteredComida;
    private Context context;
    private Activity activity;

    public CustomAdapterComida(List<ItemsGridViewComida> itemsGridViewComida, Context context, Activity activity) {
        this.itemsGridViewComida = itemsGridViewComida;
        this.itemsGridViewsFilteredComida = itemsGridViewComida;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return itemsGridViewsFilteredComida.size();
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

        View view = inflater.inflate(R.layout.item_grid, null);
        ImageView imageView = view.findViewById(R.id.item_grid_image);
        TextView textView_Name = view.findViewById(R.id.item_grid_text);

        imageView.setImageResource(itemsGridViewsFilteredComida.get(position).getImageComida());
        textView_Name.setText(itemsGridViewsFilteredComida.get(position).getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent( activity, ItemViewActivityComida.class).putExtra("item", itemsGridViewsFilteredComida.get(position)));
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
                    filterResults.count = itemsGridViewComida.size();
                    filterResults.values = itemsGridViewComida;
                }else{
                    String searchStr = constraint.toString().toLowerCase();
                    List<ItemsGridViewComida> resultData = new ArrayList<>();

                    for(ItemsGridViewComida itemsGridView : itemsGridViewComida){
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
                itemsGridViewsFilteredComida = (List<ItemsGridViewComida>) results.values;
                notifyDataSetChanged();
            }
        };

        return filter;
    }
}
