package com.guru.moviepagingtest.di.movie_details;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.guru.moviepagingtest.di.ApiViewModelFactory;
import com.guru.moviepagingtest.di.ViewModelKey;
import com.guru.moviepagingtest.movie_details.play.PlayVideoViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
@SuppressWarnings("unused")
public abstract class PlayViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindMovieDetailsViewModelFactory(ApiViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(PlayVideoViewModel.class)
    abstract ViewModel provideMovieDetailsViewModel(PlayVideoViewModel viewModel);
}