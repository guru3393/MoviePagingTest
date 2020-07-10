package com.guru.moviepagingtest.upcoming_movies;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guru.moviepagingtest.R;
import com.guru.moviepagingtest.databinding.ActivitySearchMoviesBinding;
import com.guru.moviepagingtest.movie_details.MovieDetailsActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static com.guru.moviepagingtest.utils.Constants.LANDSCAPE_SPAN_COUNT;
import static com.guru.moviepagingtest.utils.Constants.TABLET_SPAN_COUNT;


public class SearchMoviesActivity extends DaggerAppCompatActivity implements Observer<PagedList<SearchMovie>>,
        SearchMoviesAdapter.OnUpcomingMovieClickListener {
    @Inject
    SearchMoviesViewModel searchMoviesViewModel;
    private ActivitySearchMoviesBinding activityUpcomingMoviesBinding;
    private RecyclerView moviesRecyclerView;
    private SearchMoviesAdapter searchMoviesAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUpcomingMoviesBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_movies);
        initRecyclerView();
        initMoviesAdapter();
        loadMovies();
    }

    private void initRecyclerView() {
        moviesRecyclerView = activityUpcomingMoviesBinding.moviesRecyclerView;
        Context context = moviesRecyclerView.getContext();
        int orientation = RecyclerView.HORIZONTAL;
        GridLayoutManager horizontalGridLayoutManager;
        if (isLandscape()) {
            horizontalGridLayoutManager = new GridLayoutManager(context, LANDSCAPE_SPAN_COUNT);
            if (isTablet(this)) {
                horizontalGridLayoutManager = new GridLayoutManager(context, TABLET_SPAN_COUNT, orientation, false);
            }
            moviesRecyclerView.setLayoutManager(horizontalGridLayoutManager);
        }
    }

    private boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private boolean isLandscape() {
        int orientation = getResources().getConfiguration().orientation;
        return orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void initMoviesAdapter()  {
        searchMoviesAdapter = new SearchMoviesAdapter(this, this);
        moviesRecyclerView.setAdapter(searchMoviesAdapter);
    }

    private void loadMovies() {

        searchMoviesViewModel.pagedListLiveData.observe(this, this);
    }

    private void reloadMovies() {
        searchMoviesViewModel.replaceSubscription(this, null);
        loadMovies();
    }

    private void searchMovies(String searchText) {
        searchMoviesViewModel.replaceSubscription(this, searchText);
        loadMovies();
    }

    @Override
    public void onChanged(PagedList<SearchMovie> searchMovies) {
        searchMoviesAdapter.submitList(searchMovies);
        hideProgressBar();
    }

    private void hideProgressBar() {
        activityUpcomingMoviesBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onUpcomingMovieViewClick(SearchMovie searchMovie) {
        goToMovieDetailsActivity(searchMovie);
    }

    private void goToMovieDetailsActivity(SearchMovie searchMovie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        String movieId = searchMovie.id;
        intent.putExtra("movieId", movieId);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_upcoming_movies, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        initSearchView(searchItem);
        initSearchViewEditText();
        return true;
    }

    private void initSearchView(MenuItem searchItem) {
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search...");
        searchView.setOnCloseListener(() -> {
            reloadMovies();
            return false;
        });
    }

    private void initSearchViewEditText() {
        EditText searchViewEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchViewEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchText) {
                if (searchText.length() > 0) {
                    searchMovies(searchText);
                }

                return false;
            }
        });
    }
}