package com.guru.moviepagingtest.network;

import com.guru.moviepagingtest.movie_details.MovieDetails;
import com.guru.moviepagingtest.movie_details.play.TrailersApiResponse;
import com.guru.moviepagingtest.upcoming_movies.SearchMoviesApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDbApi {
    @GET(".")
    Call<SearchMoviesApiResponse> getMovies(
            @Query("apikey") String apiKey,
            @Query("page") int page,
            @Query("s") String Marvel,
            @Query("type") String type
    );

    @GET(".")
    Call<SearchMoviesApiResponse> getSearchedMovies(
            @Query("s") String query,
            @Query("apikey") String apiKey,
            @Query("page") int page,
            @Query("type") String type
    );

    @GET(".")
    Call<MovieDetails> getMovieDetails(
            @Query("i") String id,
            @Query("apikey") String apiKey
    );

    @GET("movie/{movie_id}/videos")
    Call<TrailersApiResponse> getTrailers(
            @Path("i") String id,
            @Query("apikey") String apiKey
    );
}