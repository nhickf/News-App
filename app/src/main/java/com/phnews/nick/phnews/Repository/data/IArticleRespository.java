package com.phnews.nick.phnews.Repository.data;

import android.arch.lifecycle.LiveData;

import com.phnews.nick.phnews.Entities.Articles;

import java.util.List;

public interface IArticleRespository {

    interface show{
        LiveData<List<Articles>> getArticles();
        LiveData<List<Articles>> getExactArticles(String searchValue);
        LiveData<List<Articles>> getSearchArticles(String searchValue);
    }
}
