package com.example.fitfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class ReceitaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_receita);

        //Set Action Bar
        Toolbar toolbar = findViewById(R.id.toolbar_receita);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//This make de hamburger icon works
        }
        drawer = findViewById(R.id.drawer_receita);

        //hamburger button
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorWhite));
        drawer.addDrawerListener(toggle);
        drawer.openDrawer(GravityCompat.START);
        toggle.syncState();//rotate de hamburger icon

        NavigationView navigationView = findViewById(R.id.nav_receita);//Looking the Navigation view we created in activity_receita
        navigationView.setNavigationItemSelectedListener(this);

        //First Fragment activity that will be shown at beggining
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_receita_container,
                    new ReceitaFragmentPreTreino()).commit();
            navigationView.setCheckedItem(R.id.nav_receita_pre_treino);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) { //Switch case of each topic we created in menu_receita
            case R.id.nav_receita_almoco:
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.replace(R.id.fragment_receita_container,
                        new ReceitaFragmentAlmoco()).commit();
                break;

            case R.id.nav_receita_cafe:
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.replace(R.id.fragment_receita_container,
                        new ReceitaFragmentCafe()).commit();
                break;

            case R.id.nav_receita_janta:
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.replace(R.id.fragment_receita_container,
                        new ReceitaFragmentJanta()).commit();
                break;

            case R.id.nav_receita_lanche:
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.replace(R.id.fragment_receita_container,
                        new ReceitaFragmentLanche()).commit();
                break;

            case R.id.nav_receita_pos_treino:
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.replace(R.id.fragment_receita_container,
                        new ReceitaFragmentPosTreino()).commit();
                break;

            case R.id.nav_receita_pre_treino:
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.replace(R.id.fragment_receita_container,
                        new ReceitaFragmentPreTreino()).commit();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        //make sure the hamburger icon works the way it should
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    //back animation
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
