package com.example.fitfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemViewActivityReceita extends AppCompatActivity  {

    ImageView image;
    ImageView image_info;
    TextView nome;
    TextView ingredientes;
    TextView modo_de_preparo;

    ItemsGridViewReceita itemsGridViewReceita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_item_layout_receita);

        image = findViewById(R.id.receita_detalhe_image);
        nome = findViewById(R.id.receita_detalhe_nome);
        ingredientes = findViewById(R.id.receita_detalhes_ingredientes);
        modo_de_preparo = findViewById(R.id.receita_detalhe_modo_de_preparo);
        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            itemsGridViewReceita = (ItemsGridViewReceita) intent.getSerializableExtra("item");
            image.setImageResource(itemsGridViewReceita.getImage());
            nome.setText(itemsGridViewReceita.getName());
            ingredientes.setText(itemsGridViewReceita.getIngredientes());
            modo_de_preparo.setText(itemsGridViewReceita.getModoDePreparo());
        }
    }
}
