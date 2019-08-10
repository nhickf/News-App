package com.phnews.nick.phnews.Home;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.phnews.nick.phnews.Adapter.HomeAdapter;
import com.phnews.nick.phnews.Entities.Articles;
import com.phnews.nick.phnews.News.NewsActivity;
import com.phnews.nick.phnews.R;
import com.phnews.nick.phnews.ViewModel.ArticlesViewModel;
import com.phnews.nick.phnews.databinding.ActivityHomeBinding;

import java.util.List;

import static com.phnews.nick.phnews.News.NewsActivity.KEY_INDEX;

public class HomeActivity extends AppCompatActivity implements IHomeActivityContract.View {
    private HomeAdapter homeAdapter;
    private ArticlesViewModel articlesViewModel;
    private ActivityHomeBinding activityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityHomeBinding = DataBindingUtil.setContentView(this , R.layout.activity_home);

        RecyclerView recyclerView = findViewById(R.id.home_activity_recyclerView);

        articlesViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);
        HomeActivityContractImplementation homeActivityImplementation = new HomeActivityContractImplementation();

        IHomeActivityPresenter presenter = new IHomeActivityPresenter(homeActivityImplementation, this, this);

        homeAdapter = new HomeAdapter(presenter);

        presenter.getArticles();

        getStatus();

        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchMenu = menu.findItem(R.id.menu_search);

        final SearchView searchView = (SearchView) searchMenu.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null && !query.trim().equals(" ")) {
                    getExactArticles(query.trim());
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText !=null && !newText.equals(" ")){
                    getExactArticles(newText.trim());
                }
                return true;
            }
        });
        return true;
    }

    private void getExactArticles(String searchValue){
        articlesViewModel.getExactArticles(searchValue).observe(this, new Observer<List<Articles>>() {
            @Override
            public void onChanged(@Nullable List<Articles> articles) {
                homeAdapter.setArticlesList(articles);
            }
        });
    }


    public void getStatus(){
        articlesViewModel.getStatus().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null) {
                    showGoToNewStatus(s);
                }
            }
        });
    }

    public void viewModel() {
        articlesViewModel.getAllArticles().observe(this, new Observer<List<Articles>>() {
            @Override
            public void onChanged(@Nullable final List<Articles> articles) {
                if (articles != null) {
                    homeAdapter.setArticlesList(articles);
                    activityHomeBinding.ProgressBarActivityHome.setVisibility(View.GONE);
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

    @Override
    public void showNewsActivity(String url) {
        Intent intent = new Intent(this,NewsActivity.class);
        intent.putExtra(KEY_INDEX,url);
        startActivity(intent);
    }

    @Override
    public void showInternetConnectionStatus(String message) {
        Snackbar.make(activityHomeBinding.coordinatorActivityHome, message, Snackbar.LENGTH_LONG).setAction("Turn on", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
            }
        }).show();
    }

    @Override
    public void showGoToNewStatus(String message) {
        Snackbar.make(activityHomeBinding.coordinatorActivityHome, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showArticles() {
        viewModel();
    }
}

