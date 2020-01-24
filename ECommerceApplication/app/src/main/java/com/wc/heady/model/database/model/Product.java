package com.wc.heady.model.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.wc.heady.model.DataConverter;

import java.util.List;

@Entity(tableName = "Product")
@ForeignKey(entity = Category.class, parentColumns = "category_id", childColumns = "category_id")
@TypeConverters(DataConverter.class)

public class Product {



    @PrimaryKey
    private int id;
    @SerializedName("name")
    private String product_name;
    private String date_added;
    @ColumnInfo(name="category_id")
    private int category_id;

    @Ignore
    private List<Variants> variants;
    @Embedded
    private Tax tax;

    public Product(int id, String product_name, String date_added, int category_id, Tax tax) {
       this.id = id;
       this.product_name = product_name;
       this.date_added = date_added;
       this.category_id = category_id;
       this.tax = tax;
    }

    public List<Variants> getVariants() {
        return variants;
    }

    public void setVariants(List<Variants> variants) {
        this.variants = variants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }
}
