package android.com.retrofit2.service;

import android.com.retrofit2.listener.OnUIChangeListener;
import android.com.retrofit2.model.Movie;
import android.com.retrofit2.rest.RestApiListener;
import android.com.retrofit2.rest.RestService;

import java.util.List;

public class MoviesListServiceImpl implements MoviesListService, RestApiListener {

    private OnUIChangeListener listener;

    public MoviesListServiceImpl(OnUIChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void fetchMovies() {
        listener.displayProgressBar();
        RestService.getInstance().getMoviesList(this);
    }

    @Override
    public void onSuccess(List<Movie> movies) {
        listener.hideProgressBar();
        listener.updateMovieList(movies);
    }

    @Override
    public void onFailure(String error) {
        listener.hideProgressBar();
        listener.showErrorMessage(error);
    }
}
