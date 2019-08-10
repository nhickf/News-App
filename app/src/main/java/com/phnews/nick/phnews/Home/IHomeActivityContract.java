package com.phnews.nick.phnews.Home;

import android.content.Context;

import com.phnews.nick.phnews.Entities.Articles;

public interface IHomeActivityContract {

    interface View {

        void showNewsActivity(String url);

        void showInternetConnectionStatus(String message);

        void showGoToNewStatus(String message);

        void showArticles();
    }

    interface Presenter{

        boolean validateInternetConnection();

        void getArticles();

        void validateBeforeNews(Articles articles);
    }

    void alertDialog(Context context);

}
