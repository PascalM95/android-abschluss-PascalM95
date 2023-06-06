package com.example.matchtrackerpro.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.matchtrackerpro.data.datamodels.League
import com.example.matchtrackerpro.data.local.MatchTrackerDatabase
import com.example.matchtrackerpro.data.remote.MatchTrackerApi
import java.lang.Exception

const val TAG = "Repository"

class Repository (private val api: MatchTrackerApi, private val database: MatchTrackerDatabase) {

    var leagues = MutableLiveData<List<League>>()



    suspend fun getLeagues() {

        try {
            //leagues.value = api.retrofitService.getLeagues()
        } catch (e: Exception) {
            Log.e(TAG, "Leagues couldn't be loaded. $e")
        }
    }
}