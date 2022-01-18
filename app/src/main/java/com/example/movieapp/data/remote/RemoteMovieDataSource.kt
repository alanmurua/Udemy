package com.example.movieapp.data.remote

import com.example.movieapp.application.AppConstants.API_KEY
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.domain.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteMovieDataSource(private val webService: WebService) {


    suspend fun getUpcomingMovies(): MovieList = withContext(Dispatchers.IO) {
        webService.getUpComingMovies(API_KEY)
    }


    suspend fun getTopRatedMovies(): MovieList = withContext(Dispatchers.IO) {
        webService.getTopRatedMovies(API_KEY)
    }

    suspend fun getPopularMovies(): MovieList = withContext(Dispatchers.IO) {
        webService.getPopularMovies(API_KEY)
    }
}