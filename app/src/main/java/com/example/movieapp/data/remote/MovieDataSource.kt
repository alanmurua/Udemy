package com.example.movieapp.data.remote

import com.example.movieapp.application.AppConstants.API_KEY
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.domain.WebService

class MovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList = webService.getUpComingMovies(API_KEY)

    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(API_KEY)
}