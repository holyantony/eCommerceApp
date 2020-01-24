package com.wc.heady.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wc.heady.model.database.model.Variants;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface VariantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Variants variants);
    @Query("select * from variant_table")
    Single<List<Variants>> getAllVariants();
}
