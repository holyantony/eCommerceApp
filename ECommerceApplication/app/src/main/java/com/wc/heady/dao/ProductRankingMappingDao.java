package com.wc.heady.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wc.heady.model.database.model.ProductRankingMapping;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface ProductRankingMappingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProductRankingMapping productRankingMapping);
    @Query("select * from productrankingmapping")
    Single<List<ProductRankingMapping>> getAllVariants();
}
