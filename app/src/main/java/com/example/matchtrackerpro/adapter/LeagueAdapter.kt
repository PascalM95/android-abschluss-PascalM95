package com.example.matchtrackerpro.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.matchtrackerpro.R
import com.example.matchtrackerpro.data.datamodels.League
import com.google.android.material.card.MaterialCardView

class LeagueAdapter(
    private val dataset: List<League>,
) : RecyclerView.Adapter<LeagueAdapter.ItemViewHolder>() {

    //Hier wird der ViewHolder für die Elemente aus der RecyclerView defifniert
    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imgView: ImageView = view.findViewById(R.id.iv_league)
        val cvLeague: MaterialCardView = view.findViewById(R.id.cv_league)
    }

    //Hier wird der Adapter festgelegt und der ItemViewHolder m.H. des Adapters erstellt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val leagueAdapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.league_item, parent, false)

        return ItemViewHolder(leagueAdapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        //Hier wird die aktuelle Liga aus dem dataset geholt
        val league = dataset[position]

        //Hier wird die imgUri in ein Uri-Objekt konvertiert und das Schema der Uri wird zu https geändert
        val imgUri = league.leagueImg.toUri().buildUpon().scheme("https").build()

        //Hier werden die Bilder in die ImageView geladen und die Ecken abgerundet
        holder.imgView.load(imgUri) {
            transformations(RoundedCornersTransformation(10f))
        }

        // Es wird ein setOnClickListener auf die CardView gesetzt, in welchem die Navigation zum nächsten Fragment stattfindet.
        // Mit der Navigation wird zusätzlich eine leagueId übergeben.
        holder.cvLeague.setOnClickListener {
            val leagueId = Bundle().apply { putInt("leagueId", league.leagueId) }

            val navController = holder.view.findNavController()
            navController.navigate(R.id.action_homeFragment_to_teamOrTableFragment, leagueId)
        }
    }

    //gibt die Anzahl der Elemente im Dataset zurück
    override fun getItemCount(): Int {
        return dataset.size
    }
}