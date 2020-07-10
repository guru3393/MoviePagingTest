package com.guru.moviepagingtest.upcoming_movies;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SearchMoviesApiResponse {
    @SerializedName("Search")
    List<SearchMovie> searchMovies = new ArrayList<>();
    @SerializedName("totalResults")
    int totalPages;
    @SerializedName("Response")
    String Response;
}