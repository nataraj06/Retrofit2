package android.com.retrofit2.rest;

import android.com.retrofit2.model.Movie;

import java.util.List;

public interface RestApiListener {

    void onSuccess(List<Movie> movies);

    void onFailure(String error);
}
