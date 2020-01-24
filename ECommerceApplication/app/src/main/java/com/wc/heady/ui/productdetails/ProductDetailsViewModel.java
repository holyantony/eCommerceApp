package com.wc.heady.ui.productdetails;

import androidx.lifecycle.MutableLiveData;

import com.wc.heady.base.BaseViewModel;
import com.wc.heady.repo.ProductRepo;

import javax.inject.Inject;

public class ProductDetailsViewModel extends BaseViewModel {
    private MutableLiveData<String> productName = new MutableLiveData<>();
    private MutableLiveData<Integer> productSize = new MutableLiveData<>();
    private  MutableLiveData<Double> productPrice= new MutableLiveData<>();
    private MutableLiveData<String> productColor= new MutableLiveData<>();
    private  MutableLiveData<Integer>  productShareCount= new MutableLiveData<>();
    private  MutableLiveData<Integer>  productViewCount= new MutableLiveData<>();
    private  MutableLiveData<Integer>  productOrderCount= new MutableLiveData<>();



    @Inject
    public ProductDetailsViewModel(ProductRepo productVariantRepo) {
    }

    public MutableLiveData<String> getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.setValue(productName);
    }

    public MutableLiveData<Integer> getProductSize() {
        return productSize;
    }

    public void setProductSize(Integer productSize) {
        this.productSize.setValue(productSize);
    }

    public MutableLiveData<Double> getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice.setValue(productPrice);
    }

    public MutableLiveData<String> getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor.setValue(productColor);
    }

    public MutableLiveData<Integer> getProductShareCount() {
        return productShareCount;
    }

    public void setProductShareCount(Integer productShareCount) {
        this.productShareCount.setValue(productShareCount);
    }

    public MutableLiveData<Integer> getProductViewCount() {
        return productViewCount;
    }

    public void setProductViewCount(Integer productViewCount) {
        this.productViewCount.setValue(productViewCount);
    }

    public MutableLiveData<Integer> getProductOrderCount() {
        return productOrderCount;
    }

    public void setProductOrderCount(Integer productOrderCount) {
        this.productOrderCount.setValue(productOrderCount);
    }
}
