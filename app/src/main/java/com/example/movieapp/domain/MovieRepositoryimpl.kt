package com.example.movieapp.domain

import com.example.movieapp.core.InternetCheck
import com.example.movieapp.data.local.LocalMovieDataSource
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieEntity
import com.example.movieapp.data.remote.RemoteMovieDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryimpl(
    private val remoteDataSource: RemoteMovieDataSource,
    private val localDataSource: LocalMovieDataSource
) : MovieRepository {

    override suspend fun getUpComingMovies(): MovieList {
        withContext(Dispatchers.IO){
            if (InternetCheck.isNetworkAvailable()) {
                remoteDataSource.getUpcomingMovies().results.forEach { movie ->

                    localDataSource.saveMovie(movie.toMovieEntity("upcoming"))
                }
                localDataSource.getUpcomingMovies()
            } else {
                localDataSource.getUpcomingMovies()
            }
        }
        return localDataSource.getUpcomingMovies()
    }


    override suspend fun getTopRatedMovies(): MovieList {
        withContext(Dispatchers.IO){
            if (InternetCheck.isNetworkAvailable()) {
                remoteDataSource.getTopRatedMovies().results.forEach { movie ->

                    localDataSource.saveMovie(movie.toMovieEntity("toprated"))
                }
                localDataSource.getTopRatedMovies()
            } else {
                localDataSource.getTopRatedMovies()
            }
        }
        return localDataSource.getTopRatedMovies()
    }


    override suspend fun getPopularMovies(): MovieList {
        withContext(Dispatchers.IO){
            if (InternetCheck.isNetworkAvailable()) {
                remoteDataSource.getPopularMovies().results.forEach { movie ->

                    localDataSource.saveMovie(movie.toMovieEntity("popular"))
                }
                localDataSource.getPopularMovies()
            } else {
                localDataSource.getPopularMovies()
            }
        }
        return localDataSource.getPopularMovies()
    }

}
