package com.example.fitfood;

import java.io.Serializable;

public class ItemsGridViewComida implements Serializable {

    private String name;
    private String desc;
    private String ingredientes;
    private int image_info;
    private int image_comida;

    public ItemsGridViewComida(String name, String desc, String ingredientes, int image_comida, int image_info) {
        this.name = name;
        this.desc = desc;
        this.ingredientes = ingredientes;
        this.image_info = image_info;
        this.image_comida = image_comida;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
