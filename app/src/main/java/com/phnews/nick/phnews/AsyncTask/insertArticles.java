package com.phnews.nick.phnews.AsyncTask;

import android.os.AsyncTask;

import com.phnews.nick.phnews.Dao.ArticlesDao;
import com.phnews.nick.phnews.Entities.Articles;

import java.util.List;

public class insertArticles extends AsyncTask<List<Articles>,Void,Void> {

    private ArticlesDao articlesDao;

    public insertArticles(ArticlesDao articlesDao){
        this.articlesDao = articlesDao;
    }

    @Override
    protected Void doInBackground(List<Articles>... articles) {
        articlesDao.insertArticles(articles[0]);
        return null;
    }
}
