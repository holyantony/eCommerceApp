package com.wc.heady.ui.categorylist;

import androidx.lifecycle.ViewModel;

import com.wc.heady.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class CategoryListModule {
    @Binds
    @IntoMap
    @ViewModelKey(CategoryListViewModel.class)
    abstract ViewModel bindCategoryListModule(CategoryListViewModel categoryListViewModel);
}
