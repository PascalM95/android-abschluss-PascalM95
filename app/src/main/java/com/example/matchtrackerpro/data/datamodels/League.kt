package com.example.matchtrackerpro.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "matchTracker_database")
data class League (
    @PrimaryKey
    val leagueId: Int,
    val leagueName: String,
    val leagueImg: String,
    val teams: List<Team>
        )