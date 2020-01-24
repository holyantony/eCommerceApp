package com.wc.heady.repo;

import android.os.AsyncTask;

import com.wc.heady.dao.ProductDao;
import com.wc.heady.model.database.model.Product;
import com.wc.heady.model.database.model.Variants;

import java.util.List;

import io.reactivex.Flowable;

public class ProductRepo {
    private ProductDao productVariantsDao;

    public ProductRepo(ProductDao productVariantsDao) {
        this.productVariantsDao = productVariantsDao;
    }
    public Flowable<List<Variants>> fetchAllProductVariantsByCategoryId(int categoryId) {
        return productVariantsDao.fetchAllProductVariantsByCategoryId(categoryId) ;
    }

    public void insert(Product product) {
        new insertAsyncTask(productVariantsDao).execute(product);
    }

    private static class insertAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao productVariantsDao;

        insertAsyncTask(ProductDao dao) {
            productVariantsDao = dao;
        }

        @Override
        protected Void doInBackground(final Product... params) {
            productVariantsDao.insert(params[0]);
            return null;
        }
    }
}
