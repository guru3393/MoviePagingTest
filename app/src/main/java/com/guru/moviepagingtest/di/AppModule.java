package com.guru.moviepagingtest.di;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.guru.moviepagingtest.network.TheMovieDbApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.guru.moviepagingtest.utils.Constants.BASE_URL;


@Module
class AppModule {
    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    static TheMovieDbApi provideTheMovieDbApi(Retrofit retrofit){
        return retrofit.create(TheMovieDbApi.class);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application) {
        return Glide.with(application);
    }
}