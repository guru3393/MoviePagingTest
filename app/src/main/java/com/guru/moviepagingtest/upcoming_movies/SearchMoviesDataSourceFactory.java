package com.guru.moviepagingtest.upcoming_movies;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class SearchMoviesDataSourceFactory extends DataSource.Factory<Integer, SearchMovie> {
    private MutableLiveData<PageKeyedDataSource<Integer, SearchMovie>> liveData = new MutableLiveData<>();
    private String searchText;

    SearchMoviesDataSourceFactory(String searchText) {
        this.searchText = searchText;
    }

    @NonNull
    @Override
    public DataSource<Integer, SearchMovie> create() {
        SearchMoviesDataSource searchMoviesDataSource = new SearchMoviesDataSource(searchText);
        liveData.postValue(searchMoviesDataSource);
        return searchMoviesDataSource;
    }
}