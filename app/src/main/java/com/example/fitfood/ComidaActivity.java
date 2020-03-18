package com.example.fitfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class ComidaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comida);

        //Set Action Bar
        Toolbar toolbar = findViewById(R.id.toolbar_comida);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//This make de hamburger icon works
        }
        drawer = findViewById(R.id.drawer_comida);

        //hamburger button
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorWhite));
        drawer.addDrawerListener(toggle);
        drawer.openDrawer(GravityCompat.START);
        toggle.syncState();//rotate de hamburger icon

        NavigationView navigationView = findViewById(R.id.nav_comida);//Looking the Navigation view we created in activity_receita
        navigationView.setNavigationItemSelectedListener(this);

        //First Fragment activity that will be shown at beggining
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_comida_container,
                    new ReceitaFragmentPreTreino()).commit();
            navigationView.setCheckedItem(R.id.nav_comida_pre_treino);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) { //Switch case of each topic we created in menu_comida
            case R.id.nav_comida_almoco:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_comida_container,
                        new ComidaFragmentAlmoco()).commit();
                break;

            case R.id.nav_comida_cafe:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_comida_container,
                        new ReceitaFragmentCafe()).commit();
                break;

            case R.id.nav_comida_janta:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_comida_container,
                        new ReceitaFragmentJanta()).commit();
                break;

            case R.id.nav_comida_lanche:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_comida_container,
                        new ReceitaFragmentLanche()).commit();
                break;

            case R.id.nav_comida_pos_treino:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_comida_container,
                        new ReceitaFragmentPosTreino()).commit();
                break;

            case R.id.nav_comida_pre_treino: //If this case is true, we want to open our fragment
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_comida_container,
                        new ReceitaFragmentPreTreino()).commit(); //Name of the fragment Class
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Animação no clique do botão
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
}
