package com.phnews.nick.phnews;

import com.phnews.nick.phnews.Entities.Articles;

import java.util.ArrayList;
import java.util.List;

public class NewStore {
    private static List<Articles> newsArticlesList = new ArrayList<>();

    public static  List<Articles> getNewsArticlesList(){
        return newsArticlesList;
    }

    public static void setNewsArticlesList(List<Articles>newsArticles){
        NewStore.newsArticlesList = newsArticles;
    }
}
