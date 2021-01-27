package com.example.news.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.constants.EnumCategory;
import com.example.news.model.Articles;
import com.example.news.model.Category;
import com.example.news.model.ListCategories;
import com.example.news.model.Notice;
import com.example.news.retrofit.NewsService;
import com.example.news.ui.adapter.adapters.RecyclerViewListNewsAdapter;
import com.example.news.ui.adapter.adapters.RecyclerViewCategoriesAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.news.constants.Constant.APPBAR_ECONOMY;
import static com.example.news.constants.Constant.APPBAR_ENTERTAINMENT;
import static com.example.news.constants.Constant.APPBAR_LAST_NEWS;
import static com.example.news.constants.Constant.APPBAR_SCIENCE;
import static com.example.news.constants.Constant.APPBAR_SPORTS;
import static com.example.news.constants.Constant.APPBAR_TECHNOLOGY;
import static com.example.news.constants.Constant.BASE_URL;
import static com.example.news.constants.Constant.CHECK_CLIENT_CONNECTION_MESSAGE;
import static com.example.news.constants.Constant.KEY_NEWS_MATTER;

@SuppressWarnings({"NullableProblems", "ConstantConditions"})
public class NewsList extends AppCompatActivity {


    private RecyclerView recyclerViewNews;
    private RecyclerView recyclerViewCategories;

    private List<Notice> NoticesList = new ArrayList<>();
    private List<Category> CategoriesList = new ArrayList<>();

    private RecyclerViewListNewsAdapter noticesAdapter;
    private RecyclerViewCategoriesAdapter categoriesAdapter;

    private final Notice notice = new Notice();

    private Retrofit retrofit;
    private NewsService noticesService;
    private Call<Articles> articlesCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uploadElements();
        createRetrofit();

    }

    private void uploadElements() {
        CategoriesList = ListCategories.getAllCategories();
        recyclerViewNews = findViewById(R.id.acivity_main_recyclerView);
        recyclerViewCategories = findViewById(R.id.acivity_main_categorias);
    }

    public void setList() {

        noticesAdapter = new RecyclerViewListNewsAdapter(NoticesList, this);
        recyclerViewNews.setAdapter(noticesAdapter);
        newsItemClick();

        categoriesAdapter = new RecyclerViewCategoriesAdapter(CategoriesList, this);
        recyclerViewCategories.setAdapter(categoriesAdapter);
        onCategoriesItemClick();
    }

    private void onCategoriesItemClick() {
        categoriesAdapter.setOnItemClickListenerCategory(category -> createService(category.getEnumCategory()));
    }

    private void newsItemClick() {
        noticesAdapter.setOnItemClickListenerNews(news -> {
            news.setEnumCategory(notice.getEnumCategory());
            Intent intent = new Intent(getApplicationContext(), NewsStory.class);
            intent.putExtra(KEY_NEWS_MATTER, news);
            startActivity(intent);

        });
    }

    public void createRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        createService(EnumCategory.GENERAL);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void createService(EnumCategory enumCategory) {
        noticesService = retrofit.create(NewsService.class);
        setAppBarColors(enumCategory);

        articlesCall.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                if (response.isSuccessful()) {
                    NoticesList = response.body().getArticles();
                    setList();
                }
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Toast.makeText(NewsList.this, CHECK_CLIENT_CONNECTION_MESSAGE, Toast.LENGTH_LONG).show();

            }
        });
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
    private void setAppBarColors(EnumCategory enumCategory) {
        switch (enumCategory) {
            case GENERAL:
                articlesCall = noticesService.getAllNews();
                notice.setEnumCategory(EnumCategory.GENERAL);
                setTitle(APPBAR_LAST_NEWS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.red));

                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.red));
                }

                break;
            case BUSINESS:
                articlesCall = noticesService.getBusiness();
                notice.setEnumCategory(EnumCategory.BUSINESS);
                setTitle(APPBAR_ECONOMY);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.blue));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
                }

                break;
            case ENTERTAINMENT:
                articlesCall = noticesService.getEntertainment();
                notice.setEnumCategory(EnumCategory.ENTERTAINMENT);
                setTitle(APPBAR_ENTERTAINMENT);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.orange));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.orange));
                }

                break;
            case SCIENCE:
                articlesCall = noticesService.getScience();
                notice.setEnumCategory(EnumCategory.SCIENCE);
                setTitle(APPBAR_SCIENCE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.purple));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.purple));
                }

                break;
            case SPORTS:
                articlesCall = noticesService.getSports();
                notice.setEnumCategory(EnumCategory.SPORTS);
                setTitle(APPBAR_SPORTS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.green));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.green));
                }

                break;
            case TECHNOLOGY:
                articlesCall = noticesService.getTechnology();
                notice.setEnumCategory(EnumCategory.TECHNOLOGY);
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

