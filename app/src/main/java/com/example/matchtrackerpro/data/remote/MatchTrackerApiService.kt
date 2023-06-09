package com.example.matchtrackerpro.data.remote

import com.example.matchtrackerpro.data.datamodels.League
import com.example.matchtrackerpro.data.datamodels.LeagueResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://public.syntax-institut.de/apps/batch6/PascalMoench/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MatchTrackerApiService {
    @GET("MatchTrackerPro.json")
    suspend fun getLeagues(): LeagueResponse
}

object MatchTrackerApi {
    val retrofitService: MatchTrackerApiService by lazy { retrofit.create(MatchTrackerApiService::class.java) }
}