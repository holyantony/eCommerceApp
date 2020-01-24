package com.wc.heady.ui.productvariantlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wc.heady.R;
import com.wc.heady.model.database.model.Variants;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private List<Variants> variantsList;
    private final PublishSubject<Variants> onClickSubject = PublishSubject.create();
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName,productColor,productSize,productPrice;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            productName = v.findViewById(R.id.tv_product_name);
            productColor = v.findViewById(R.id.tv_product_color);
            productSize = v.findViewById(R.id.tv_product_size);
            productPrice = v.findViewById(R.id.tv_product_price);

            v.setOnClickListener(view -> {
                Variants variant = variantsList.get(getAdapterPosition());
                onClickSubject.onNext(variant);
            });

        }
    }

    ProductListAdapter(List<Variants> variantsList) {
        this.variantsList = variantsList;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.product_row_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String name = variantsList.get(position).getProduct_name();
        final String color = variantsList.get(position).getColor();
        final double price = variantsList.get(position).getPrice();
        final int size = variantsList.get(position).getSize();

        holder.productName.setText(name);
        if (size ==0){
            holder.productSize.setVisibility(View.GONE);
        }
        holder.productSize.setText("Size: "+size );
        holder.productPrice.setText("Price: "+price +" Rs");
        holder.productColor.setText("Color: "+color);

    }

    Observable<Variants> clickEvent(){
        return onClickSubject;
    }

    @Override
    public int getItemCount() {
        return variantsList.size();
    }

}