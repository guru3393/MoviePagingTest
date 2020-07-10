package com.guru.moviepagingtest.upcoming_movies;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.guru.moviepagingtest.network.RetrofitClient;
import com.guru.moviepagingtest.network.TheMovieDbApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.guru.moviepagingtest.utils.Constants.APY_KEY;
import static com.guru.moviepagingtest.utils.Constants.FIRST_PAGE;
import static com.guru.moviepagingtest.utils.HelperClass.createNewCall;
import static com.guru.moviepagingtest.utils.HelperClass.logMessage;


public class SearchMoviesDataSource extends PageKeyedDataSource<Integer, SearchMovie> {
    private TheMovieDbApi api;
    private String searchText;

    SearchMoviesDataSource(String searchText) {
        this.searchText = searchText;
        api = RetrofitClient.getInstance().getTheMovieDbApi();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, SearchMovie> callback) {
        Call<SearchMoviesApiResponse> call = getTheMovieDbApiResponseCall(FIRST_PAGE);
        Callback<SearchMoviesApiResponse> upcomingMoviesCallback = new Callback<SearchMoviesApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<SearchMoviesApiResponse> call, @NonNull Response<SearchMoviesApiResponse> response) {
                SearchMoviesApiResponse searchMoviesApiResponse = response.body();
                if (searchMoviesApiResponse != null) {
                    callback.onResult(searchMoviesApiResponse.searchMovies, null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchMoviesApiResponse> call, @NonNull Throwable t) {
                logMessage(t.getMessage());
                createNewCall(call);
            }
        };
        call.enqueue(upcomingMoviesCallback);
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, SearchMovie> callback) {}

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, SearchMovie> callback) {
        Call<SearchMoviesApiResponse> call = getTheMovieDbApiResponseCall(params.key);
        Callback<SearchMoviesApiResponse> upcomingMoviesCallback = new Callback<SearchMoviesApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<SearchMoviesApiResponse> call, @NonNull Response<SearchMoviesApiResponse> response) {
                SearchMoviesApiResponse searchMoviesApiResponse = response.body();
                if (searchMoviesApiResponse != null) {
                    Integer key = (params.key <= searchMoviesApiResponse.totalPages) ? params.key + 1 : null;
                    callback.onResult(searchMoviesApiResponse.searchMovies, key);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchMoviesApiResponse> call, @NonNull Throwable t) {
                logMessage(t.getMessage());
                createNewCall(call);
            }
        };
        call.enqueue(upcomingMoviesCallback);
    }

    private Call<SearchMoviesApiResponse> getTheMovieDbApiResponseCall(int pageNumber) {
        return (searchText == null) ? api.getMovies(APY_KEY, pageNumber,"Marvel","movie") : api.getSearchedMovies(searchText, APY_KEY, pageNumber,"movie");

    }
}