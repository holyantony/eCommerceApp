package com.wc.heady.ui.productvariantlist;

import androidx.lifecycle.ViewModel;

import com.wc.heady.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ProductVariantListModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProductVariantListViewModel.class)
    abstract ViewModel bindProductVariantListModule(ProductVariantListViewModel productVariantListViewModel);
}
