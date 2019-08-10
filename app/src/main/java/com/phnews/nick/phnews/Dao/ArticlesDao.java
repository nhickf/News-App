package com.phnews.nick.phnews.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.phnews.nick.phnews.Entities.Articles;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ArticlesDao {

    @Insert(onConflict = REPLACE)
    void insertArticles(List<Articles> articles);

    @Query("SELECT * FROM articles ORDER BY publishedAt DESC")
    LiveData<List<Articles>> getAllArticles();

    @Query("SELECT * FROM ARTICLES WHERE `title` LIKE '%'||:searchValue ||'%'")
    LiveData<List<Articles>> getExactArticles(String searchValue);

    @Query("SELECT * FROM ARTICLES WHERE `title` LIKE :searchValue")
    LiveData<List<Articles>> getSeartchArticles(String searchValue);

}
