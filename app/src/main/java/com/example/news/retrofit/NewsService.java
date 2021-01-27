package com.example.news.retrofit;

import com.example.news.model.Articles;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.example.news.config.KEY.*;


public interface NewsService {



    @GET(URL_ALL_NEWS)
    Call<Articles> getAllNews();

    @GET(URL_ENTERTAINMENT)
    Call<Articles> getEntertainment();

    @GET(URL_SPORTS)
    Call<Articles> getSports();

    @GET(URL_BUSINESS)
    Call<Articles> getBusiness();

    @GET(URL_SCIENCE)
    Call<Articles> getScience();

    @GET(URL_TECHNOLOGY)
    Call<Articles> getTechnology();
}
