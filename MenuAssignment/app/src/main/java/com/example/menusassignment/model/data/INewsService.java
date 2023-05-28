package com.example.menusassignment.model.data;

import com.example.menusassignment.model.articles.Article;
import com.example.menusassignment.model.articles.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface INewsService {

    //Get top headlines from the api
    @GET("/v2/top-headlines?country=us&apiKey=00aef6fd105b4f40bd392776be21a483") //https://newsapi.org/v2/top-headlines
    Call<Root> getArticles();

    //TODO: Define GET call to get specific news categories
}
