package com.example.fitfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;

import com.google.android.material.navigation.NavigationView;

public class ComidaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comida); //Openning layout

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

        NavigationView navigationView = findViewById(R.id.nav_comida);//Looking the Navigation view we created in activity_comida
        navigationView.setNavigationItemSelectedListener(this);

        //First Fragment activity that will be shown at beggining
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_comida_container,
                    new ComidaFragmentPreTreino()).commit();
            navigationView.setCheckedItem(R.id.nav_comida_pre_treino);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) { //Switch case of each topic we created in menu_comida
            case R.id.nav_comida_almoco:
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.replace(R.id.fragment_comida_container,
                        new ComidaFragmentAlmoco()).commit();
                break;

            case R.id.nav_comida_cafe:
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.replace(R.id.fragment_comida_container,
                        new ComidaFragmentCafe()).commit();
                break;

            case R.id.nav_comida_janta:
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.replace(R.id.fragment_comida_container,
                        new ComidaFragmentJanta()).commit();
                break;

            case R.id.nav_comida_lanche:
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.replace(R.id.fragment_comida_container,
                        new ComidaFragmentLanche()).commit();
                break;

            case R.id.nav_comida_pos_treino:
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.replace(R.id.fragment_comida_container,
                        new ComidaFragmentPosTreino()).commit();
                break;

            case R.id.nav_comida_pre_treino:
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.replace(R.id.fragment_comida_container,
                        new ComidaFragmentPreTreino()).commit();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //back animation
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
