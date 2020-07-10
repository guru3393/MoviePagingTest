package com.guru.moviepagingtest.di;

import com.guru.moviepagingtest.movie_details.MovieDetailsActivity;
import com.guru.moviepagingtest.upcoming_movies.SearchMoviesActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
@SuppressWarnings("unused")
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract SearchMoviesActivity contributeUpcomingMoviesActivity();

    @ContributesAndroidInjector
    abstract MovieDetailsActivity contributeMovieDetailsActivity();
}