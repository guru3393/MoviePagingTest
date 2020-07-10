package com.guru.moviepagingtest.movie_details.play;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.guru.moviepagingtest.network.TheMovieDbApi;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.guru.moviepagingtest.utils.Constants.APY_KEY;
import static com.guru.moviepagingtest.utils.HelperClass.createNewCall;
import static com.guru.moviepagingtest.utils.HelperClass.logMessage;

@Singleton
class PlayRepository {
    private TheMovieDbApi api;

    @Inject
    PlayRepository(TheMovieDbApi api) {
        this.api = api;
    }

    MutableLiveData<TrailersApiResponse> addTrailersToLiveData(String movieId) {
        MutableLiveData<TrailersApiResponse> trailersMutableLiveData = new MutableLiveData<>();
        Call<TrailersApiResponse> call = api.getTrailers(movieId, APY_KEY);
        Callback<TrailersApiResponse> trailersApiResponseCallback = new Callback<TrailersApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<TrailersApiResponse> call, @NonNull Response<TrailersApiResponse> response) {
                TrailersApiResponse trailersApiResponse = response.body();
                if (trailersApiResponse != null) {
                    trailersMutableLiveData.setValue(trailersApiResponse);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrailersApiResponse> call, @NonNull Throwable t) {
                logMessage(t.getMessage());
                createNewCall(call);
            }
        };
        call.enqueue(trailersApiResponseCallback);
        return trailersMutableLiveData;
    }
}