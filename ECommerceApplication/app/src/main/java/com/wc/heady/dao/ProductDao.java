package com.wc.heady.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.wc.heady.model.database.model.Product;
import com.wc.heady.model.database.model.ProductAndAllVariants;
import com.wc.heady.model.database.model.Variants;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);

    @Transaction
    @Query("select * from product where product.category_id = :categoryId")
    Flowable<List< ProductAndAllVariants>> loadProductAllVariants(int categoryId);

    @Query("select variant_table.*,product.product_name from product inner join variant_table on product.id = variant_table.product_id  where product.category_id = :categoryId")
    Flowable<List<Variants>> loadAllVariants(int categoryId);

    @Query("select * from product")
    Single<List<Product>> getAllProducts();

    @Query("select variant_table.*,product.product_name,Max(productrankingmapping.order_count) as order_count ,Max(productrankingmapping.view_count) as view_count,Max(productrankingmapping.shares) shares from product inner join variant_table on product.id =variant_table.product_id inner join productrankingmapping on  productrankingmapping.product_id = product.id where   product.category_id = :categoryId  group by  variant_table.id")
    Flowable<List<Variants>> fetchAllProductVariantsByCategoryId(int categoryId);

}
