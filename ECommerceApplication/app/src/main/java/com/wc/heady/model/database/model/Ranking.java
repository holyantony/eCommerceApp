package com.wc.heady.model.database.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ranking {

    private String ranking;
    @SerializedName("products")
    private List<RankingData> rankingDataList;

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public List<RankingData> getRankingDataList() {
        return rankingDataList;
    }

    public void setRankingDataList(List<RankingData> rankingDataList) {
        this.rankingDataList = rankingDataList;
    }
}
