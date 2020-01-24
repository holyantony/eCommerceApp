package com.wc.heady.model.database.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.wc.heady.model.DataConverter;

import java.util.List;

@Entity(tableName = "Category")
public class Category {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "category_id")
    private int id;
    @ColumnInfo(name = "category_name")
    private String name;

    @TypeConverters(DataConverter.class)
    @SerializedName("child_categories")
    private List<Integer> childCategories;

    @Ignore
    private List<Product> products;



    public Category(int id, String name,List<Integer> childCategories) {
        this.id = id;
        this.name = name;
        this.childCategories = childCategories;
       // this.products = products;
    }

    @Ignore
    public Category(String name,int id){
        this.id = id;
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<Integer> childCategories) {
        this.childCategories = childCategories;
    }
}
