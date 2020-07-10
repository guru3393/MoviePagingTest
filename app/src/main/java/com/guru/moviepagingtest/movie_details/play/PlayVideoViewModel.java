package com.guru.moviepagingtest.movie_details.play;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class PlayVideoViewModel extends ViewModel {
    private PlayRepository repository;
    public LiveData<TrailersApiResponse> trailersLiveData;

    @Inject
    PlayVideoViewModel(PlayRepository repository) {
        this.repository = repository;
    }

    public void setMovieId(String movieId) {
        trailersLiveData = repository.addTrailersToLiveData(movieId);
    }
}