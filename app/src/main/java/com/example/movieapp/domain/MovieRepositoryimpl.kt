package com.example.movieapp.domain

import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.remote.MovieDataSource

class MovieRepositoryimpl(private val dataSource: MovieDataSource) : MovieRepository {

    override suspend fun getUpComingMovies(): MovieList = dataSource.getUpcomingMovies()


    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRatedMovies()


    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()
}