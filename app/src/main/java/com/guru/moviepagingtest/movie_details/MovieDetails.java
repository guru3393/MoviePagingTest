package com.guru.moviepagingtest.movie_details;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;


public class MovieDetails {
    @SerializedName("imdbID")
    public String id;
    @SerializedName("Title")
    String title;
    @SerializedName("Language")
    String language;
//    @SerializedName("Genre")
//    ArrayList genres;
    @SerializedName("Plot")
    public String overview;
    @SerializedName("Released")
    String releaseDate;
    @SerializedName("Poster")
    String posterPath;
    @SerializedName("Runtime")
    String time;
    @SerializedName("Year")
    String year;
    @SerializedName("imdbRating")
    String imdbRating;
    @SerializedName("Director")
    String director;
    @SerializedName("Writer")
    String writer;
    @SerializedName("Actors")
    String actors;





    MovieDetails() {}

    public String getDirector(MovieDetails movieDetails) {
        return movieDetails.director;
    }

    public String getWriters(MovieDetails movieDetails) {
        return movieDetails.writer;
    }
    public String getActors(MovieDetails movieDetails) {
        return movieDetails.actors;
    }

    public String getTitleAndReleaseDate(MovieDetails movieDetails) {
        return movieDetails.title + " (" + movieDetails.releaseDate.substring(0, 4) + ")";
    }
    public String getYear(MovieDetails movieDetails) {
        return movieDetails.year;
    }
    public String getTime(MovieDetails movieDetails) {
        return movieDetails.time ;
    }
    public String getRating(MovieDetails movieDetails) {
        return "IMDB " +movieDetails.imdbRating;
    }

    public String getLanguage(MovieDetails movieDetails){
        String language = movieDetails.language;
        return  language.substring(0,1).toUpperCase() + language.substring(1);
    }

    public String getGenres(MovieDetails movieDetails) {
//        ArrayList genres = movieDetails.genres;
//        StringBuilder genresStringBuilder = new StringBuilder();
//        String separator = ", ";
//        for (int i = 0 ; i < genres.size(); i++) {
//            LinkedTreeMap treeMap = (LinkedTreeMap) genres.get(i);
//            String name = (String) treeMap.get("name");
//            if (name != null) {
//                if (i == genres.size() - 1) {
//                    separator = ".";
//                }
//                genresStringBuilder.append(name).append(separator);
//            }
//        }
//        return "Genres: " + genresStringBuilder;
        return  "";
    }
}