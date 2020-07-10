package com.guru.moviepagingtest.movie_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class MovieDetailsViewModel extends ViewModel {
    private MovieDetailsRepository repository;
    LiveData<MovieDetails> movieDetailsLiveData;

    @Inject
    MovieDetailsViewModel(MovieDetailsRepository repository) {
        this.repository = repository;
    }

    void setMovieId(String movieId) {
        movieDetailsLiveData = repository.addMovieDetailsToLiveData(movieId);
    }
}