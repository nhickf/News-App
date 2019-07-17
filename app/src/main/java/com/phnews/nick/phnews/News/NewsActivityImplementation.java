package com.phnews.nick.phnews.News;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.phnews.nick.phnews.IConnectivity;

public class NewsActivityImplementation implements IConnectivity {

    @Override
    public Boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.isConnected();
    }
}
