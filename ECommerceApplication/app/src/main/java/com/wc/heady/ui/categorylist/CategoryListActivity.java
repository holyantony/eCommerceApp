package com.wc.heady.ui.categorylist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.wc.heady.R;
import com.wc.heady.base.BaseActivity;
import com.wc.heady.databinding.ActivityCategoryListBinding;
import com.wc.heady.di.ViewModelProviderFactory;
import com.wc.heady.ui.subcategorylist.SubCategoryListActivity;
import com.wc.heady.utils.NetworkCheck;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class CategoryListActivity extends BaseActivity {
    @Inject
    ViewModelProviderFactory providerFactory;
    private CategoryListAdapter categoryListAdapter;
    private Disposable subscribe;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CategoryListViewModel categoryListViewModel = ViewModelProviders.of(this, providerFactory).get(CategoryListViewModel.class);
        ActivityCategoryListBinding activityCategoryListBinding = DataBindingUtil.setContentView(this, R.layout.activity_category_list);
        activityCategoryListBinding.setCategoryListViewModel(categoryListViewModel);
        activityCategoryListBinding.setLifecycleOwner(this);

        if (NetworkCheck.isNetworkConnected(CategoryListActivity.this)){
            showProgress();
            categoryListViewModel.getData();
        }else {
            Toast.makeText(CategoryListActivity.this, R.string.internet_check_msg,Toast.LENGTH_LONG).show();
            return;
        }

        categoryListViewModel.getParentCategoryList().observe(this, categories -> {
            hideProgress();

            if (categories != null && categories.size() > 0) {
                categoryListAdapter = new CategoryListAdapter(categories);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(CategoryListActivity.this, 2);
                activityCategoryListBinding.recyclerView.setLayoutManager(gridLayoutManager);

                activityCategoryListBinding.recyclerView.setAdapter(categoryListAdapter);

                subscribe = categoryListAdapter.clickEvent().subscribe(category -> {
                    Intent intent = new Intent(CategoryListActivity.this, SubCategoryListActivity.class);
                    intent.putExtra("category_id", category.getId());
                    startActivity(intent);
                });
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscribe != null) subscribe.dispose();
    }
}
