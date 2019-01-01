package android.com.retrofit2.rest;

import android.com.retrofit2.model.Movie;
import android.com.retrofit2.model.MovieResponse;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestService extends RestApiClient {

    private static RestService instance;
    private static final String API_KEY = "PLACE_YOUR_API_KEY";
    //Visit http://api.themoviedb.org for generating api key

    public static RestService getInstance() {
        if (instance == null) {
            instance = new RestService();
        }
        return instance;
    }

    public void getMoviesList(final RestApiListener listener) {
        RestApiInterface apiService = RestApiClient.getApiClient().create(RestApiInterface.class);
        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                listener.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                listener.onFailure(t.getMessage().toString());
            }
        });
    }
}
