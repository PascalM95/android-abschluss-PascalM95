package com.example.matchtrackerpro

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matchtrackerpro.data.Repository
import com.example.matchtrackerpro.data.datamodels.League
import com.example.matchtrackerpro.data.datamodels.Team
import com.example.matchtrackerpro.data.local.MatchTrackerDatabase.Companion.getDatabase
import com.example.matchtrackerpro.data.remote.MatchTrackerApi
import kotlinx.coroutines.launch

class SharedViewModel (application: Application): AndroidViewModel(application) {

    private val TAG = "SharedViewModel"

    private val database = getDatabase(application)

    private val repository = Repository(MatchTrackerApi, database)

    var leagues = repository.leagues

    var currentLeague = MutableLiveData<League>()

    fun loadLeagues () {
        viewModelScope.launch {
            repository.getLeagues()
            Log.i(TAG, "Leagues been loaded.")
        }
    }

    fun getLeagueById (leagueId: Int) {
        viewModelScope.launch {
            currentLeague.value = repository.getLeagueById(leagueId)
        }
    }
}