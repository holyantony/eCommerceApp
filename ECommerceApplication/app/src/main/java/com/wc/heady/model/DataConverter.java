package com.wc.heady.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wc.heady.model.database.model.Product;
import com.wc.heady.model.database.model.Variants;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {
    @TypeConverter
    public String fromProductList(List<Product> products) {
        if (products == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Product>>() {}.getType();
        String json = gson.toJson(products, type);
        return json;
    }

    @TypeConverter
    public List<Product> toCountryLangList(String productString) {
        if (productString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Product>>() {}.getType();
        List<Product> countryLangList = gson.fromJson(productString, type);
        return countryLangList;
    }

    @TypeConverter
    public String fromChildCategoryList(List<Integer> products) {
        if (products == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        String json = gson.toJson(products, type);
        return json;
    }

    @TypeConverter
    public List<Integer> toChildCategoryLangList(String productString) {
        if (productString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> countryLangList1 = gson.fromJson(productString, type);
        return countryLangList1;
    }

    @TypeConverter
    public String fromVariantsList(List<Variants> products) {
        if (products == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Variants>>() {}.getType();
        String json = gson.toJson(products, type);
        return json;
    }

    @TypeConverter
    public List<Variants> toVariantsList(String variantString) {
        if (variantString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Variants>>() {}.getType();
        List<Variants> countryLangList1 = gson.fromJson(variantString, type);
        return countryLangList1;
    }
}
