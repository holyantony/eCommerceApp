package com.wc.heady.repo;

import android.os.AsyncTask;

import com.wc.heady.dao.ProductRankingMappingDao;
import com.wc.heady.model.database.model.ProductRankingMapping;

import java.util.List;

import io.reactivex.Single;


public class ProductRankingMappingRepo {
    private ProductRankingMappingDao productRankingMappingDao;


    public ProductRankingMappingRepo(ProductRankingMappingDao productRankingMappingDao) {
        this.productRankingMappingDao = productRankingMappingDao;
    }
    public Single<List<ProductRankingMapping>> getAllVariants() {
        return productRankingMappingDao.getAllVariants();
    }

    public void insert(ProductRankingMapping variants) {
        new insertAsyncTask(productRankingMappingDao).execute(variants);
    }

    private static class insertAsyncTask extends AsyncTask<ProductRankingMapping, Void, Void> {

        private ProductRankingMappingDao variantDao;

        insertAsyncTask(ProductRankingMappingDao dao) {
            variantDao = dao;
        }

        @Override
        protected Void doInBackground(final ProductRankingMapping... params) {
            variantDao.insert(params[0]);
            return null;
        }
    }
}
