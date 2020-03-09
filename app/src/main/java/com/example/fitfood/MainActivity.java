package com.example.fitfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //Instanciando a classe ViewHolder
    private final ViewHolder mViewHolder = new ViewHolder();
    //Animação no clique do botão
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fazendo as chamadas dos butões para não ter que buscar de novo em toads as chamadas
        this.mViewHolder.button_receita = findViewById(R.id.main_button_receita);
        this.mViewHolder.button_comida = findViewById(R.id.main_button_comida);

        //Button listener in order to open another activity
        this.mViewHolder.button_receita.setOnClickListener(this);
        this.mViewHolder.button_comida.setOnClickListener(this);

    }

    private void openReceitaActivity() {
        //open Receita Activity after press button
        Intent intent = new Intent(this, ReceitaActivity.class);
        startActivity(intent);
    }

    private void openComidaActivity() {
        //open Comida Activity after press button
        Intent intent = new Intent(this, ComidaActivity.class);
        startActivity(intent);
    }

    public void onClick(View v) {
        //listening click button and open an activity
        if (v.getId() == R.id.main_button_receita) {
            v.startAnimation(buttonClick);
            openReceitaActivity();
        } else if (v.getId() == R.id.main_button_comida) {
            v.startAnimation(buttonClick);
            openComidaActivity();
        }
    }

    private static class ViewHolder {
        //Aux class to not over call findViewById
        Button button_receita;
        Button button_comida;

    }

}


