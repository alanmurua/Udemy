package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.Resource
import com.example.movieapp.domain.MovieRepository
import java.lang.Exception
import kotlinx.coroutines.Dispatchers


class MovieViewModel(private val repo : MovieRepository) : ViewModel() {

    fun fetchMainScreenMovies() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(Triple(repo.getUpComingMovies(),repo.getTopRatedMovies(),repo.getPopularMovies())))

        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

}

class MovieViewModelFactory(private val repo : MovieRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }

}