package com.phnews.nick.phnews.Networking;

import com.phnews.nick.phnews.Repository.GetArticlesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.phnews.nick.phnews.Networking.NewsAPI.APIKEY;
import static com.phnews.nick.phnews.Networking.NewsAPI.COUNTRY;

public interface NewsService {

    @GET("top-headlines?country="+COUNTRY+"&apiKey="+APIKEY )
    Call<GetArticlesResponse> getArticles (@Query("sortBy")String sortBy);
}
