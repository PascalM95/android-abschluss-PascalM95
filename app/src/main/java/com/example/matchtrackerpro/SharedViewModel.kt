package com.example.matchtrackerpro

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.matchtrackerpro.data.Repository
import com.example.matchtrackerpro.data.datamodels.LeagueData
import com.example.matchtrackerpro.data.datamodels.TeamData
import com.example.matchtrackerpro.data.local.MatchTrackerDatabase.Companion.getDatabase
import com.example.matchtrackerpro.data.remote.MatchTrackerApi
import kotlinx.coroutines.launch

class SharedViewModel (application: Application): AndroidViewModel(application) {

    // Tag zum identifizieren der Log-Nachricht
    private val TAG = "SharedViewModel"

    //Instanz der Database wird erstellt und der Parameter application wird übergeben
    private val database = getDatabase(application)

    //Instanz des Repository wird erstellt und die Parameter MatchTrackerApi und database werden übergeben
    private val repository = Repository(MatchTrackerApi, database)

    //Die Variable leagues vom Typ MutableLiveData, die eine Liste von Ligen enthält, wird aus dem Repository geladen
    var leagues = repository.leagues

    //Instanz einer MuatableLiveData, die ein LeagueData-Objekt enthält
    var currentLeague = MutableLiveData<LeagueData>()

    //Funktion, die mit hilfe einer Coroutine eine Funktion aus dem Repository aufruft, um alle Ligen zu laden.
    //Nach dem Laden erscheint eine Log-Nachricht im LogCat
    fun loadLeagues () {
        viewModelScope.launch {
            repository.getLeagues()
            Log.d(TAG, "Leagues been loaded.")
        }
    }

    //Funktion, die mit Hilfe einer Coroutine eine Funktion aus dem Repository aufruft, um eine Liga aus der Datenbank abzurufen.
    fun getLeagueById (leagueId: Int) {
        viewModelScope.launch {
            currentLeague.value = repository.getLeagueById(leagueId)
        }
    }

    //Funktion die, anhand des übergebenen Parameters leagueId, alle zugehörigen Teams aus der Datenbank abruft.
    fun getTeams(leagueId: Int): List<TeamData> {

        return repository.getTeams(leagueId)
    }

    //Funktion, die anhand des übergebenen Parameters teamId, das zugehörige Team aus der Datenbank abruft.
    fun getTeam(teamId: Int): TeamData {

        return repository.getTeam(teamId)
    }
}