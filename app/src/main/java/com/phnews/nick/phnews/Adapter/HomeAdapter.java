package com.phnews.nick.phnews.Adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.phnews.nick.phnews.Entities.Articles;
import com.phnews.nick.phnews.Home.HomeActivityImplementation;
import com.phnews.nick.phnews.News.NewsActivity;
import com.phnews.nick.phnews.R;
import com.phnews.nick.phnews.utils.DateUtils;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewAdapter> {

    private List<Articles> articlesList;
    static class HomeViewAdapter extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvTime;
        TextView tvDetails;

        HomeViewAdapter(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cardView_textView_image);
            tvTitle = itemView.findViewById(R.id.cardView_textView_title);
            tvTime = itemView.findViewById(R.id.cardView_textView_time);
            tvDetails = itemView.findViewById(R.id.cardView_textView_details);
            tvAuthor = itemView.findViewById(R.id.cardView_textView_author);
        }
    }

    @NonNull
    @Override
    public HomeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_news_cardview, parent, false);
        return new HomeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewAdapter holder, @SuppressLint("RecyclerView") final int position) {
        if (articlesList != null) {
            final Articles newsArticles = articlesList.get(position);
            Glide.with(holder.imageView.getContext()).load(newsArticles.getUrlToImage())
                    .into(holder.imageView);
            holder.tvTitle.setText(newsArticles.getTitle());
            holder.tvTime.setText(DateUtils.formatNewsApiDate(newsArticles.getPublishedAt()));
            holder.tvDetails.setText(newsArticles.getDescription());
            holder.tvAuthor.setText(newsArticles.getAuthor());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomeActivityImplementation newActivityImplementation = new HomeActivityImplementation();
                    if (newActivityImplementation.isOnline(v.getContext())){
                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(v.getContext());
                        Bundle bundle = new Bundle();
                        bundle.putString("index", String.valueOf(position));
                        firebaseAnalytics.logEvent("cardClicked", bundle);
                        NewsActivity.launch(v.getContext(), position);
                    }else{
                        newActivityImplementation.alertDialog(v.getContext());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (articlesList != null) {
            return articlesList.size();
        } else {
            return 0;
        }
    }
    public void setArticlesList(List<Articles> articlesList) {
        this.articlesList = articlesList;
        notifyDataSetChanged();
    }
}
