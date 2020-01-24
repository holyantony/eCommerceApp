package com.wc.heady.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.wc.heady.dao.CategoryDao;
import com.wc.heady.dao.ParentChildCategoryMappingDao;
import com.wc.heady.dao.ProductDao;
import com.wc.heady.dao.ProductRankingMappingDao;
import com.wc.heady.dao.VariantDao;
import com.wc.heady.model.database.model.Category;
import com.wc.heady.model.database.model.ParentChildCategoryMapping;
import com.wc.heady.model.database.model.Product;
import com.wc.heady.model.database.model.ProductRankingMapping;
import com.wc.heady.model.database.model.Variants;

@Database(entities = {Category.class, Product.class, Variants.class, ParentChildCategoryMapping.class,ProductRankingMapping.class}, version = 1, exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {

    private static ProductDatabase instance;

    public abstract CategoryDao categoryDao();
    public abstract ParentChildCategoryMappingDao parentChildCategoryMappingDao();
    public abstract ProductDao productDao();
    public abstract VariantDao variantDao();
    public abstract ProductRankingMappingDao productRankingMappingDao();


    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

}
