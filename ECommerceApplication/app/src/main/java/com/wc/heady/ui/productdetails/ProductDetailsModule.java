package com.wc.heady.ui.productdetails;

import androidx.lifecycle.ViewModel;

import com.wc.heady.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ProductDetailsModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailsViewModel.class)
    abstract ViewModel bindProductDetailsModule(ProductDetailsViewModel productDetailsViewModel);
}
