package com.example.news.key;

public interface KeysURL {
    //Insira a key da API aqui
    //insert the api key here
    String KEY = "insert the API_KEY here";


    String URL_ALL_NEWS = "top-headlines?country=br&apiKey=" + KEY;
    String URL_TECHNOLOGY = "top-headlines?country=br&category=technology&apiKey=" + KEY;
    String URL_SCIENCE = "top-headlines?country=br&category=science&apiKey=" + KEY;
    String URL_BUSINESS = "top-headlines?country=br&category=business&apiKey=" + KEY;
    String URL_SPORTS = "top-headlines?country=br&category=sports&apiKey=" + KEY;
    String URL_ENTERTAINMENT = "top-headlines?country=br&category=entertainment&apiKey=" + KEY;


}
