package com.wc.heady.repo;

import android.os.AsyncTask;

import com.wc.heady.dao.VariantDao;
import com.wc.heady.model.database.model.Variants;

import java.util.List;

import io.reactivex.Single;

public class VariantRepo {
    private VariantDao variantDao;


    public VariantRepo(VariantDao variantDao) {
        this.variantDao = variantDao;
    }
    public Single<List<Variants>> getAllVariants() {
        return variantDao.getAllVariants();
    }

    public void insert(Variants variants) {
        new insertAsyncTask(variantDao).execute(variants);
    }

    private static class insertAsyncTask extends AsyncTask<Variants, Void, Void> {

        private VariantDao variantDao;

        insertAsyncTask(VariantDao dao) {
            variantDao = dao;
        }

        @Override
        protected Void doInBackground(final Variants... params) {
            variantDao.insert(params[0]);
            return null;
        }
    }
}
