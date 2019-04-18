package com.udacity.popular_movies_stage_02;

import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.udacity.popular_movies_stage_02.db.MovieDb;
import com.udacity.popular_movies_stage_02.db.MoviePopularDao;
import com.udacity.popular_movies_stage_02.repository.MoviePopularRepository;
import com.udacity.popular_movies_stage_02.repository.MovieRatedRepository;
import com.udacity.popular_movies_stage_02.api.MovieService;
import com.udacity.popular_movies_stage_02.ui.MovieAdapter;
import com.udacity.popular_movies_stage_02.ui.PaginationScrollListener;
import com.udacity.popular_movies_stage_02.viewmodel.MainViewModel;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    public final static String LOAD_MOVIES_BY_RATING = "load_movies_by_rating";

    public final static String LOAD_MOVIES_BY_POPULARITY = "load_movies_by_popularity";

    public final static String LOAD_FAVORITES = "load_favorites";

    @Inject
    MovieService movieService;
    @Inject
    MovieRatedRepository movieRatedRepository;
    @Inject
    MoviePopularRepository moviePopularRepository;
    @Inject
    MoviePopularDao moviePopularDao;
    @Inject
    MainViewModel viewModel;
    @Inject
    PaginationScrollListener paginationScrollListener;

    @SuppressWarnings("WeakerAccess")
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivitySubcomponent().inject(this);

        recyclerView = (RecyclerView) findViewById(R.id.movie_recycler_view);
        recyclerView.setHasFixedSize(true);

        StaggeredGridLayoutManager layoutManager
                = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        layoutManager
                .setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        recyclerView.setLayoutManager(layoutManager);

        MovieAdapter adapter = new MovieAdapter(this, viewModel);

        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(paginationScrollListener);

    }

    private void launchDetailActivity(MovieDb movieDb) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE_ID, movieDb.getId());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildLayoutPosition(v);
        MovieAdapter adapter = (MovieAdapter) recyclerView.getAdapter();

        MovieDb movieDb = null;

        if (adapter != null) {
            movieDb = adapter.getMovieAtPosition(position);
        }

        if (movieDb != null) {
            launchDetailActivity(movieDb);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MutableLiveData<String> loadIdentifier = viewModel.getLoadIdentifierData();

        switch (item.getItemId()) {
            case R.id.load_movies_by_popularity:
                setTitle(getString(R.string.sorted_by) + " "
                        + getString(R.string.load_movies_by_popularity));
                loadIdentifier.setValue(LOAD_MOVIES_BY_POPULARITY);
                break;
            case R.id.load_movies_by_rating:
                setTitle(getString(R.string.sorted_by) + " "
                        + getString(R.string.load_movies_by_rating));
                loadIdentifier.setValue(LOAD_MOVIES_BY_RATING);
                break;
            case R.id.load_favorites:
                setTitle(R.string.load_favorites);
                loadIdentifier.setValue(LOAD_FAVORITES);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}