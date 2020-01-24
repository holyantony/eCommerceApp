package com.wc.heady.ui.categorylist;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wc.heady.base.BaseViewModel;
import com.wc.heady.model.database.model.ProductRankingMapping;
import com.wc.heady.model.database.model.Ranking;
import com.wc.heady.model.database.model.RankingData;
import com.wc.heady.model.database.model.Variants;
import com.wc.heady.repo.CategoryRepo;
import com.wc.heady.repo.ParentChildCategoryMappingRepo;
import com.wc.heady.repo.ProductRankingMappingRepo;
import com.wc.heady.repo.ProductRepo;
import com.wc.heady.model.ECommerceData;
import com.wc.heady.model.database.model.Category;
import com.wc.heady.model.database.model.ParentChildCategoryMapping;
import com.wc.heady.model.database.model.Product;
import com.wc.heady.network.APIServices;
import com.wc.heady.repo.VariantRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CategoryListViewModel extends BaseViewModel {

    private APIServices apiServices;
    private MutableLiveData<List<Category>> parentCategoryList = new MutableLiveData<>();
    private CategoryRepo categoryRepo;
    @Inject
    ParentChildCategoryMappingRepo parentChildCategoryMappingRepo;
    @Inject
    ProductRepo productVariantRepo;
    @Inject
    VariantRepo variantRepo;
    @Inject
    ProductRankingMappingRepo productRankingMappingRepo;


    @Inject
    public CategoryListViewModel(APIServices apiServices, CategoryRepo categoryRepo) {
        this.apiServices = apiServices;
        this.categoryRepo = categoryRepo;
    }

    public void getData() {

        compositeDisposable.add(categoryRepo.getCategoryList().subscribeOn(Schedulers.io())
                .map(categories -> categories != null && categories.size() > 0).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkIfListExists, this::handleError));

    }

    private void checkIfListExists(Boolean aBoolean) {
        if (aBoolean){
            fetchParentCategories();

        }else {

                compositeDisposable.add(apiServices.getECommerceData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponse, this::handleError));
            }


    }

    private void handleError(Throwable throwable) {
        Log.d("Edd", "ee");
    }


    private void handleResponse(ECommerceData eCommerceResponseData) {
        saveDataFromServer(eCommerceResponseData);
    }

    private void saveDataFromServer(ECommerceData eCommerceData) {
        if (eCommerceData.getCategoryList() != null && eCommerceData.getCategoryList().size() > 0) {
            for (Category category : eCommerceData.getCategoryList()) {
                Category categoryData = new Category(category.getId(), category.getName(), category.getChildCategories());
                try {
                    categoryRepo.insert(categoryData);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (category.getProducts() != null && category.getProducts().size() >0){

                    for (Product product: category.getProducts()) {
                        Product productData = new Product(product.getId(),product.getProduct_name(),product.getDate_added(),category.getId(),product.getTax());
                        productVariantRepo.insert(productData);

                        if (product.getVariants() != null && product.getVariants().size() >0){
                            for (Variants variants: product.getVariants()) {
                                Variants variantData = new Variants(variants.getId(),variants.getColor(),variants.getSize(),variants.getPrice(),product.getId());
                                variantRepo.insert(variantData);
                            }
                        }

                    }

                }else if(category.getChildCategories()!= null &&category.getChildCategories().size() > 0){
                    for (Integer childCategoryId : category.getChildCategories()) {
                        ParentChildCategoryMapping parentChildCategoryMapping = new ParentChildCategoryMapping(childCategoryId, category.getId());
                        try {
                            parentChildCategoryMappingRepo.insert(parentChildCategoryMapping);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }


        }

        if (eCommerceData.getRankingList() != null && eCommerceData.getRankingList().size() >0){
            for (Ranking ranking: eCommerceData.getRankingList() ) {
                for (RankingData rankingData: ranking.getRankingDataList()) {
                    ProductRankingMapping productRankingMapping = new ProductRankingMapping(rankingData.getId(),rankingData.getView_count(),rankingData.getShares(),rankingData.getOrder_count());
                    productRankingMappingRepo.insert(productRankingMapping);
                }
            }
        }

        fetchParentCategories();
    }

    private void fetchParentCategories() {
        compositeDisposable.add(categoryRepo.getParentCategoryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::populateParentCategory, this::handleError));
    }

    private void populateParentCategory(List<Category> categories) {
        parentCategoryList.setValue(categories);
    }

    LiveData<List<Category>> getParentCategoryList() {
        return parentCategoryList;
    }
}