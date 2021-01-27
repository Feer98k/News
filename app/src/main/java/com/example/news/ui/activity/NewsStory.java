package com.example.news.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.news.R;
import com.example.news.constants.EnumCategory;
import com.example.news.model.Notice;

import static com.example.news.constants.Constant.APPBAR_ECONOMY;
import static com.example.news.constants.Constant.APPBAR_ENTERTAINMENT;
import static com.example.news.constants.Constant.APPBAR_LAST_NEWS;
import static com.example.news.constants.Constant.APPBAR_SCIENCE;
import static com.example.news.constants.Constant.APPBAR_SPORTS;
import static com.example.news.constants.Constant.APPBAR_TECHNOLOGY;
import static com.example.news.constants.Constant.KEY_NEWS_MATTER;


@SuppressWarnings("ConstantConditions")
public class NewsStory extends AppCompatActivity {


    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_materia);
        uploadWeb();
    }

    public void uploadWeb() {
        webView = findViewById(R.id.acivity_noticia_materias_webView);
        hasIntent();
    }

    public void hasIntent() {
        Intent intent = getIntent();
        if ((intent.hasExtra(KEY_NEWS_MATTER))) {
            Notice notice = (Notice) intent.getSerializableExtra(KEY_NEWS_MATTER);
            String noticeURL = notice.getUrl();
            setAppBarColors(notice.getEnumCategory());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            webView.loadUrl(noticeURL);
        }

    }

    /**
     * setAppBarColors()
     * Metodo responsavel por fazer a troca de cores da appbar e o status appbar conforme a troca de categoria de noticias da lista
     * Method responsible for changing the color of the appbar and
     * the appbar status according to the change of news category in the list
     *
     * @param enumCategory Deve-se receber uma categoria de noticia
     *                     You must receive a news category
     */

    public void setAppBarColors(EnumCategory enumCategory) {
        switch (enumCategory) {
            case GENERAL:
                setTitle(APPBAR_LAST_NEWS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.red));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.red));
                }
                break;

            case BUSINESS:
                setTitle(APPBAR_ECONOMY);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.blue));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
                }
                break;

            case ENTERTAINMENT:

                setTitle(APPBAR_ENTERTAINMENT);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.orange));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.orange));
                }

                break;

            case SCIENCE:

                setTitle(APPBAR_SCIENCE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.purple));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.purple));
                }
                break;

            case SPORTS:

                setTitle(APPBAR_SPORTS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.green));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.green));
                }
                break;

            case TECHNOLOGY:

                setTitle(APPBAR_TECHNOLOGY);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.black));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black));
                }
                break;
        }

    }

}