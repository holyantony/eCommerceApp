package com.wc.heady.ui.categorylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wc.heady.R;
import com.wc.heady.model.database.model.Category;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


public class CategoryListAdapter  extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    private List<Category> parentCategoryList;
    private final PublishSubject<Category> onClickSubject = PublishSubject.create();
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryIcon;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            categoryName = v.findViewById(R.id.tv_cat_name);
            categoryIcon = v.findViewById(R.id.tv_cat_icon);
            v.setOnClickListener(view -> onClickSubject.onNext(parentCategoryList.get(getAdapterPosition())));

        }
    }

    CategoryListAdapter(List<Category> parentCategoryList) {
        this.parentCategoryList = parentCategoryList;
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.category_row_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String name = parentCategoryList.get(position).getName();
        holder.categoryName.setText(name);
        if (name.equals("Mens Wear")){
            holder.categoryIcon.setImageResource(R.drawable.mens_cat);
        }else {
            holder.categoryIcon.setImageResource(R.drawable.electronic);

        }
    }

    Observable<Category> clickEvent(){
        return onClickSubject;
    }

    @Override
    public int getItemCount() {
        return parentCategoryList.size();
    }

}