package com.example.fitfood;

import java.io.Serializable;

public class ItemsGridView implements Serializable {

    private String name;
    private String desc;
    private String ingredientes;
    private int image;

    public ItemsGridView(String name, String desc, String ingredientes, int image) {
        this.name = name;
        this.desc = desc;
        this.ingredientes = ingredientes;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
