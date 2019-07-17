package com.phnews.nick.phnews.RoomDb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.phnews.nick.phnews.Dao.ArticlesDao;
import com.phnews.nick.phnews.Entities.Articles;

@Database(entities = {Articles.class},version = 1,exportSchema = false)
public abstract class ArticlesRoomDatabase extends RoomDatabase {

    public abstract ArticlesDao articlesDao();
    private static ArticlesRoomDatabase INSTANCE;

    public static ArticlesRoomDatabase getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (ArticlesRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            ,ArticlesRoomDatabase.class,"articles_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
