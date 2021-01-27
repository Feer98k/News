package com.example.news.ui.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.news.R;

import static com.example.news.constants.Constant.FIRST;
import static com.example.news.constants.Constant.USER_PREFERENCES;

@SuppressWarnings("deprecation")
public class SplashScreen extends AppCompatActivity {

    final Handler handle = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splaash_screen);
        SharedPreferences preferences = getSharedPreferences(USER_PREFERENCES, MODE_PRIVATE);
        check(handle, preferences);

    }

    private void check(Handler handle, SharedPreferences preferences) {

        if (!preferences.contains(FIRST)) {
            createPreference(preferences);
            handle.postDelayed(this::startNewsList, 2000);

        } else {
            handle.postDelayed(this::startNewsList, 500);
        }
    }

    public void startNewsList() {
        Intent intent = new Intent(this, NewsList.class);
        startActivity(intent);
        finish();
    }

    private void createPreference(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(FIRST, true);
        editor.apply();
    }
}