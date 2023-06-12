package com.example.matchtrackerpro.adapter

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
import com.example.matchtrackerpro.data.datamodels.TeamData
import com.example.matchtrackerpro.ui.TeamFragmentDirections
import com.google.android.material.card.MaterialCardView

class TeamAdapter(
    private val dataset: List<TeamData>,
) : RecyclerView.Adapter<TeamAdapter.ItemViewHolder>() {

    //Hier wird der ViewHolder für die Elemente aus der RecyclerView defifniert
    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imgTeam: ImageView = view.findViewById(R.id.iv_team)
        val cvTeam: MaterialCardView = view.findViewById(R.id.cv_team)
    }

    //Hier wird der Adapter festgelegt und der ItemViewHolder m.H. des Adapters erstellt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val teamAdapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.team_item, parent, false)

        return ItemViewHolder(teamAdapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        //Hier wird das aktuelle Team aus dem dataset geholt.
        val team = dataset[position]

        //Hier wird die imgUri in ein Uri-Objekt konvertiert und das Schema der Uri wird zu https geändert
        val imgUri = team.img.toUri().buildUpon().scheme("https").build()

        //Hier werden die Bilder in die ImageView geladen und die Ecken abgerundet
        holder.imgTeam.load(imgUri) {
            transformations(RoundedCornersTransformation(10f))
        }

        // Es wird ein setOnClickListener auf die CardView gesetzt, in welchem die Navigation zum nächsten Fragment stattfindet.
        // Mit der Navigation wird zusätzlich eine teamId übergeben.
        holder.cvTeam.setOnClickListener {
            val navController = holder.view.findNavController()
            navController.navigate(
                TeamFragmentDirections.actionTeamFragmentToDetailTeamFragment(
                    team.teamId
                )
            )
        }
    }

    //gibt die Anzahl der Elemente im Dataset zurück
    override fun getItemCount(): Int {
        return dataset.size
    }
}