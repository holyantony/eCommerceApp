package com.wc.heady.ui.subcategorylist;

import androidx.lifecycle.ViewModel;

import com.wc.heady.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SubCategoryListModule {
    @Binds
    @IntoMap
    @ViewModelKey(SubCategoryListViewModel.class)
    abstract ViewModel bindSubCategoryListViewModel(SubCategoryListViewModel subCategoryListViewModel);
}
