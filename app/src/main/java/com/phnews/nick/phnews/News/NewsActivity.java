package com.phnews.nick.phnews.News;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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

public class NewsActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;
    private static final String KEY_INDEX ="news_index";
    private NewsActivityImplementation newActivityImplementation;
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
       
        webView = findViewById(R.id.WebView_activity_news);
        progressBar = findViewById(R.id.ProgressBar_activity_news);
        imageView = findViewById(R.id.newsImage);
        textView =findViewById(R.id.noInternet);
        textView.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);

        newActivityImplementation = new NewsActivityImplementation();
            int index = getIntent().getIntExtra(KEY_INDEX, -1);
            if (index != -1){
                newsUpdate(index);
            }
            else {
                Toast.makeText(this, "Error index", Toast.LENGTH_SHORT).show();
            }
        }
    @SuppressLint("SetJavaScriptEnabled")
    public void newsUpdate (int index) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                progressBar.setVisibility(View.GONE);
            }
        });
        if (newActivityImplementation.isOnline(this)){
            webView.loadUrl(NewStore.getNewsArticlesList().get(index).getUrl());
        }else {
            webView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
        }
    }
    public static void launch(Context context, int index){
        Intent intent = new Intent(context,NewsActivity.class);
        intent.putExtra(KEY_INDEX,index);
        context.startActivity(intent);
    }
}
