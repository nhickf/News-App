package com.phnews.nick.phnews.Repository.data;


import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.phnews.nick.phnews.AsyncTask.insertArticles;
import com.phnews.nick.phnews.Dao.ArticlesDao;
import com.phnews.nick.phnews.Entities.Articles;
import com.phnews.nick.phnews.RoomDb.ArticlesRoomDatabase;

import java.util.List;

public class ArticlesRepository {
    private ArticlesDao articlesDao;
    private LiveData<List<Articles>> listLiveData;


    ArticlesRepository(Context context){
        ArticlesRoomDatabase db = ArticlesRoomDatabase.getDatabase(context);
        this.articlesDao = db.articlesDao();
        listLiveData = articlesDao.getAllArticles();
    }

    public LiveData<List<Articles>> getArticles(){ return listLiveData; }

    public void insert(List<Articles> articles){
        new insertArticles(articlesDao).execute(articles);

    }
}
