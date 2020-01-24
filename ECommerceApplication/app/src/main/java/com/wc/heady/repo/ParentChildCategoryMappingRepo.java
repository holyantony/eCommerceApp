package com.wc.heady.repo;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.wc.heady.dao.ParentChildCategoryMappingDao;
import com.wc.heady.database.ProductDatabase;
import com.wc.heady.model.database.model.Category;
import com.wc.heady.model.database.model.ParentChildCategoryMapping;

import java.util.List;

import javax.inject.Inject;

public class ParentChildCategoryMappingRepo {
    private LiveData<List<Category>> parentCategoryList;
    private ParentChildCategoryMappingDao parentChildCategoryMappingDao;
    @Inject
    ProductDatabase productDatabase;

    public ParentChildCategoryMappingRepo(ParentChildCategoryMappingDao parentChildCategoryMappingDao) {
        this.parentChildCategoryMappingDao = parentChildCategoryMappingDao;
    }

    public void insert (ParentChildCategoryMapping parentChildCategoryMapping) {
        new insertAsyncTask(parentChildCategoryMappingDao).execute(parentChildCategoryMapping);
    }

    private static class insertAsyncTask extends AsyncTask<ParentChildCategoryMapping, Void, Void> {

        private ParentChildCategoryMappingDao mAsyncTaskDao;

        insertAsyncTask(ParentChildCategoryMappingDao parentChildCategoryMappingDao) {
            mAsyncTaskDao = parentChildCategoryMappingDao;
        }

        @Override
        protected Void doInBackground(final ParentChildCategoryMapping... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
