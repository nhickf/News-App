package com.phnews.nick.phnews.Repository.data;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.phnews.nick.phnews.Entities.Articles;
import com.phnews.nick.phnews.Networking.NewsAPI;
import com.phnews.nick.phnews.NewStore;
import com.phnews.nick.phnews.Repository.GetArticlesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepositoryImplementation implements IArticleRespository.show {

    private ArticlesRepository articlesRepository;

    public ArticleRepositoryImplementation(Context context){
        this.articlesRepository = new ArticlesRepository(context);

    }

    @Override
    public LiveData<List<Articles>> getArticles() {

        Call<GetArticlesResponse> call = NewsAPI.getAPI().getArticles("top-headlines");
        call.enqueue(new Callback<GetArticlesResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetArticlesResponse> call
                    , @NonNull Response<GetArticlesResponse> response) {

                GetArticlesResponse getArticlesResponse = response.body();
                NewStore.setNewsArticlesList(getArticlesResponse.getArticles());
                articlesRepository.insert(getArticlesResponse.getArticles());
            }

            @Override
            public void onFailure(@NonNull Call<GetArticlesResponse> call, @NonNull Throwable t) {

            }
        });

            return articlesRepository.getArticles();
    }

    @Override
    public LiveData<List<Articles>> getExactArticles(String searchValue) {
        return articlesRepository.getExactArticles(searchValue);
    }

    @Override
    public LiveData<List<Articles>> getSearchArticles(String searchValue) {
        return articlesRepository.getSearchArticles(searchValue);
    }
}