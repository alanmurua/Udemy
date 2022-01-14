package com.example.movieapp.domain

import com.example.movieapp.data.model.MovieList


interface MovieRepository {

    suspend fun getUpComingMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
}