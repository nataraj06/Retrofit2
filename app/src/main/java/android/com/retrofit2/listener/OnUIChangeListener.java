package android.com.retrofit2.listener;

import android.com.retrofit2.model.Movie;
import android.com.retrofit2.model.MovieResponse;

import java.util.List;

public interface OnUIChangeListener {
    void displayProgressBar();

    void hideProgressBar();

    void showErrorMessage(String message);

    void updateMovieList(List<Movie> movies);
}
