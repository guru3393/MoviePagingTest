package com.guru.moviepagingtest.di.upcoming_movies;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import com.guru.moviepagingtest.di.ApiViewModelFactory;
import com.guru.moviepagingtest.di.ViewModelKey;
import com.guru.moviepagingtest.upcoming_movies.SearchMoviesViewModel;

import static com.guru.moviepagingtest.utils.Constants.ITEMS_PER_PAGE;

@Module
@SuppressWarnings("unused")
public abstract class SearchMoviesViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindApiViewModelFactory(ApiViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(SearchMoviesViewModel.class)
    abstract ViewModel provideUpcomingMoviesViewModel(SearchMoviesViewModel viewModel);

    @Singleton
    @Provides
    static PagedList.Config providePagedListConfig() {
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(ITEMS_PER_PAGE)
                .build();
    }
}