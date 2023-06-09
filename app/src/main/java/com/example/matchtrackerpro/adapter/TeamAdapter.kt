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

class TeamAdapter (
    private val dataset: List<TeamData>,
    ):RecyclerView.Adapter<TeamAdapter.ItemViewHolder>() {

        class ItemViewHolder ( val view: View): RecyclerView.ViewHolder(view) {
            val imgTeam: ImageView = view.findViewById(R.id.iv_team)
            val cvTeam: MaterialCardView = view.findViewById(R.id.cv_team)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val teamAdapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.team_item, parent, false)

        return ItemViewHolder(teamAdapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val team = dataset[position]

        val imgUri = team.img.toUri().buildUpon().scheme("https").build()

        holder.imgTeam.load(imgUri) {
            transformations(RoundedCornersTransformation(10f))
        }

        holder.cvTeam.setOnClickListener {
            val navController = holder.view.findNavController()
            navController.navigate(TeamFragmentDirections.actionTeamFragmentToDetailTeamFragment(team.teamId))
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}