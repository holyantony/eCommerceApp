package com.wc.heady.di;

import com.wc.heady.di.scope.ActivityScoped;
import com.wc.heady.ui.categorylist.CategoryListActivity;
import com.wc.heady.ui.categorylist.CategoryListModule;
import com.wc.heady.ui.productdetails.ProductDetailsActivity;
import com.wc.heady.ui.productdetails.ProductDetailsModule;
import com.wc.heady.ui.productvariantlist.ProductVariantListActivity;
import com.wc.heady.ui.productvariantlist.ProductVariantListModule;
import com.wc.heady.ui.subcategorylist.SubCategoryListActivity;
import com.wc.heady.ui.subcategorylist.SubCategoryListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = {CategoryListModule.class})
    abstract CategoryListActivity contributeCategoryListActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {SubCategoryListModule.class})
    abstract SubCategoryListActivity contributeSubCategoryListActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {ProductVariantListModule.class})
    abstract ProductVariantListActivity contributeProductVariantListActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {ProductDetailsModule.class})
    abstract ProductDetailsActivity contributeProductDetailsActivity();
}
