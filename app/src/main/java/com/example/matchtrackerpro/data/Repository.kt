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
            leagues.value = api.retrofitService.getLeagues()
        } catch (e: Exception) {
            Log.e(TAG, "Leagues couldn't be loaded. $e")
        }
    }

    suspend fun insert (teams: List<Team>) {

        try {
            database.matchTrackerDao.insert(teams)
        } catch (e: Exception) {
            Log.d(TAG, "Failed to insert into Database: $e")
        }
    }

    suspend fun update (teams: List<Team>) {

        try {
            database.matchTrackerDao.update(teams)
        } catch (e: Exception) {
            Log.d(TAG, "Failed to update Database: $e")
        }
    }
}