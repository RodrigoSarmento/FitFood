package com.example.fitfood;

import java.io.Serializable;

public class ItemsGridViewReceita implements Serializable {

    private String name;
    private String ingredientes;
    private String modo_de_preparo;
    private int image;

    public ItemsGridViewReceita(String name, String ingredientes, String modo_de_preparo, int image) {
        this.name = name;
        this.ingredientes = ingredientes;
        this.modo_de_preparo = modo_de_preparo;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModoDePreparo() {
        return modo_de_preparo;
    }

    public void setModoDePreparo(String modo_de_preparo) {
        this.modo_de_preparo = modo_de_preparo;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
