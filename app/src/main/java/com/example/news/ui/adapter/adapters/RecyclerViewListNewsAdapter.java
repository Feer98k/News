package com.example.news.ui.adapter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news.R;
import com.example.news.model.Notice;
import com.example.news.ui.adapter.clickListener.OnItemClickListenerNews;

import java.util.List;


public class RecyclerViewListNewsAdapter extends RecyclerView.Adapter<RecyclerViewListNewsAdapter.noticiaHolder> {
    private final List<Notice> noticesList;
    private final Context context;
    private OnItemClickListenerNews onItemClickListenerNews;

    public RecyclerViewListNewsAdapter(List<Notice> noticesList, Context context) {
        this.noticesList = noticesList;
        this.context = context;
    }

    public void setOnItemClickListenerNews(OnItemClickListenerNews onItemClickListenerNews) {
        this.onItemClickListenerNews = onItemClickListenerNews;
    }

    @NonNull
    @Override
    public noticiaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater
                .from(context)
                .inflate(R.layout.item_noticia, parent, false);
        return new noticiaHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull noticiaHolder holder, int position) {
        Notice notice = noticesList.get(position);
        holder.toLink(notice);
    }

    @Override
    public int getItemCount() {
        return noticesList.size();
    }

    class noticiaHolder extends RecyclerView.ViewHolder {
        Notice notice;
        final ImageView image;
        final TextView title;
        final TextView description;

        public noticiaHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_noticia_titulo);
            description = itemView.findViewById(R.id.item_noticia_descricao);
            image = itemView.findViewById(R.id.item_noticia_image);
            itemView.setOnClickListener(t -> onItemClickListenerNews.onItemClick(notice));
        }

        public void toLink(Notice notice) {
            this.notice = notice;

            title.setText(notice.getTitle());
            description.setText(notice.getDescription());
            if (notice.getUrlToImage() == null || notice.getUrlToImage().isEmpty() || notice.getUrlToImage().equals("")) {
                Glide.with(context)
                        .load(R.drawable.newspaper)
                        .into(image);
            } else {
                Glide.with(context)
                        .load(notice.getUrlToImage())
                        .into(image);
            }
        }
    }

}