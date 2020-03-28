package com.example.fitfood;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemViewActivityComida extends AppCompatActivity implements View.OnClickListener {
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    ImageView image;
    ImageView image_info;
    TextView nome;
    TextView ingredientes;
    TextView modo_de_preparo;
    Button button_info;

    ItemsGridViewComida itemsGridViewComida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_item_layout_comida);

        image = findViewById(R.id.comida_detalhe_image);
        nome = findViewById(R.id.comida_detalhe_nome);
        ingredientes = findViewById(R.id.comida_detalhes_ingredientes);
        modo_de_preparo = findViewById(R.id.comida_detalhe_modo_de_preparo);
        button_info = findViewById(R.id.button_info_comida);
        button_info.setOnClickListener(this);
        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            itemsGridViewComida = (ItemsGridViewComida) intent.getSerializableExtra("item");
            image.setImageResource(itemsGridViewComida.getImageComida());
            nome.setText(itemsGridViewComida.getName());
            ingredientes.setText(itemsGridViewComida.getIngredientes());
            modo_de_preparo.setText(itemsGridViewComida.getModoDePreparo());
        }
    }

    public void onClick(View v) {
        if (v.getId() == button_info.getId()) {
            v.startAnimation(buttonClick); //button animation
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            LayoutInflater factory = LayoutInflater.from(this);
            @SuppressLint("InflateParams") final View view = factory.inflate(R.layout.info_generic, null);
            ImageView image = view.findViewById(R.id.dialog_imageview);
            image.setImageResource(itemsGridViewComida.getImageInfo());
            alert.setView(view);
            alert.show();
        }
    }
}
