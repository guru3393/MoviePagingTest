package com.guru.moviepagingtest.upcoming_movies;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class SearchMovie {
    @SerializedName("imdbID")
    public String id;
    @SerializedName("Title")
    public String title;
    @SerializedName("Poster")
    public String posterPath;
    @SerializedName("Year")
    public String Year;
    @SerializedName("Type")
    public String Type;

    public SearchMovie(String id, String title, String posterPath) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}