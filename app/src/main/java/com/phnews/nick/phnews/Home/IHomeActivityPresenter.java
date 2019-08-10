package com.phnews.nick.phnews.Home;

import android.content.Context;

import com.phnews.nick.phnews.Entities.Articles;

public class IHomeActivityPresenter implements IHomeActivityContract.Presenter {

    private HomeActivityContractImplementation homeActivityContractImplementation;
    private IHomeActivityContract.View view;
    private Context context;

    public IHomeActivityPresenter(HomeActivityContractImplementation homeActivityContractImplementation,
                                  IHomeActivityContract.View view, Context context) {
        this.homeActivityContractImplementation = homeActivityContractImplementation;
        this.view = view;
        this.context = context;
    }

    @Override
    public boolean validateInternetConnection() {
        return homeActivityContractImplementation.isOnline(context);
    }

    @Override
    public void getArticles() {
        if (validateInternetConnection()){
            view.showArticles();
        }else {
            view.showInternetConnectionStatus("No Internet Connection");
        }
    }

    @Override
    public void validateBeforeNews(Articles articles) {
        if (validateInternetConnection()){
            view.showNewsActivity(articles.getUrl());
        }else {
            view.showGoToNewStatus("No Internet Connection , Failed to show news");
        }
    }
}
