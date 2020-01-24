package com.wc.heady.repo;

import android.os.AsyncTask;

import com.wc.heady.dao.CategoryDao;
import com.wc.heady.model.database.model.Category;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class CategoryRepo {

    private CategoryDao categoryDao;

    public CategoryRepo(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public Flowable<List<Category>> getSubCategoryList(int categoryId) {
        return categoryDao.getSubCategoriesFromParentID(categoryId) ;
    }

    public Flowable<List<Category>> getAllChildCategoriesByParentID (int parentID) {
        return categoryDao.getAllChildCategoriesByParentID(parentID) ;
    }

    public Single<List<Category>> getCategoryList() {
        return categoryDao.getAllCategories();
    }

    public Flowable<List<Category>> getParentCategoryList() {
        return categoryDao.getParentCategories();
    }

    public void insert (Category word) {
        new insertAsyncTask(categoryDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDao mAsyncTaskDao;

        insertAsyncTask(CategoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Category... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
