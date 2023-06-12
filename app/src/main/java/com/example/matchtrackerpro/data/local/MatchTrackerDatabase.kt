package com.example.matchtrackerpro.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.matchtrackerpro.data.datamodels.LeagueData
import com.example.matchtrackerpro.data.datamodels.TeamData

//Abstrakte Klasse, die als Zugriffspunkt zur Datenbank dient.
@Database(entities = [LeagueData::class, TeamData::class], version = 1)
abstract class MatchTrackerDatabase : RoomDatabase() {

    //Ermöglicht den Zugriff auf das Dao-Objekt, dass zur Kommunikation mit der Datenbank verwendet wird.
    abstract val matchTrackerDao: MatchTrackerDao

    //Companion-Objekt-Funktion, die verwendet wird um eine Instanz der 'MatchTrackerDatabase'-Klasse zurückzugeben
    companion object {

        // Deklarieren der Instanz vom Typ MatchTrackerDatabase, damit diese gespeichert werden kann.
        private lateinit var dbInstance: MatchTrackerDatabase

        /*
        Die Funktion getDatabase akzeptiert einen Context-Parameter und gibt eine Instanz der MatchTrackerDatabase zurück.
        Die synchronized-Anweisung stellt sicher, dass der Zugriff auf den synchronisierten Block nur von einem Thread gleichzeitig erfolgen kann.
        In diesem Fall wird der synchronisierte Block verwendet, um sicherzustellen, dass die Initialisierung der Datenbankinstanz atomar erfolgt
        und keine inkonsistenten Zustände entstehen.
        Innerhalb des synchronisierten Blocks wird überprüft, ob die Eigenschaft dbInstance bereits initialisiert wurde (!::dbInstance.isInitialized).
        Wenn dies nicht der Fall ist, wird Room.databaseBuilder aufgerufen, um eine Instanz der MatchTrackerDatabase zu erstellen.
        Es wird der Anwendungskontext, der Klassentyp und der Name der Datenbank angegeben. Die Methode allowMainThreadQueries()
        ermöglicht das Ausführen von Datenbankabfragen auf dem Hauptthread, was normalerweise nicht empfohlen wird.
        Schließlich wird build() aufgerufen, um die Datenbankinstanz zu erstellen und der dbInstance-Eigenschaft zuzuweisen.
        Nachdem die Datenbankinstanz initialisiert wurde (oder bereits initialisiert war), wird sie zurückgegeben.
        Dadurch wird sichergestellt, dass bei jedem Aufruf derselben Funktion dieselbe Instanz der Datenbank zurückgegeben wird,
        anstatt jedes Mal eine neue Instanz zu erstellen.*/
        fun getDatabase(context: Context): MatchTrackerDatabase {
            synchronized(MatchTrackerDatabase::class.java) {
                if (!::dbInstance.isInitialized) {
                    dbInstance = Room.databaseBuilder(
                        context.applicationContext,
                        MatchTrackerDatabase::class.java,
                        "matchTracker_database",
                    ).allowMainThreadQueries().build()
                }
            }
            return dbInstance
        }
    }
}