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

public class CustomAdapter extends BaseAdapter implements Filterable {
    private List<ItemsGridView> itemsGridViews;
    private List<ItemsGridView> itemsGridViewsFiltered;
    private Context context;
    private Activity activity;

    public CustomAdapter(List<ItemsGridView> itemsGridViews, Context context, Activity activity) {
        this.itemsGridViews = itemsGridViews;
        this.itemsGridViewsFiltered = itemsGridViews;
        this.context = context;
        this.activity = activity;
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
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_grid, null);
        ImageView imageView = view.findViewById(R.id.receita_imageView);
        TextView textView_Name = view.findViewById(R.id.receita_textView_Name);

        imageView.setImageResource(itemsGridViewsFiltered.get(position).getImage());
        textView_Name.setText(itemsGridViewsFiltered.get(position).getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent( activity, ItemViewActivity.class).putExtra("item", itemsGridViewsFiltered.get(position)));
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
                    filterResults.count = itemsGridViews.size();
                    filterResults.values = itemsGridViews;
                }else{
                    String searchStr = constraint.toString().toLowerCase();
                    List<ItemsGridView> resultData = new ArrayList<>();

                    for(ItemsGridView itemsGridView : itemsGridViews){
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
