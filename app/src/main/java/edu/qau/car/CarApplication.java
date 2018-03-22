package edu.qau.car;

import okhttp3.OkHttpClient;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CarApplication extends Application {

    public static OkHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();

        client = new OkHttpClient();
        //load url
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        AppURL.BASE_URL = sharedPreferences.getString(AppURL.BASE_URL_KEY, AppURL.BASE_URL);
        AppURL.SECOND_URL = sharedPreferences.getString(AppURL.SECOND_URL_KEY, AppURL.SECOND_PATH_1);
    }

}
