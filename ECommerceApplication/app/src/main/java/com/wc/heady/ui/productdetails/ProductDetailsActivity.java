package com.wc.heady.ui.productdetails;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.wc.heady.R;
import com.wc.heady.base.BaseActivity;
import com.wc.heady.databinding.ActivityProductDetailsBinding;
import com.wc.heady.di.ViewModelProviderFactory;
import com.wc.heady.model.database.model.Variants;

import java.util.Objects;

import javax.inject.Inject;

public class ProductDetailsActivity extends BaseActivity {
    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Variants variantsObjInToClass = (Variants)Objects.requireNonNull(getIntent().getExtras()).getSerializable("variantObj");


        ProductDetailsViewModel productDetailsViewModel = ViewModelProviders.of(this, providerFactory).get(ProductDetailsViewModel.class);
        ActivityProductDetailsBinding productDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_details);
        productDetailsBinding.setProductDetailsViewModel(productDetailsViewModel);
        productDetailsBinding.setLifecycleOwner(this);
        productDetailsViewModel.setProductColor(Objects.requireNonNull(variantsObjInToClass).getColor());
        productDetailsViewModel.setProductName(variantsObjInToClass.getProduct_name());
        productDetailsViewModel.setProductOrderCount(variantsObjInToClass.getOrder_count());
        productDetailsViewModel.setProductSize(variantsObjInToClass.getSize());
        productDetailsViewModel.setProductPrice(variantsObjInToClass.getPrice());
        productDetailsViewModel.setProductShareCount(variantsObjInToClass.getShares());
        productDetailsViewModel.setProductViewCount(variantsObjInToClass.getView_count());

    }
}
