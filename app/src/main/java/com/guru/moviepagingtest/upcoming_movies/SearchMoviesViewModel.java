package com.guru.moviepagingtest.upcoming_movies;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import javax.inject.Inject;

public class SearchMoviesViewModel extends ViewModel {
    private PagedList.Config config;
    private SearchMoviesDataSourceFactory sourceFactory;
    LiveData<PagedList<SearchMovie>> pagedListLiveData;

    @Inject
    public SearchMoviesViewModel(PagedList.Config config) {
        this.config = config;
        sourceFactory = new SearchMoviesDataSourceFactory(null);
        pagedListLiveData = new LivePagedListBuilder<>(sourceFactory, config).build();
    }

    void replaceSubscription(LifecycleOwner lifecycleOwner, String searchText) {
        pagedListLiveData.removeObservers(lifecycleOwner);
        if (searchText == null) {
            sourceFactory = new SearchMoviesDataSourceFactory(null);
        } else {
            sourceFactory = new SearchMoviesDataSourceFactory(searchText);
        }
        pagedListLiveData = new LivePagedListBuilder<>(sourceFactory, config).build();
    }
}
