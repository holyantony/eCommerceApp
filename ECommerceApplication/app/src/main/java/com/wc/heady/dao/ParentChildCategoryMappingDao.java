package com.wc.heady.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.wc.heady.model.database.model.ParentChildCategoryMapping;

@Dao
public interface ParentChildCategoryMappingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ParentChildCategoryMapping parentChildCategoryMapping);


}
