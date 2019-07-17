package com.phnews.nick.phnews.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsAPI {
    static final String APIKEY = "ea05d5b68dd94133a3c3b34d232f94cd";
    private static final String APIPATH = "https://newsapi.org/v2/";
    static final String COUNTRY = "ph";
    private static NewsService newsService = null;


    public static NewsService getAPI(){
        if (newsService == null){
            Retrofit retrofit =new  Retrofit.Builder()
                    .baseUrl(APIPATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsService = retrofit.create(NewsService.class);
        }
        return newsService;
    }
}
