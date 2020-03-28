package com.example.fitfood;

import java.io.Serializable;

public class ItemsGridViewComida implements Serializable {

    private String name;
    private String ingredientes;
    private String modo_de_preparo;
    private int image_info;
    private int image_comida;

    public ItemsGridViewComida(String name, String ingredientes, String modo_de_preparo, int image_comida, int image_info) {
        this.name = name;
        this.ingredientes = ingredientes;
        this.modo_de_preparo = modo_de_preparo;
        this.image_info = image_info;
        this.image_comida = image_comida;
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

    public int getImageComida() {
        return image_comida;
    }

    public void setImageComida(int image_comida) {
        this.image_comida = image_comida;
    }

    public int getImageInfo() {
        return image_info;
    }

    public void setImageInfo(int image_info) {
        this.image_info = image_info;
    }
}
