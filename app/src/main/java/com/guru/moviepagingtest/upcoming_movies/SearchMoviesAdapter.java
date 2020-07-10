package com.guru.moviepagingtest.upcoming_movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.guru.moviepagingtest.R;
import com.guru.moviepagingtest.databinding.UpcomingMovieDataBinding;


public class SearchMoviesAdapter extends PagedListAdapter<SearchMovie, SearchMoviesAdapter.UpcomingMovieViewHolder> {
    private Context context;
    private OnUpcomingMovieClickListener onUpcomingMovieClickListener;

    SearchMoviesAdapter(Context context, OnUpcomingMovieClickListener onUpcomingMovieClickListener) {
        super(diffCallback);
        this.context = context;
        this.onUpcomingMovieClickListener = onUpcomingMovieClickListener;
    }

    @NonNull
    @Override
    public UpcomingMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        UpcomingMovieDataBinding upcomingMovieDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_movie, parent, false);
        return new UpcomingMovieViewHolder(upcomingMovieDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final UpcomingMovieViewHolder holder, int position) {
        SearchMovie searchMovie = getItem(position);
        if (searchMovie != null) {
            holder.bindMovie(searchMovie);
        }
    }

    private static DiffUtil.ItemCallback<SearchMovie> diffCallback = new DiffUtil.ItemCallback<SearchMovie>() {
        @Override
        public boolean areItemsTheSame(SearchMovie oldMovie, SearchMovie newMovie) {
            return oldMovie.id == newMovie.id;
        }

        @Override
        public boolean areContentsTheSame(SearchMovie oldMovie, @NonNull SearchMovie newMovie) {
            return oldMovie.equals(newMovie);
        }
    };

    class UpcomingMovieViewHolder extends RecyclerView.ViewHolder {
        private UpcomingMovieDataBinding upcomingMovieDataBinding;

        UpcomingMovieViewHolder(UpcomingMovieDataBinding upcomingMovieDataBinding) {
            super(upcomingMovieDataBinding.getRoot());
            this.upcomingMovieDataBinding = upcomingMovieDataBinding;
        }

        void bindMovie(SearchMovie searchMovie) {
            upcomingMovieDataBinding.setUpcomingMovie(searchMovie);
            upcomingMovieDataBinding.setOnUpcomingMovieClickListener(onUpcomingMovieClickListener);
        }
    }

    public interface OnUpcomingMovieClickListener {
        void onUpcomingMovieViewClick(SearchMovie searchMovie);
    }
}