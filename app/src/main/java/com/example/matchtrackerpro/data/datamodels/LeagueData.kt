package com.example.matchtrackerpro.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "matchTracker_database")
data class LeagueData (
    @PrimaryKey
    val leagueId: Int,
    val leagueName: String,
    val leagueImg: String
    )