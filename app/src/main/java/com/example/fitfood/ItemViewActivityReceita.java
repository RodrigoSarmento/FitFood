package com.example.fitfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemViewActivityReceita extends AppCompatActivity  {

    ImageView image;
    TextView textView;
    TextView textViewIngredientes;

    ItemsGridViewReceita itemsGridViewReceita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_item_layout_receita);

        image = findViewById(R.id.image_detalhe_receita);
        textView = findViewById(R.id.titulo_detalhe_receita);
        textViewIngredientes = findViewById(R.id.ingredientes_detalhe_receita);
        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            itemsGridViewReceita = (ItemsGridViewReceita) intent.getSerializableExtra("item");
            image.setImageResource(itemsGridViewReceita.getImage());
            textView.setText(itemsGridViewReceita.getName());
            textViewIngredientes.setText(itemsGridViewReceita.getIngredientes());
        }
    }
}
