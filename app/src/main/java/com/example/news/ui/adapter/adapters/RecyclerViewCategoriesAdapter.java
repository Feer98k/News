package com.example.news.ui.adapter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.constants.EnumCategory;
import com.example.news.model.Category;
import com.example.news.ui.adapter.ClickListeners.OnItemClickListenerCategory;

import java.util.List;

public class RecyclerViewCategoriesAdapter extends RecyclerView.Adapter<RecyclerViewCategoriesAdapter.CategoriesHolder> {
    final List<Category> categoriesList;
    final Context context;
    private EnumCategory enumCategory;
    OnItemClickListenerCategory onItemClickListenerCategory;

    public void setOnItemClickListenerCategory(OnItemClickListenerCategory onItemClickListenerCategory) {
        this.onItemClickListenerCategory = onItemClickListenerCategory;
    }

    public RecyclerViewCategoriesAdapter(List<Category> listCategories, Context context) {
        this.categoriesList = listCategories;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater
                .from(context)
                .inflate(R.layout.itens_categoria, parent, false);
        return new CategoriesHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesHolder holder, int position) {
        Category categories = categoriesList.get(position);
        enumCategory = categories.getEnumCategory();
        createCategoriesIcons(holder);
        holder.toLink(categories);
    }

    private void createCategoriesIcons(@NonNull CategoriesHolder holder) {
        switch (enumCategory) {
            case GENERAL:
                holder.view.setBackgroundResource(R.drawable.newspaper);
                break;

            case BUSINESS:
                holder.view.setBackgroundResource(R.drawable.economic);
                break;
            case ENTERTAINMENT:
                holder.view.setBackgroundResource(R.drawable.popcorn);
                break;
            case SCIENCE:
                holder.view.setBackgroundResource(R.drawable.chemistry);
                break;

            case SPORTS:
                holder.view.setBackgroundResource(R.drawable.sports);
                break;

            case TECHNOLOGY:
                holder.view.setBackgroundResource(R.drawable.technology);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }


    class CategoriesHolder extends RecyclerView.ViewHolder {
        private final View view;
        private Category category;

        public CategoriesHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.itens_categoria);
            itemView.setOnClickListener(v -> onItemClickListenerCategory.onItemClick(category));
        }

        public void toLink(Category category) {
            this.category = category;
        }
    }
}