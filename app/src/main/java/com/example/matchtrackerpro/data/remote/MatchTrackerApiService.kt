package com.example.matchtrackerpro.data.remote

import com.example.matchtrackerpro.data.datamodels.LeagueResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


//Basis-Url für den Zugriff auf eine API um von dieser Daten abrufen zu können.
const val BASE_URL = "https://public.syntax-institut.de/apps/batch6/PascalMoench/"

//Instanz der Klasse Moshi um JSON-Daten in Kotlin-Objekte und zurück konvertieren zu können.
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//Mit dieser Retrofit-Instanz können HTTP-Anfragen an die angegebene Basis-URL gesendet werden,
//und die JSON-Daten werden automatisch mit Hilfe von Moshi in Kotlin-Objekte konvertiert.
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//API-Methode zum abrufen der Liga-Daten, gibt eine Objekt vom Typ LeagueResponse zurück.
interface MatchTrackerApiService {
    @GET("MatchTrackerPro.json")
    suspend fun getLeagues(): LeagueResponse
}

//Dadurch kann auf den MatchTrackerApiServce zugegriffen werden und es wird garantiert, dass
//bei jedem Zugriff dasselbe MatchTrackerApiService-Objekt zurückgegeben wird,
//das von Retrofit generiert wurde.
object MatchTrackerApi {
    val retrofitService: MatchTrackerApiService by lazy { retrofit.create(MatchTrackerApiService::class.java) }
}