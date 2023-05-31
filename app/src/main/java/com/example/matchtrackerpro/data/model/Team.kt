package com.example.matchtrackerpro.data.model

data class Team (
    val id: Long,
    val img: Int,
    val name: String,
    val games: Int,
    val goalsShoot: Int,
    val goalsConceded: Int,
    val points: Int
        )