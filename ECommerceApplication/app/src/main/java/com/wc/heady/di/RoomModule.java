package com.wc.heady.di;

import android.app.Application;

import androidx.room.Room;

import com.wc.heady.dao.CategoryDao;
import com.wc.heady.dao.ParentChildCategoryMappingDao;
import com.wc.heady.dao.ProductDao;
import com.wc.heady.dao.ProductRankingMappingDao;
import com.wc.heady.dao.VariantDao;
import com.wc.heady.repo.CategoryRepo;
import com.wc.heady.repo.ParentChildCategoryMappingRepo;
import com.wc.heady.repo.ProductRankingMappingRepo;
import com.wc.heady.repo.ProductRepo;
import com.wc.heady.database.ProductDatabase;
import com.wc.heady.repo.VariantRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {


    @Singleton
    @Provides
    static ProductDatabase providesRoomDatabase(Application application) {
        return Room.databaseBuilder(application, ProductDatabase.class, "product_database").build();
    }

    @Singleton
    @Provides
    static CategoryDao providesCategoryDao(ProductDatabase productDatabase) {
        return productDatabase.categoryDao();
    }

    @Singleton
    @Provides
    static CategoryRepo provideCategoryRepository(CategoryDao categoryDao) {
        return new CategoryRepo(categoryDao);
    }


    @Singleton
    @Provides
    static ParentChildCategoryMappingDao providesParentChildCategoryMappingDao(ProductDatabase productDatabase) {
        return productDatabase.parentChildCategoryMappingDao();
    }

    @Singleton
    @Provides
    static ParentChildCategoryMappingRepo provideChildCategoryRepository(ParentChildCategoryMappingDao parentChildCategoryMappingDao) {
        return new ParentChildCategoryMappingRepo(parentChildCategoryMappingDao);
    }

    @Singleton
    @Provides
    static ProductDao providesProductDao(ProductDatabase productDatabase) {
        return productDatabase.productDao();
    }

    @Singleton
    @Provides
    static ProductRepo provideProductRepo(ProductDao productDao) {
        return new ProductRepo(productDao);
    }

    @Singleton
    @Provides
    static VariantDao providesVariantDao(ProductDatabase productDatabase) {
        return productDatabase.variantDao();
    }

    @Singleton
    @Provides
    static VariantRepo provideVariantRepo(VariantDao variantDao) {
        return new VariantRepo(variantDao);
    }

    @Singleton
    @Provides
    static ProductRankingMappingDao provideProductRankingMappingDao(ProductDatabase productDatabase) {
        return productDatabase.productRankingMappingDao();
    }

    @Singleton
    @Provides
    static ProductRankingMappingRepo provideProductRankingMappingRepo(ProductRankingMappingDao productRankingMappingDao) {
        return new ProductRankingMappingRepo(productRankingMappingDao);
    }

}
