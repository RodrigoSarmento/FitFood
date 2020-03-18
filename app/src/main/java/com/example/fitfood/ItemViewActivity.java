package com.example.fitfood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemViewActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    TextView textViewIngredientes;

    ItemsGridView itemsGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_item_layout);

        imageView = findViewById(R.id.image_view_item);
        textView = findViewById(R.id.text_view_name_item);
        textViewIngredientes = findViewById(R.id.text_view_name_item);

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            itemsGridView = (ItemsGridView) intent.getSerializableExtra("item");
            imageView.setImageResource(itemsGridView.getImage());
            textView.setText(itemsGridView.getName());
            textViewIngredientes.setText(itemsGridView.getIngredientes());
        }
    }
}
