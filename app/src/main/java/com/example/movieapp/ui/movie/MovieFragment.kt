package com.example.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.movieapp.R
import com.example.movieapp.core.Resource
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.remote.MovieDataSource
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.domain.MovieRepositoryimpl
import com.example.movieapp.domain.RetrofitClient
import com.example.movieapp.presentation.MovieViewModel
import com.example.movieapp.presentation.MovieViewModelFactory
import com.example.movieapp.ui.movie.adapters.MovieAdapter
import com.example.movieapp.ui.movie.adapters.concat.PopularConcatAdapter
import com.example.movieapp.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.example.movieapp.ui.movie.adapters.concat.UpcomingConcatAdapter


class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.title,
            movie.poster_path,
            movie.release_date,
            movie.original_language,
            movie.overview,
            movie.vote_count
        )
        findNavController().navigate(action)
    }

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryimpl(
                MovieDataSource(RetrofitClient.webService)
            )
        )
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
                when (result) {

                    is Resource.Loading -> {
                        binding.rlProgressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.rlProgressBar.visibility = View.GONE
                        concatAdapter.apply {
                            addAdapter(
                                0,
                                UpcomingConcatAdapter(
                                    MovieAdapter(
                                        result.data.first.results,
                                        this@MovieFragment
                                    )
                                )
                            )
                            addAdapter(
                                1,
                                TopRatedConcatAdapter(
                                    MovieAdapter(
                                        result.data.second.results,
                                        this@MovieFragment
                                    )
                                )
                            )
                            addAdapter(
                                2,
                                PopularConcatAdapter(
                                    MovieAdapter(
                                        result.data.third.results,
                                        this@MovieFragment
                                    )
                                )
                            )
                        }
                        binding.rvMovies.adapter = concatAdapter
                    }
                    is Resource.Failure -> {
                        binding.rlProgressBar.visibility = View.GONE
                        Log.d("ErrorUpcoming", "${result.exception}")
                    }
                }
            })
    }
}