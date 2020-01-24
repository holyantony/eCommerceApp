package com.wc.heady.model;

import com.google.gson.annotations.SerializedName;
import com.wc.heady.model.database.model.Category;
import com.wc.heady.model.database.model.Ranking;

import java.util.List;

public class ECommerceData {

    @SerializedName("categories")
    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Ranking> getRankingList() {
        return rankingList;
    }

    public void setRankingList(List<Ranking> rankingList) {
        this.rankingList = rankingList;
    }
    @SerializedName("rankings")

    private List<Ranking> rankingList;
}
