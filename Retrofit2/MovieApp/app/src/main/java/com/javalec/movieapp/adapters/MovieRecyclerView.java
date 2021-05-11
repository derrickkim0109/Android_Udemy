package com.javalec.movieapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.javalec.movieapp.R;
import com.javalec.movieapp.models.MovieModel;
import com.javalec.movieapp.utils.Credentials;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    // -------------- //
    //   Properties   //
    // -------------- //
    private List<MovieModel> mMovies;
    private OnMovieListener onMovieListener;

    private static final int DISPLAY_POP = 1 ;
    private static final int DISPLAY_SEARCH = 2;



    // Constructor
    public MovieRecyclerView(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == DISPLAY_SEARCH){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,
                    parent, false);
            return new MovieViewHolder(view, onMovieListener);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_layout,
                    parent, false);
            return new Popular_view_holder(view, onMovieListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int p) {

//        ((MovieViewHolder)holder).title.setText(mMovies.get(p).getTitle());
//        ((MovieViewHolder)holder).release_date.setText(mMovies.get(p).getRelease_date());
//        // There is an error in runtime duration.
//        // Let's Fix this error.
//        ((MovieViewHolder)holder).duration.setText(mMovies.get(p).getOriginal_language());

        int itemViewType = getItemViewType(p);
        if (itemViewType == DISPLAY_SEARCH){
        // we have five stars, but vote average is over 10. So our rating bar is over 5 stars: dividing by 2
            ((MovieViewHolder)holder).ratingBar.setRating((mMovies.get(p).getVote_average())/2);

            // ImageView : Using Glide Library
            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/"
                            +mMovies.get(p).getPoster_path())
                    .into(((MovieViewHolder)holder).imageView);
        }else {
            ((Popular_view_holder)holder).ratingBar_pop.setRating((mMovies.get(p).getVote_average())/2);

            // ImageView : Using Glide Library
            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/"
                            +mMovies.get(p).getPoster_path())
                    .into(((Popular_view_holder)holder).imageView_pop);
        }

        // We need to get the runtime & the category.
        // We need to change the api response.

    }

    @Override
    public int getItemCount() {
        if (mMovies != null ){
            return mMovies.size(); // For ImageView
        }
        return 0;
    }

    public void setmMovies(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }

    // Getting the id of the movie clicked
    public MovieModel getSelectedMovie(int position){
        if (mMovies != null){
            if (mMovies.size() > 0){
                return mMovies.get(position);
            }
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (Credentials.POPULAR){
            return DISPLAY_POP;
        }
        else {
            return DISPLAY_SEARCH;
        }
    }
}
