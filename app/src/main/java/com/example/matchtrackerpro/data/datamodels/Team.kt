package com.example.matchtrackerpro.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Team (
    @PrimaryKey(autoGenerate = true)
    val teamId: Int,
    val img: String,
    val teamName: String,
    val games: Int,
    val goals: String,
    val points: Int,
    val founding: String,
    val colors: String,
    val stadium: String,
    val seats: String
        )