package com.phnews.nick.phnews.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.phnews.nick.phnews.Entities.Articles;
import com.phnews.nick.phnews.Repository.data.ArticleRepositoryImplementation;

import java.util.List;

public class ArticlesViewModel extends AndroidViewModel {

    private ArticleRepositoryImplementation articlesRepository;
    private LiveData<List<Articles>> articlesLiveData;

    public ArticlesViewModel(@NonNull Application application) {
        super(application);
        this.articlesRepository = new ArticleRepositoryImplementation(application);
        this.articlesLiveData = articlesRepository.getArticles();
    }

    public LiveData<List<Articles>> getAllArticles(){
            if (articlesRepository == null){
            }
        return articlesLiveData;
    }
}









