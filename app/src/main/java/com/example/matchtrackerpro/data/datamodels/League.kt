package com.example.matchtrackerpro.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey



data class League (
    val leagueId: Int,
    val leagueName: String,
    val leagueImg: String,
    val teams: List<Team>
        )