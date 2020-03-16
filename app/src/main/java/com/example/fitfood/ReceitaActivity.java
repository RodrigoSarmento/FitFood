package com.example.fitfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.fitfood.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ReceitaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    // VARIÁVEIS PARA O GRIDVIEW
    int images[] = {R.drawable.batata_doce, R.drawable.brownie_banana, R.drawable.crepioca_requeijao, R.drawable.panqueca_banana};
    String names[] = {"Batata Doce", "Brownie de Banana", "Crepioca de Requeijão", "Panqueca de Banana"};
    String desc[] = {"batata doce", "brownie", "crepioca", "panqueca"};
    GridView gridView;

    List<ItemsGridView> itemsList = new ArrayList<>();
    CustomAdapter customAdapter;
    // FIM VARIÁVEIS PARA O GRIDVIEW

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_receita);

        // PARADAS DO GRIDVIEW
        gridView = findViewById(R.id.grid_view_receita_pre_treino);

        for (int i = 0; i < names.length; i++) {
            ItemsGridView itemsGridView = new ItemsGridView(names[i], desc[i], images[i]);
            itemsList.add(itemsGridView);
        }
        customAdapter = new CustomAdapter(itemsList, this);
        gridView.setAdapter(customAdapter);
        // FIM DAS PARADAS DO GRIDVIEW


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
        drawer.openDrawer(Gravity.START);
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
        switch (item.getItemId()) { //Switch case of each topic we created in menu_receita
            case R.id.nav_receita_almoco:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_receita_container,
                        new ReceitaFragmentAlmoco()).commit();
                break;

            case R.id.nav_receita_cafe:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_receita_container,
                        new ReceitaFragmentCafe()).commit();
                break;

            case R.id.nav_receita_janta:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_receita_container,
                        new ReceitaFragmentJanta()).commit();
                break;

            case R.id.nav_receita_lanche:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_receita_container,
                        new ReceitaFragmentLanche()).commit();
                break;

            case R.id.nav_receita_pos_treino:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_receita_container,
                        new ReceitaFragmentPosTreino()).commit();
                break;

            case R.id.nav_receita_pre_treino: //If this case is true, we want to open our fragment
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_receita_container,
                        new ReceitaFragmentPreTreino()).commit(); //Name of the fragment Class
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

    public class CustomAdapter extends BaseAdapter {

        private List<ItemsGridView> itemsGridViews;
        private List<ItemsGridView> itemsGridViewsFiltered;
        private Context context;

        public CustomAdapter(List<ItemsGridView> itemsGridViews, Context context) {
            this.itemsGridViews = itemsGridViews;
            this.itemsGridViewsFiltered = itemsGridViews;
            this.context = context;
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
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);

            View view = inflater.inflate(R.layout.item_grid, null);
            ImageView imageView = view.findViewById(R.id.receita_imageView);
            TextView textView_Name = view.findViewById(R.id.receita_textView_Name);
            TextView textView_Desc = view.findViewById(R.id.receita_textView_Desc);

            imageView.setImageResource(itemsGridViewsFiltered.get(position).getImage());
            textView_Name.setText(itemsGridViewsFiltered.get(position).getName());
            textView_Desc.setText(itemsGridViewsFiltered.get(position).getDesc());

            return view;
        }
    }
}
