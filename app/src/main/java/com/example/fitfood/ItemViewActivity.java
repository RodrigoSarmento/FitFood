package com.example.fitfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemViewActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    ItemsGridView itemsGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        imageView = findViewById(R.id.image_view_item);
        textView = findViewById(R.id.text_view_name_item);

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            itemsGridView = (ItemsGridView) intent.getSerializableExtra("item");
            imageView.setImageResource(itemsGridView.getImage());
            textView.setText(itemsGridView.getName());
        }
    }
}
