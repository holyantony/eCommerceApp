package com.wc.heady.model.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@ForeignKey(entity = Product.class,parentColumns ="id", childColumns = "product_id")
@Entity(tableName = "variant_table")
public class Variants implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String color;
    private int size;
    private double price;
    private int view_count;
    private int shares;
    private int order_count;
    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    private String product_name;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @ColumnInfo(name = "product_id")
    private int productId;

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public Variants(int id, String color, int size, double price, int productId) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.price = price;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
