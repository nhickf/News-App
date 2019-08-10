package com.phnews.nick.phnews.News;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.phnews.nick.phnews.NewStore;
import com.phnews.nick.phnews.R;
import com.phnews.nick.phnews.databinding.ActivityNewsBinding;

public class NewsActivity extends AppCompatActivity {
    public static final String KEY_INDEX = "news_url";
    private NewsActivityImplementation newActivityImplementation;
    private ActivityNewsBinding activityNewsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        activityNewsBinding = DataBindingUtil.setContentView(this,R.layout.activity_news);


        activityNewsBinding.noInternet.setVisibility(View.GONE);
        activityNewsBinding.newsImage.setVisibility(View.GONE);

        newActivityImplementation = new NewsActivityImplementation();

        String url = getIntent().getStringExtra(KEY_INDEX);
        if (url!= null && !url.equals("")) {
            newsUpdate(url);
        } else {
            activityNewsBinding.noInternet.setVisibility(View.VISIBLE);
            activityNewsBinding.newsImage.setVisibility(View.VISIBLE);
            showSnackBar("Error in loading the news");
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void newsUpdate(String url) {
        activityNewsBinding.WebViewActivityNews.getSettings().setJavaScriptEnabled(true);
        activityNewsBinding.WebViewActivityNews.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                activityNewsBinding.ProgressBarActivityNews.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                activityNewsBinding.ProgressBarActivityNews.setVisibility(View.GONE);
                activityNewsBinding.WebViewActivityNews.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                activityNewsBinding.ProgressBarActivityNews.setVisibility(View.GONE);
            }
        });
        if (newActivityImplementation.isOnline(this)) {

            activityNewsBinding.WebViewActivityNews.loadUrl(url);
        } else {
            activityNewsBinding.WebViewActivityNews.setVisibility(View.GONE);
            activityNewsBinding.ProgressBarActivityNews.setVisibility(View.GONE);
            activityNewsBinding.noInternet.setVisibility(View.VISIBLE);
            activityNewsBinding.newsImage.setVisibility(View.VISIBLE);
        }
    }

    public void showSnackBar(String message) {
        Snackbar.make(activityNewsBinding.contraintLayout, message, Snackbar.LENGTH_LONG)
                .setAction("Go back", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).show();
    }
}
