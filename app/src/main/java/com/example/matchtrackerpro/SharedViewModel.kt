package com.example.matchtrackerpro

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matchtrackerpro.data.Repository
import com.example.matchtrackerpro.data.local.getDatabase
import com.example.matchtrackerpro.data.remote.MatchTrackerApi
import kotlinx.coroutines.launch

class SharedViewModel (application: Application): AndroidViewModel(application) {

    private val TAG = "SharedViewModel"

    private val database = getDatabase(application)

    private val repository = Repository(MatchTrackerApi, database)

    val leagues = repository.leagues

    fun loadLeagues () {
        viewModelScope.launch {
            repository.getLeagues()
            Log.i(TAG, "Leagues been loaded.")
        }
    }
}