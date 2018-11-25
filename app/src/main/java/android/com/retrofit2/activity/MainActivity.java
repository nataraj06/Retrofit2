package android.com.retrofit2.activity;

import android.com.retrofit2.R;
import android.com.retrofit2.adapter.MovieListAdapter;
import android.com.retrofit2.listener.OnUIChangeListener;
import android.com.retrofit2.model.Movie;
import android.com.retrofit2.service.MoviesListService;
import android.com.retrofit2.service.MoviesListServiceImpl;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnUIChangeListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MoviesListService moviesListService;
    private MovieListAdapter movieListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        moviesListService = new MoviesListServiceImpl(this);
        moviesListService.fetchMovies();
    }

    @Override
    public void displayProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void updateMovieList(List<Movie> movies) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieListAdapter = new MovieListAdapter(movies, this);
        recyclerView.setAdapter(movieListAdapter);
    }
}
