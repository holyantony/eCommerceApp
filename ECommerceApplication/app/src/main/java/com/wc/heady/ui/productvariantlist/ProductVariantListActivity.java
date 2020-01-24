package com.wc.heady.ui.productvariantlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.wc.heady.R;
import com.wc.heady.base.BaseActivity;
import com.wc.heady.databinding.ActivityProductVariantListBinding;
import com.wc.heady.di.ViewModelProviderFactory;
import com.wc.heady.model.database.model.Variants;
import com.wc.heady.ui.productdetails.ProductDetailsActivity;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class ProductVariantListActivity extends BaseActivity {
    @Inject
    ViewModelProviderFactory providerFactory;
    private int categoryId;
    private List<Variants>variantsList;
    private   ProductListAdapter productListAdapter;
    private Disposable subscribe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            categoryId = extras.getInt("category_id");
        }

        ProductVariantListViewModel productVariantListViewModel = ViewModelProviders.of(this, providerFactory).get(ProductVariantListViewModel.class);
         ActivityProductVariantListBinding productVariantListBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_variant_list);
        productVariantListBinding.setProductVariantLisViewModel(productVariantListViewModel);
        productVariantListBinding.setLifecycleOwner(this);
        productVariantListViewModel.fetchAllProductVariantsByCategoryId(categoryId);

        productVariantListViewModel.getVariantList().observe(this, variantsList -> {
            if (variantsList != null && variantsList.size() > 0) {
                this.variantsList = variantsList;
                 productListAdapter = new ProductListAdapter(variantsList);
                productVariantListBinding.recyclerView.setLayoutManager(new GridLayoutManager(ProductVariantListActivity.this,2));
                productVariantListBinding.recyclerView.setAdapter(productListAdapter);

                subscribe = productListAdapter.clickEvent().subscribe(variants -> {
                    if (variants != null){
                        Intent intent = new Intent(ProductVariantListActivity.this, ProductDetailsActivity.class);
                        intent.putExtra("variantObj", variants);
                        startActivity(intent);
                    }
                });
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_dropdown1:
                Collections.sort(variantsList, (o1, o2) -> o2.getOrder_count() - o1.getOrder_count());
                productListAdapter.notifyDataSetChanged();
                return true;

            case R.id.action_dropdown2:
                Collections.sort(variantsList, (o1, o2) -> o2.getView_count() - o1.getView_count());
                productListAdapter.notifyDataSetChanged();

                return true;
            case R.id.action_dropdown3:
                Collections.sort(variantsList, (o1, o2) -> o2.getShares() - o1.getShares());
                productListAdapter.notifyDataSetChanged();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
