package com.example.movieapp.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding : FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentMovieDetailBinding.bind(view)

        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500${args.posterimageUrl}").into(binding.ivMovie)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500${args.backgroundimageUrl}").into(binding.ivBackground)
        binding.tvtitle.text = args.movietitle
        binding.tvDescripcion.text = args.overview
        binding.tvCalendar.text = args.releasedate
        binding.tvLanguage.text = "language ${args.language}"
        binding.tvRating.text = "${args.voteaverage} (${args.votecount}) Reviews"

    }
}