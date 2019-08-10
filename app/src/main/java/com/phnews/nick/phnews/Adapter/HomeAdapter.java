package com.phnews.nick.phnews.Adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.phnews.nick.phnews.Entities.Articles;
import com.phnews.nick.phnews.Home.IHomeActivityPresenter;
import com.phnews.nick.phnews.IItemInteraction;
import com.phnews.nick.phnews.R;
import com.phnews.nick.phnews.databinding.ActivityNewsCardviewBinding;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewAdapter> {

    private List<Articles> articlesList;
    private RequestOptions requestOptions;
    private IHomeActivityPresenter presenter;

    public HomeAdapter(IHomeActivityPresenter presenter) {
        this.presenter = presenter;
        requestOptions = new RequestOptions().placeholder(R.drawable.ic_image_grey)
                .centerCrop();
    }

    static class HomeViewAdapter extends RecyclerView.ViewHolder {
        ActivityNewsCardviewBinding activityNewsCardviewBinding;
        ImageView imageView;

        HomeViewAdapter(ActivityNewsCardviewBinding activityNewsCardviewBinding) {
            super(activityNewsCardviewBinding.getRoot());
            this.activityNewsCardviewBinding = activityNewsCardviewBinding;
            imageView = itemView.findViewById(R.id.cardView_textView_image);
        }
        public void bind(Articles articles, IHomeActivityPresenter presenter) {
            activityNewsCardviewBinding.setNewsarticles(articles);
            activityNewsCardviewBinding.setPresenter(presenter);
            activityNewsCardviewBinding.executePendingBindings();
        }

    }

    @NonNull
    @Override
    public HomeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        ActivityNewsCardviewBinding activityNewsCardviewBinding = ActivityNewsCardviewBinding.inflate(
            layoutInflater,parent,false);

        return new HomeViewAdapter(activityNewsCardviewBinding);
    }



    @Override
    public void onBindViewHolder(@NonNull final HomeViewAdapter holder, @SuppressLint("RecyclerView") final int position) {
        if (articlesList != null) {
            final Articles newsArticles = articlesList.get(position);
            Glide.with(holder.imageView.getContext())
                    .applyDefaultRequestOptions(requestOptions)
                    .load(newsArticles.getUrlToImage())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.imageView);

            holder.bind(newsArticles,presenter);

        }
    }

    @Override
    public int getItemCount() {
        if (articlesList != null) {
            return articlesList.size();
        }
        return 0;
    }
    public void setArticlesList(List<Articles> articlesList) {
        this.articlesList = articlesList;
        notifyDataSetChanged();
    }
}
