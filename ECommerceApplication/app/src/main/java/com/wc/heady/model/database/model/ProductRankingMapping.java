package com.wc.heady.model.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "ProductRankingMapping")
public class ProductRankingMapping {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private int product_id;
    private int view_count;
    private int shares;
    private int order_count;

    public ProductRankingMapping( int product_id, int view_count, int shares, int order_count) {
        this.product_id = product_id;
        this.view_count = view_count;
        this.shares = shares;
        this.order_count = order_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

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
}




