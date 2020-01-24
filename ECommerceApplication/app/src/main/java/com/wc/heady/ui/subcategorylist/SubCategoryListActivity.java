package com.wc.heady.ui.subcategorylist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.wc.heady.R;
import com.wc.heady.base.BaseActivity;
import com.wc.heady.databinding.ActivitySubCategoryListBinding;
import com.wc.heady.di.ViewModelProviderFactory;
import com.wc.heady.ui.productvariantlist.ProductVariantListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class SubCategoryListActivity extends BaseActivity {
    private SubCategoryListViewModel subCategoryListViewModel;
    @Inject
    ViewModelProviderFactory providerFactory;
    private int categoryId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            categoryId = extras.getInt("category_id");
        }

        subCategoryListViewModel = ViewModelProviders.of(this, providerFactory).get(SubCategoryListViewModel.class);
        ActivitySubCategoryListBinding activitySubCategoryListBinding = DataBindingUtil.setContentView(this, R.layout.activity_sub_category_list);
        activitySubCategoryListBinding.setSubCategoryListViewModel(subCategoryListViewModel);
        activitySubCategoryListBinding.setLifecycleOwner(this);
        subCategoryListViewModel.fetchSubCategoryData(categoryId);

        subCategoryListViewModel.getSubCategoryList().observe(this, categories -> {
            List<String> expandableListTitle = new ArrayList<>(categories.keySet());
            SubCategoryListAdapter subCategoryListAdapter = new SubCategoryListAdapter(expandableListTitle, categories);
            activitySubCategoryListBinding.simpleExpandableListView.setAdapter(subCategoryListAdapter);


            activitySubCategoryListBinding.simpleExpandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
                startActivity(new Intent(SubCategoryListActivity.this, ProductVariantListActivity.class).putExtra("category_id", Objects.requireNonNull(categories.get(
                        expandableListTitle.get(groupPosition))).get(
                        childPosition).getId()));
                return false;
            });
        });


    }
}
