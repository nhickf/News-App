package com.phnews.nick.phnews.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.phnews.nick.phnews.Entities.Articles;
import com.phnews.nick.phnews.Repository.data.ArticleRepositoryImplementation;
import com.phnews.nick.phnews.Repository.data.IArticleRespository;

import java.util.List;

public class ArticlesViewModel extends AndroidViewModel  implements IArticleRespository.show {

    private ArticleRepositoryImplementation articlesRepository;
    private LiveData<List<Articles>> articlesLiveData;
    private MutableLiveData<String> mutableLiveData;
    public static String RESULT_OK = "Loading news article complete";
    public static String RESULT_FAILED = "No news article";

    public ArticlesViewModel(@NonNull Application application) {
        super(application);
        this.articlesRepository = new ArticleRepositoryImplementation(application);
        this.articlesLiveData = articlesRepository.getArticles();

        mutableLiveData = new MutableLiveData<>();
        mutableLiveData.postValue(RESULT_OK);
    }

    public LiveData<List<Articles>> getAllArticles(){
         return getArticles();
    }

    public MutableLiveData<String> getStatus(){
        return mutableLiveData;
    }

    @Override
    public LiveData<List<Articles>> getArticles() {
        if (articlesRepository == null){
            mutableLiveData.setValue(RESULT_FAILED);
        }
        return articlesLiveData;
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









