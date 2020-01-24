package com.wc.heady.ui.subcategorylist;

import androidx.lifecycle.MutableLiveData;

import com.wc.heady.base.BaseViewModel;
import com.wc.heady.repo.CategoryRepo;
import com.wc.heady.model.database.model.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SubCategoryListViewModel extends BaseViewModel {

    private CategoryRepo categoryRepo;
    private MutableLiveData< HashMap<String, List<Category>> > subCategoryList = new MutableLiveData<>();
    private List<Category> subCategoryData = new ArrayList<>();
    private int parentCategoryID;

    @Inject
    public SubCategoryListViewModel( CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    void fetchSubCategoryData(int categoryId){
        this.parentCategoryID = categoryId;

        compositeDisposable.add(categoryRepo.getSubCategoryList(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::childCategoryData, this::handleError));
    }

    private void handleError(Throwable throwable) {
    }

    private void childCategoryData(List<Category> categories) {
        this.subCategoryData = categories;
        compositeDisposable.add(categoryRepo.getAllChildCategoriesByParentID(parentCategoryID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getChildCategoryDetails, this::handleError));
    }

    private void getChildCategoryDetails(List<Category> categories) {

        HashMap<String, List<Category>> subCategoryAndChildData = new HashMap<>();

        for (int i = 0; i < subCategoryData.size() ; i++) {
            ArrayList<Category> listDataChild = new ArrayList<>();
            for (int j = 0; j < subCategoryData.get(i).getChildCategories().size(); j++) {
                for (int k = 0; k < categories.size(); k++) {
                    if ( subCategoryData.get(i).getChildCategories().get(j) == categories.get(k).getId()){
                        Category category = new Category(categories.get(k).getName(),categories.get(k).getId());
                        listDataChild.add(category);
                    }
                }
            }
            subCategoryAndChildData.put(subCategoryData.get(i).getName(),listDataChild);
        }

        subCategoryList.setValue(subCategoryAndChildData);
    }


    MutableLiveData< HashMap<String, List<Category>>> getSubCategoryList() {
        return subCategoryList;
    }
}
