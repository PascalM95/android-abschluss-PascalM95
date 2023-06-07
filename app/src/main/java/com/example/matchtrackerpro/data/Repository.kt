package com.example.matchtrackerpro.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.matchtrackerpro.data.datamodels.League
import com.example.matchtrackerpro.data.datamodels.Team
import com.example.matchtrackerpro.data.local.MatchTrackerDatabase
import com.example.matchtrackerpro.data.remote.MatchTrackerApi
import kotlin.Exception

const val TAG = "Repository"

class Repository (private val api: MatchTrackerApi, private val database: MatchTrackerDatabase) {

    var leagues = MutableLiveData<List<League>>()

    suspend fun getLeagues() {

        try {
            database.matchTrackerDao.insert(api.retrofitService.getLeagues())
        } catch (e: Exception) {
            Log.e(TAG, "Leagues couldn't be loaded. $e")
        }
    }

    suspend fun getLeagueById(leagueId: Int): League {
        return database.matchTrackerDao.getLeagueById(leagueId)
    }

    suspend fun insert (leagues: List<League>) {

        try {
            database.matchTrackerDao.insert(leagues)
        } catch (e: Exception) {
            Log.d(TAG, "Failed to insert into Database: $e")
        }
    }

    suspend fun update (leagues: List<League>) {

        try {
            database.matchTrackerDao.update(leagues)
        } catch (e: Exception) {
            Log.d(TAG, "Failed to update Database: $e")
        }
    }
}