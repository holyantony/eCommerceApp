package com.wc.heady.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wc.heady.model.database.model.Category;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Category category);

    @Query("select * from category")
    Single<List<Category>> getAllCategories();

    @Query("select distinct category.* from category inner join parentchildcategorymapping ON category.category_id = parentchildcategorymapping.parent_cat_id WHERE Category.category_id NOT IN (SELECT DISTINCT ParentChildCategoryMapping.child_cat_id FROM ParentChildCategoryMapping)")
    Flowable<List<Category>> getParentCategories();

    @Query("select category.* From category Inner join parentchildcategorymapping on category.category_id= parentchildcategorymapping.child_cat_id where ParentChildCategoryMapping.parent_cat_id in (:parentID)")
    Flowable<List<Category>> getSubCategoriesFromParentID(int parentID);

   @Query("select category.* from Category inner join parentchildcategorymapping on Category.category_id = ParentChildCategoryMapping.child_cat_id where ParentChildCategoryMapping.parent_cat_id in (select ParentChildCategoryMapping.child_cat_id from ParentChildCategoryMapping where parentchildcategorymapping.parent_cat_id = :id)")
    Flowable<List<Category>> getAllChildCategoriesByParentID(int id);


}
