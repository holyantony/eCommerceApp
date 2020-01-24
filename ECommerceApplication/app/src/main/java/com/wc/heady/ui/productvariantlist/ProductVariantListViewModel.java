package com.wc.heady.ui.productvariantlist;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.wc.heady.base.BaseViewModel;
import com.wc.heady.model.database.model.Variants;
import com.wc.heady.repo.ProductRepo;
import com.wc.heady.repo.VariantRepo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductVariantListViewModel extends BaseViewModel {
    private ProductRepo productVariantRepo;
    @Inject
    VariantRepo variantRepo;
    private MutableLiveData<List<Variants>> variantList = new MutableLiveData<>();
    private List<Variants> productAndAllVariants = new ArrayList<>();
    @Inject
    public ProductVariantListViewModel(ProductRepo productVariantRepo) {
        this.productVariantRepo = productVariantRepo;
    }

    void fetchAllProductVariantsByCategoryId(int ID) {
        compositeDisposable.add(productVariantRepo.fetchAllProductVariantsByCategoryId(ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::productVariantsData, this::handleError));
    }

    private void handleError(Throwable throwable) {
    }

    private void productVariantsData(List<Variants> productAndAllVariants) {
        Log.d("hello", "hello");
        this.productAndAllVariants = productAndAllVariants;
        variantList.setValue(productAndAllVariants);
    }

    public MutableLiveData<List<Variants>> getVariantList() {

        return variantList;
    }
}
