package com.phnews.nick.phnews.Home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

import com.phnews.nick.phnews.Adapter.HomeAdapter;
import com.phnews.nick.phnews.Entities.Articles;
import com.phnews.nick.phnews.R;
import com.phnews.nick.phnews.ViewModel.ArticlesViewModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    private ProgressBar progressBar;
    private HomeAdapter homeAdapter;
    private ArticlesViewModel articlesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progressBar = findViewById(R.id.ProgressBar_activity_home);
        coordinatorLayout = findViewById(R.id.coordinator_activity_home);
        RecyclerView recyclerView = findViewById(R.id.home_activity_recyclerView);


        articlesViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);

        homeAdapter = new HomeAdapter();

        HomeActivityImplementation homeActivityImplementation = new HomeActivityImplementation();
        if (homeActivityImplementation.isOnline(this)) {
            viewModel();
        } else {
            showSnackbar("No internet Connection");
            viewModel();
        }
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void showSnackbar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).setAction("Turn on", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
                }
        }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void viewModel() {
        articlesViewModel.getAllArticles().observe(this, new Observer<List<Articles>>() {
            @Override
            public void onChanged(@Nullable final List<Articles> articles) {
                if (articles != null) {
                    homeAdapter.setArticlesList(articles);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        articlesViewModel.getAllArticles();
    }

    @Override
    protected void onPause() {
        super.onPause();
        articlesViewModel.getAllArticles();
    }
}

